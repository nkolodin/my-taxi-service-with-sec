package ru.digitalleague.taxi_company.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import ru.digitalleague.taxi_company.model.Order;
import ru.digitalleague.taxi_company.model.TaxiDriverInfoModel;

import java.util.List;

@Repository
@Mapper
public interface OrderMapper {

    /**
     * Сохранить заказ.
     *
     * @param order информация о заказе.
     */
    @Insert(" insert into orders (client_number, driver_id, start_trip, end_trip)" +
            " values( #{clientNumber}, #{driverId}, #{startTrip}, #{endTrip})")
    void saveOrder(Order order);

    @Select("SELECT * FROM order_total" )
    List<Order> orders();

    /**
     * Поиск первого свободного водителя по городу
     * @param cityName - город поиска
     * @return - id водителя
     */
    @Select("SELECT driver_id FROM taxi_drive_info where active = true and city = #{cityName} limit 1" )
    Long getDriver(String cityName);

    /**
     * Обновление записи в таблицу order и добавление времени начала по номеру заказа
     * @param orderId - id заказа
     */
    @Update("UPDATE orders SET start_trip = now() where order_id = #{orderId}")
    void setStartTime(Long orderId);

    /**
     * Обновление записи в таблицу order и добавление времени окончания по номеру заказа
     * @param orderId - id заказа
     */
    @Update("UPDATE orders SET end_trip = now() where order_id = #{orderId}")
    void setEndTime(Long orderId);

    /**
     * Выставление значения водитель занят
     * @param orderId
     */
    @Update("UPDATE taxi_drive_info SET active = false where driver_id = #{driverId}")
    void setDriverInactive(Long orderId);
}
