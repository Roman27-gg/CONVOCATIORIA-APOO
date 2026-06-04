package com.ut.sucursales.repository;

import com.ut.sucursales.model.entity.Branch;
import com.ut.sucursales.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByBranch(Branch branch);

    @Query("SELECT p FROM Product p WHERE p.branch = :branch AND p.stock = " +
            "(SELECT MAX(p2.stock) FROM Product p2 WHERE p2.branch = :branch)")
    List<Product> findProductsWithHighestStockByBranch(@Param("branch") Branch branch);
}
