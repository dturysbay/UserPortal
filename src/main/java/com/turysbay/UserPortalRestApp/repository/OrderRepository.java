package com.turysbay.UserPortalRestApp.repository;

import com.turysbay.UserPortalRestApp.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Order getOrderById(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Order o SET " +
            "o.description = :description, " +
            "o.number = :number, " +
            "o.address = :address," +
            "o.phoneNumber = :phoneNumber " +
            "WHERE o.id = :id")
    void updateOrder(@Param("description") String description,
                      @Param("number") Integer number,
                      @Param("address") String address,
                      @Param("phoneNumber") String phoneNumber,
                      @Param("id") Long id);

    List<Order> findByCreatedBy(Long userId);
}

