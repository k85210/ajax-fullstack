package com.eeit.ajax.backend.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/shop")
public class ShopController {

    private static List<String> products = List.of("手機", "手錶", "筆電");


    @GetMapping("/page")
    public String shopPage(Model model) {
        // 模擬從 model 中取得資料
        model.addAttribute("productList", products);

        return "shop_mvc.html";
    }
    
    @GetMapping("/products")
    public List<String> getProducts() {
        return products;
    }
    

}
