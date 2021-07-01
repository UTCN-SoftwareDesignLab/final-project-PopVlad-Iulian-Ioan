package com.lab4.hotel.report;

import com.lab4.hotel.booking.BookingService;
import com.lab4.hotel.booking.model.Booking;
import com.lab4.hotel.client.ClientService;
import com.lab4.hotel.client.model.Client;
import com.lab4.hotel.hotel.HotelService;
import com.lab4.hotel.hotel.model.Hotel;
import com.lab4.hotel.room.RoomService;
import com.lab4.hotel.room.model.Room;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ReportService {

    private ClientService clientService;
    private RoomService roomService;
    private HotelService hotelService;
    private BookingService bookingService;

    public String invoice(Booking booking){
        Client client=clientService.findById(booking.getClientId().getId());
        Room room=roomService.findById(booking.getRoomID().getId());
        Hotel hotel=hotelService.findById(room.getHotelId().getId());

        long timeDiff=(booking.getOutDate().getTime()-booking.getInDate().getTime());
        long days= TimeUnit
                .MILLISECONDS
                .toDays(timeDiff)
                % 365;

        long totalPrice=days*room.getPrice();
        double reducedPrice=0;
        if(client.getFrequency()%5==0 && client.getFrequency()!=0)
            reducedPrice=(totalPrice*(40.0f/100.0f));

        try {
            PDDocument doc = new PDDocument ();
            PDPage page = new PDPage ();
            doc.addPage ( page );
            PDPageContentStream stream = new PDPageContentStream(doc,page);
            stream.setFont( PDType1Font.TIMES_ROMAN, 12);
            stream.beginText();
            stream.setLeading(14.5f);
            stream.newLineAtOffset(25, 700);
            stream.showText("Invoice: ");
            stream.newLine();

                stream.showText("ID: " + booking.getId());
                stream.newLine();
                stream.showText("Room: " + booking.getRoomID().getNumber());
                stream.newLine();
                stream.showText("Hotel: " + hotel.getName()+" "+hotel.getCity());
                stream.newLine();
                stream.showText("Client: " + client.getName());
                stream.newLine();
                stream.showText("Checkin: " + booking.getInDate());
                stream.newLine();
                stream.showText("Checkout: "+booking.getOutDate());
                stream.newLine();
                stream.showText("Total Price: "+totalPrice);
                stream.newLine();
            if(client.getFrequency()%5==0 && client.getFrequency()!=0){
                stream.showText("Loyalty discount applied, Total Price: "+reducedPrice);
            }

            stream.endText();
            stream.close();

            doc.save ( "Invoice.pdf" );
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return "Invoice Created";
    }

    public String bookedRooms(Date from, Date to,Hotel hotel){
        List<Booking>bookings=bookingService.findBetween(from,to);

        try {
            PDDocument doc = new PDDocument ();
            PDPage page = new PDPage ();
            doc.addPage ( page );
            PDPageContentStream stream = new PDPageContentStream(doc,page);
            stream.setFont( PDType1Font.TIMES_ROMAN, 12);
            stream.beginText();
            stream.setLeading(14.5f);
            stream.newLineAtOffset(25, 700);
            stream.showText("Hotel: "+hotel.getName()+" from "+hotel.getCity());
            stream.newLine();
            stream.showText("Busy Rooms:");
            stream.newLine();

            for (Booking booking:bookings
            ) {
                Room room=roomService.findById(booking.getRoomID().getId());
                if(hotel.getId().equals(room.getHotelId().getId())) {
                    stream.showText("" + room.getNumber());
                    stream.newLine();
                }
            }

            stream.endText();
            stream.close();

            doc.save ( "BusyRooms.pdf" );
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return "Report Created";

    }


}

