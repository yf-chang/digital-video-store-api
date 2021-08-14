package com.example.digitalvideostoreapi.controllers;

import com.example.digitalvideostoreapi.CustomizedResponse;
import com.example.digitalvideostoreapi.models.TVModel;
import com.example.digitalvideostoreapi.services.TVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@CrossOrigin(origins = "https://github.com/yf-chang/Digital-Video-Store.git")
@RestController
public class TVController {
    @Autowired
    private TVService service;

    //get all tvs
    @GetMapping("/tvs")
    public ResponseEntity getTVs()
    {
        var customizedResponse = new CustomizedResponse(" A list of TVs" , service.getTVs());
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    //get tv by id
    @GetMapping("/tvs/{id}")
    public ResponseEntity getATV(@PathVariable("id") String id) {

        CustomizedResponse customizedResponse = null;
        try {
            customizedResponse = new CustomizedResponse(" TV with id " + id , Collections.singletonList(service.getATV(id)));
        } catch (Exception e) {
            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    //delete a tv
    @DeleteMapping("/tvs/{id}")
    public ResponseEntity deleteATV(@PathVariable("id") String id)
    {
        CustomizedResponse customizedResponse = null;
        try{
            service.deleteATV(id);
        } catch (Exception e){
            customizedResponse = new CustomizedResponse(e.getMessage());
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(HttpStatus.OK);
    }


    //get tv by title
    @GetMapping("/tvs/title")
    public ResponseEntity getTVsByTitle(@RequestParam(value = "name") String t)
    {
        var customizedResponse = new CustomizedResponse(" A list of TVs with the title : " + t, service.getTVsWithTitle(t));
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }


    //get featured tvs
    @GetMapping("/tvs/featured")
    public ResponseEntity getFeaturedTVs()
    {
        var customizedResponse = new CustomizedResponse(" A list of featured TVs : ", service.getFeaturedTVs());
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }


    //create a tv
    @PostMapping(value = "/tvs", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity addTV(@RequestBody TVModel tv)
    {
        service.insertIntoTVs(tv);
        return new ResponseEntity(tv, HttpStatus.OK);
    }


    //update a tv
    @PutMapping(value = "/tvs/{id}", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity editTV(@PathVariable("id") String id, @RequestBody TVModel newTV)
    {
        var customizedResponse = new CustomizedResponse(" TV with ID:  " + id + " was updated successfully " , Collections.singletonList(service.editTV(id, newTV)));
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

}
