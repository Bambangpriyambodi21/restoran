package com.rasaboga.RasaBoga.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PelangganResponse {
    private String id_pelanggan;
    private String email_pelanggan;
    private String nama_pelanggan;
}
