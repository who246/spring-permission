package com.test.sys;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.demo.WebAppConfig;
import com.demo.common.data.jpa.utlis.DynamicSpecification;
import com.demo.common.data.jpa.utlis.DynamicSpecifications;
import com.demo.common.data.jpa.utlis.SearchFilter;
import com.demo.web.back.sys.entity.Menu;
import com.demo.web.back.sys.entity.User;
import com.demo.web.back.sys.repository.UserRepository;
import com.demo.web.back.sys.service.MenuService;
import com.demo.web.back.sys.service.UserService;

 

@SpringApplicationConfiguration(classes = WebAppConfig.class) 
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ActiveProfiles("junit")
public class UserTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MenuService menuService;
    @Test
    public void addUser() throws InterruptedException{

       com.demo.web.back.sys.entity.User user = userRepository.findOne(16l);
       user.setPassword("123456");
     //  userService.encodePassword(user);
        userService.changePassword(user);

    }
    @Test
    public void getUserMenus() throws InterruptedException{
        Set<Menu> root = menuService.getUserMenus(null);
        for (Menu menu : root) {
            System.out.println(menu);
        }
    }
    @Test
    public void findAll(){
//           Map<String, Object> params = new HashMap<String, Object>();
//            params.put("EQ_username", "foo");
//            params.put("EQ_password", "foo");
//            com.demo.web.back.sys.entity.User user = new com.demo.web.back.sys.entity.User();
            
//            Map<String, SearchFilter> filters = SearchFilter.parse(params);
            Specification<com.demo.web.back.sys.entity.User> spe = new DynamicSpecification<com.demo.web.back.sys.entity.User>(){

                @Override
                public Collection<? extends Predicate> addCondition(Root root,CriteriaQuery<?> query, CriteriaBuilder builder) {
                   
                    List<Predicate> predicates = new ArrayList<Predicate>();
                    Predicate pr = or(equal(getString("username"),"admin"),equal(getString("password"), "123"));
                    predicates.add(equal(getString("salt"),"123"));
                    predicates.add(pr);
                    return predicates;
                }
                
            };
            userRepository.findAll(spe);
    }
    @Test
    public void login() throws InterruptedException{

  
        userService.login("admin","123456");

    }
    @Test
    public void searchBydate(){
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("EQ_username", "sad");
      params.put("EQ_createDate", "2014-09-22 22:01:11");
      Map<String, SearchFilter> filters = SearchFilter.parse(params);
      Specification<com.demo.web.back.sys.entity.User> spe =  DynamicSpecifications.bySearchFilter(filters.values());
      userRepository.findAll(spe);
    }
    @Test
    public void findOne(){
        User user =  userRepository.findOne(14l);
      // User user =  userRepository.getOne(14l);
       System.out.println("延迟");
       System.out.println(user.getUsername());
    }
}
