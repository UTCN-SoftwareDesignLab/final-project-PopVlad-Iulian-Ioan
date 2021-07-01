package com.lab4.hotel.booking;


import com.lab4.hotel.booking.model.dto.BookingDTO;
import com.lab4.hotel.booking.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    @Mappings({
        @Mapping(target = "roomId", source = "roomID.id"),
        @Mapping(target = "clientId", source = "clientId.id"),
})
    BookingDTO toDto(Booking booking);


    @Mappings({
            @Mapping(target = "roomID.id", source = "roomId"),
            @Mapping(target = "clientId.id", source = "clientId"),
    })
     Booking fromDto(BookingDTO bookingDTO);
}
