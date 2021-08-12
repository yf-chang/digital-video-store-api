package com.example.digitalvideostoreapi.models;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TVRepository extends MongoRepository<TVModel, String>{
}
