package com.luxoftmarket.service;

import com.luxoftmarket.domain.Good;

import java.util.List;

/*
¬ уровен —ервиса должна находитс€ бизнес логика, но, к сожалению, у мен€ нет времени, что-бы сделать ее тут.
In Service level must be business logic, but, unfortunately, I have not any time make here her
 */
public interface IGoodService {
    void add(Good good);
    void edit(Good good);
    void delete(int id);
    Good getGood(int id);
    List getAllGood();
    Good findGoodByName(String goodname);
}
