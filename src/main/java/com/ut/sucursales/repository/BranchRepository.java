package com.ut.sucursales.repository;

import com.ut.sucursales.model.entity.Branch;
import com.ut.sucursales.model.entity.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BranchRepository extends JpaRepository<Branch, UUID> {
    Optional<Branch> findByName(String name);

    boolean existsByName(String name);

    boolean existsByAddress(String address);

    List<Branch> findByFranchise(Franchise franchise);
}
