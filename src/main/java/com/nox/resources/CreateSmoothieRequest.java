package com.nox.resources;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSmoothieRequest {

    @NotNull
    private String smoothieName;

    @NotNull
    private String smoothieIngredients;

    private BigDecimal smoothieNutritionValue;

    @NotNull
    private BigDecimal smoothiePrice;

}
