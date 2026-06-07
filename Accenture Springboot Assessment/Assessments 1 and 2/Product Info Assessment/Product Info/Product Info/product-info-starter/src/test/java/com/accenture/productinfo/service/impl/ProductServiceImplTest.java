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
    void getProductId_WithValidCode123456_ShouldReturn1234567() {
        ProductIdResponse response = productService.getProductId("123456");
        assertNotNull(response);
        assertEquals("1234567", response.getProductId());
    }

    @Test
    void getProductId_WithValidCode123451_ShouldReturn123456() {
        ProductIdResponse response = productService.getProductId("123451");
        assertNotNull(response);
        assertEquals("123456", response.getProductId());
    }

    @Test
    void getProductId_WithInvalidCode_ShouldThrowBadRequestException() {
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            productService.getProductId("invalid");
        });
        assertEquals(4001, exception.getErrorId());
        assertTrue(exception.getErrorMessage().contains("invalid"));
    }

    @Test
    void getProductId_WithNullCode_ShouldThrowBadRequestException() {
        assertThrows(BadRequestException.class, () -> {
            productService.getProductId(null);
        });
    }
}
