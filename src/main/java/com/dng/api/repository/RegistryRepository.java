package com.dng.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dng.api.domain.Registry;

@Repository
public interface RegistryRepository extends JpaRepository<Registry, Long> {

}
