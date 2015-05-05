package com.luxoftmarket.domain;
// I`m using Many to many mapping since Many users can have many roles and many roles can be assigned to many users.

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

@Entity
@Table(name = "USERS")
public class User implements Serializable {

        @Id
        @Column(name  = "ID_USER", nullable = false, length = 5)
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Integer id;

        @NotNull
        @Size(min = 1 , max = 50)
        @Column(name = "USER_NAME", nullable = true, length = 50, unique = true)
        private String nick;

        @NotNull
        @Column(name = "USER_PASSWORD", nullable = true, length = 200)
        private String password;

        @Column(name = "USER_EMAIL", nullable = true, length = 50)
        private String email;

        @ManyToMany(cascade = CascadeType.ALL)
        @JoinTable(name="USERSANDROLES",
                joinColumns = @JoinColumn(name="ID_USER"),
                inverseJoinColumns = @JoinColumn(name = "ID_ROLE"))
        private List<Role> roles;

        @NotNull
        @Column(name = "STATUS", nullable = false, length = 15)
        @Enumerated(EnumType.STRING)
        private UserStatus status;

        public User() {}

        public User(String nick, String password, String email, List<Role> roles, UserStatus status) {
                this.nick = nick;
                this.password = password;
                this.email = email;
                this.roles = roles;
                this.status = status;
        }

        public Integer getId() { return id; }

        public void setId(Integer id) { this.id = id; }

        public String getNick() { return nick; }

        public void setNick(String nick) { this.nick = nick; }

        public String getPassword() { return password; }

        public void setPassword(String password) { this.password = password; }

        public String getEmail() { return email; }

        public void setEmail(String email) { this.email = email; }

        public UserStatus getStatus() { return status; }

        public void setStatus(UserStatus status) { this.status = status; }

        public List<Role> getRoles() { return roles; }

        public void setRoles(List<Role> roles) { this.roles = roles; }

}
