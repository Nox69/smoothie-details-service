package com.nox.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.nox.exception.UnAuthorizedException;
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

    private static final String ADMIN_ROLE = "business-owner";

    @Override
    public SmoothieDetailsResponse createSmoothie(CreateSmoothieRequest request, Map<String, String> user) {
        SmoothieCustomer newCustomer;
        String adminId = user.get("userId");
        String role = user.get("role");
        String id = UUID.randomUUID().toString();
        Optional<SmoothieCustomer> smoothieCustomer = smoothieRepository.findById(adminId);
        if (smoothieCustomer.isPresent()) {
            smoothieCustomer.get().getSmoothie()
                    .add(Smoothie.builder().smoothieId(id).smoothieName(request.getSmoothieName())
                            .smoothieNutritionValue(request.getSmoothieNutritionValue()).smoothiePrice(request.getSmoothiePrice())
                            .smoothieAddedBy(adminId).smoothieIngredients(request.getSmoothieIngredients()).build());
            smoothieRepository.save(smoothieCustomer.get());
        } else if (role.equals(ADMIN_ROLE)) {
            newCustomer = new SmoothieCustomer();
            List<Smoothie> smoothies = new ArrayList<>();
            smoothies.add(Smoothie.builder().smoothieId(id).smoothieName(request.getSmoothieName())
                    .smoothieNutritionValue(request.getSmoothieNutritionValue()).smoothiePrice(request.getSmoothiePrice()).smoothieAddedBy(adminId)
                    .smoothieIngredients(request.getSmoothieIngredients()).build());
            newCustomer.setSmoothie(smoothies);
            newCustomer.setSmoothieCustomerId(adminId);
            smoothieRepository.insert(newCustomer);
        } else {
            throw new UnAuthorizedException("Logged in User doesnt have authorization to add Smoothie with role :" + role);
        }
        return SmoothieDetailsResponse.builder().smoothieId(id).smoothieName(request.getSmoothieName())
                .smoothieNutritionValue(request.getSmoothieNutritionValue()).smoothieIngredients(request.getSmoothieIngredients())
                .smoothiePrice(request.getSmoothiePrice()).build();
    }

    /**
     * This method retrieves the smoothies based on the role.
     * 
     * For admin : smoothies created by admin are fetched. For users : All smoothies in db are fetched so that they can order.
     */
    @Override
    public List<Smoothie> retrieveByIdAndRole(Map<String, String> user) {
        String adminId = user.get("userId");
        String role = user.get("role");
        if (role.equals(ADMIN_ROLE)) {
            Optional<SmoothieCustomer> smoothieCustomer = smoothieRepository.findById(adminId);
            return smoothieCustomer.isPresent() ? smoothieCustomer.get().getSmoothie() : Collections.emptyList();
        } else {
            return smoothieRepository.findAll().stream().flatMap(x -> x.getSmoothie().stream()).distinct().collect(Collectors.toList());
        }
    }

    @Override
    public SmoothieDetailsResponse updateSmoothie(UpdateSmoothieRequest request, Map<String, String> user) {
        String adminId = user.get("userId");
        String role = user.get("role");
        Smoothie updatedSmoothie = null;
        if (role.equals(ADMIN_ROLE)) {
            Optional<SmoothieCustomer> smoothieCustomer = smoothieRepository.findById(adminId);
            if (smoothieCustomer.isPresent()) {
                List<Smoothie> smoothieList = smoothieCustomer.get().getSmoothie();
                Optional<Smoothie> existingSmoothie = smoothieList.stream().filter(x -> x.getSmoothieId().equals(request.getSmoothieId())).findAny();
                if (existingSmoothie.isPresent()) {
                    int index = smoothieList.indexOf(existingSmoothie.get());
                    updatedSmoothie = Smoothie.builder().smoothieId(request.getSmoothieId())
                            .smoothieName(StringUtils.isEmpty(request.getSmoothieName()) ? existingSmoothie.get().getSmoothieName()
                                    : request.getSmoothieName())
                            .smoothieIngredients(
                                    StringUtils.isEmpty(request.getSmoothieIngredients()) ? existingSmoothie.get().getSmoothieIngredients()
                                            : request.getSmoothieIngredients())
                            .smoothieNutritionValue(request.getSmoothieNutritionValue() != null ? request.getSmoothieNutritionValue()
                                    : existingSmoothie.get().getSmoothieNutritionValue())
                            .smoothiePrice(
                                    request.getSmoothiePrice() != null ? request.getSmoothiePrice() : existingSmoothie.get().getSmoothiePrice())
                            .smoothieAddedBy(adminId).build();
                    smoothieList.set(index, updatedSmoothie);
                }
                smoothieCustomer.get().setSmoothie(smoothieList);
                smoothieRepository.save(smoothieCustomer.get());
                return SmoothieDetailsResponse.builder().smoothieId(updatedSmoothie.getSmoothieId()).smoothieName(updatedSmoothie.getSmoothieName())
                        .smoothieNutritionValue(updatedSmoothie.getSmoothieNutritionValue())
                        .smoothieIngredients(updatedSmoothie.getSmoothieIngredients()).smoothiePrice(updatedSmoothie.getSmoothiePrice()).build();
            }
        } else {
            throw new UnAuthorizedException("Logged in User doesnt have authorization to edit Smoothie with role :" + role);
        }
        return null;

    }

}
