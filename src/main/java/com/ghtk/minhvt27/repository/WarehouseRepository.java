package com.ghtk.minhvt27.repository;

import com.ghtk.minhvt27.model.entity.ProductEntity;
import com.ghtk.minhvt27.model.entity.WarehouseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<WarehouseEntity , Integer> {
    @Query(value = "SELECT * FROM warehouse WHERE status = :status",nativeQuery = true)
    Page<WarehouseEntity> findAllByStatus(@Param(value = "status") int status , Pageable pageable);

    @Query(value = "SELECT COUNT(id) FROM warehouse WHERE status = :status",nativeQuery = true)
    Long countWarehouseByStatus(@Param(value = "status") int status);
}
