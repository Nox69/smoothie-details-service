package com.nox.model;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("order")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Order {

    @Id
    private String orderId;
    private String orderBy;
    private List<Smoothie> orderDetails;
    private BigDecimal orderPrice;

}
