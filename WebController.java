package App.web;

import App.database.*;
import App.model.ShipTicket;
import App.model.AirTicket;
import App.model.TaxiTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.DateTimeException;
import java.time.LocalDate;

@RestController
public class WebController {
    @Autowired
    private ShipTicketRepository shipTicketRepository;
    @Autowired
    private AirTicketRepository airTicketRepository;
    @Autowired
    private TaxiTicketRepository taxiTicketRepository;




    SetVIPInfo setVIPInfo = new SetVIPInfo();



    @DeleteMapping("/delete/all")
    public synchronized ResponseEntity<Object> deleteAllTickets() {
        shipTicketRepository.deleteAll();
        airTicketRepository.deleteAll();
        taxiTicketRepository.deleteAll();
        return new ResponseEntity<Object>("Deleted.", HttpStatus.OK);
    }


    @GetMapping("/all")
    public synchronized ResponseEntity<Object> showAllTickets() {
        return new ResponseEntity<Object>("Taxi tickets: " + taxiTicketRepository.findAll().toString() + " \nAir tickets: " +   airTicketRepository.findAll().toString() + " \nShip tickets: " +  shipTicketRepository.findAll().toString(), HttpStatus.OK);
    }


    @GetMapping("/all/taxi")
    public synchronized ResponseEntity<Object> showAllTaxiTickets() {
        return new ResponseEntity<>(taxiTicketRepository.findAll().toString(), HttpStatus.OK);
    }


    @GetMapping("/all/air")
    public synchronized ResponseEntity<Object> showAllAirTickets() {
        return new ResponseEntity<>(airTicketRepository.findAll().toString(), HttpStatus.OK);
    }


    @GetMapping("/all/ship")
    public synchronized ResponseEntity<Object> showAllShipTickets() {
        return new ResponseEntity<>(shipTicketRepository.findAll().toString(), HttpStatus.OK);
    }


    @GetMapping("/taxi/{id}")
    public synchronized ResponseEntity<Object> showTaxiTicket(@PathVariable String id) {
        if(!taxiTicketRepository.existsById("T"+id)) {
            throw new NotFoundException("T"+id);
        }
        return new ResponseEntity<>(taxiTicketRepository.findById("T"+id).toString(), HttpStatus.OK);
    }


    @GetMapping("/air/{id}")
    public synchronized ResponseEntity<Object> showAirTicket(@PathVariable String id) {
        if(!airTicketRepository.existsById("A"+id)) {
            throw new NotFoundException("A"+id);
        }
        return new ResponseEntity<>(airTicketRepository.findById("A"+id).toString(), HttpStatus.OK);
    }

    @GetMapping("/ship/{id}")
    public synchronized ResponseEntity<Object> showShipTicket(@PathVariable String id) {
        if(!shipTicketRepository.existsById("S"+id)) {
            throw new NotFoundException("S"+id);
        }
        return new ResponseEntity<>(shipTicketRepository.findById("S"+id).toString(), HttpStatus.OK);
    }


    @PostMapping("/create/taxi/{id}/{taxiType}/{taxiStopName}/{client}/{numberOfTickets}/{day}/{month}/{year}")
    public synchronized ResponseEntity<Object> createTaxiTicket(@PathVariable String id, @PathVariable TaxiTicket.TaxiType taxiType, @PathVariable String taxiStopName, @PathVariable String client, @PathVariable Integer numberOfTickets, @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {
        if(!taxiTicketRepository.existsById("T"+id)) {
            try {
                TaxiTicket newTaxiTicket = new TaxiTicket(id, taxiType, taxiStopName, client, numberOfTickets, LocalDate.of(year, month, day));
                TaxiTicketRepository.save(newTaxiTicket);
            }
            catch(DateTimeException e){
                throw new DateException();
            }
            catch(DataIntegrityViolationException e){
                throw new DatabaseException();
            }
            return new ResponseEntity<>("Created.", HttpStatus.OK);
        }
        throw new AlreadyExistException();
    }


    @PostMapping("/create/air/{id}/{passengerClass}/{airportName}/{costLuggageIncluded}/{client}/{numberOfTickets}/{day}/{month}/{year}")
    public synchronized ResponseEntity<Object> createAirTicket(@PathVariable String id, @PathVariable AirTicket.passengerClass passengerClass, @PathVariable String airportName, @PathVariable boolean costLuggageIncluded, @PathVariable String client, @PathVariable Integer numberOfTickets, @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {
        if(!AirTicketRepository.existsById("A"+id)) {
            try {
                AirTicket newAirTicket = new AirTicket(id, passengerClass, airportName, costLuggageIncluded, client, numberOfTickets, LocalDate.of(year, month, day));
                AirTicketRepository.save(newAirTicket);
            }
            catch(DateTimeException e){
                throw new DateException();
            }
            catch(DataIntegrityViolationException e){
                throw new DatabaseException();
            }
            return new ResponseEntity<>("Created.", HttpStatus.OK);
        }
        throw new AlreadyExistException();
    }

    @PostMapping("/create/ship/{id}/{seatType}/{portName}/{mealsIncluded}/{client}/{numberOfTickets}/{day}/{month}/{year}")
    public synchronized ResponseEntity<Object> createShipTicket(@PathVariable String id, @PathVariable ShipTicket.SeatType seatType, @PathVariable String portName, @PathVariable boolean mealsIncluded, @PathVariable String client, @PathVariable Integer numberOfTickets, @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {
        if(!AirTicketRepository.existsById("T"+id)) {
            try {
                AirTicket newShipTicket = new ShipTicket(id, seatType, portName, mealsIncluded, client, numberOfTickets, LocalDate.of(year, month, day));
                AirTicketRepository.save(newAirTicket);
            }
            catch(DateTimeException e){
                throw new DateException();
            }
            catch(DataIntegrityViolationException e){
                throw new DatabaseException();
            }
            return new ResponseEntity<>("Created.", HttpStatus.OK);
        }
        throw new AlreadyExistException();
    }


    @PutMapping("/update/taxi/{id}/{taxiType}/{taxiStopName}/{client}/{numberOfTickets}/{day}/{month}/{year}")
    public synchronized ResponseEntity<Object> updateTaxiTicket(@PathVariable String id, @PathVariable TaxiTicket.taxiType taxiType, @PathVariable String taxiStopName, @PathVariable String client, @PathVariable Integer numberOfTickets, @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {
        String response = "Created.";
        if(TaxiTicketRepository.existsById("T"+id)) {
            TaxiTicketRepository.deleteById("T"+id);
            response= "Updated.";
        }
        try {
            TaxiTicket newTaxiTicket = new TaxiTicket(id, taxiType, taxiStopName, client, numberOfTickets, LocalDate.of(year, month, day));
            TaxiTicketRepository.save(newTaxiTicket);
        }
        catch(DateTimeException e){
            throw new DateException();
        }
        catch(DataIntegrityViolationException e){
            throw new DatabaseException();
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PutMapping("/update/air/{id}/{passengerClass}/{airportName}/{costLuggageIncluded}/{client}/{numberOfTickets}/{day}/{month}/{year}")
    public synchronized ResponseEntity<Object> updateAirTicket(@PathVariable String id, @PathVariable AirTicket.passengerClass passengerClass, @PathVariable String airportName, @PathVariable boolean costLuggageIncluded, @PathVariable String client, @PathVariable Integer numberOfTickets, @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {
        String response = "Created.";
        if(AirTicketRepository.existsById("A"+id)) {
            AirTicketRepository.deleteById("A"+id);
            response = "Updated.";
        }
        try {
            AirTicket newAirTicket = new AirTicket(id, passengerClass, airportName, costLuggageIncluded, client, numberOfTickets, LocalDate.of(year, month, day));
            AirTicketRepository.save(newAirTicket);
        }
        catch(DateTimeException e){
            throw new DateException();
        }
        catch(DataIntegrityViolationException e){
            throw new DatabaseException();
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PutMapping("/update/ship/{id}/{seatType}/{portName}/{mealsIncluded}/{client}/{numberOfTickets}/{day}/{month}/{year}")
    public synchronized ResponseEntity<Object> updateShipTicket(@PathVariable String id, @PathVariable ShipTicket.SeatType seatType, @PathVariable String portName, @PathVariable boolean mealsIncluded, @PathVariable String client, @PathVariable Integer numberOfTickets, @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {
        String response = "Created.";
        if(ShipTicketRepository.existsById("S"+id)) {
            ShipTicketRepository.deleteById("S"+id);
            response = "Updated.";
        }
        try {
            ShipTicket newShipTicket = new ShipTicket(id, seatType, portName, mealsIncluded, client, numberOfTickets, LocalDate.of(year, month, day));
            ShipTicketRepository.save(newShipTicket);
        }
        catch(DateTimeException e){
            throw new DateException();
        }
        catch(DataIntegrityViolationException e){
            throw new DatabaseException();
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/Ship/{id}")
    public synchronized ResponseEntity<Object> deleteShipTicket(@PathVariable String id){
        if(!ShipTicketRepository.existsById("S"+id)){
            throw new NotFoundException("S"+id);
        }
        ShipTicketRepository.deleteById("S"+id);
        return new ResponseEntity<>("Deleted.", HttpStatus.OK);
    }



    @DeleteMapping("/delete/Air/{id}")
    public synchronized ResponseEntity<Object> deleteAirTicket(@PathVariable String id){
        if(!AirTicketRepository.existsById("A"+id)){
            throw new NotFoundException("A"+id);
        }
        AirTicketRepository.deleteById("A"+id);
        return new ResponseEntity<>("Deleted.", HttpStatus.OK);
    }

    @DeleteMapping("/delete/taxi/{id}")
    public synchronized ResponseEntity<Object> deleteTaxiTicket(@PathVariable String id){
        if(!TaxiTicketRepository.existsById("T"+id)){
            throw new NotFoundException("T"+id);
        }
        TaxiTicketRepository.deleteById("T"+id);
        return new ResponseEntity<>("Deleted.", HttpStatus.OK);
    }

    @PutMapping("/setVIPInfo/taxi/{id}/{taxiType}/{taxiStopName}/{client}/{numberOfTickets}/{day}/{month}/{year}")
    public synchronized ResponseEntity<Object> setVIPTaxiTicketInfo(@PathVariable String id, @PathVariable TaxiTicket.taxiType taxiType, @PathVariable String taxiStopName, @PathVariable String client, @PathVariable Integer numberOfTickets, @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {
        try {
            setVIPInfo.setTaxiVIPTicket(id, taxiType, taxiStopName, client, numberOfTickets, LocalDate.of(year, month, day));
        }
        catch(DateTimeException e){
            throw new DateException();
        }
        return new ResponseEntity<>("Information added", HttpStatus.OK);
    }

    @PutMapping("/setVIPInfo/air/{id}/{passengerClass}/{airportName}/{costLuggageIncluded}/{client}/{numberOfTickets}/{day}/{month}/{year}")
    public synchronized ResponseEntity<Object> setVIPAirTicketInfo(@PathVariable String id, @PathVariable AirTicket.passengerClass passengerClass, @PathVariable String airportName, @PathVariable boolean costLuggageIncluded, @PathVariable String client, @PathVariable Integer numberOfTickets, @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {
        try {
            setVIPInfo.setPlaneVIPTicket(id, passengerClass, airportName, costLuggageIncluded, client, numberOfTickets, LocalDate.of(year, month, day));
        }
        catch(DateTimeException e){
            throw new DateException();
        }
        return new ResponseEntity<>("Information added", HttpStatus.OK);
    }

    @PutMapping("/setVIPInfo/ship/{id}/{seatType}/{portName}/{mealsIncluded}/{client}/{numberOfTickets}/{day}/{month}/{year}")
    public synchronized ResponseEntity<Object> setVIPShipTicketInfo(@PathVariable String id, @PathVariable AirTicket.SeatType seatType, @PathVariable String portName, @PathVariable boolean mealsIncluded, @PathVariable String client, @PathVariable Integer numberOfTickets, @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {
        try {
            setVIPInfo.setShipVIPTicket(id, seatType, portName, mealsIncluded, client, numberOfTickets, LocalDate.of(year, month, day));
        }
        catch(DateTimeException e){
            throw new DateException();
        }
        return new ResponseEntity<>("Information added", HttpStatus.OK);
    }


    @Transactional(rollbackFor = RuntimeException.class)
    @PostMapping("/create/three_types/{id}/{taxiType}/{passengerClass}/{seatType}/{taxiStopName}/{airportName}/{portName}/{costLuggageIncluded}/{mealsIncluded}/{client}/{numberOfTickets}/{day}/{month}/{year}")
    public synchronized ResponseEntity<Object> createShipTaxiAirTicket(@PathVariable String id, @PathVariable TaxiTicket.taxiType taxiType, @PathVariable AirTicket.passengerClass passengerClass,
                                                                         @PathVariable AirTicket.SeatType seatType, @PathVariable String taxiStopName,
                                                                         @PathVariable String airportName, @PathVariable String portName,
                                                                         @PathVariable boolean costLuggageIncluded, @PathVariable boolean mealsIncluded,
                                                                         @PathVariable String client, @PathVariable Integer numberOfTickets,
                                                                         @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {
        if(!TaxiTicketRepository.existsById("T"+id)) {
            try {
                TaxiTicket newTaxiTicket = new TaxiTicket(id, taxiType, taxiStopName, client, numberOfTickets, LocalDate.of(year, month, day));
                TaxiTicketRepository.save(newTaxiTicket);
            } catch (DateTimeException e) {
                throw new DateException();
            }
            catch(DataIntegrityViolationException e){
                throw new DatabaseException();
            }
        }
        else{
            throw new AlreadyExistException();
        }
        if(!AirTicketRepository.existsById("A"+id)) {
            try {
                AirTicket newAirTicket = new AirTicket(id, passengerClass, airportName, costLuggageIncluded, client, numberOfTickets, LocalDate.of(year, month, day));
                AirTicketRepository.save(newAirTicket);
            } catch (DateTimeException e) {
                throw new DateException();
            }
            catch(DataIntegrityViolationException e){
                throw new DatabaseException();
            }
        }
        else {
            throw new AlreadyExistException();
        }
        if(!ShipTicketRepository.existsById("S"+id)) {
            try {
                ShipTicket newShipTicket = new ShipTicket(id, seatType, portName, mealsIncluded, client, numberOfTickets, LocalDate.of(year, month, day));
                ShipTicketRepository.save(newShipTicket);
            } catch (DateTimeException e) {
                throw new DateException();
            }
            catch(DataIntegrityViolationException e){
                throw new DatabaseException();
            }
        }
        else {
            throw new AlreadyExistException();
        }
        return new ResponseEntity<>("Created.", HttpStatus.OK);
    }


    @PostMapping("/update/three_types/{id}/{taxiType}/{passengerClass}/{seatType}/{taxiStopName}/{airportName}/{portName}/{costLuggageIncluded}/{mealsIncluded}/{client}/{numberOfTickets}/{day}/{month}/{year}")
    public synchronized ResponseEntity<Object> updateBusPlaneAirTicket(@PathVariable String id, @PathVariable TaxiTicket.taxiType taxiType, @PathVariable AirTicket.passengerClass passengerClass,
                                                                         @PathVariable AirTicket.SeatType seatType, @PathVariable String taxiStopName,
                                                                         @PathVariable String airportName, @PathVariable String portName,
                                                                         @PathVariable boolean costLuggageIncluded, @PathVariable boolean mealsIncluded,
                                                                         @PathVariable String client, @PathVariable Integer numberOfTickets,
                                                                         @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {


        String TaxiTicketResponse = "Created.";
        String AirTicketResponse = "Created.";
        String AirTicketResponse = "Created.";
        if(TaxiTicketRepository.existsById("T"+id)) {
            TaxiTicketRepository.deleteById("T"+id);
            TaxiTicketResponse = "Updated.";
        }
        try {
            TaxiTicket newTaxiTicket = new TaxiTicket(id, taxiType, taxiStopName, client, numberOfTickets, LocalDate.of(year, month, day));
            TaxiTicketRepository.save(newTaxiTicket);
        }
        catch(DateTimeException e){
            throw new DateException();
        }
        catch(DataIntegrityViolationException e){
            throw new DatabaseException();
        }
        if(AirTicketRepository.existsById("A"+id)) {
            AirTicketRepository.deleteById("A"+id);
            AirTicketResponse = "Updated.";
        }
        try {
            AirTicket newAirTicket = new AirTicket(id, passengerClass, airportName, costLuggageIncluded, client, numberOfTickets, LocalDate.of(year, month, day));
            AirTicketRepository.save(newAirTicket);
        }
        catch(DateTimeException e){
            throw new DateException();
        }
        catch(DataIntegrityViolationException e){
            throw new DatabaseException();
        }
        if(AirTicketRepository.existsById("T"+id)) {
            AirTicketRepository.deleteById("T"+id);
            AirTicketResponse = "Updated.";
        }
        try {
            ShipTicket newShipTicket = new ShipTicket(id, seatType, portName, mealsIncluded, client, numberOfTickets, LocalDate.of(year, month, day));
            ShipTicketRepository.save(newShipTicket);
        }
        catch(DateTimeException e){
            throw new DateException();
        }
        catch(DataIntegrityViolationException e){
            throw new DatabaseException();
        }
        return new ResponseEntity<>("Taxi Ticket: " + TaxiTicketResponse + "Air Ticket: "
                + AirTicketResponse + "Ship Ticket: " + ShipTicketResponse, HttpStatus.OK);
    }
}



