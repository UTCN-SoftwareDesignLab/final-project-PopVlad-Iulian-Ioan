package com.lab4.hotel.booking;

import com.lab4.hotel.booking.model.Booking;
import com.lab4.hotel.room.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>, JpaSpecificationExecutor<Booking> {
    List<Booking>findAllByAndInDateBetweenOrOutDateBetween(Date inDate, Date outDate, Date inDate1, Date outDate2);
    Booking findBookingByRoomIDAndInDateBetween(Room roomId, Date inDate, Date outDate);
    Booking findBookingByRoomIDAndOutDateBetween(Room roomId, Date inDate, Date outDate);

}
