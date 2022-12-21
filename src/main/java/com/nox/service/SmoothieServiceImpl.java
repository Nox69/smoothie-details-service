package com.nox.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nox.exception.SmoothieNotFoundException;
import com.nox.model.Smoothie;
import com.nox.model.SmoothieCustomer;
import com.nox.repository.SmoothieRepository;
import com.nox.resources.CreateSmoothieRequest;
import com.nox.resources.SmoothieDetailsResponse;
import com.nox.resources.UpdateSmoothieRequest;

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
            newCustomer.setSmoothieId(adminId);
            smoothieRepository.insert(newCustomer);
        }
        return null;
    }


    @Override
    public boolean deleteSmoothie(String id, String adminId) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<SmoothieCustomer> retrieveAllSmoothies() {
        return smoothieRepository.findAll();
    }

    @Override
    public SmoothieDetailsResponse retrieveBySmoothieId(String id, String adminId) {
        return null;
    }

    @Override
    public List<Smoothie> retrieveByAdminId(String admindId) {
        return smoothieRepository.findById(admindId).get().getSmoothie();
    }

}
