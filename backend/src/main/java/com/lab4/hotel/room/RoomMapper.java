package com.lab4.hotel.room;


import com.lab4.hotel.room.model.Room;
import com.lab4.hotel.room.model.dto.RoomDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    @Mappings({
            @Mapping(target = "hotelId", source = "hotelId.id"),
            @Mapping(target = "hotelName", source = "hotelId.name"),
            @Mapping(target = "hotelCity", source = "hotelId.city"),
    })
    RoomDTO toDto(Room Room);


        @Mappings({
                @Mapping(target = "hotelId.id", source = "hotelId"),
                @Mapping(target = "hotelId.name", source = "hotelName"),
                @Mapping(target = "hotelId.city", source = "hotelCity"),
        })
    Room fromDto(RoomDTO roomDTO);
}
