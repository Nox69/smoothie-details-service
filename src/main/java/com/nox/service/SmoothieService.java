package com.nox.service;

import java.util.List;
import java.util.Map;

import com.nox.model.Smoothie;
import com.nox.resources.CreateSmoothieRequest;
import com.nox.resources.SmoothieDetailsResponse;
import com.nox.resources.UpdateSmoothieRequest;

public interface SmoothieService {

    public SmoothieDetailsResponse createSmoothie(CreateSmoothieRequest request, Map<String, String> user);

    public List<Smoothie> retrieveByIdAndRole(Map<String, String> user);

    public SmoothieDetailsResponse updateSmoothie(UpdateSmoothieRequest request, Map<String, String> user);

}
