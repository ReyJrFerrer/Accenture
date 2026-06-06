package com.accenture.productinfo.service.impl;

import com.accenture.productinfo.dto.ProductIdResponse;
import com.accenture.productinfo.exception.BadRequestException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class ProductServiceImpl implements com.accenture.productinfo.service.ProductService{

    private Logger log = Logger.getLogger(ProductServiceImpl.class.getName());

    private Map<String, String> readFile()
    {
     try{
         ObjectMapper objectMapper = new ObjectMapper();
         ClassPathResource resource = new ClassPathResource("product.json");

         try (InputStream inputStream = resource.getInputStream()){
             return objectMapper.readValue(inputStream, new TypeReference<HashMap<String, String>>(){}
             );
         }

     } catch (IOException e){
         log.severe("Unable to read JSON file: " + e.getMessage());
         throw new RuntimeException("Unable to read JSON file", e);
     }

    }

    @Override
    public ProductIdResponse getProductId(String productId) {
        log.info("Looking up product code: " + productId);
        Map<String, String> codeMap = readFile();
        String productCode = codeMap.get(productId);
        if(productCode == null){
            log.warning("Product code not found: " + productId);
            throw new BadRequestException(
                    4001, "Product code " + productId + " not found", null
            );
        }
        log.info("Product found: " + productId + " -> " + productCode);
        return new ProductIdResponse(productCode);
    }
}
