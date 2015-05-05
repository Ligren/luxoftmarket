package com.luxoftmarket.domain;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "ROLES")
public class Role implements Serializable {
    @Id
    @Column(name  = "ID_ROLE", nullable = false, length = 10)
    @GeneratedValue
    private Integer id;

    @NotNull
    @Column(name = "ROLE_NAME", nullable = false, length = 50)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Role() {}

    public Role(String name, List users) {
        this.name = name;
        this.users = users;
    }

    public Role(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
