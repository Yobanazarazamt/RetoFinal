package com.cabin.cabin.Reto5.Service;

import com.cabin.cabin.Reto5.Interfaz.Client;
import com.cabin.cabin.Reto5.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll() {
        return clientRepository.getAll();
    }

    public Optional<Client> getClient(Integer id) {
        return clientRepository.getClient(id);
    }

    public Client save(Client client) {
        if (client.getIdClient() == null) {
            return clientRepository.save(client);
        } else {
            Optional<Client> e = clientRepository.getClient(client.getIdClient());
            if (e.isPresent()) {

                return client;
            } else {
                return clientRepository.save(client);
            }
        }
    }

    public Client update(Client client) {
        if (client.getIdClient() != null) {
            Optional<Client> e = clientRepository.getClient(client.getIdClient());
            if (!e.isEmpty()) {
                if (client.getName() != null) {
                    e.get().setName(client.getName());
                }
                if (client.getEmail() != null) {
                    e.get().setEmail(client.getEmail());
                }
                if (client.getPassword() != null) {
                    e.get().setPassword(client.getPassword());
                }
                if (client.getAge() != null) {
                    e.get().setAge(client.getAge());
                }
                clientRepository.save(e.get());
                return e.get();
            } else {
                return client;
            }
        } else {
            return client;
        }
    }

    public boolean deleteClient(int id) {
        Boolean respuesta = getClient(id).map(elemento -> {
            clientRepository.delete(elemento);
            return true;
        }).orElse(false);

        return respuesta;
    }
}

/*
    public boolean deleteClient(int id){
        boolean d=false;
        Optional<Client> e = clientRepository.getClient(id);
        if(e.isPresent()){
            clientRepository.delete(e.get());
            d = true;
        }
        return d;
    }*/
