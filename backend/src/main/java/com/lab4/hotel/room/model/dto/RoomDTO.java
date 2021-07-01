package com.lab4.hotel.room.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    private Long id;

    private int number;

    private int capacity;

    private Long hotelId;

    private String hotelName;

    private String hotelCity;

    private int price;

}
