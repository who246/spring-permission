package com.demo.web.back.sys.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.demo.web.back.sys.entity.Notice;


 


public class NoticeRepositoryImpl  {
    @PersistenceContext
    private EntityManager em;
    public Notice getOneNotice(){
        String jql = "select n from Notice n order by createDate desc"; 
        Query q =em.createQuery(jql);
        q.setFirstResult(0);
        q.setMaxResults(1);
        try{
        return (Notice) q.getSingleResult();
        }catch(NoResultException e){
        
        return null;
        }
    }
}
