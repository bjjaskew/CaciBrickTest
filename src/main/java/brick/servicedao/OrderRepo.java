package brick.servicedao;

import java.util.Optional;

import brick.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepo extends MongoRepository<Order, String> {
    public Optional<Order> findById(String id);

}
