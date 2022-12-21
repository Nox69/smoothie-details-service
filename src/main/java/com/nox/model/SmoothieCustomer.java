package com.nox.model;

import java.util.List;

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
public class SmoothieCustomer {

    @Id
    private String smoothieCustomerId;
    private List<Smoothie> smoothie;

}
