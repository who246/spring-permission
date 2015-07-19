package com.demo.web.back.download.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service("panSerive")
public class PanSerive extends TaskService {
    
    @Autowired
    @Qualifier("taskInfoTask")
    private TaskInfoTask youtubeTask;


    @Override
    public DownloadTask getDownloadTask() {
         
        return youtubeTask;
    }
    public void startTask(Long[] ids) {
       
    }
   
}
