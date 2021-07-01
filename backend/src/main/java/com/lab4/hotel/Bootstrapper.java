package com.lab4.hotel;

import com.lab4.hotel.booking.BookingService;
import com.lab4.hotel.client.ClientService;
import com.lab4.hotel.hotel.HotelService;
import com.lab4.hotel.room.RoomService;
import com.lab4.hotel.security.AuthService;
import com.lab4.hotel.security.dto.SignupRequest;
import com.lab4.hotel.user.RoleRepository;
import com.lab4.hotel.user.UserRepository;
import com.lab4.hotel.user.model.ERole;
import com.lab4.hotel.user.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class Bootstrapper implements ApplicationListener<ApplicationReadyEvent> {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final AuthService authService;

    private final ClientService clientService;

    private final RoomService roomService;

    private final HotelService hotelService;

    private final BookingService bookingService;
    @Value("${app.bootstrap}")
    private Boolean bootstrap;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (bootstrap) {
            hotelService.deleteAll();
            roomService.deleteAll();
            clientService.deleteAll();
            bookingService.deleteAll();
            userRepository.deleteAll();
            roleRepository.deleteAll();
            for (ERole value : ERole.values()) {
                roleRepository.save(
                        Role.builder()
                                .name(value)
                                .build()
                );
            }
            authService.register(SignupRequest.builder()
                    .email("vlad@email.com")
                    .username("admin")
                    .password("WooHoo1!")
                    .roles(Set.of("ADMIN"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("vlad1@email.com")
                    .username("regular")
                    .password("WooHoo1!")
                    .roles(Set.of("REGULAR"))
                    .build());
        }
    }
}
