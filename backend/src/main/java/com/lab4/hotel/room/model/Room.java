package com.lab4.hotel.room.model;

import com.lab4.hotel.hotel.model.Hotel;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int number;
    @Column
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotelId;

    @Column
    private int price;

}
