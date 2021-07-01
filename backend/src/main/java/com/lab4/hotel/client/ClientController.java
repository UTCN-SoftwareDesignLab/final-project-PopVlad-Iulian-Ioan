package com.lab4.hotel.client;

import com.lab4.hotel.client.model.dto.ClientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lab4.hotel.UrlMapping.CLIENTS;
import static com.lab4.hotel.UrlMapping.ENTITY;

@RestController
@RequestMapping(CLIENTS)
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping
    public List<ClientDTO> allItems() {
        return clientService.findAll();
    }

    @PostMapping
    public ClientDTO create(@RequestBody ClientDTO clientDTO) {
        return clientService.create(clientDTO);
    }

    @PatchMapping(ENTITY)
    public ClientDTO edit(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        return clientService.edit(id,clientDTO);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id) {
        clientService.delete(id);
    }

    @GetMapping(ENTITY)
    public ClientDTO getClient(@PathVariable Long id) {
        return clientService.get(id);
    }

}


