package ru.digitalleague.taxi_company.listener;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import ru.digitalleague.core.model.OrderDetails;
import ru.digitalleague.taxi_company.controller.TripController;
import ru.digitalleague.taxi_company.model.Order;

import java.nio.charset.StandardCharsets;

@EnableRabbit
@Slf4j
public class OrderListener implements MessageListener {

    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private TripController tripController;


    public OrderListener() {
    }

    @SneakyThrows
    public void onMessage(Message message) {

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        byte[] bytes = message.getBody();
        Order order = objectMapper.readValue(bytes, Order.class);
        OrderDetails orderDetails = objectMapper.readValue(bytes, OrderDetails.class);
        /*tripController.tripStart(orderDetails);*/
        /**
         * Выводит в таком формате OrderDetails@b160696
         * Также не дает использовать методы orderDetails.getCarModel и тд
         */
        log.info("Received message from rabbitmq " + orderDetails);
        /**
         * в order подставляются значения Order(orderId=null, level=1, city=Moscow, clientNumber=1, driverId=null, startTrip=null, endTrip=null)
         */
        log.info("Received message from rabbitmq " + order);
    }

}
