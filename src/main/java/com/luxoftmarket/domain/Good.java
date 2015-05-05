package com.luxoftmarket.domain;

import org.hibernate.dialect.H2Dialect;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "GOODS")
public class Good implements Serializable {

    @Id
    @Column(name  = "ID_GOOD", nullable = false, length = 8)
    @GeneratedValue
    private Integer id;

    @NotNull
    @Size(min=1,max=150)
    @Column(name = "GOOD_NAME", nullable = false, length = 150)
    private String name;

    @Column(name = "GOOD_PRICE", nullable = false, length = 8)
    private Integer price;

    @Column(name = "GOOD_AMOUNT", nullable = true, length = 8)
    private Integer amount;

    public Good() {}

    public Good(Integer id, String name, Integer price, Integer amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public Integer getId() { return id; }

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