package com.luxoftmarket.domain;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "goods")
public class Good implements Serializable {

    @Id
    @NotNull
    @Column(name  = "id_good", nullable = false, length = 8)
    @GeneratedValue
    private Integer id;

    @NotNull
    @Column(name = "good_name", nullable = false, length = 150)
    private String name;

    @Column(name = "good_price", nullable = false, length = 8)
    private Integer price;

    @Column(name = "good_amount", nullable = true, length = 8)
    private Integer amount;

    public Good() {}

    public Good(Integer id, String name, Integer price, Integer amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}