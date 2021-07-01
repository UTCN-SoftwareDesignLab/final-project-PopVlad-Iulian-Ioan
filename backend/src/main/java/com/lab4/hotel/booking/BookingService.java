package com.lab4.hotel.booking;

import com.lab4.hotel.booking.model.Booking;
import com.lab4.hotel.booking.model.dto.BookingDTO;
import com.lab4.hotel.client.ClientService;
import com.lab4.hotel.client.model.Client;
import com.lab4.hotel.room.RoomService;
import com.lab4.hotel.room.model.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final ClientService clientService;
    private final RoomService roomService;

    private Booking findById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found: " + id));
    }

    public List<BookingDTO> findAll() {
        return bookingRepository.findAll().stream()
                .map(bookingMapper::toDto)
                .collect(Collectors.toList());
    }

    public BookingDTO create(BookingDTO bookingDTO) throws Exception {
        Room room = roomService.findById(bookingDTO.getRoomId());
        Booking booking=bookingRepository.findBookingByRoomIDAndInDateBetween(room, bookingDTO.getInDate(), bookingDTO.getOutDate());
        Booking booking1=bookingRepository.findBookingByRoomIDAndOutDateBetween(room, bookingDTO.getInDate(), bookingDTO.getOutDate());
        try {
            if (booking == null && booking1==null) {
                Client client = clientService.findById(bookingDTO.getClientId());
                clientService.increaseFrequency(client.getId());
                return bookingMapper.toDto(bookingRepository.save(
                        bookingMapper.fromDto(bookingDTO)
                ));
            } else throw new Exception();
        } catch (Exception e) {
            System.out.println("Booking not available");
            return null;
        }
    }


    public BookingDTO edit(Long id, BookingDTO bookingDTO) {
        Booking actBook = findById(id);
        Date initInDate=actBook.getInDate();
        Date initOutDate=actBook.getOutDate();
        actBook.setClientId(clientService.findById(bookingDTO.getClientId()));
        actBook.setRoomID(roomService.findById(bookingDTO.getRoomId()));
        actBook.setInDate ( bookingDTO.getInDate() );
        actBook.setOutDate ( bookingDTO.getOutDate() );

        if(initInDate.getTime()==actBook.getInDate().getTime() && initOutDate.getTime()==actBook.getOutDate().getTime())
            return bookingMapper.toDto(
                    bookingRepository.save(actBook)
            );
        else {
            Room room = roomService.findById(bookingDTO.getRoomId());
            Booking booking = bookingRepository.findBookingByRoomIDAndInDateBetween(room, bookingDTO.getInDate(), bookingDTO.getOutDate());
            Booking booking1 = bookingRepository.findBookingByRoomIDAndOutDateBetween(room, bookingDTO.getInDate(), bookingDTO.getOutDate());
            try {
                if (booking == null && booking1 == null) {
                    return bookingMapper.toDto(
                            bookingRepository.save(actBook)
                    );
                } else throw new Exception();
            } catch (Exception e) {
                System.out.println("Booking not available");
                return null;
            }
        }

    }

    public BookingDTO get(Long id) {
        return bookingMapper.toDto(findById(id));
    }

    public void delete(Long id) {
        bookingRepository.deleteById(id);
    }

    public List<Booking>findBetween(Date inDate, Date outDate){
        return bookingRepository.findAllByAndInDateBetweenOrOutDateBetween(inDate,outDate,inDate,outDate);
    }

    public void deleteAll(){bookingRepository.deleteAll();}


}
