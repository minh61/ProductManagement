package com.ghtk.minhvt27.repository;
import com.ghtk.minhvt27.model.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity , Integer> {


    @Query(value = "SELECT * FROM category c WHERE c.status = :status AND c.name LIKE %:name% ORDER BY c.id DESC", nativeQuery = true)
//    @Query(value = "SELECT * FROM category c ORDER BY c.status desc", nativeQuery = true)
    Page<CategoryEntity> getByStatus(@Param(value = "status") int status, @Param(value = "name") String name , Pageable pageable);
}
