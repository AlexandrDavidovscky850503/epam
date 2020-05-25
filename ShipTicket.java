package App.model;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class ShipTicket extends Ticket implements Serializable {

    public enum AppartamentType{
        ECONOMY,
        BUSINESS
    }
    private AppartamentType appartamentType;
    private boolean mealsIncluded;
    private String portName;
    public ShipTicket(){

    }
    public ShipTicket(String id, AppartamentType appartamentType, String portName, boolean mealsIncluded, String client, int numberOfTickets, LocalDate date){
        super("S"+id, client, numberOfTickets, date);
        this.appartamentType=appartamentType;
        this.mealsIncluded=mealsIncluded;
        this.portName=portName;
    }

    public void setInformation(String id, AppartamentType appartamentType, String portName, boolean mealsIncluded, String client, int numberOfTickets, LocalDate date) {
        super.setInformation(id, client, numberOfTickets, date);
        this.appartamentType=appartamentType;
        this.mealsIncluded=mealsIncluded;
        this.portName=portName;
    }

    public void buy(){
        System.out.println("Ship_Ticket::buy was called");
    }
    public void cancel(){
        System.out.println("Ship_Ticket::cancel was called");
    }
    public void show(){
        System.out.println("Ship_Ticket::show was called");
        System.out.println(appartamentType.name());
        System.out.println(mealsIncluded);
        System.out.println(portName);
        System.out.println(getclient());
        System.out.println(getnumberOfTickets());
        System.out.println(getDate().toString());
    }
    public String toString(){
        return "Ship ticket: ID: " + getId() + "; Type: " +appartamentType.name() + "; Meals included: " + String.valueOf(mealsIncluded) + "; Port name: " + portName + "; Client: " + getclient() + "; Ticket(s): " + getnumberOfTickets() + "; Date: " + date.toString();
    }
}
