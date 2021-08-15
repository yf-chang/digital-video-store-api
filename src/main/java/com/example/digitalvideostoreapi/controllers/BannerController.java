package com.example.digitalvideostoreapi.controllers;

import com.example.digitalvideostoreapi.CustomizedResponse;
import com.example.digitalvideostoreapi.models.BannerModel;
import com.example.digitalvideostoreapi.services.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "https://digital-video-store-frontend.herokuapp.com/")
@RestController
public class BannerController {
    @Autowired
    private BannerService service;

    //get all banners
    @GetMapping("/banners")
    public ResponseEntity getBanners()
    {
        return new ResponseEntity(new CustomizedResponse(" A list of all banners" , service.getBanners()), HttpStatus.OK);
    }

    //create a banner
    @PostMapping(value = "/banners", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity addBanner(@RequestBody BannerModel banner)
    {
        service.insertIntoBanners(banner);
        return new ResponseEntity(banner, HttpStatus.OK);
    }
}
