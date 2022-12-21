package com.nox.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nox.model.Smoothie;
import com.nox.model.SmoothieCustomer;
import com.nox.repository.SmoothieRepository;
import com.nox.resources.CreateSmoothieRequest;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SmoothieServiceImpl implements SmoothieService {

    private final SmoothieRepository smoothieRepository;

    @Override
    public Smoothie createSmoothie(CreateSmoothieRequest request, String adminId) {
        SmoothieCustomer newCustomer;
        Optional<SmoothieCustomer> smoothieCustomer = smoothieRepository.findById(adminId);
        if (smoothieCustomer.isPresent()) {
            smoothieCustomer.get().getSmoothie()
                    .add(Smoothie.builder().smoothieId(UUID.randomUUID().toString()).smoothieName(request.getSmoothieName())
                            .smoothieNutritionValue(request.getSmoothieNutritionValue()).smoothiePrice(request.getSmoothiePrice())
                            .smoothieAddedBy(adminId).smoothieIngredients(request.getSmoothieIngredients()).build());
            smoothieRepository.save(smoothieCustomer.get());
        } else {
            newCustomer = new SmoothieCustomer();
            List<Smoothie> smoothies = new ArrayList<>();
            smoothies.add(Smoothie.builder().smoothieId(UUID.randomUUID().toString()).smoothieName(request.getSmoothieName())
                    .smoothieNutritionValue(request.getSmoothieNutritionValue()).smoothiePrice(request.getSmoothiePrice()).smoothieAddedBy(adminId)
                    .smoothieIngredients(request.getSmoothieIngredients()).build());
            newCustomer.setSmoothie(smoothies);
            newCustomer.setSmoothieCustomerId(adminId);
            smoothieRepository.insert(newCustomer);
        }
        return null;
    }

    @Override
    public List<SmoothieCustomer> retrieveAllSmoothies() {
        return smoothieRepository.findAll();
    }

    @Override
    public List<Smoothie> retrieveByAdminId(String admindId) {
        Optional<SmoothieCustomer> smoothieCustomer = smoothieRepository.findById(admindId);
        return smoothieCustomer.isPresent() ? smoothieCustomer.get().getSmoothie() : Collections.emptyList();
    }

}
