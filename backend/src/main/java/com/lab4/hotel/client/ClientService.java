package com.lab4.hotel.client;

import com.lab4.hotel.client.model.Client;
import com.lab4.hotel.client.model.dto.ClientDTO;
import com.lab4.hotel.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found: " + id));
    }

    public List<ClientDTO> findAll() {
        return clientRepository.findAll().stream()
                .map( clientMapper::toDto)
                .collect( Collectors.toList());
    }

    public ClientDTO create(ClientDTO clientDTO) {
        return clientMapper.toDto( clientRepository.save(
                clientMapper.fromDto(clientDTO)
        ));
    }

    public ClientDTO edit(Long id, ClientDTO clientDTO) {
        Client client = findById(id);
        client.setName (clientDTO.getName());
        client.setCnp(clientDTO.getCnp());
        client.setEmail ( clientDTO.getEmail() );
        client.setFrequency ( clientDTO.getFrequency() );
        if(client.getFrequency()%5==0 && client.getFrequency()!=0)
            EmailService.send(EmailService.EMAIL,EmailService.PASSWORD,client.getEmail(),EmailService.SUBJECT,EmailService.MSG);
        return clientMapper.toDto(
                clientRepository.save(client)
        );
    }
    public ClientDTO increaseFrequency(Long id) {
        Client client = findById(id);
        client.setFrequency ( client.getFrequency() +1);
        if(client.getFrequency()%5==0 && client.getFrequency()!=0)
            EmailService.send(EmailService.EMAIL,EmailService.PASSWORD,client.getEmail(),EmailService.SUBJECT,EmailService.MSG);
        return clientMapper.toDto(
                clientRepository.save(client)
        );
    }

    public ClientDTO get(Long id) {
        return clientMapper.toDto(findById(id));
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    public void deleteAll(){clientRepository.deleteAll();}
}
