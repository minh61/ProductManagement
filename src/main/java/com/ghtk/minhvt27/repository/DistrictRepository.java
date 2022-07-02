package com.ghtk.minhvt27.repository;

import com.ghtk.minhvt27.model.entity.DistrictEntity;
import com.ghtk.minhvt27.service.DistrictService;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.function.Function;

public interface DistrictRepository extends JpaRepository<DistrictEntity , Integer> {

    @Query(value = "SELECT * FROM district WHERE id = :id",nativeQuery = true)
    DistrictEntity findDistrictById(@Param(value = "id") Integer id);

}
