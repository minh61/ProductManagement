package com.ghtk.minhvt27.repository;

import com.ghtk.minhvt27.model.entity.DistrictEntity;
import com.ghtk.minhvt27.model.entity.ProvinceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProvinceRepository extends JpaRepository<ProvinceEntity , Integer> {
    @Query(value = "SELECT * FROM province WHERE id = :id",nativeQuery = true)
    ProvinceEntity findProvinceById(@Param(value = "id") Integer id);
}
