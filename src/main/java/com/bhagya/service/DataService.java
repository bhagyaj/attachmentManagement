package com.bhagya.service;

import com.bhagya.model.Data;
import com.bhagya.model.DataWrapper;

/**
 * Created by brupasinghe on 7/13/2017.
 */
public interface DataService {
    Data save (Data data);

    Data get(int id);

    DataWrapper getFile(String fileName);

    DataWrapper saveFile(DataWrapper dataWrapper);
}
