package com.lab4.hotel.client.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String cnp;
    @Column
    private long frequency;
}
