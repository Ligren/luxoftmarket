package com.luxoftmarket.dao;

import com.luxoftmarket.domain.Good;

import java.util.List;

public interface IGoodDao {
    public void add(Good good);
    public void edit(Good good);
    public void delete(int id);
    public Good getGood(int id);
    public List getAllGood();
}
