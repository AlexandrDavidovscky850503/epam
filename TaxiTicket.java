package App.model;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class TaxiTicket extends Ticket implements Serializable {
    public enum TaxiType{
        CITY,
        REGIONAL
    }
    private TaxiType taxiType;
    private String taxiStopName;
    public TaxiTicket(){

    }
    public TaxiTicket(String id, taxiType taxiType, String taxiStopName, String client, int numberOfTickets, LocalDate date){
        super("T"+id, client, numberOfTickets, date);
        this.taxiType=taxiType;
        this.taxiStopName=taxiStopName;
    }
    public void buyTicket(){
        System.out.println("Taxi_ticket::buy was called");
    }
    public void cancel(){
        System.out.println("Taxi_ticket::cancel was called");
    }
    public void setInformation(String id, taxiType taxiType, String taxiStopName, String client, int numberOfTickets, LocalDate date){
        super.setInformation(id, client, numberOfTickets, date);
        this.taxiType=taxiType;
        this.taxiStopName=taxiStopName;
    }
    public void show(){
        System.out.println("Taxi_ticket::show was called");
        System.out.println(taxiType.name());
        System.out.println(taxiStopName);
        System.out.println(getclient());
        System.out.println(getnumberOfTickets());
        System.out.println(getDate().toString());
    }
    public String toString(){
        return "taxi ticket: ID: " +
                getId() + "; Type: "
                + taxiType.toString() +
                "; Taxi stop: " + taxiStopName + "; Client: " +
                getclient() + "; Ticket(s): " + String.valueOf(getnumberOfTickets()) +
                "; Date: " + date.toString();
    }
}
