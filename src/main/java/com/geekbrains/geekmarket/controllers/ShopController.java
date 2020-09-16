package com.geekbrains.geekmarket.controllers;

import com.geekbrains.geekmarket.entities.Product;
import com.geekbrains.geekmarket.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/shop")
@RestController
public class ShopController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public String shopPage(Model model) {
        List<Product> allProducts = productService.getAllProducts();
        model.addAttribute("products", allProducts);
        return "shop-page";
    }

    @GetMapping("/ajax")
    public String ajaxShopPage(Model model) {
       // List<Product> allProducts = productService.getAllProducts();
       // model.addAttribute("products", allProducts);
        return "shop-page-ajax";
    }

    @GetMapping("/product/{productId}")
    public Product getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/products")
    public List<Product> getProductId() {
        return productService.getAllProducts();
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product theProduct) {
        theProduct.setId(0L);
        theProduct = productService.saveOrUpdate(theProduct);
        return theProduct;
    }


}
