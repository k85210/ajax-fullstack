
package com.eeit.ajax.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eeit.ajax.backend.model.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
    boolean existsByProductPhotoIsNotNull();
}