package brick.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import brick.servicedao.*;
import brick.entity.*;
import java.util.Collection;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderImpl orderImpl;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Collection<Order> getAllStudents() {
        return orderImpl.getAllOrders();
    }

    @RequestMapping(value = "/{ID}", method = RequestMethod.GET)
    public Order getOrderById(@PathVariable String ID) {
        return orderImpl.getOrderById(ID);
    }

    @RequestMapping(value = "/{ID}", method = RequestMethod.PUT)
    public String modifyOrder(@PathVariable String ID, @RequestBody String noBricks) {
        return orderImpl.modifyOrder(ID, noBricks).getId();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String newOrder(@RequestBody String noBricks) {
        return orderImpl.newOrder(noBricks).getId();
    }
}
