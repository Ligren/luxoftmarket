package com.luxoftmarket.domain;

import javax.persistence.*;

// class model
@Entity
@Table(name = "goods")
public class Good {

    @Id
    @Column(name  = "id_good")
    @GeneratedValue
    private Integer id;

    @Column(name = "good_name")
    private String name;

    @Column(name = "good_price")
    private Integer price;

    @Column(name = "good_amount")
    private Integer amount;

    public Good() {}

    public Good(String name, Integer price, Integer amount) {
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