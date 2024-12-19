package com.example.ems.repository;

import com.example.ems.entity.Items;
import com.example.ems.model.ItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Items, String>{
    @Query("UPDATE Items i SET i.balance = i.balance + :count, " +
            " i.outStock = i.outStock - :count " +
            " WHERE i.name = :items ")
   Items increaseCount(Integer count, String items);

    Items findByName(String name);

    @Query("UPDATE Items i SET i.balance = i.balance - :count, " +
            " i.outStock = i.outStock + :count " +
            " WHERE i.name = :items ")
    Items decreaseCount(Integer count, String items);
}
