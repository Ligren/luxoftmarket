package com.luxoftmarket.dao;

import com.luxoftmarket.domain.Good;

import java.util.List;

public interface IGoodDao {
    void add(Good good);
    void edit(Good good);
    void delete(int id);
    Good getGood(int id);
    List getAllGood();
    Good findGoodByName(String goodname);
}
