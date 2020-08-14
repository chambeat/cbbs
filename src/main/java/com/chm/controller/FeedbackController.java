package com.chm.controller;

import com.chm.entity.Feedback;
import com.chm.service.FeedbackService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/feedback")
    public String submit(Feedback feedback) {
        System.out.println("提交问题反馈...");
        //提交问题反馈
        feedbackService.saveFeedback(feedback);
        return "redirect:/gotoMain";//自动跳转 => 首页
    }

    /* admin权限：查询所有反馈信息 */
    @GetMapping("/feedbacks")
    public String list(Map<String, Object> map,
                       @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        System.out.println("admin查看所有反馈...");
        PageHelper.startPage(pageNum, 10);
        List<Feedback> feedbackList = feedbackService.getFeedbacks();
        PageInfo<Feedback> feedbacks = new PageInfo<>(feedbackList);
        map.put("feedbacks", feedbacks);
        return "admin/listFeedback";
    }

    /* admin权限：清除反馈内容 */
    @DeleteMapping("/feedbacks/{id}")
    public String deleteInfo(@PathVariable("id") Integer id) {
        feedbackService.deleteFeedback(id);
        return "redirect:/feedbacks";
    }

}
