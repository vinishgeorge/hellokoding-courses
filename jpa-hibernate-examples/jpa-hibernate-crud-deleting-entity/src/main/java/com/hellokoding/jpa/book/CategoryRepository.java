package com.hellokoding.jpa.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer>,CrudRepository<Category, Integer> {
}
