package com.cabin.cabin.Reto5.Repository;

import com.cabin.cabin.Reto5.Interfaz.Message;
import com.cabin.cabin.Reto5.Interfaz.Reservation;
import com.cabin.cabin.Reto5.CrudRepository.ReservationCrudRepository;
import com.cabin.cabin.Reto5.Interfaz.Client;
import com.cabin.cabin.Reto5.Interfaz.custom.CountClient;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public class ReservationRepository {

    @Autowired
    ReservationCrudRepository reservationCrudRepository;

    public List<Reservation> getAll() {
        return (List<Reservation>) reservationCrudRepository.findAll();
    }

    public Optional<Reservation> getReservation(Integer id) {
        return reservationCrudRepository.findById(id);
    }

    public Reservation save(Reservation reservation) {
        return reservationCrudRepository.save(reservation);
    }

    public void delete(Reservation reservation) {
        reservationCrudRepository.delete(reservation);
    }

    public List<Reservation> getReservationsByStatus(String status) {
        return reservationCrudRepository.findAllByStatus(status);
    }

    // Listar reservaciones entre dos fechas
    public List<Reservation> getReservationPeriod(Date dateOne, Date dateTwo) {
        return reservationCrudRepository.findAllByStartDateAfterAndStartDateBefore(dateOne, dateTwo);
    }

    // Listado de clientes con sus reservaciones
    public List<CountClient> getTopClients() {
        List<CountClient> res = new ArrayList<>();
        List<Object[]> report = reservationCrudRepository.countTotalReservationByClient();
        for (int i = 0; i < report.size(); i++) {
            Client cli = (Client) report.get(i)[0];
            Integer cantidad = Integer.parseInt(report.get(i)[1].toString());
            CountClient cc = new CountClient(cantidad, cli);
            res.add(cc);
        }
        return res;
}
    
    
}
