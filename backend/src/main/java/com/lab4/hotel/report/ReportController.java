package com.lab4.hotel.report;

import com.lab4.hotel.booking.model.Booking;
import com.lab4.hotel.hotel.model.Hotel;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.io.IOUtils;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import static com.lab4.hotel.UrlMapping.INVOICE;
import static com.lab4.hotel.UrlMapping.REPORTS;

@RestController
@RequestMapping(REPORTS)
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;
    @PostMapping()
    public @ResponseBody byte[] getBookedRooms(@PathVariable Date inDate,@PathVariable Date outDate,@RequestBody Hotel hotel) throws IOException {
        String file= reportService.bookedRooms(inDate,outDate,hotel);
        InputStream in = new FileInputStream(file);
        return IOUtils.toByteArray(in);
    }
    @PostMapping(INVOICE)
    public @ResponseBody byte[] getInvoice(@RequestBody Booking booking) throws IOException {
        String file = reportService.invoice(booking);
        InputStream in = new FileInputStream(file);
        return IOUtils.toByteArray(in);
    }

}
