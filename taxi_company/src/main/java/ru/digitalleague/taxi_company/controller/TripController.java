package ru.digitalleague.taxi_company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.digitalleague.core.model.OrderDetails;
import ru.digitalleague.taxi_company.mapper.OrderMapper;
import ru.digitalleague.taxi_company.model.Order;
import ru.digitalleague.taxi_company.service.OrderServiceImpl;

@RestController
public class TripController {

    @Autowired
    final private OrderMapper orderMapper;
    final private OrderServiceImpl orderService;

    public TripController(OrderMapper orderMapper, OrderServiceImpl orderService) {
        this.orderMapper = orderMapper;
        this.orderService = orderService;
    }


    public void tripStart(Order order){
        orderMapper.saveOrder(order);
        /*orderMapper.orders().toString();*/

        /*orderService.selectDriver();*/
       /* orderMapper.setStartTime(1L);*/
    }

}
