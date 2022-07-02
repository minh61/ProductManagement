package com.ghtk.minhvt27.controller;

import com.ghtk.minhvt27.model.dto.WarehouseDTO;

import com.ghtk.minhvt27.service.WarehouseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1.0/warehouse")
public class WarehouseController {


    @Autowired
    private WarehouseServiceImpl warehouseService;

    @GetMapping("")
    public ResponseEntity get(@RequestParam(value = "page") int page , @RequestParam(value = "page_size") int pageSize ) {
        return ResponseEntity.ok(warehouseService.getWarehouse(PageRequest.of(page,pageSize))) ;
    }


    @PostMapping("")
    public ResponseEntity create(@Valid @RequestBody WarehouseDTO newWarehouse) {
        return ResponseEntity.ok(warehouseService.create(newWarehouse));
    }


    @PutMapping("/{id}")
    public ResponseEntity update( @PathVariable Integer id , @Valid @RequestBody WarehouseDTO newWarehouse ) {
        return ResponseEntity.ok(warehouseService.update(id , newWarehouse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete( @PathVariable Integer id ) {
        return ResponseEntity.ok(warehouseService.delete(id));
    }

}
