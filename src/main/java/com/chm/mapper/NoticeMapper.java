package com.chm.mapper;

import com.chm.entity.Notice;

import java.util.List;

public interface NoticeMapper {

    /* 查询所有公告 */
    List<Notice> getNotices();

    /* 查看某一条公告(根据id查询) */
    Notice getNoticeById(Integer id);

    /* admin权限：发布公告 */
    int saveNotice(Notice notice);

    /* admin权限：删除公告 */
    int deleteNotice(Integer id);
}
