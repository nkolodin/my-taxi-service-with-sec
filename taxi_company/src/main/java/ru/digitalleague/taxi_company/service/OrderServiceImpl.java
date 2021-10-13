package ru.digitalleague.taxi_company.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.digitalleague.core.model.OrderDetails;
import ru.digitalleague.taxi_company.api.OrderService;
import ru.digitalleague.taxi_company.mapper.OrderMapper;
import ru.digitalleague.taxi_company.model.Order;

/**
 * Сервис обработки заказов.
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void selectDriver(String city) {
        System.out.println(orderMapper.getDriver(city));
    }

    @Override
    public void save(Order order) {
        orderMapper.saveOrder(order);
        System.out.println("Order saved");
    }

    /**
     *Сохранение заказа и выставление водилея неактивен
     *
     * @param orderDetails - параметры заказа
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void saveOrder(OrderDetails orderDetails){
        Order order = new Order();
        order.setDriverId(findDriver(orderDetails));
        order.setClientNumber(orderDetails.getClientNumber());
        orderMapper.saveOrder(order);
        orderMapper.setDriverInactive(order.getDriverId());
    }

    /**
     * Поиск водителя
     * @param orderDetails - детали поездки для получения города и поиску водителя по городу
     * @return - возвращение id водителя
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Long findDriver(OrderDetails orderDetails){
        return orderMapper.getDriver(orderDetails.getCity());
    }


}
