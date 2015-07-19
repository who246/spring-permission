package com.demo.web.back.download.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

 

@Repository

public class TaskRepositoryImpl  {
    
    @PersistenceContext
    private EntityManager em;
    public void clear(){
        em.clear();
     
    }
}
