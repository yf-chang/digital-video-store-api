package com.example.digitalvideostoreapi.services;

import com.example.digitalvideostoreapi.models.MovieModel;
import com.example.digitalvideostoreapi.models.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService
{

    @Autowired
    private MovieRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;


    //get all movie
    public List<MovieModel> getMovies()
    {
        return repository.findAll();
    }


    //create a movie
    public void insertIntoMovies(MovieModel movie)
    {
        repository.insert(movie);
    }

    //get a movie by id
    public Optional<MovieModel> getAMovie(String id) throws Exception
    {
        Optional<MovieModel> movie = repository.findById(id);
        if (!movie.isPresent())
        {
            throw new Exception (" Movie with " + id + " is not found ");
        }
        return movie;
    }

    //get a movie by title
    public List<MovieModel> getMoviesWithTitle(String t)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("title" ).is(t));
        List<MovieModel> movies = mongoTemplate.find(query, MovieModel.class);
        return movies;
    }

    //get featured movies
    public List<MovieModel> getFeaturedMovies()
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("featured" ).is(true));
        List<MovieModel> movies = mongoTemplate.find(query, MovieModel.class);
        return movies;
    }


    //update a movie
    public MovieModel editMovie(String id, MovieModel newMovieData)
    {
        Optional<MovieModel> movie = repository.findById(id);

        movie.get().setId(newMovieData.getId());
        movie.get().setTitle(newMovieData.getTitle());
        movie.get().setSynopsis(newMovieData.getSynopsis());
        movie.get().setCategory(newMovieData.getCategory());
        movie.get().setFeatured(newMovieData.getFeatured());
        movie.get().setSmall_poster(newMovieData.getSmall_poster());
        movie.get().setLarge_poster(newMovieData.getLarge_poster());
        movie.get().setRent_price(newMovieData.getRent_price());
        movie.get().setPurchase_price(newMovieData.getPurchase_price());

        MovieModel updateMovie = repository.save(movie.get());

        return updateMovie;
    }



    //delete a movie by id
    public void deleteAMovie(String id) throws Exception
    {
        Optional<MovieModel> movie = repository.findById(id);
        if (!movie.isPresent())
        {
            throw new Exception (" Movie with " + id + " is not found ");
        }
        repository.deleteById(id);
    }

}

