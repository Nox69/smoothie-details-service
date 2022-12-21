package com.nox.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Document("smoothie")
public class Smoothie {

    @Id
    private String smoothieId;
    private String smoothieName;
    private String smoothieAddedBy;
    private String smoothieIngredients;
    private BigDecimal smoothieNutritionValue;
    private BigDecimal smoothiePrice;

}
