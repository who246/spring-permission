package com.demo.web.back.download.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
/**
 * 
 * 
 * 任务执行器
 * 
 * 
 * 池超凡
 * 
 * 2015年6月27日 上午12:47:20
 * 
 * @version 1.0.0
 *
 */
public abstract class DownloadTask<K,T,M> {
   protected  Map<K,T> taskMap = new ConcurrentHashMap<K,T>();
   
   {
       Timer timer = new Timer();  
       timer.schedule(new ReomoveTask(), 0, 1000*60*2);//2分钟清理一次 
   }
   public abstract List<M> getInfobyKey(K[] keys);
   public  T removeTask(Object key){
       
      return  taskMap.remove(key);
      
   }
 
   public abstract void addTask(T task);
   
 
   protected abstract void stop(K key);
   protected abstract boolean canRemove(T task);
    class ReomoveTask extends TimerTask {  
       
       @Override  
       public void run() {  
           Iterator<T>  taskIt = taskMap.values().iterator();
           while (taskIt.hasNext()) {
               T task = taskIt.next();
            if(canRemove(task))
                taskIt.remove();
           }
       }  
     
   }
   
    
  
}
