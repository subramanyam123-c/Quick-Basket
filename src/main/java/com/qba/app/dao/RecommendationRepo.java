package com.qba.app.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qba.app.model.Category;
import com.qba.app.model.Item;
import com.qba.app.model.Recommendation;
import com.qba.app.model.Store;
import com.qba.app.model.User;


@Repository
public interface RecommendationRepo extends JpaRepository<Recommendation, Long>{

	@Query( value = "select * from recommendations where email = :email", nativeQuery = true)
	List<Recommendation> findRecommendationsByEmail(@Param("email") String email);




}
