package com.lab4.hotel.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class MessageResponse {
    private String message;
}