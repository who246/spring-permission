package com.demo.web.back.sys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.repository.BaseRepository;
import com.demo.web.back.sys.entity.Menu;
import com.demo.web.back.sys.enums.MenuType;

 

@Repository
public interface MenuRepository extends BaseRepository<Menu,Long> {

    public List<Menu> findByType(MenuType type);
    public List<Menu> findByPid(long pid);
}
