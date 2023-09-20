package com.tutorial.platzimarket.domain.repository;

import com.tutorial.platzimarket.domain.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {
    List<Purchase> getAll();
    Optional<List<Purchase>> getByClient(String idCliente);
    Purchase save(Purchase purchase);
}
