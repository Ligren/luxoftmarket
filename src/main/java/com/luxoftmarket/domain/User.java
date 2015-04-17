package com.luxoftmarket.domain;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

        @Id
        @Column(name  = "id")
        @GeneratedValue
        private Integer id;

        @Column(name = "nick")
        private String nick;

        @Column(name = "password")
        private String password;

        @Column(name = "dateBirthday")
        private String dateBirthday;

        public Integer getId() { return id; }

        public void setId(Integer id) { this.id = id; }

        public String getNick() { return nick; }

        public void setNick(String nick) { this.nick = nick; }

        public String getPassword() { return password; }

        public void setPassword(String password) { this.password = password; }

        public String getDatebirthday() { return dateBirthday; }

        public void setDatebirthday(String datebirthday) { this.dateBirthday = dateBirthday; }

}
