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

/**
 * This is a controller (interface adapter) used by the web interface. Each controller is responsible for one functionality only, following this way
 * the Single Responsibility Principle.
 *
 * The REST controllers consume and produce JSON by default.
 */
@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class SmoothieController {

    private final SmoothieService smoothieService;
    private final JWTService jwtService;

    /**
     * This method is executed when the customers perform a `POST` request to the `/smoothie` endpoint. The request's JSON body is converted into the
     * `request` param. This endpoint returns a `201 CREATED` status code to the client after successfully creating the Smoothie in DB
     * 
     */
    @PostMapping(value = "/smoothie")
    public ResponseEntity<SmoothieDetailsResponse> createSmoothie(@Valid @RequestBody CreateSmoothieRequest request) {
        return new ResponseEntity<>(smoothieService.createSmoothie(request, jwtService.getAuthenticatedCustomer()), HttpStatus.CREATED);
    }

    /**
     * This method is executed when the customers perform a `GET` request to the `/smoothies` endpoint. This retrieves the list of smoothies for the
     * loggedInUSer. This endpoint returns a `200 OK` status code to the client after successfully creating the Customer in DB
     * 
     */
    @GetMapping(value = "/smoothies")
    public ResponseEntity<List<Smoothie>> retrieveSmoothieByAdminId() {
        return ResponseEntity.ok(smoothieService.retrieveByIdAndRole(jwtService.getAuthenticatedCustomer()));
    }

    /**
     * This method is executed when the customers perform a `PUT` request to the `/smoothie` endpoint. The request's JSON body is converted into the
     * `request` param. This endpoint returns a `200 OK` status code to the client after successfully updating the Smoothie in DB
     * 
     */
    @PutMapping(value = "/smoothie")
    public ResponseEntity<SmoothieDetailsResponse> updateSmoothie(@RequestBody UpdateSmoothieRequest request) {
        return ResponseEntity.ok(smoothieService.updateSmoothie(request, jwtService.getAuthenticatedCustomer()));
    }

}
