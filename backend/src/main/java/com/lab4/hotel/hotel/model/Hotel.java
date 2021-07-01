package com.lab4.hotel.hotel.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private String city;
}
