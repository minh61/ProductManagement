package com.ghtk.minhvt27.controller;


import com.ghtk.minhvt27.model.entity.ProductEntity;
import com.ghtk.minhvt27.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1.0/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("")
    public ResponseEntity get( @RequestParam(value = "page") int page , @RequestParam(value = "page_size") int pageSize ) {
        Page<ProductEntity> productEntities =  productRepository.findAll(PageRequest.of(page,pageSize));
        return ResponseEntity.ok(productEntities);
    }

    @PostMapping("")
    public ResponseEntity create( @RequestBody ProductEntity productEntity ) {
        return ResponseEntity.ok(productRepository.save(productEntity));
    }

    @PutMapping("")
    public ResponseEntity update( @RequestBody ProductEntity productEntity ) {
        return ResponseEntity.ok(productRepository.save(productEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete( @PathVariable Integer id ) {
        productRepository.deleteById(id);
        return ResponseEntity.ok("Xoa thanh cong");
    }

    // search api with params
    @GetMapping("/search")
    public ResponseEntity search(
        @RequestParam(value = "price") float price,
        @RequestParam(value = "name") String name,
        @RequestParam(value = "page") int page,
        @RequestParam(value = "page_size") int pageSize
    ) {
        Pageable pageable = PageRequest.of(page,pageSize);
        Page<ProductEntity> productEntities = productRepository.searchByPrice( price,name, pageable);
        return ResponseEntity.ok(productEntities);
    }


}
