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
    @Insert(" insert into orders (order_id, client_number, driver_id, start_trip, end_trip)" +
            " values(#{orderId}, #{clientNumber}, #{driverId}, #{startTrip}, #{endTrip})")
    void saveOrder(Order order);

    @Select("SELECT * FROM taxi_drive_info where active = true and city = #{cityName} limit 1" )
    TaxiDriverInfoModel getDriver(String cityName);

    @Select("SELECT * FROM order_total" )
    List<Order> orders();

    @Update("UPDATE orders SET start_trip = now() where order_id = #{orderId}")
    void setStartTime(Long orderId);


}
