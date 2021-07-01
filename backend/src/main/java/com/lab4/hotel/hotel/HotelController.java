package com.lab4.hotel.hotel;

import com.lab4.hotel.hotel.model.dto.HotelDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lab4.hotel.UrlMapping.ENTITY;
import static com.lab4.hotel.UrlMapping.HOTELS;

@RestController
@RequestMapping(HOTELS)
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;

    @GetMapping
    public List<HotelDTO> allItems() {
        return hotelService.findAll();
    }

    @PostMapping
    public HotelDTO create(@RequestBody HotelDTO hotelDTO) {
        return hotelService.create(hotelDTO);
    }

    @PatchMapping(ENTITY)
    public HotelDTO edit(@PathVariable Long id, @RequestBody HotelDTO hotelDTO) {
        return hotelService.edit(id,hotelDTO);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id) {
        hotelService.delete(id);
    }

    @GetMapping(ENTITY)
    public HotelDTO getHotel(@PathVariable Long id) {
        return hotelService.get(id);
    }
}
