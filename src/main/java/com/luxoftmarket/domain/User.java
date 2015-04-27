package com.luxoftmarket.domain;

import javax.persistence.*;

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

        public User() {}

        public User(String nick, String password, String email) {
                this.nick = nick;
                this.password = password;
                this.email = email;
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
