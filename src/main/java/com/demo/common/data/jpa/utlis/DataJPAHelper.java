package com.demo.common.data.jpa.utlis;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

/**
 * 
 * 
 * 数据库操作辅助类
 * 
 * 
 * 池超凡
 * 
 * 2015年5月18日 上午11:02:30
 * 
 * @version 1.0.0
 *
 */
public class DataJPAHelper<T> {
    private Root<T> root;
    private CriteriaBuilder builder;
    
    
    
    public Root<T> getRoot() {
        return root;
    }


    
    public CriteriaBuilder getBuilder() {
        return builder;
    }


    public void setRoot(Root<T> root) {
        this.root = root;
    }
   
    
    public void setBuilder(CriteriaBuilder builder) {
        this.builder = builder;
    }
     
    protected Predicate equal(Expression<?> x, Object y){
        return builder.equal(x, y);
    }
    protected Predicate like(Expression<String> x, String y){
        return builder.like(x, y);
    }
    
    protected <Y extends Comparable<? super Y>>  Predicate greaterThan(Expression<? extends Y> x, Y y){
        return builder.greaterThan(x, y);
    }
    protected<Y extends Comparable<? super Y>> Predicate lessThan(Expression<? extends Y> x, Y y){
        return builder.lessThan(x, y);
    }
    protected <Y extends Comparable<? super Y>> Predicate greaterThanOrEqualTo(Expression<? extends Y> x, Y y){
        return builder.greaterThanOrEqualTo(x, y);
    }
    protected <Y extends Comparable<? super Y>> Predicate lessThanOrEqualTo(Expression<? extends Y> x, Y y){
        return builder.lessThanOrEqualTo(x, y);
    }
    protected Predicate or(Predicate... restrictions){
        return builder.or(restrictions);
    }
    protected Predicate and(Predicate... restrictions){
        return builder.and(restrictions);
    }
    @SuppressWarnings("hiding")
    protected   <T> Expression<T> get(String attributeName , Class<T> t){
      return  root.get(attributeName).as(t);
    }
    protected  <M>  SetJoin<T,M> JoinMany(String attributeName , Class<M> clasz,JoinType type){
        SetJoin<T,M>  setjoin =  root.join(root.getModel().getSet(attributeName,clasz), type);
        return setjoin;
      }
    protected  <M>   Join<T, M> JoinSingular(String attributeName , Class<M> clasz,JoinType type){
        Join<T, M>  setjoin =  root.join(root.getModel().getSingularAttribute(attributeName,clasz), type);
        return setjoin;
      }
    protected   Expression<String> getString(String attributeName){
        return  get(attributeName,String.class);
    }
    protected   Expression<Integer> getInteger(String attributeName){
        return  get(attributeName,Integer.class);
    }
    protected   Expression<Long> getLong(String attributeName){
        return  get(attributeName,Long.class);
    }
    protected   Expression<Double> getDouble(String attributeName){
        return  get(attributeName,Double.class);
    }
    protected   Expression<Boolean> getBoolean(String attributeName){
        return  get(attributeName,Boolean.class);
    }
    protected   Expression<Number> getNumber(String attributeName){
        return get(attributeName,Number.class);
    }
    protected   Expression<Date> getDate(String attributeName){
        return get(attributeName,Date.class);
    }



   



  



    
}
