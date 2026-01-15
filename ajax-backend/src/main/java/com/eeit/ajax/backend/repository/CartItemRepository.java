package com.eeit.ajax.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eeit.ajax.backend.model.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer>{
    
    void deleteByMemberMemberId(Integer memberId);

}
