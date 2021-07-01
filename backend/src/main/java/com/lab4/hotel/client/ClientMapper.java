package com.lab4.hotel.client;


import com.lab4.hotel.client.model.Client;
import com.lab4.hotel.client.model.dto.ClientDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDTO toDto(Client client);

    Client fromDto(ClientDTO bookDTO);
}
