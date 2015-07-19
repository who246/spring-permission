package com.demo.web.back.download.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.demo.common.data.jpa.utlis.DynamicSpecifications;
import com.demo.common.data.jpa.utlis.SearchFilter;
import com.demo.common.data.jpa.utlis.SearchFilter.Operator;
import com.demo.common.utils.SessionUser;
import com.demo.web.back.download.entity.Task;
import com.demo.web.back.download.enums.Pace;
import com.demo.web.back.download.info.TaskInfo;


@Service("youtubeSerive")
public class YoutubeSerive extends TaskService {
    
    @Autowired
    @Qualifier("taskInfoTask")
    private TaskInfoTask youtubeTask;


    @Override
    public DownloadTask getDownloadTask() {
         
        return youtubeTask;
    }
    public void startTask(Long[] ids) {
        List<Task>  tasks =  getDao().findAll(Arrays.asList(ids));
        for (Task task : tasks) {
            if(task.getPace() .equals(Pace.stop) || task.getPace() .equals(Pace.fail) ){
                TaskInfo tTask = new TaskInfo();
                BeanUtils.copyProperties(task, tTask);
                tTask.setId(task.getId());
                YoutubeExcutor ye = new YoutubeExcutor(tTask);
                youtubeTask.addTask(ye); 
                
            }
        }
    }
    @Override
    public Specification<Task> bulidSearch( Map<String, Object> searchParams){
        searchParams.put(Operator.EQ+"_"+"username", SessionUser.getUserName());
       return super.bulidSearch(searchParams);
    }
}
