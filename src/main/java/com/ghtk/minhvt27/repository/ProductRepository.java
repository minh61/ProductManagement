package com.ghtk.minhvt27.repository;

import com.ghtk.minhvt27.model.dto.ProductDTO;
import com.ghtk.minhvt27.model.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    @Query(value = "SELECT COUNT(id) FROM product WHERE status = :status",nativeQuery = true)
    Long countProductByStatus(@Param(value = "status") int status);

    @Query(value = "SELECT * FROM product WHERE status = :status",nativeQuery = true)
    Page<ProductEntity> findAllByStatus(@Param(value = "status") int status , Pageable pageable);

    @Query(value = "SELECT * FROM product WHERE price > :price AND name LIKE %:name% ORDER BY price DESC", nativeQuery = true)
    Page<ProductEntity> searchByPrice( @Param(value = "price") float price, @Param(value = "name") String name , Pageable pageable);
}
