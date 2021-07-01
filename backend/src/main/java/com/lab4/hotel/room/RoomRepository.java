package com.lab4.hotel.room;

import com.lab4.hotel.hotel.model.Hotel;
import com.lab4.hotel.room.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>, JpaSpecificationExecutor<Room> {
    List<Room>findAllByHotelId(Hotel hotelId);
}
