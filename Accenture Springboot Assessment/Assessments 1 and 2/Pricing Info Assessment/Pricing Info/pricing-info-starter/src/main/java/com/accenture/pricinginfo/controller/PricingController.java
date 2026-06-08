package com.accenture.pricinginfo.controller;

import com.accenture.pricinginfo.dto.PricingForProductResponse;
import com.accenture.pricinginfo.dto.PricingInitializationResponse;
import com.accenture.pricinginfo.dto.ValidatePricingRequest;
import com.accenture.pricinginfo.dto.ValidatePricingResponse;
import com.accenture.pricinginfo.service.PricingService;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/ms-pricing-info")
public class PricingController {
    private Logger log = Logger.getLogger(getClass().getName());;

    private final PricingService pricingService;

    public PricingController(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    // get pricing function
    @GetMapping("/getPricing/{productCode}")
    public PricingForProductResponse getPricing(@PathVariable String productCode) {
        log.info("Getting pricing for product code: " + productCode);
        return this.pricingService.getPricing(productCode);
    }
    // validate pricing
    @PostMapping("/validatePricing")
    public ValidatePricingResponse validatePricing(@RequestBody ValidatePricingRequest request) {
        log.info("Validating pricing for product code: " + request.getProductId());
        return this.pricingService.validatePricing(request);
    }
    @PostMapping("/initializeDatabase")
    public PricingInitializationResponse initializePricing() {
        log.info("Initializing pricing database");
        return this.pricingService.initializeDatabase();
    }


}
