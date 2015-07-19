package com.demo.web.back.sys.repository;

import org.springframework.stereotype.Repository;

import com.demo.repository.BaseRepository;
import com.demo.web.back.sys.entity.Notice;

 

@Repository
public interface NoticeRepository extends BaseRepository<Notice,Long> {
    public Notice getOneNotice();
}
