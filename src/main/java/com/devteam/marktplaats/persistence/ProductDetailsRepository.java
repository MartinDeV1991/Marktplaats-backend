package com.devteam.marktplaats.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devteam.marktplaats.model.ProductDetails;

public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Long> {
}
