package com.cabin.cabin.Reto5.Controller;

import com.cabin.cabin.Reto5.Interfaz.Reservation;
import com.cabin.cabin.Reto5.Interfaz.custom.CountClient;
import com.cabin.cabin.Reto5.Interfaz.custom.StatusAmount;
import com.cabin.cabin.Reto5.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/all")
    public List<Reservation> getAll() {
        return reservationService.getAll();
    }

    @GetMapping("/{idReservation}")
    public Optional<Reservation> getReservation(@PathVariable("idReservation") Integer idReservation) {
        return reservationService.getReservation(idReservation);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation save(@RequestBody Reservation reservation) {
        return reservationService.save(reservation);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation update(@RequestBody Reservation reservation) {
        return reservationService.update(reservation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int reservationId) {
        return reservationService.deleteReservation(reservationId);
    }
    
    @GetMapping("/report-status")
    public StatusAmount getReservationStatus() {
        return reservationService.getStatusReport();
    }
    
    // Reservation/report-clients
	@GetMapping("/report-clients")
    public List<CountClient> getCountClient() {
        return reservationService.getTopClients();
    }
    
    // Reservation/report-dates/date1/date2
    @GetMapping("/report-dates/{dateOne}/{dateTwo}")
    public List<Reservation> getDatesReport(@PathVariable("dateOne") String d1, @PathVariable("dateTwo") String d2) {
        return reservationService.getReservationPeriod(d1, d2);
    }
}
