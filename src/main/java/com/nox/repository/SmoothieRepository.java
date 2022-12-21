package com.nox.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nox.model.SmoothieCustomer;

@Repository
public interface SmoothieRepository extends MongoRepository<SmoothieCustomer, String> {

}
