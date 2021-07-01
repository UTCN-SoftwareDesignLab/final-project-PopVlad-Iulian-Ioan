package com.lab4.hotel.booking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {

    private Long id;
    private Long roomId;
    private Long clientId;
    private Date inDate;
    private Date outDate;
}
