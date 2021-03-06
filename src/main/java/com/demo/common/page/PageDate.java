package com.demo.common.page;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;


public class PageDate {
    
    private Page page = new Page();
    
    public Page getPage() {
        return page;
    }
    
    public void setPage(Page page) {
        this.page = page;
    }
    
    public PageRequest toPageRequest() {
        if (StringUtils.isEmpty(page.getDirection()) || StringUtils.isEmpty(page.getSort()))
            return new PageRequest(page.getPn() - 1, page.getSize());
        Sort sort = null;
        if (page.getDirection().toLowerCase().equals("desc")) {
            sort = new Sort(Direction.DESC, page.getSort());
        } else if (page.getDirection().toLowerCase().equals("asc")) {
            sort = new Sort(Direction.ASC, page.getSort());
        } else {
            sort = new Sort(Direction.ASC, page.getSort());
            return new PageRequest(page.getPn() - 1, page.getSize());
        }
        return new PageRequest(page.getPn() - 1, page.getSize(), sort);
    }
}
