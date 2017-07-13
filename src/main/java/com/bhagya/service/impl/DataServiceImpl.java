package com.bhagya.service.impl;

import com.bhagya.model.Data;
import com.bhagya.repository.DataRepository;
import com.bhagya.service.DataService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by brupasinghe on 7/13/2017.
 */
@Service
@Transactional
public class DataServiceImpl implements DataService {
    @Autowired
    DataRepository dataRepository;
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public Data save(Data data) {
        return dataRepository.save(data);
    }
    public Data get(int id){
        return dataRepository.get(id);
    }
}
