package com.rasaboga.RasaBoga.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
    private String email;
    private List<String> roles;
}
