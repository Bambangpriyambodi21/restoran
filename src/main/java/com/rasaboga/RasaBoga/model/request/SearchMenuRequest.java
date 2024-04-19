package com.rasaboga.RasaBoga.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchMenuRequest {
    private String menu;
    private int halaman;
    private int ukuran;
}
