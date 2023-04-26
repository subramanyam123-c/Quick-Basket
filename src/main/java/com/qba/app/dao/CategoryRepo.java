package com.qba.app.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qba.app.model.Category;
import com.qba.app.model.Store;
import com.qba.app.model.User;


@Repository
public interface CategoryRepo extends JpaRepository<Category, Long>{

	@Query( value = "select * from categories where id = :id", nativeQuery = true)
	Category findCategoryById(@Param("id") Long id);




}
