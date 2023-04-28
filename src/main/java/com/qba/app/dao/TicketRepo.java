package com.qba.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qba.app.model.Ticket;
import com.qba.app.model.User;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Long>{

	@Query( value = "select * from tickets where id = :id", nativeQuery = true)
	Ticket findTicketsbyId(@Param("id") Long id);


}
