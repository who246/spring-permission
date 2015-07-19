package com.test.repository.imp;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.test.entitiy.UserEntity;

 
public class UserRepositoryImpl {
    private int i;
    public UserRepositoryImpl(){
        System.out.println("UserRepositoryImpl 构造方法");
    }
    @PostConstruct
    public void init(){
        System.out.println("UserRepositoryImpl 初始化");
        System.out.println(em == null);
    }
    @PreDestroy
    public void destroy(){
        System.out.println("UserRepositoryImpl 销毁");
    }
    @PersistenceContext
    private EntityManager em;
    public UserEntity findByUserName5(String username){
        System.out.println("调用："+i++);
        String hql = "select o from UserEntity o where  o.userName = :username";
        Query q  = em.createQuery(hql);
        q.setParameter("username", username);
        
        return (UserEntity) q.getSingleResult();
    }
    public UserEntity findByUserName6(String username){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
        Root<UserEntity> root =  cq.from(UserEntity.class);
        Predicate p1 = cb.equal(root.get("userName"), username);
        cq.where(p1);
        return em.createQuery(cq).getSingleResult();
    }
    public  void main(String[] args) {
//        em.getCriteriaBuilder().createQuery(resultClass);
        BigDecimal a = new BigDecimal("1.20111117");
        BigDecimal b = a.multiply(new BigDecimal("10000"));
        
       System.out.println(b.setScale(2, RoundingMode.HALF_UP));
    }
    
}
