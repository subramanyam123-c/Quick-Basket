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
import com.qba.app.model.Store;
import com.qba.app.model.User;


@Repository
public interface CartRepo extends JpaRepository<Cart, Long>{

	@Query( value = "select * from cart where email = :email", nativeQuery = true)
	List<Cart> findCartByEmail(@Param("email") String email);


	@Query( value = "select * from cart where id = :id", nativeQuery = true)
	Cart getCartById(@Param("id") Long id);


	@Query( value = "delete from cart where email = :email", nativeQuery = true)
	@Modifying
    @Transactional
	void deleteCart(@Param("email") String email);




}
