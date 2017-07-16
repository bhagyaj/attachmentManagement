package com.bhagya.repository.impl;

import com.bhagya.model.Data;
import com.bhagya.model.DataWrapper;
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
    @Override
    public Data get(int id) {
        System.out.println(id);
        return entityManager.find(Data.class,id);
    }

    @Override
    public DataWrapper getFile(String fileName) {
        return entityManager.find(DataWrapper.class,fileName);
    }

    @Override
    public DataWrapper saveFile(DataWrapper dataWrapper) {
        if(String.valueOf(dataWrapper.getFileName())==null){
            entityManager.persist(dataWrapper);
        }else {
            entityManager.merge(dataWrapper);
        }
        entityManager.flush();
        return dataWrapper;
    }
}
