package com.chm.service.impl;

import com.chm.entity.Notice;
import com.chm.mapper.NoticeMapper;
import com.chm.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public List<Notice> getNotices() {
        return noticeMapper.getNotices();
    }

    @Override
    public Notice getNoticeById(Integer id) {
        return noticeMapper.getNoticeById(id);
    }

    @Override
    public int saveNotice(Notice notice) {
        noticeMapper.saveNotice(notice);
        return 0;
    }

    @Override
    public int deleteNotice(Integer id) {
        noticeMapper.deleteNotice(id);
        return 0;
    }
}
