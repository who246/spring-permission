
package com.demo.common.data.jpa.utlis;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

/**
 * 
 * 
 * 表操作工具类
 * 
 * 
 * 
 * 
 * 2015年5月17日 下午6:33:22
 * 
 * @version 1.0.0
 *
 */
public abstract class DynamicSpecification<T>  extends DataJPAHelper<T>  implements Specification<T>{
    

    private List<Predicate> predicates = new ArrayList<Predicate>();
    
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        this.setRoot( root);
        this.setBuilder(builder);
        Collection<? extends Predicate>  pc =   addCondition(root, query , builder);
       
        if(pc != null &&  pc.size() > 0)
        predicates.addAll(pc);
        if (!predicates.isEmpty())
        return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        return builder.conjunction();
    }
    public Collection<? extends Predicate> bySearchFilter(final Collection<SearchFilter> filters ) {
        return  bySearchFilter( filters,null) ;
    }
   
    protected Collection<? extends Predicate> bySearchFilter(final Collection<SearchFilter> filters,final String fliterPrefix){
        if (filters!=null&&!filters.isEmpty()) {
            
            List<Predicate> predicates = new ArrayList<Predicate>();
            for (SearchFilter filter : filters) {
                // 
                String[] names = StringUtils.split(filter.fieldName, SearchFilter.ALIAS);
                if(StringUtils.isNotEmpty(fliterPrefix) && fliterPrefix.equals(names[0])){
                    continue;
                }
                Path expression = getRoot().get(names[0]);
                for (int i = 1; i < names.length; i++) {
                    expression = expression.get(names[i]);
                }

                // logic operator
                switch (filter.operator) {
                case EQ:
                    predicates.add(equal(expression, filter.value));
                    
                    break;
                case LIKE:
                    predicates.add(like(expression, "%" + filter.value + "%"));
                    break;
                case GT:
                    predicates.add(greaterThan(expression, (Comparable) filter.value));
                    break;
                case LT:
                    predicates.add(lessThan(expression, (Comparable) filter.value));
                    break;
                case GTE:
                    predicates.add(greaterThanOrEqualTo(expression, (Comparable) filter.value));
                    break;
                case LTE:
                    predicates.add(lessThanOrEqualTo(expression, (Comparable) filter.value));
                    break;
                }
            }

            return predicates;
        }
        return Collections.emptyList();
    }
    public  abstract  Collection<? extends Predicate> addCondition(Root root,CriteriaQuery<?> query,CriteriaBuilder builder);
	
}
