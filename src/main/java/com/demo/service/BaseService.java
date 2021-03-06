package com.demo.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import com.demo.common.data.jpa.utlis.DynamicSpecifications;
import com.demo.common.data.jpa.utlis.SearchFilter;
import com.demo.common.page.PageDate;
import com.demo.common.utils.ReflectUtils;
import com.demo.entity.BaseEntity;
import com.demo.repository.BaseRepository;

@Transactional
public abstract class  BaseService<M extends BaseEntity<ID>,ID extends Serializable> {
    
    private BaseRepository<M,ID> baseRepository ;  
   // public abstract JpaRepository<M,ID> getJpaRepository();
    
    public void save(M o){
        getBaseRepository().save(o);
    }
    
    public Page<M> list(Map<String, Object> searchParams, PageDate pageDate) {
        Specification<M> spe=  bulidSearch(searchParams);
        return getBaseRepository().findAll(spe, pageDate.toPageRequest());
    }
    
    public BaseRepository<M, ID> getBaseRepository() {
        return baseRepository;
    }
    public <T> T getBaseRepository(Class<T> t) {
        return (T)baseRepository;
    }

    @Autowired
    public void setBaseRepository(BaseRepository<M, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }
    

    public void update(M o){
        
        getBaseRepository().save(o);
    }
    //只更新不为null的
    public void updateNotNull(M o){
       M o2 = getBaseRepository().findOne(o.getId());
        try {
            ReflectUtils.setValueIfNotNull(o2, o, o.getClass());
            getBaseRepository().save(o2);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        
    }
    public Specification<M> bulidSearch( Map<String, Object> searchParams){
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        
        Specification<M> spec = DynamicSpecifications.bySearchFilter(filters.values());
        return spec;
    }
    public void deleteBatch(ID[] ids){
        for (ID id : ids) {
            delete(id);
        }
    }
    public void delete(ID id) {
        baseRepository.delete(id);
    }
    
    public M findOne(ID id) {
        return baseRepository.findOne(id);
    }
    public List<M> findAll() {
        return  baseRepository.findAll();
    }
}
