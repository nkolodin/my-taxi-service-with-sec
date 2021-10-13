package ru.digitalleague.taxi_company.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.digitalleague.core.model.OrderDetails;
import ru.digitalleague.taxi_company.service.OrderServiceImpl;



@Slf4j
@Component
public class OrderListener implements MessageListener {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final OrderServiceImpl orderService;

    public OrderListener(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @SneakyThrows
    public void onMessage(Message message) {
        byte[] bytes = message.getBody();
        OrderDetails orderDetails = objectMapper.readValue(bytes, OrderDetails.class);
        log.info("Received message from rabbitmq " + orderDetails.toString());
        orderService.saveOrder(orderDetails);
    }

}
