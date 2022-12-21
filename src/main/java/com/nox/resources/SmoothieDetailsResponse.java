package com.nox.resources;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmoothieDetailsResponse {

    private String smoothieId;
    private String smoothieName;
    private String smoothieAddedBy;
    private String smoothieIngredients;
    private BigDecimal smoothieNutritionValue;
    private BigDecimal smoothiePrice;

}
