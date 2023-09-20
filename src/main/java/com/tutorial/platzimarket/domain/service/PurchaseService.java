package com.tutorial.platzimarket.domain.service;

import com.tutorial.platzimarket.domain.Purchase;
import com.tutorial.platzimarket.persistence.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    @Autowired
    private CompraRepository compraRepository;

    public List<Purchase> getAll(){
        return compraRepository.getAll();
    }

    public Optional<List<Purchase>> getByClient(String idCliente){
        return compraRepository.getByClient(idCliente);
    }

    public Purchase save(Purchase purchase){
       return compraRepository.save(purchase);
    }
}
