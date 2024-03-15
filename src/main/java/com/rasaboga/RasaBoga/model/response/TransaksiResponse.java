package com.rasaboga.RasaBoga.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransaksiResponse {
    private String id;
    private String menu;
    private String pelanggan;
    private int bill;
    private int quantity;
}
