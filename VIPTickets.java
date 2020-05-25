package App.web;

import App.database.*;
import App.model.;
import App.model.AirTicket;
import App.model.TaxiTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class VIPTickets {
    @Autowired
    private AirTicketRepository airTicketRepository;
    @Autowired
    private Repository Repository;
    @Autowired
    private TaxiTicketRepository TaxiTicketRepository;

    private Integer idCounter;
    private SetVIPInfo setVIPInfo;

    public VIPTickets(){
        idCounter = 0;
        setVIPInfo = new SetVIPInfo();
    }

    @Async
    @Scheduled(cron = "30 * * * * 1-7")
    public synchronized void createVIPTicket(){
        AirTicket AirTicket = setVIPInfo.getAirVIPTicket();
        ShipTicket shipTicket = setVIPInfo.getShipVIPTicket();
        TaxiTicket taxiTicket = setVIPInfo.getTaxiVIPTicket();
        taxiTicket.setId("T-VIP" + idCounter.toString());
        airTicket.setId("A-VIP" + idCounter.toString());
        shipTicket.setId("S-VIP" + idCounter.toString());
        if(shipTicketRepository.existsById("S-VIP" + idCounter.toString()) || airTicketRepository.existsById("A-VIP" + idCounter.toString())
                || taxiTicketRepository.existsById("T-VIP" + idCounter.toString())){
            idCounter++;
            throw new AsyncAlreadyExistException();
        }else{
            shipTicketRepository.save(shipTicket);
            airTicketRepository.save(airTicket);
            taxiTicketRepository.save(taxiTicket);
        }
        idCounter++;
    }
}
