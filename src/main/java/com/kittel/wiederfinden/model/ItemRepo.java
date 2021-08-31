package com.kittel.wiederfinden.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ItemRepo extends JpaRepository<Item, Long> {

    @Query("select i from Item i where i.name like %?1%")
    List<Item> getByName(String name);
}
