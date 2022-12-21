package com.nox.service;

import java.util.List;

import com.nox.model.Smoothie;
import com.nox.model.SmoothieCustomer;
import com.nox.resources.CreateSmoothieRequest;
import com.nox.resources.SmoothieDetailsResponse;

public interface SmoothieService {

    public Smoothie createSmoothie(CreateSmoothieRequest request, String adminId);

    public boolean deleteSmoothie(String id, String adminId);

    public List<SmoothieCustomer> retrieveAllSmoothies();

    public SmoothieDetailsResponse retrieveBySmoothieId(String id, String adminId);

    public List<Smoothie> retrieveByAdminId(String adminId);

}
