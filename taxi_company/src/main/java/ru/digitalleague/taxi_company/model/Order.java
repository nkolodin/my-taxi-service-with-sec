package ru.digitalleague.taxi_company.model;

import lombok.Data;

import java.lang.annotation.Target;
import java.time.OffsetDateTime;

@Data
public class Order {

    /**
     * Идентификатор поездки.
     */
    private Long orderId;

    private Long level;

    private String city;

    /**
     * Идентификатор клиента.
     */
    private Long clientNumber;

    /**
     * Идентификатор водителя.
     */
    private Long driverId;

    /**
     * Дата, время начала поездки.
     */
    private OffsetDateTime startTrip;

    /**
     * Дата, время окончания поездки.
     */
    private OffsetDateTime endTrip;

}
