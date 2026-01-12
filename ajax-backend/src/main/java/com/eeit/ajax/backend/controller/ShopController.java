package com.eeit.ajax.backend.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/shop")
public class ShopController {

    @GetMapping("/page")
    public String shopPage(Model model) {
        // 模擬從 model 中取得資料
        List<String> products = List.of("item1", "item2", "item3");
        model.addAttribute("productList", products);

        return "shop_mvc.html";
    }
    
}
