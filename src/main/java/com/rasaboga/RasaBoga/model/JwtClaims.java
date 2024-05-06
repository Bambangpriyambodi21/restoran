package com.rasaboga.RasaBoga.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtClaims {
    private String userId;
    private List<String> roles;
}