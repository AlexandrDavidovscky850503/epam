package App.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public abstract class Ticket implements TicketActions{
    @Id
    private String id;
    
    private String client;
    private int numberofTickets;

    LocalDate date;
    public Ticket(){}
    public Ticket(String id, String client, int numberofTickets, LocalDate date){
        this.id=id;
        this.client=client;
        this.numberofTickets=numberofTickets;
        this.date = date;
    }
    public void setInformation(String id, String client, int numberofTickets, LocalDate date){
        this.id=id;
        this.client=client;
        this.numberofTickets=numberofTickets;
        this.date = date;
    }
    public String getclient(){return client;}
    public String getId(){ return id; }
    public void  setId(String id){ this.id = id; }

    public LocalDate getDate(){
        return date;
    }
    public int getnumberofTickets(){ return numberofTickets; }
}
