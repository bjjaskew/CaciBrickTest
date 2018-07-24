package brick.entity;

import org.springframework.data.annotation.Id;


public class Order {

    @Id
    public String id;
    public String noBricks;


    public Order() {}

    public String getId() {
        return id;
    }

    public Order(String noBricks) {
        this.noBricks = noBricks;
    }

    public String getNoBricks() {
        return noBricks;
    }

    public void setNoBricks(String noBricks) {
        this.noBricks = noBricks;
    }

    @Override
    public String toString() {
        return String.format(
                "Order[id=%s, noBricks='%s']",
                id, noBricks);
    }
}



