package com.nox.resources;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSmoothieRequest {

    private String smoothieName;
    @NotNull
    private String smoothieId;
    private String smoothieIngredients;
    private BigDecimal smoothieNutritionValue;
    private BigDecimal smoothiePrice;

}
