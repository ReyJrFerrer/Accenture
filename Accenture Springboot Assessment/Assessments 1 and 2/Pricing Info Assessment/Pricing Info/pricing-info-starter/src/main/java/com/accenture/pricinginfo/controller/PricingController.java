package com.accenture.pricinginfo.controller;

import com.accenture.pricinginfo.dto.PricingInitializationResponse;
import com.accenture.pricinginfo.service.PricingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ms-pricing-info")
public class PricingController {

    private final PricingService pricingService;

    public PricingController(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    @PostMapping("/initializeDatabase")
    public PricingInitializationResponse initializePricing() {
        return this.pricingService.initializeDatabase();
    }


}
