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
import com.qba.app.model.Reward;
import com.qba.app.model.Store;
import com.qba.app.model.User;


@Repository
public interface RewardRepo extends JpaRepository<Reward, Long>{

	@Query( value = "select * from reward where email = :email", nativeQuery = true)
	List<Reward> findRewardByEmail(@Param("email") String email);






}
