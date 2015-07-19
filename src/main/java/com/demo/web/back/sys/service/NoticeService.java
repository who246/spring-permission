package com.demo.web.back.sys.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.demo.common.Constants;
import com.demo.service.BaseService;
import com.demo.web.back.sys.entity.Notice;
import com.demo.web.back.sys.repository.NoticeRepository;

@Service
public class NoticeService extends BaseService<Notice, Long> {
   @Cacheable(value = Constants.DM_CacheKey, key = "#root.targetClass+#root.methodName+'getWelcomeNotice'")
  //  @Cacheable(value = Constants.DM_CacheKey, key = "keyGenerator")
    public Notice getWelcomeNotice() {
       
        return getBaseRepository(NoticeRepository.class).getOneNotice();
    }
 
   @Override
   @CacheEvict(value = Constants.DM_CacheKey, key = "'#root.targetClass+#root.methodName+getWelcomeNotice'",beforeInvocation=true)
 //  @CacheEvict(value = Constants.DM_CacheKey, key = "keyGenerator",beforeInvocation=true)
public void update(Notice o) {
    super.update(o);
}
    
    
}
