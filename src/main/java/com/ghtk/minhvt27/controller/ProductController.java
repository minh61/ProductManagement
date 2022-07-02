package com.ghtk.minhvt27.controller;


import com.ghtk.minhvt27.model.dto.ProductDTO;
import com.ghtk.minhvt27.model.entity.ProductEntity;
import com.ghtk.minhvt27.repository.ProductRepository;
import com.ghtk.minhvt27.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/v1.0/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productService;

    @GetMapping("")
    public ResponseEntity get( @RequestParam(value = "page") int page , @RequestParam(value = "page_size") int pageSize ) {
        return ResponseEntity.ok(productService.getProduct(PageRequest.of(page,pageSize))) ;
    }


    @PostMapping("")
    public ResponseEntity create(@Valid @RequestBody ProductDTO newProduct) {
        return ResponseEntity.ok(productService.create(newProduct));
    }


    @PutMapping("/{id}")
    public ResponseEntity update( @PathVariable Integer id , @Valid @RequestBody ProductDTO productDTO ) {
        return ResponseEntity.ok(productService.update(id , productDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete( @PathVariable Integer id ) {
        return ResponseEntity.ok(productService.delete(id));
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
