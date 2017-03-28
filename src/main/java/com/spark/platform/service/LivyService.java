package com.spark.platform.service;

import com.spark.platform.model.Info;
import org.springframework.stereotype.Service;



@Service
public class LivyService {

    public Info getSession() {
        return new Info("0","",null);
    }

}
