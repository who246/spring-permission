package com.test.sys;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.WebAppConfig;
import com.demo.web.back.sys.service.NoticeService;

 

@SpringApplicationConfiguration(classes = WebAppConfig.class) 
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ActiveProfiles("junit")
public class NoticeTest {

    @Autowired
    private NoticeService noticeService;
    @Test
    public void getWellcomeNotice() {
           
       System.out.println("id"+noticeService.getWelcomeNotice().getId());
    }
    
}
