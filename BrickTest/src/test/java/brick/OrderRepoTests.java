package brick;

import brick.controller.OrderController;
import brick.entity.Order;
import brick.servicedao.OrderImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderRepoTests {

    //Concentrating on Controller code therefore mock OrderImpl
    @Autowired
    private MockMvc mvc;

    @MockBean
    private OrderImpl impl;

    //Stage 1
    @Test
    public void newOrder() throws  Exception {
        Order test = new Order("123");
        given (impl.newOrder("123")).willReturn(test);

        mvc.perform((post("/order/", 200)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]", is(test.getId())));
    }

    @Test
    public void getOrder() throws Exception {
        Order test = new Order("123");
        given(impl.getOrderById(test.getId())).willReturn(test);

        mvc.perform((get("/order/{id}",test.getId()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].noBricks", is(test.getNoBricks())))
                .andExpect(jsonPath("$[0].id", is(test.getId())));
    }

    @Test
    public void getAllOrders() throws Exception {

        //Tests all orders are got from DB and are unique
        Order test = new Order("123");
        Order test2 = new Order("456");
        Order test3 = new Order("1231");

        List<Order> allOrders = new ArrayList<Order>();
        allOrders.add(test);
        allOrders.add(test2);
        allOrders.add(test3);
        given(impl.getAllOrders()).willReturn(allOrders);

        mvc.perform((get("/order/"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].noBricks", is(test.getNoBricks())))
                .andExpect(jsonPath("$[0].id", is(test.getId())))
                .andExpect(jsonPath("$[1].noBricks", is(test2.getNoBricks())))
                .andExpect(jsonPath("$[1].id", is(test2.getId())))
                .andExpect(jsonPath("$[2].noBricks", is(test3.getNoBricks())))
                .andExpect(jsonPath("$[2].id", is(test3.getId())));
    }
}
