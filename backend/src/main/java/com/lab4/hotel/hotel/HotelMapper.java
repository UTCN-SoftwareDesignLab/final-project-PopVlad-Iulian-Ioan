package com.lab4.hotel.hotel;

import com.lab4.hotel.hotel.model.Hotel;
import com.lab4.hotel.hotel.model.dto.HotelDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HotelMapper {
    HotelDTO toDto(Hotel hotel);

    Hotel fromDto(HotelDTO hotelDTO);
}
