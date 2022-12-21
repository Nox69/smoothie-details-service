package com.nox.service;

import java.util.List;

import com.nox.model.Smoothie;
import com.nox.model.SmoothieCustomer;
import com.nox.resources.CreateSmoothieRequest;

public interface SmoothieService {

    public Smoothie createSmoothie(CreateSmoothieRequest request, String adminId);

    public List<SmoothieCustomer> retrieveAllSmoothies();

    public List<Smoothie> retrieveByAdminId(String adminId);

}
