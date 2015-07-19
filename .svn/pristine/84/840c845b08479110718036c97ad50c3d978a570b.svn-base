package com.test.repository;
//package com.demo.repository;
//
//
//import java.util.Date;
//import java.util.List;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
//import org.springframework.context.annotation.Scope;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.domain.Sort.Direction;
//import org.springframework.data.domain.Sort.Order;
//import org.springframework.stereotype.Controller;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.ui.Model;
//import org.springframework.validation.Errors;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.demo.entity.AddressEntity;
//import com.demo.entity.ErrorMsg;
//import com.demo.entity.UserEntity;
//import com.demo.repository.UserRepository;
//@Scope("prototype")
//@Controller()
//@RequestMapping(value={"/user"})
//
//public class UserController {
//    @Value("${addressEntity.address:222}")
//    private String address;
//    
//    @Autowired
//    private UserRepository userRepository;
//    private int i;
////    @DeclareParents(
////        value="com.test.YinruTest",
////        defaultImpl=com.test.impl.YinruTestImpl.class)
////    private YinruTest yinru;
//    @RequestMapping(value={" ", "/", "/index"})
//    public String index(Model mode){
//        mode.addAttribute("hello");
//        return "index.jsp";
//    }
//  //http://127.0.0.1:8080/user/getOneUser/123213
//    @ResponseBody
//    @RequestMapping(value={ "/getOneUser/{id}"})
//    public  UserEntity getOneUser(@PathVariable("id") Long id){
//        System.out.println("searchId:"+id);
//        return userRepository.findOne(id);
//    }
//    //http://127.0.0.1:8080/user/add?userName=admin&password=123456&sex=2&tel=15058010692&addresses[0].city=瑞安&addresses[0].province=浙江
//    @ResponseBody
//    @RequestMapping(value={ "/add"})
//    public  UserEntity add( UserEntity userEntity){
//        userEntity.setUpdateDate(new Date());
//        userEntity.setCreateDate(new Date());
//        List<AddressEntity> addresses =userEntity.getAddresses();
//        for (AddressEntity addressEntity : addresses) {
//            addressEntity.setUser(userEntity);
//        }
//        userRepository.save(userEntity);
//        
//        return userEntity;
//    }
//    //http://127.0.0.1:8080/user/page?page=0&size=2
//    @ResponseBody
//    @RequestMapping(value={ "/page"})
//    public  List<UserEntity> page( int page, int size){
//        
//        Page<UserEntity> p =  userRepository.findAll(new PageRequest(page,size,new Sort(new
//            Order(Direction.DESC,"id"))));
//            
//     
//        return p.getContent();
//    }
//  //http://127.0.0.1:8080/user/findByUserName/admin
//    @ResponseBody
//    @RequestMapping(value={ "/findByUserName/{username}"})
//    public  UserEntity findByUserName( @PathVariable String username){
//        
//        UserEntity userEntity =  userRepository.findByUserName(username,new Sort(new
//            Order(Direction.DESC,"id")));
//            
//     
//        return userEntity;
//    }
//    //http://127.0.0.1:8080/user/findByUserName/admin/4
//    @ResponseBody
//    @RequestMapping(value={ "/findByUserName/{username}/{id}"})
//    public  UserEntity findByUserName( @PathVariable String username,@PathVariable long id){
//        
//        UserEntity userEntity =  userRepository.findByUserNameAndId(username,id,new Sort(new
//            Order(Direction.DESC,"id")));
//            
//        
//        return userEntity;
//    } 
//    //http://127.0.0.1:8080/user/findByUserName1/admin
//   
//    @ResponseBody
//    @RequestMapping(value={ "/findByUserName1/{username}"})
//    public  UserEntity findByUserName1( @PathVariable String username){
//        
//        UserEntity userEntity =  userRepository.findByUserName1(username);
//            
//        
//        return userEntity;
//    } 
//    //http://127.0.0.1:8080/user/findByUserName2/admin
//    @ResponseBody
//    @RequestMapping(value={ "/findByUserName2/{username}"})
//    public  UserEntity findByUserName2( @PathVariable String username){
//        
//        UserEntity userEntity =  userRepository.findByUserName2(username);
//            
//        
//        return userEntity;
//    } 
//    //http://127.0.0.1:8080/user/findByUserName3/admin
//    @ResponseBody
//    @RequestMapping(value={ "/findByUserName3/{username}"})
//    public  UserEntity findByUserName3( @PathVariable String username){        
//        UserEntity userEntity =  userRepository.findByUserName3(username);
//        return userEntity;
//    } 
//    
//  //http://127.0.0.1:8080/user/findByUserName4/admin
//    @ResponseBody
//    @RequestMapping(value={ "/findByUserName4/{username}"}) 
//    public  UserEntity findByUserName4( @PathVariable String username){        
//        UserEntity userEntity =  userRepository.findByUserName4(username);
//        return userEntity;
//    } 
//    //http://127.0.0.1:8080/user/updatePassword/admin
//    @Transactional
//    @ResponseBody
//    @RequestMapping(value={ "/updatePassword/{username}"}) 
//    public  int updatePassword( @PathVariable String username){        
//        return  userRepository.updatePassword("123456",username);
//    } 
//    //http://127.0.0.1:8080/user/findByUserName5/admin
// 
//    @ResponseBody
//    @RequestMapping(value={ "/findByUserName5/{username}"}) 
//    public  UserEntity findByUserName5( @PathVariable String username){    
//        System.out.println("调用findByUserName5:"+i++);
//        return  userRepository.findByUserName5(username);
//    } 
//  //http://127.0.0.1:8080/user/findByUserName6/admin
//    @ResponseBody
//    @RequestMapping(value={ "/findByUserName6/{username}"}) 
//    public  UserEntity findByUserName6( @PathVariable String username){        
//        return  userRepository.findByUserName6(username);
//    } 
// //http://127.0.0.1:8080/user/validate
//    
//    @ResponseBody
//    @RequestMapping(value={ "/validate"}) 
//    public  ErrorMsg validate( @Valid  UserEntity userEntity, Errors errors){
//        
//        ErrorMsg msg = new ErrorMsg();
//        if(errors.hasErrors()) {
//            System.out.println(errors.getAllErrors().get(0).getDefaultMessage());
//            
//            msg.setMsg(errors.getAllErrors().get(0).getDefaultMessage());
//            msg.setFeild(errors.getAllErrors().get(0).getObjectName());
//            return msg;
//        }
//        return msg;
//    } 
//    @Autowired @Qualifier("user1") 
//    private UserEntity user;
//    @ResponseBody
//    @RequestMapping(value={ "/getConfigUser"}) 
//    
//    public  UserEntity getConfigUser( ) {        
//         return user;
//    } 
//}
