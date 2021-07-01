package com.lab4.hotel.client.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private long id;
    private String name;
    private String email;
    private String cnp;
    private long frequency;

}
