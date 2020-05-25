package App.database;

import App.model.AirTicket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

import java.util.Optional;

@Service
public interface AirTicketRepository extends CrudRepository<AirTicket, String>{
    @Override
    @Cacheable(value = "Air Ticket")
    Optional<AirTicket> findById(String id);
}
