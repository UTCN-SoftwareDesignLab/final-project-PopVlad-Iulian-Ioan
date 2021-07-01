package com.lab4.hotel.booking.model;

import com.lab4.hotel.client.model.Client;
import com.lab4.hotel.room.model.Room;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room roomID;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client clientId;
    @Column
    private Date inDate;
    @Column
    private Date outDate;
}
