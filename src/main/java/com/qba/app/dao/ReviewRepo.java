package com.qba.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qba.app.model.Request;
import com.qba.app.model.Review;
import com.qba.app.model.Ticket;
import com.qba.app.model.User;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long>{

	@Query( value = "select * from reviews where email = :email", nativeQuery = true)
	User findReviewsbyEmail(@Param("email") String email);

	@Query( value = "select * from reviews where id = :id", nativeQuery = true)
	Request getReviewById(@Param("id") Long id);


}
