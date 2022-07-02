package com.ghtk.minhvt27.controller;

import com.ghtk.minhvt27.model.entity.CategoryEntity;
import com.ghtk.minhvt27.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1.0/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;


    // get all category from Database , Without paging
//    @GetMapping("")
//    public ResponseEntity get() {
//        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
//        return ResponseEntity.ok(categoryEntities);
//    }

    // get all category from Database WITH paging
    @GetMapping("")
    public ResponseEntity get(
        @RequestParam(value = "page") int page,
        @RequestParam(value = "page_size") int pageSize
    ) {

        Page<CategoryEntity> categoryEntities = categoryRepository.findAll(PageRequest.of(page, pageSize));
        return ResponseEntity.ok(categoryEntities);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity delete(
        @PathVariable Integer id
    ) {
        categoryRepository.deleteById(id);
        return ResponseEntity.ok("Xoa thanh cong");
    }

    @PostMapping("")
    public ResponseEntity create(
        @RequestBody CategoryEntity categoryEntity
    ) {
        return ResponseEntity.ok(categoryRepository.save(categoryEntity));
    }

    @PutMapping("")
    public ResponseEntity update(
        @RequestBody CategoryEntity categoryEntity
    ) {
        return ResponseEntity.ok(categoryRepository.save(categoryEntity));
    }

    //search category
    @GetMapping("/search")
    public ResponseEntity search(
//        @RequestParam(value = "status") Integer status,
        @RequestParam(value = "page") int page,
        @RequestParam(value = "page_size") int pageSize
    ) {
        Pageable pageable = PageRequest.of(page,pageSize);
        int status = 1;
        String name = "the";
        Page<CategoryEntity> categoryEntities = categoryRepository.getByStatus(status,name, pageable);
        return ResponseEntity.ok(categoryEntities);

    }
}
