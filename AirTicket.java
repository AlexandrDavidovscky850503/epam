package App.model;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class AirTicket extends Ticket implements Serializable {

    private PassengerClass passengerClass;
    private String airportName;
    private boolean costLuggageIncluded;

    public enum PassengerClass{
        ECONOMY,
        BUSINESS,
        FIRST
    }
    public AirTicket(){

    }
    public AirTicket(String id, PassengerClass passengerClass, String airportName, boolean costLuggageIncluded, String client, int numberOfTickets, LocalDate date){
        super("A"+id, client, numberOfTickets, date);
        this.passengerClass = passengerClass;
        this.costLuggageIncluded = costLuggageIncluded;
        this.airportName = airportName;
    }
    public void setInformation(String id, PassengerClass passengerClass, String airportName, boolean costLuggageIncluded, String client, int numberOfTickets, LocalDate date){
        super.setInformation(id, client, numberOfTickets, date);
        this.passengerClass = passengerClass;
        this.costLuggageIncluded = costLuggageIncluded;
        this.airportName = airportName;
    }
    public void buyTicket(){
        System.out.println("Air_Ticket::buyTicket was called");
    }
    public void cancel(){
        System.out.println("Air_Ticket::cancel was called");
    }
    public void show(){
        System.out.println("Air_Ticket::show was called");
        System.out.println(passengerClass.name());
        System.out.println(airportName);
        System.out.println(costLuggageIncluded);
        System.out.println(getclient());
        System.out.println(getnumberOfTickets());
        System.out.println(getDate().toString());
    }
    public String toString(){
        return "Air ticket: ID: " + getId()
                + "; Type: " + passengerClass.toString() +
                "; Airport name: " + airportName + "; Cost luggage included: " +
                String.valueOf(costLuggageIncluded) + "; Client: " + getclient() +
                "; Ticket(s): " + String.valueOf(getnumberOfTickets()) +
                "; Date: " + date.toString();
    }
}
