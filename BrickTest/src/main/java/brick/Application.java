package brick;

import brick.servicedao.OrderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application { //implements CommandLineRunner{


    //rivate OrderRepo repository;

    @Autowired
    public OrderImpl orderdao;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /*@Override
    public void run(String... args) throws Exception {

        for (Order customer : orderdao.getAllOrders()) {
            System.out.println(customer);
        }

        Order ordid = orderdao.getOrderById("5b54844c002c0838b0fc8be0");
        System.out.println(ordid);
    }*/
}
