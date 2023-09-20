package com.tutorial.platzimarket.persistence;

import com.tutorial.platzimarket.domain.Purchase;
import com.tutorial.platzimarket.domain.repository.PurchaseRepository;
import com.tutorial.platzimarket.persistence.crud.CompraCrudRepository;
import com.tutorial.platzimarket.persistence.entity.Compra;
import com.tutorial.platzimarket.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {
    @Autowired
    private CompraCrudRepository compraCrudRepository;
    @Autowired
    private PurchaseMapper purchaseMapper;
    @Override
    public List<Purchase> getAll() {
        return purchaseMapper.toPurchases((List<Compra>) compraCrudRepository.findAll()) ;
    }

    @Override
    public Optional<List<Purchase>> getByClient(String ClientId) {
        return compraCrudRepository.findByIdCliente(ClientId).
                map(compra -> purchaseMapper.toPurchases(compra));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = purchaseMapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        return purchaseMapper.toPurchase(compraCrudRepository.save(compra));
    }
}
