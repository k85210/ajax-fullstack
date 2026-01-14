package com.eeit.ajax.backend.config;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.eeit.ajax.backend.model.entity.Member;
import com.eeit.ajax.backend.model.entity.Product;
import com.eeit.ajax.backend.repository.MemberRepository;
import com.eeit.ajax.backend.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class initialize implements CommandLineRunner {

    // 寫法一: Autowired注入
    // @Autowired
    // private MemberRepository memberRepository;
    
    // 寫法二: 建構子注入
    // private final MemberRepository memberRepository;
    // public Initialize (MemberRepository memberRepository) {
    //     this.memberRepository = memberRepository;
    // }

    // 寫法三:
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    
    @Override
    public void run(String... args) throws Exception {
        System.out.println("AAA");
        initMemberPhoto();
        initProductPhoto();
    }

    private void initMemberPhoto() throws Exception {
        // 判斷要不要初始化
        boolean anyoneHasPhoto = memberRepository.existsByMemberPhotoIsNotNull();
        if (anyoneHasPhoto) {
            // 如有會員擁有圖片，直接return(終止程式)
            log.info("有任意會員擁有圖片，終止初始化");
            return;
        }

        log.info("開始初始化會員圖片");
        // 讀取所有會員
        List<Member> members = memberRepository.findAll();

        // 設定圖片
        for (Member m : members) {
            int id = m.getMemberId();

            // 定位圖片
            File photo = ResourceUtils.getFile("classpath:initialization_data/image/member/%s.webp".formatted(id));

            // 讀取圖片
            byte[] bytes = Files.readAllBytes(photo.toPath());

            // 設定到 entity 內
            m.setMemberPhoto(bytes);
        }

        // 寫入資料庫
        memberRepository.saveAll(members);
        log.info("初始化完成");
    }

    private void initProductPhoto() throws Exception {
        // 判斷要不要初始化
        boolean anyoneHasPhoto = productRepository.existsByProductPhotoIsNotNull();
        if (anyoneHasPhoto) {
            // 如有會員擁有圖片，直接return(終止程式)
            log.info("有任意商品擁有圖片，終止初始化");
            return;
        }

        log.info("開始初始化商品圖片");
        // 讀取所有會員
        List<Product> products = productRepository.findAll();

        // 設定圖片
        for (Product p : products) {
            int id = p.getProductId();

            // 定位圖片
            File photo = ResourceUtils.getFile("classpath:initialization_data/image/phone/%s.webp".formatted(id));

            // 讀取圖片
            byte[] bytes = Files.readAllBytes(photo.toPath());

            // 設定到 entity 內
            p.setProductPhoto(bytes);
        }

        // 寫入資料庫
        productRepository.saveAll(products);
        log.info("初始化完成");
    }
}
