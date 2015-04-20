package com.luxoftmarket.repository;

import com.luxoftmarket.domain.Good;

import java.util.List;

public interface IGoodRepository {
    void addGood(Good good);

    List<Good> listAll();

    void removeGood(Integer id);
}
