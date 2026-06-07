package com.accenture.pricinginfo.service.impl;

import com.accenture.pricinginfo.dto.PricingInitializationResponse;
import com.accenture.pricinginfo.entity.Pricing;
import com.accenture.pricinginfo.repository.productMappingApi.ProductRepository;
import com.accenture.pricinginfo.repository.PricingRepository;
import com.accenture.pricinginfo.service.PricingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PricingServiceImpl implements PricingService {

    private final PricingRepository pricingRepository;

    private final ProductRepository productRepository;

    public PricingServiceImpl(
            PricingRepository pricingRepository,
            ProductRepository productRepository
    ) {
        this.pricingRepository = pricingRepository;
        this.productRepository = productRepository;
    }

    @Override
    public PricingInitializationResponse initializeDatabase() {
        Pricing pricing1 = buildPricing("1234567", 1, "YEAR");
        Pricing pricing2 = buildPricing("12345678", 1, "YEAR");
        Pricing pricing3 = buildPricing("123457", 1, "YEAR");
        Pricing pricing4 = buildPricing("1234573", 6, "MONTHS");
        Pricing pricing5 = buildPricing("1234572", 9, "MONTHS");
        Pricing pricing6 = buildPricing("12345671", 2, "YEARS");

        List<Pricing> pricings = pricingRepository.saveAll(
                List.of(
                        pricing1,
                        pricing2,
                        pricing3,
                        pricing4,
                        pricing5,
                        pricing6
                )
        );

        return new PricingInitializationResponse(
                pricings
        );
    }

    private Pricing buildPricing(
            String productId,
            int minAllowedTerm,
            String minAllowedTermType
    ) {
        return Pricing
                .builder(productId, minAllowedTerm, minAllowedTermType)
                .build();
    }
}
