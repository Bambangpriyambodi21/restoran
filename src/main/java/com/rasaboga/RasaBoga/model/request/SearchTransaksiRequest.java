package com.rasaboga.RasaBoga.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchTransaksiRequest {
    private String id;
    private Integer halaman;
    private Integer ukuran;
}
