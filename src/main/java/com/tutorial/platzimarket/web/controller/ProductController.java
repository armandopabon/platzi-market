package com.tutorial.platzimarket.web.controller;

import com.tutorial.platzimarket.domain.Product;
import com.tutorial.platzimarket.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/All")
    public ResponseEntity<List<Product>> getAll(){
        return  new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") int productId){
        return ResponseEntity.of(productService.getProduct(productId));
    }
    @GetMapping("/Category/{category}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable("category")int categoryId){
        return ResponseEntity.of(productService.getByCategory(categoryId));
    }

    @PostMapping("/Save")
    public ResponseEntity<Product> Save(@RequestBody Product product){
        return new ResponseEntity<>(productService.Save(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/Delete/{id}")
    public ResponseEntity delete(@PathVariable("id") int productId){
       return new ResponseEntity(this.productService.delete(productId) ? HttpStatus.OK: HttpStatus.NOT_FOUND);
    }

}
