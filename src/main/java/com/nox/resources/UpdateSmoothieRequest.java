package com.nox.resources;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSmoothieRequest {

    private String smoothieName;
    private String smoothieId;
    private String smoothieIngredients;
    private BigDecimal smoothieNutritionValue;
    private BigDecimal smoothiePrice;

}
