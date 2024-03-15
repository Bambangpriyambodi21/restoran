package com.rasaboga.RasaBoga.repository;

import com.rasaboga.RasaBoga.entity.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaksiRepository extends JpaRepository<Transaksi, String> {
}
