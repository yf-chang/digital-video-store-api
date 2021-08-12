package com.example.digitalvideostoreapi.services;

import com.example.digitalvideostoreapi.models.TVModel;
import com.example.digitalvideostoreapi.models.TVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TVService {

    @Autowired
    private TVRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    //get all tv
    public List<TVModel> getTVs()
    {
        return repository.findAll();
    }

    //create a tv
    public void insertIntoTVs(TVModel tv)
    {
        repository.insert(tv);
    }

    //get a tv by id
    public Optional<TVModel> getATV(String id) throws Exception
    {
        Optional<TVModel> tv = repository.findById(id);
        if (!tv.isPresent())
        {
            throw new Exception (" TV with " + id + " is not found ");
        }
        return tv;
    }

    //get a tv by title
    public List<TVModel> getTVsWithTitle(String t)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("title" ).is(t));
        List<TVModel> tvs = mongoTemplate.find(query, TVModel.class);
        return tvs;
    }


    //get featured tvs
    public List<TVModel> getFeaturedTVs()
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("featured" ).is(true));
        List<TVModel> tvs = mongoTemplate.find(query, TVModel.class);
        return tvs;
    }


    //update a tv
    public TVModel editTV(String id, TVModel newTVData)
    {
        Optional<TVModel> tv = repository.findById(id);

        tv.get().setId(newTVData.getId());
        tv.get().setTitle(newTVData.getTitle());
        tv.get().setEpisode(newTVData.getEpisode());
        tv.get().setSynopsis(newTVData.getSynopsis());
        tv.get().setCategory(newTVData.getCategory());
        tv.get().setFeatured(newTVData.getFeatured());
        tv.get().setSmall_poster(newTVData.getSmall_poster());
        tv.get().setLarge_poster(newTVData.getLarge_poster());
        tv.get().setRent_price(newTVData.getRent_price());
        tv.get().setPurchase_price(newTVData.getPurchase_price());

        TVModel updateTV = repository.save(tv.get());

        return updateTV;
    }


    //delete a tv by id
    public void deleteATV(String id) throws  Exception
    {
        Optional<TVModel> tv = repository.findById(id);
        if (!tv.isPresent())
        {
            throw new Exception (" TV with " + id + " is not found ");
        }
        repository.deleteById(id);
    }
}
