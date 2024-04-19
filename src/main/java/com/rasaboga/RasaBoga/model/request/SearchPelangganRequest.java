package com.rasaboga.RasaBoga.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchPelangganRequest {
    private String pelanggan;
    private Integer halaman;
    private Integer ukuran;
}
