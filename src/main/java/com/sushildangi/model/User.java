package com.sushildangi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private String id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(name = "username", nullable = false, unique = true, updatable = false)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    @NaturalId
    private String email;

    @Column(nullable = false, name = "password")
    private String password;

    @Column(nullable = false, name = "account_non_expired")
    private boolean accountNonExpired = true;

    @Column(nullable = false, name = "account_non_locked")
    private boolean accountNonLocked = true;

    @Column(nullable = false, name = "credentials_non_expired")
    private boolean credentialsNonExpired = true;

    @Column(nullable = false, name = "enabled")
    private boolean enabled = true;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private Instant createdDate;

    @Column(name = "last_modified_date")
    @LastModifiedDate
    private Instant lastModifiedDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
}
