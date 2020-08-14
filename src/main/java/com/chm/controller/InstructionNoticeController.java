package com.chm.controller;

import com.chm.entity.Notice;
import com.chm.service.NoticeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class InstructionNoticeController {

    @Autowired
    private NoticeService noticeService;

    /* 前往"公告发布"页面 */
    @GetMapping("/toNotice")
    public String toNotice() {
        return "admin/notice";
    }

    @PostMapping("/notice")
    public String saveNotice(Notice notice) {
        noticeService.saveNotice(notice);
        return "redirect:/gotoNoticeBoard";//自动跳转 => 公告栏(查看所有公告)
    }

    @DeleteMapping("/notice/{id}")
    public String deleteNotice(@PathVariable("id") Integer id) {
        noticeService.deleteNotice(id);
        return "redirect:/noticeBoard";
    }

    /* ============================== 通用权限(如下) ============================== */
    @GetMapping("/noticeBoard")
    public String getNotices(Map<String, Object> map,
                             @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        System.out.println("查看公告列表...");
        PageHelper.startPage(pageNum, 10);
        List<Notice> noticeList = noticeService.getNotices();
        PageInfo<Notice> notices = new PageInfo<>(noticeList);
        map.put("notices", notices);
        return "main/noticeBoard";
    }

    @GetMapping("/noticeBoard/{id}")
    public String getNoticeById(Map<String, Object> map,
                                @PathVariable("id") Integer id) {
        Notice notice = noticeService.getNoticeById(id);
        map.put("notice", notice);
        return "main/noticeView";
    }
}
