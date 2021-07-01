package com.lab4.hotel.booking;


import com.lab4.hotel.booking.model.dto.BookingDTO;
import com.lab4.hotel.report.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lab4.hotel.UrlMapping.BOOKINGS;
import static com.lab4.hotel.UrlMapping.ENTITY;

@RestController
@RequestMapping(BOOKINGS)
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    private final ReportService reportService;

    @GetMapping
    public List<BookingDTO> allItems() {
        return bookingService.findAll();
    }

    @PostMapping
    public BookingDTO create(@RequestBody BookingDTO bookingDTO) throws Exception {
        return bookingService.create(bookingDTO);
    }

    @PatchMapping(ENTITY)
    public BookingDTO edit(@PathVariable Long id, @RequestBody BookingDTO bookingDTO) {
        return bookingService.edit(id,bookingDTO);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id) {
        bookingService.delete(id);
    }

    @GetMapping(ENTITY)
    public BookingDTO getBooking(@PathVariable Long id) {
        return bookingService.get(id);
    }



}
