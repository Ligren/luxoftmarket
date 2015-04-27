package com.luxoftmarket.domain;
// I`m using Many to many mapping since Many users can have many roles and many roles can be assigned to many users.
import javax.persistence.*;
import java.util.List;
import com.luxoftmarket.domain.Role;

@Entity
@Table(name = "users")
public class User {

        @Id
        @Column(name  = "id_user")
        @GeneratedValue
        private Integer id;

        @Column(name = "user_name")
        private String nick;

        @Column(name = "user_password")
        private String password;

        @Column(name = "user_email")
        private String email;

//        @Column(name = "user_roles")
        @ManyToMany
        @JoinTable(name="UsersAndRoles",
                joinColumns = @JoinColumn(name="id_user"),
                inverseJoinColumns = @JoinColumn(name = "id_role"))
        private List<Role> roles;

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

}
