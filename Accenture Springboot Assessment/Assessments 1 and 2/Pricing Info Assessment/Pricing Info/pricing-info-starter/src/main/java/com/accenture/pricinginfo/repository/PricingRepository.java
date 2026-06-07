package com.accenture.pricinginfo.repository;

import com.accenture.pricinginfo.entity.Pricing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricingRepository extends JpaRepository<Pricing, String> {
}
