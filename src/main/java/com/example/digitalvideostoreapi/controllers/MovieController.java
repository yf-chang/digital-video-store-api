package com.example.digitalvideostoreapi.controllers;

import com.example.digitalvideostoreapi.CustomizedResponse;
import com.example.digitalvideostoreapi.models.MovieModel;
import com.example.digitalvideostoreapi.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@CrossOrigin(origins = "hhttps://github.com/yf-chang/Digital-Video-Store.git")
@RestController
public class MovieController {
    @Autowired
    private MovieService service;

    //get all movies
    @GetMapping("/movies")
    public ResponseEntity getMovies()
    {

        return new ResponseEntity(new CustomizedResponse(" A list of all movies" , service.getMovies()), HttpStatus.OK);
    }

    //get movie by id
    @GetMapping("/movies/{id}")
    public ResponseEntity getAMovie(@PathVariable("id") String id)
    {
        CustomizedResponse customizedResponse = null;
        try {
            customizedResponse = new CustomizedResponse(" Movie with id " + id , Collections.singletonList(service.getAMovie(id)));
        } catch (Exception e) {
            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customizedResponse, HttpStatus.OK);

    }

    //delete movie by id
    @DeleteMapping("/movies/{id}")
    public ResponseEntity deleteAMovie(@PathVariable("id") String id)
    {
        CustomizedResponse customizedResponse = null;
        try{
            service.deleteAMovie(id);
        } catch (Exception e){
            customizedResponse = new CustomizedResponse(e.getMessage());
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    //get movie by title
    @GetMapping("/movies/title")
    public ResponseEntity getMoviesByTitle(@RequestParam(value = "name") String t)
    {

        return new ResponseEntity(new CustomizedResponse(" A list of movies with the title : " + t, service.getMoviesWithTitle(t)), HttpStatus.OK);
    }


    //get featured movie
    @GetMapping("/movies/featured")
    public ResponseEntity getFeaturedMovies()
    {
        return new ResponseEntity(new CustomizedResponse(" A list of featured movies : ", service.getFeaturedMovies()), HttpStatus.OK);
    }


    //create a movie
    @PostMapping(value = "/movies", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity addMovie(@RequestBody MovieModel movie)
    {
        service.insertIntoMovies(movie);
        return new ResponseEntity(movie, HttpStatus.OK);
    }


    //update a movie
    @PutMapping(value = "/movies/{id}", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity editMovie(@PathVariable("id") String id, @RequestBody MovieModel newMovie )
    {
        return new ResponseEntity(new CustomizedResponse(" Movie with ID:  " + id + "was updated successfully " , Collections.singletonList(service.editMovie(id, newMovie))), HttpStatus.OK);
    }
}
