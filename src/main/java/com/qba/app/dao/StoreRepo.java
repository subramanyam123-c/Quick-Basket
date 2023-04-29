package com.qba.app.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qba.app.model.Store;
import com.qba.app.model.User;


@Repository
public interface StoreRepo extends JpaRepository<Store, Long>{

	@Query( value = "select * from stores where id = :id", nativeQuery = true)
	Store findStoreById(@Param("id") Long id);




}
