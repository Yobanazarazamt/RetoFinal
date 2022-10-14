package com.cabin.cabin.Reto5.Repository;

import com.cabin.cabin.Reto5.Interfaz.Cabin;
import com.cabin.cabin.Reto5.CrudRepository.CabinCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class CabinRepository {

    @Autowired
    private CabinCrudRepository cabinCrudRepository;

    public List<Cabin> getAll(){
        return (List<Cabin>) cabinCrudRepository.findAll();
    }

    public Optional<Cabin> getCabin(Integer id){
        return cabinCrudRepository.findById(id);
    }

    public Cabin save(Cabin cabin){
        return cabinCrudRepository.save(cabin);
    }

    public void delete(Cabin cabin){
        cabinCrudRepository.delete(cabin);
    }

}
