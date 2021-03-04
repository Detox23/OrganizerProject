package com.example.src.entities;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "public", name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails {

    @Id
    @Column(name="id", nullable = false, updatable = false, unique = true)
    private UUID id = UUID.randomUUID();

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="surname", nullable = false)
    private String surname;

    @Column(name="email", unique = true, updatable = false, nullable = false)
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    @Builder.Default
    @Column(name="role")
    private UserRole role = UserRole.USER;

    @Builder.Default
    @Column(name="locked")
    private Boolean locked = false;

    @Builder.Default
    @Column(name="enabled")
    private Boolean enabled = false;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="confirmation_tokens_id", referencedColumnName = "id")
    private ConfirmationToken confirmationToken;

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime created;

    @LastModifiedDate
    @Column(name = "last_modified_date", nullable = false)
    private LocalDateTime modified;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
