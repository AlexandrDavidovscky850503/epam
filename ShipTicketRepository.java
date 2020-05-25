package App.database;

import App.model.ShipTicket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

import java.util.Optional;

@Service
public interface ShipTicketRepository extends CrudRepository<ShipTicket, String>{
    @Override
    @Cacheable(value = "Ship Ticket")
    Optional<ShipTicket> findById(String id);
}
