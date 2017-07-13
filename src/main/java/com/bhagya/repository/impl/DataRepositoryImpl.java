package com.bhagya.repository.impl;

import com.bhagya.model.Data;
import com.bhagya.repository.DataRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by brupasinghe on 7/13/2017.
 */
@Repository
public class DataRepositoryImpl implements DataRepository {
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public Data save(Data data) {
        if(Integer.valueOf(data.getId())==null){
            entityManager.persist(data);
        }else {
            entityManager.merge(data);
        }
        entityManager.flush();
        return data;
    }
}
