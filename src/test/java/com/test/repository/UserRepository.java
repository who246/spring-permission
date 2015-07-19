package com.test.repository;

 

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.entitiy.UserEntity;
@Scope("singleton")
@Repository(value="")
@Lazy(true)
public interface UserRepository extends JpaRepository<UserEntity,Long>{
   public UserEntity findByUserName(String username,Sort sort);
   public UserEntity findByUserNameAndId(String username,long id,Sort sort);
   public UserEntity findByUserName1(String username);
   @Query("select o from UserEntity o where  o.userName = ?1")
   public UserEntity findByUserName2(String username);
   @Query(value="select * from user o where  o.user_name = ?1" , nativeQuery=true)
   public UserEntity findByUserName3(String username);
   
   @Query("select o from UserEntity o where  o.userName = :username")
   public UserEntity findByUserName4(@Param("username") String username);
   @Modifying(clearAutomatically = true)
   @Query("update UserEntity o set o.password = :password  where  o.userName = :username")
   public int updatePassword(@Param("password")String password,@Param("username")String username);
   public UserEntity findByUserName5(String username);
   public UserEntity findByUserName6(String username);
}
