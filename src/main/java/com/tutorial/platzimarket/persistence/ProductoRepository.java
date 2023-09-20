package com.tutorial.platzimarket.persistence;

import com.tutorial.platzimarket.domain.Product;
import com.tutorial.platzimarket.domain.repository.ProductRepository;
import com.tutorial.platzimarket.persistence.crud.ProductoCrudRepository;
import com.tutorial.platzimarket.persistence.entity.Producto;
import com.tutorial.platzimarket.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {
    @Autowired
    private ProductoCrudRepository productoCrudRepository;
    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll(){
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    public Optional<List<Product>> getByCategory(int idCategoria){
        var productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
        return Optional.ofNullable(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos =   productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prod -> mapper.toProducts(prod));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        Optional<Producto> producto = productoCrudRepository.findById(productId);
        return producto.map(prod -> mapper.toProduct(prod));
    }

    @Override
    public Product Save(Product product) {
        Producto producto= mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    @Override
    public void delete (int productId){
         productoCrudRepository.deleteById(productId);
    }
}
