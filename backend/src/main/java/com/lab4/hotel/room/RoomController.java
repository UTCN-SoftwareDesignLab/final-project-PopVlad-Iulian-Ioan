package com.lab4.hotel.room;

import com.lab4.hotel.room.model.dto.RoomDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lab4.hotel.UrlMapping.ENTITY;
import static com.lab4.hotel.UrlMapping.ROOMS;

@RestController
@RequestMapping(ROOMS)
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping
    public List<RoomDTO> allItems() {
        return roomService.findAll();
    }

    @PostMapping
    public RoomDTO create(@RequestBody RoomDTO roomDTO) {
        return roomService.create(roomDTO);
    }

    @PatchMapping(ENTITY)
    public RoomDTO edit(@PathVariable Long id, @RequestBody RoomDTO roomDTO) {
        return roomService.edit(id,roomDTO);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id) {
        roomService.delete(id);
    }

    @GetMapping(ENTITY)
    public RoomDTO getRoom(@PathVariable Long id) {
        return roomService.get(id);
    }
}
