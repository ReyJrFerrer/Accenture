package com.accenture.productinfo.controller;

import com.accenture.productinfo.dto.ProductIdResponse;
import com.accenture.productinfo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;


@RestController
@RequestMapping
public class ProductController {
    private Logger log = Logger.getLogger(ProductController.class.getName());

    @Autowired
     ProductService productService;

    @GetMapping("/getProductId/{productCode}")
    public ProductIdResponse getProductDetails(@PathVariable String productCode){
        log.info("Request received for product code: " + productCode);
        return productService.getProductId(productCode);
    }
}
