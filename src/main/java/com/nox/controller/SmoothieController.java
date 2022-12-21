package com.nox.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nox.model.Smoothie;
import com.nox.model.SmoothieCustomer;
import com.nox.resources.CreateSmoothieRequest;
import com.nox.security.JWTService;
import com.nox.service.SmoothieService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class SmoothieController {

    private final SmoothieService smoothieService;
    private final JWTService jwtService;

    @PostMapping(value = "/smoothie")
    public ResponseEntity<Smoothie> createSmoothie(@Valid @RequestBody CreateSmoothieRequest request) {
        return new ResponseEntity<>(smoothieService.createSmoothie(request, jwtService.getAuthenticatedCustomer()), HttpStatus.CREATED);
    }

    @GetMapping(value = "/smoothies")
    public ResponseEntity<List<SmoothieCustomer>> retrieveAllSmoothies() {
        return ResponseEntity.ok(smoothieService.retrieveAllSmoothies());
    }

    @GetMapping(value = "/smoothie/owner")
    public ResponseEntity<List<Smoothie>> retrieveSmoothieByAdminId(@RequestParam String adminId) {
        return ResponseEntity.ok(smoothieService.retrieveByAdminId(adminId));
    }

}
