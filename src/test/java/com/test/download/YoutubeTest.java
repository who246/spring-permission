package com.test.download;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.demo.WebAppConfig;
import com.demo.web.back.download.entity.Task;
import com.demo.web.back.download.enums.Pace;
import com.demo.web.back.download.info.TaskInfo;
import com.demo.web.back.download.repository.TaskRepository;
import com.demo.web.back.download.service.TaskInfoTask;
import com.demo.web.back.download.service.YoutubeExcutor;
import com.demo.web.back.download.service.YoutubeSerive;

@SpringApplicationConfiguration(classes = WebAppConfig.class) 
@RunWith(SpringJUnit4ClassRunner.class)
//@Transactional
@ActiveProfiles("junit")
public class YoutubeTest {
    @Autowired 
    private TaskRepository taskRepository ;
    @Autowired @Qualifier("youtubeTask")
    private TaskInfoTask youtubeTask; 
    
    @Autowired @Qualifier("youtubeSerive")
    private YoutubeSerive youtubeSerive; 
    @Test
    public void startTask(){
      // Task task = taskRepository.findOne(1l);
        taskRepository.updatePace(Pace.stop, 1l);
    }
      
    
}
