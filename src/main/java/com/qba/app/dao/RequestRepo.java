package com.qba.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qba.app.model.Request;
import com.qba.app.model.Ticket;
import com.qba.app.model.User;

@Repository
public interface RequestRepo extends JpaRepository<Request, Long>{

	@Query( value = "select * from requests where email = :email", nativeQuery = true)
	User findRequetsbyEmail(@Param("email") String email);

	@Query( value = "select * from requests where id = :id", nativeQuery = true)
	Request getRequestById(@Param("id") Long id);


}
