package com.example.digitalvideostoreapi.services;

import com.example.digitalvideostoreapi.models.BannerRepository;
import com.example.digitalvideostoreapi.models.BannerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerService {
    @Autowired
    private BannerRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    //get all banner
    public List<BannerModel> getBanners()
    {
        return repository.findAll();
    }

    //create a banner
    public void insertIntoBanners(BannerModel banner)
    {
        repository.insert(banner);
    }
}
