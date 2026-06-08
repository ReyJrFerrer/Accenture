package com.accenture.productinfo.service.impl;

import com.accenture.productinfo.dto.ProductIdResponse;
import com.accenture.productinfo.exception.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceImplTest {

    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl();
    }

    @Test
    void testGetProductId_ValidProductCode() {
        ProductIdResponse response = productService.getProductId("123456");
        assertNotNull(response);
        assertEquals("1234567", response.getProductId());
    }


    @Test
    void testGetProductId_InvalidProductCode() {
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            productService.getProductId("12345");
        });
        assertEquals(400, exception.getErrorId());
        assertEquals("Product code 12345 not found.", exception.getErrorMessage());
        assertNotNull(exception.getErrorDetails());
        assertTrue(exception.getErrorDetails().isEmpty());
    }

}
