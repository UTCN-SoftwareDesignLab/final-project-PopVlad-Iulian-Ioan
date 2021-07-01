package com.lab4.hotel.hotel;

import com.lab4.hotel.hotel.model.Hotel;
import com.lab4.hotel.hotel.model.dto.HotelDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;
    public Hotel findById(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hotel not found: " + id));
    }

    public List<HotelDTO> findAll() {
        return hotelRepository.findAll().stream()
                .map( hotelMapper::toDto)
                .collect( Collectors.toList());
    }

    public HotelDTO create(HotelDTO hotelDTO) {
        return hotelMapper.toDto( hotelRepository.save(
                hotelMapper.fromDto(hotelDTO)
        ));
    }

    public HotelDTO edit(Long id, HotelDTO hotelDTO) {
        Hotel hotel = findById(id);
        hotel.setName (hotelDTO.getName());
        hotel.setCity(hotelDTO.getCity());
        return hotelMapper.toDto(
                hotelRepository.save(hotel)
        );
    }

    public HotelDTO get(Long id) {
        return hotelMapper.toDto(findById(id));
    }

    public void delete(Long id) {
        hotelRepository.deleteById(id);
    }

    public void deleteAll(){hotelRepository.deleteAll();}
}
