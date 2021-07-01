package com.lab4.hotel.room;

import com.lab4.hotel.hotel.HotelRepository;
import com.lab4.hotel.hotel.model.Hotel;
import com.lab4.hotel.room.model.Room;
import com.lab4.hotel.room.model.dto.RoomDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final HotelRepository hotelRepository;

    public Room findById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room not found: " + id));
    }

    public List<RoomDTO> findAll() {
        return roomRepository.findAll().stream()
                .map( roomMapper::toDto)
                .collect( Collectors.toList());
    }

    public RoomDTO create(RoomDTO roomDTO) {
        return roomMapper.toDto( roomRepository.save(
                roomMapper.fromDto(roomDTO)
        ));
    }

    public RoomDTO edit(Long id, RoomDTO roomDTO) {
        Room room = findById(id);
        room.setHotelId(hotelRepository.findById(roomDTO.getHotelId()).orElseThrow(()-> new EntityNotFoundException("No Room with that id ")));
        room.setCapacity(roomDTO.getCapacity());
        room.setNumber(roomDTO.getNumber());
        room.setPrice(roomDTO.getPrice());

        return roomMapper.toDto(
                roomRepository.save(room)
        );
    }

    public RoomDTO get(Long id) {
        return roomMapper.toDto(findById(id));
    }

    public void delete(Long id) {
        roomRepository.deleteById(id);
    }

    public void deleteAll(){roomRepository.findAll();}

    public List<Room>findAllByHotel(Hotel hotel){
        return roomRepository.findAllByHotelId(hotel);

    }
}
