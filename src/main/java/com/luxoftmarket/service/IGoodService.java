package com.luxoftmarket.service;

import com.luxoftmarket.domain.Good;

import java.util.List;

public interface IGoodService {
    void add(Good good);
    void edit(Good good);
    void delete(int id);
    Good getGood(int id);
    List getAllGood();
}
