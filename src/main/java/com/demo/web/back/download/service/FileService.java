package com.demo.web.back.download.service;

import org.springframework.stereotype.Service;

import com.demo.service.BaseService;
import com.demo.web.back.download.entity.File;
import com.demo.web.back.download.repository.FileRepository;

@Service
public class FileService extends BaseService<File, Long> {
    public File findByUrl(String url){
        return getBaseRepository(FileRepository.class).findByUrl(url);
    }
}
