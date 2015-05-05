package com.luxoftmarket.service;

import com.luxoftmarket.domain.Good;

import java.util.List;

/*
*In the Service level there must be business logic but, unfortunately, I have not any time to make it there.
* */
public interface IGoodService {
    void add(Good good);
    void edit(Good good);
    void delete(int id);
    Good getGood(int id);
    List getAllGood();
    Good findGoodByName(String goodname);
}
