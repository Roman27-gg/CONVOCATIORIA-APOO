package com.ut.sucursales.repository;


import com.ut.sucursales.model.entity.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FranchiseRepository extends JpaRepository<Franchise,UUID> {

    boolean existsByName(String name);

    Optional<Franchise> findByName(String name);


}
