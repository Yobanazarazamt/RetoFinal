package com.cabin.cabin.Reto5.Service;

import com.cabin.cabin.Reto5.Interfaz.Cabin;
import com.cabin.cabin.Reto5.Repository.CabinRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CabinService {

    @Autowired
    private CabinRepository CabinRepository;

    public List<Cabin> getCabins() {
        return CabinRepository.getAll();
    }

    public Optional<Cabin> getCabin(int id) {
        return CabinRepository.getCabin(id);
    }

    public Cabin save(Cabin cabin) {
        if (cabin.getId() == null) {
            return CabinRepository.save(cabin);
        } else {
            Optional<Cabin> e = CabinRepository.getCabin(cabin.getId());
            if (e.isPresent()) {
                return cabin;
            } else {
                return CabinRepository.save(cabin);
            }
        }
    }
    public Cabin update(Cabin cabin){
        if(cabin.getId()!=null){
            Optional<Cabin> e= CabinRepository.getCabin(cabin.getId());
            if(!e.isEmpty()){
                if(cabin.getName()!=null){
                    e.get().setName(cabin.getName());
                }
                if(cabin.getBrand()!=null){
                    e.get().setBrand(cabin.getBrand());
                }
                if(cabin.getRooms()!=null){
                    e.get().setRooms(cabin.getRooms());
                }
                if(cabin.getDescription()!=null){
                    e.get().setDescription(cabin.getDescription());
                }
                CabinRepository.save(e.get());
                return e.get();
            }else{
                return cabin;
            }
        }else{
            return cabin;
        }
    }
    public boolean deleteCabin(int id){
        Boolean d = getCabin(id).map(cabin -> {
            CabinRepository.delete(cabin);
            return true;
        }).orElse(false);
        return d;
    }

}