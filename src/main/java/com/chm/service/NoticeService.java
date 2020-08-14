package com.chm.service;

import com.chm.entity.Notice;

import java.util.List;

public interface NoticeService {

    /* 查询所有公告 */
    List<Notice> getNotices();

    /* 根据id查询公告 */
    Notice getNoticeById(Integer id);

    /* 发布公告 */
    int saveNotice(Notice notice);

    /* 删除公告 */
    int deleteNotice(Integer id);
}
