package com.nox.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nox.model.Smoothie;
import com.nox.resources.CreateSmoothieRequest;
import com.nox.resources.SmoothieDetailsResponse;
import com.nox.resources.UpdateSmoothieRequest;
import com.nox.security.JWTService;
import com.nox.service.SmoothieService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class SmoothieController {

    private final SmoothieService smoothieService;
    private final JWTService jwtService;

    @PostMapping(value = "/smoothie")
    public ResponseEntity<SmoothieDetailsResponse> createSmoothie(@Valid @RequestBody CreateSmoothieRequest request) {
        return new ResponseEntity<>(smoothieService.createSmoothie(request, jwtService.getAuthenticatedCustomer()), HttpStatus.CREATED);
    }

    @GetMapping(value = "/smoothies")
    public ResponseEntity<List<Smoothie>> retrieveSmoothieByAdminId() {
        return ResponseEntity.ok(smoothieService.retrieveByIdAndRole(jwtService.getAuthenticatedCustomer()));
    }

    @PutMapping(value = "/smoothie")
    public ResponseEntity<SmoothieDetailsResponse> updateSmoothie(@RequestBody UpdateSmoothieRequest request) {
        return ResponseEntity.ok(smoothieService.updateSmoothie(request, jwtService.getAuthenticatedCustomer()));
    }

}
