package com.rasaboga.RasaBoga.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuResponse {
    private String menu;
    private int stok;
    private int harga;
}
