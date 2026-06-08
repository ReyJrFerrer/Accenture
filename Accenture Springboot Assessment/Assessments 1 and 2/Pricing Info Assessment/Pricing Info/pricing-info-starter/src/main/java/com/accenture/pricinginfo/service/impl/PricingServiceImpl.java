package com.accenture.pricinginfo.service.impl;

import com.accenture.pricinginfo.dto.PricingForProductResponse;
import com.accenture.pricinginfo.dto.PricingInitializationResponse;
import com.accenture.pricinginfo.dto.ValidatePricingRequest;
import com.accenture.pricinginfo.dto.ValidatePricingResponse;
import com.accenture.pricinginfo.entity.Pricing;
import com.accenture.pricinginfo.exception.BadRequestException;
import com.accenture.pricinginfo.repository.productMappingApi.ProductRepository;
import com.accenture.pricinginfo.repository.PricingRepository;
import com.accenture.pricinginfo.repository.productMappingApi.dto.ProductIdResponse;
import com.accenture.pricinginfo.service.PricingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public PricingForProductResponse getPricing(String productCode) {
        if (productCode == null || productCode.isBlank()){
            throw new BadRequestException(400, "Product code must not be empty", Map.of());
        }
        String productId = getProductId(productCode);

        if (productId == null || productId.isBlank()){
            throw new BadRequestException(400, "Product code " + productCode + "  not found", Map.of());
        }
        Pricing pricing = pricingRepository.findById(productId)
                .orElseThrow(() -> new BadRequestException(400, "Pricing details not found for product", Map.of()));

        return new PricingForProductResponse(
                pricing.getInterestRate(),
                pricing.getMinDepositAmount(),
                pricing.getMaxDepositAmount(),
                pricing.getMinAllowedTerm() + "_"+pricing.getMinAllowedTermType()
        );
    }

    @Override
    public ValidatePricingResponse validatePricing(ValidatePricingRequest request) {
        if (request.getProductId() == null || request.getProductId().isBlank()){
            throw new BadRequestException(400, "Product code must not be empty", Map.of());
        }
        Pricing pricing = pricingRepository.findById(request.getProductId())
                .orElseThrow(() -> new BadRequestException(400, "Product id " + request.getProductId() + " not found.", Map.of()));
        if (!areInterestRatesEqual(pricing, request)){
            return new ValidatePricingResponse(false, "The interest rate is incorrect.");
        }
        if (!isValidTerm(pricing, request)){
            return new ValidatePricingResponse(false, "The term is incorrect.");
        }
        return new ValidatePricingResponse(true, "");

    }
    private String getProductId(String productCode) {
        ProductIdResponse response = productRepository.getProductId(productCode);
        return response != null ? response.getProductId() : null;
    }
    private boolean areInterestRatesEqual(Pricing pricing, ValidatePricingRequest request){
        return pricing.getInterestRate().compareTo(request.getInterestRate()) == 0;
    }
    private boolean isValidTerm(Pricing pricing, ValidatePricingRequest request){
        if (request.getTerm() == null || request.getTerm().isBlank()){
            return false;
        }
        String[] parts = request.getTerm().split("_");
        if (parts.length != 2){
            return false;
        }
        try {
            int termValue = Integer.parseInt(parts[0]);
            String termType = parts[1];
            return pricing.getMinAllowedTerm() == termValue && pricing.getMinAllowedTermType().equalsIgnoreCase(termType);
        } catch (NumberFormatException e){
            return false;
        }


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
