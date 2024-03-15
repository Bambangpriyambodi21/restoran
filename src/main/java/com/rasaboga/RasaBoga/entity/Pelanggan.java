package com.rasaboga.RasaBoga.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "pelanggan")
public class Pelanggan {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id_pelanggan;

    private String email_pelanggan;
    private String password_pelanggan;
    private String nama_pelanggan;
    private String alamat_pelanggan;
}
