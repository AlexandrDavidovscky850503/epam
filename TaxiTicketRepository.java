package App.database;

import App.model.TaxiTicket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

import java.util.Optional;

@Service
public interface TaxiTicketRepository extends CrudRepository<TaxiTicket, String>{
    @Override
    @Cacheable(value = "Taxi Ticket")
    Optional<TaxiTicket> findById(String id);
}
