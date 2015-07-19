package com.demo.web.back.download.service;

import java.util.List;

import com.demo.service.BaseService;
import com.demo.web.back.download.entity.Task;
import com.demo.web.back.download.enums.Pace;
import com.demo.web.back.download.info.TaskInfo;
import com.demo.web.back.download.repository.TaskRepository;


public abstract class  TaskService extends BaseService<Task, Long> {
    public TaskRepository getDao(){
        return getBaseRepository(TaskRepository.class);
    }
    public abstract void startTask(Long[] ids);
    public abstract DownloadTask getDownloadTask();
    public List<TaskInfo> taskCheck(Long[] ids) {
        return  getDownloadTask().getInfobyKey(ids);
    }
    public void updatePace( Pace pace,  Long id){
        getBaseRepository(TaskRepository.class).updatePace(pace,id);
    }
    public void updatePaceAndUserSecond(Pace pace, Long id, String userSecond){
        getBaseRepository(TaskRepository.class).updatePaceAndUserSecond(pace,id,userSecond);
    }
    //删除和停止任务
    public void removeAll(Long[] ids) {
        for (Long long1 : ids) {
            getDownloadTask().stop(long1);
            getBaseRepository(TaskRepository.class).delete(long1);
        }
        
    }
   
}
