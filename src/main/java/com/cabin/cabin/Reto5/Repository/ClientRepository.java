package com.cabin.cabin.Reto5.Repository;

import com.cabin.cabin.Reto5.Interfaz.Category;
import com.cabin.cabin.Reto5.Interfaz.Client;
import com.cabin.cabin.Reto5.CrudRepository.ClientCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class ClientRepository {

    @Autowired
    ClientCrudRepository clientCrudRepository;

    public List<Client> getAll(){
        return (List<Client>) clientCrudRepository.findAll();
    }

    public Optional<Client> getClient(Integer id){
        return clientCrudRepository.findById(id);
    }

    public Client save(Client client){
        return clientCrudRepository.save(client);
    }

    public void delete(Client client){
        clientCrudRepository.delete(client);
    }
}
