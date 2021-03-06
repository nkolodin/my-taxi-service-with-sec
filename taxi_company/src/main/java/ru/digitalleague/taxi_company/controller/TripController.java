package ru.digitalleague.taxi_company.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.digitalleague.taxi_company.mapper.OrderMapper;
import ru.digitalleague.taxi_company.model.Order;

@RestController
public class TripController {

    private final OrderMapper orderMapper;

    public TripController(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    /**
     * При вызове контроллера передаются данные о времени начала в order
     * @param order - данные заказа, для добавления времени начала
     */
    @ApiOperation(value = "Контроллер для начала поездки")
    @PostMapping("/start_trip")
    public ResponseEntity<String> setStartTime(@RequestBody Order order){
        orderMapper.setStartTime(order.getOrderId());
        return ResponseEntity.ok("Начало поездки");
    }

    /**
     * Контроллер передает данные в талицу order об окончании заказа
     * @param order - данные заказа, для добавления времени окончания
     */
    @ApiOperation(value = "Контроллер для окончания поездки")
    @PostMapping("/end_trip")
    public ResponseEntity<String> setEndTime(@RequestBody Order order){
        orderMapper.setEndTime(order.getOrderId());
        return ResponseEntity.ok("Конец поездки");
    }
}
