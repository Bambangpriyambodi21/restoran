package com.rasaboga.RasaBoga.repository;

import com.rasaboga.RasaBoga.entity.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TransaksiRepository extends JpaRepository<Transaksi, String>, JpaSpecificationExecutor<Transaksi> {
}
