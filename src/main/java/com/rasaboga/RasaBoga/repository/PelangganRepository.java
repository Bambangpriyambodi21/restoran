package com.rasaboga.RasaBoga.repository;

import com.rasaboga.RasaBoga.entity.Pelanggan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PelangganRepository extends JpaRepository<Pelanggan, String>, JpaSpecificationExecutor<Pelanggan> {
}
