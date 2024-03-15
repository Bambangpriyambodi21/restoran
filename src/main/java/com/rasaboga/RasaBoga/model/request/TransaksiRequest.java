package com.rasaboga.RasaBoga.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransaksiRequest {
    private String id;
    private String menu;
    private String pelanggan;
    private int quantity;
    private int bill;
}
