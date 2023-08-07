package com.innovationcamp.messenger.domain.wallet.entity;

import com.innovationcamp.messenger.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class GroupWalletUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserAuthorityEnum userAuthority;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "group_wallet_id")
    private GroupWallet groupWallet;

    public GroupWalletUser(UserAuthorityEnum userAuthority, User user, GroupWallet groupWallet) {
        this.userAuthority = userAuthority;
        this.user = user;
        this.groupWallet = groupWallet;
    }
}
