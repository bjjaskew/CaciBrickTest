package brick.servicedao;

import brick.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public class OrderImpl {

    @Autowired
    private OrderRepo repository;

    public Collection<Order> getAllOrders() {
        return this.repository.findAll();
    }

    public Order getOrderById(String id) {
        Optional<Order> order = this.repository.findById(id);
            if (order.isPresent()) {
                return
                    order.get();
        } else {
            return null;
        }
    }

    public Order newOrder(String noBricks) {
        Order newOrder = new Order(noBricks);
        this.repository.save(newOrder);
        return newOrder;
    }

    public Order modifyOrder(String Id, String noBricks) {
        Order order = getOrderById(Id);
        order.setNoBricks(noBricks);
        this.repository.save(order);
        return order;
    }



}
