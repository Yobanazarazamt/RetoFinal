package com.cabin.cabin.Reto5.CrudRepository;

import com.cabin.cabin.Reto5.Interfaz.Reservation;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ReservationCrudRepository extends CrudRepository<Reservation,Integer> {

@Query("SELECT r.client, COUNT(r.client) FROM Reservation AS r GROUP BY r.client ORDER BY COUNT(r.client) desc")
    public List<Object[]> countTotalReservationByClient();
    
    // Reservaciones entre dos fechas
    public List<Reservation> findAllByStartDateAfterAndStartDateBefore(Date dateOne, Date dateTwo);
    
    // Resevaciones canceladas y completadas (Atributo 'status')
    public List<Reservation> findAllByStatus(String status);
    
}