package com.demo.common.data.jpa.utlis;

import java.util.Collection;
import java.util.List;


/**
 * 
 * 
 * SearchFilter集合用来收集SearchFilter
 * 
 * 
 * 池超凡
 * 
 * 2015年5月12日 上午9:50:19
 * 
 * @version 1.0.0
 *
 */

public class SearchCollection {
    
    private List<SearchFilter> filters;
    
    private SearchCollection() {
    }
    
    public static SearchCollection create(SearchFilter... searchFilters) {
        SearchCollection se = new SearchCollection();
        for (SearchFilter searchFilter : searchFilters) {
            se.add(searchFilter);
        }
        return se;
    }
    
    public SearchCollection add(SearchFilter searchFilters) {
        filters.add(searchFilters);
        return this;
    }
    
    public Collection<SearchFilter> values() {
        return filters;
    }
    
}
