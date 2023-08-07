package com.innovationcamp.messenger.domain.channel.service;

import com.innovationcamp.messenger.domain.channel.dto.ChannelContentResponseDto;
import com.innovationcamp.messenger.domain.channel.dto.CreateChannelRequestDto;
import com.innovationcamp.messenger.domain.channel.dto.UpdateChannelRequestDto;
import com.innovationcamp.messenger.domain.channel.dto.UserChannelResponseDto;
import com.innovationcamp.messenger.domain.channel.entity.Channel;
import com.innovationcamp.messenger.domain.channel.entity.ChannelContent;
import com.innovationcamp.messenger.domain.channel.entity.UserChannel;
import com.innovationcamp.messenger.domain.channel.repository.ChannelContentRepository;
import com.innovationcamp.messenger.domain.channel.repository.ChannelRepository;
import com.innovationcamp.messenger.domain.channel.repository.UserChannelRepository;
import com.innovationcamp.messenger.domain.user.entity.User;
import com.innovationcamp.messenger.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChannelServiceImpl implements ChannelService {
    @NonNull
    private final ChannelRepository channelRepository;
    @NonNull
    private final UserRepository userRepository;
    @NonNull
    private final UserChannelRepository userChannelRepository;
    @NonNull
    private final ChannelContentRepository channelContentRepository;

    @Override
    public Channel createChannel(CreateChannelRequestDto createChannelRequestDto) {
        // Create new Channel entity based on the data in the NewChannelRequestDto
        Channel channel = Channel.builder()
                .channelName(createChannelRequestDto.getChannelName())
                .channelDescription(createChannelRequestDto.getChannelDescription())
                .build();

        // Save new Channel entity in the database
        return channelRepository.save(channel);
    }
    @Override
    public Channel getChannel(Long id) {
        return channelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Channel not found"));
    }
    @Override
    public List<UserChannelResponseDto> getChannelsUserJoined(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        List<UserChannel> userChannels = userChannelRepository.findByUser(user);

        List<UserChannelResponseDto> dtoList = userChannels.stream()
                .map(userChannel -> {
                    Channel channel = userChannel.getChannel();
                    return new UserChannelResponseDto(
                            channel.getId(), userChannel.getReadTimestamp(), userChannel.isAdmin()
                    );
                })
                .collect(Collectors.toList());

        return dtoList;
    }

    @Transactional
    @Override
    public Channel updateChannel(Long id, UpdateChannelRequestDto updateChannelRequestDto) {
        Channel channel = getChannel(id);
        return channelRepository.save(channel);
    }

    @Transactional
    @Override
    public void deleteChannel(Long id) {
        channelRepository.deleteById(id);
    }

    @Override
    public List<ChannelContentResponseDto> getChannelContents(Long channelId) {
        Channel channel = getChannel(channelId);

        List<ChannelContent> channelContents = channelContentRepository.findByChannel(channel);

        List<ChannelContentResponseDto> dtoList = channelContents.stream()
                .map(channelContent -> {
                    User user = channelContent.getUser();
                    return new ChannelContentResponseDto(
                            channelContent.getId(),
                            user.getId(),
                            channelContent.getChannel().getId(),
                            channelContent.getCalloutContent().getId(),
                            channelContent.getCreatedAt(),
                            channelContent.getNotReadCount(),
                            channelContent.getContentType());
                })
                .collect(Collectors.toList());

        return dtoList;
    }

    @Transactional
    @Override
    public void addUserToChannel(Long channelId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Channel channel = getChannel(channelId);

        UserChannel userChannel = UserChannel.builder()
                .user(user)
                .channel(channel)
                .build();

        userChannelRepository.save(userChannel);
    }

    @Transactional
    @Override
    public void kickUserFromChannel(Long channelId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Channel channel = getChannel(channelId);

        userChannelRepository.deleteByUserAndChannel(user, channel);
    }


}
