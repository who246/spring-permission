package com.demo.web.back.download.repository;

import org.springframework.stereotype.Repository;

import com.demo.repository.BaseRepository;
import com.demo.web.back.download.entity.File;

@Repository
public interface FileRepository extends BaseRepository<com.demo.web.back.download.entity.File, Long> {

    File findByUrl(String url);
    
}
