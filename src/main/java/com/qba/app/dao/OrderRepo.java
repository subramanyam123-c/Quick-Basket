package com.qba.app.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qba.app.model.Cart;
import com.qba.app.model.Category;
import com.qba.app.model.Item;
import com.qba.app.model.Order;
import com.qba.app.model.Store;
import com.qba.app.model.User;


@Repository
public interface OrderRepo extends JpaRepository<Order, Long>{

	@Query( value = "select * from orders where email = :email", nativeQuery = true)
	List<Order> findOrderByEmail(@Param("email") String email);


	@Query( value = "select * from orders where id = :id", nativeQuery = true)
	Order getOrderById(@Param("id") Long id);


	@Query(value = "update orders set status = :status where id = :id", nativeQuery = true)
	@Modifying
    @Transactional
	void updateOrderStatus(@Param("status") String status, @Param("id") Long id);






}
