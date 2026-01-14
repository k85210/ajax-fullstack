package com.eeit.ajax.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eeit.ajax.backend.model.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>{

    /**
     * 判斷會員表中的所有會員，是否有任一個會員擁有圖片
     * @return true: 其中一個會員有圖片 ； false: 所有會員都沒有圖片
    */
    boolean existsByMemberPhotoIsNotNull();
}
