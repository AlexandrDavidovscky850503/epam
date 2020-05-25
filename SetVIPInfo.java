package App.database;

import App.model.TaxiTicket;
import App.model.AirTicket;
import App.model.ShipTicket;

import java.time.LocalDate;
import java.util.HashMap;

public class SetVIPInfo {
    private static TaxiTicket tVIPTicket;
    private static AirTicket aVIPTicket;
    private static ShipTicket sVIPTicket;

    public SetVIPInfo(){
        tVIPTicket = new TaxiTicket("VIP-default",TaxiTicket.BusType.CITY, "Mosk", "Alex", 1, LocalDate.of(2020,11,11));
        aVIPTicket = new AirTicket("VIP-default", AirTicket.SeatClass.ECONOMY, "MSQ", true, "Lolik", 10, LocalDate.of(2220, 11, 11));
    }

    public TaxiTicket gettVIPTicket(){ return tVIPTicket; }

    public AirTicket getaVIPTicket(){ return aVIPTicket; }

    public ShipTicket getsVIPTicket(){ return sVIPTicket; }

    public void settVIPTicket(String id, taxiType taxiType, String taxiStopName, String client, int numberOfTickets, LocalDate date){ tVIPTicket.setInformation(id, taxiType,taxiStopName, client, numberOfTickets, date); }

    public void setaVIPTicket(String id, PassengerClass passengerClass, String airportName, boolean costLuggageIncluded, String client, int numberOfTickets, LocalDate date){ aVIPTicket.setInformation(id, passengerClass, airportName, costLuggageIncluded, client, numberOfTickets, date); }

    public void setsVIPTicket(String id, AppartamentType appartamentType, String portName, boolean mealsIncluded, String client, int numberOfTickets, LocalDate date){ sVIPTicket.setInformation(id, AppartamentType, portName, mealsIncluded, client, numberOfTickets, date); }
}
