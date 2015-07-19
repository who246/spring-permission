package com.demo.web.back.download.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.demo.web.back.download.enums.Pace;
 

 @Component("taskInfoTask")
public  class TaskInfoTask extends DownloadTask<Object,TaskExcutor,Object> {
    

    private  Object me = new Object();
   

    @Override
    protected void stop(Object key) {
        TaskExcutor task = removeTask(key);
        if(task!=null) task.delete();
    }
    @Override
    public  List<Object> getInfobyKey(Object[] keys){
        List<Object> tasks = new ArrayList<Object>();
        for (Object long1 : keys) {
            TaskExcutor task = taskMap.get(long1);
            if(task ==  null) continue;
            
            if(task != null){
                
                tasks.add(task.read());
            }
        }
        return tasks;
    }
    
    @Async("workExecutor")
    @Override
    public  void addTask(TaskExcutor task){
        boolean isAdd =false;
        if(!taskMap.containsKey(task.key())){
            synchronized (me) {
                if(!taskMap.containsKey(task.key())){
                 
                    taskMap.put(task.key(), task);
                    isAdd = true;
                }
                
             }
            if(isAdd) execute(task);
        }
        
    }
    public  void execute(TaskExcutor task){
        task.excute();
    }
    
    
    @Override
    protected boolean canRemove(TaskExcutor task) {
        
        return task.state().equals(Pace.finish)||task.state().equals(Pace.fail);
    }

     
}
