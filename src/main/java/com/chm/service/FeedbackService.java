package com.chm.service;

import com.chm.entity.Feedback;

import java.util.List;

public interface FeedbackService {

    /* 查询所有问题反馈 */
    List<Feedback> getFeedbacks();

    /* 提交问题反馈 */
    int saveFeedback(Feedback feedback);

    /* 删除反馈信息 */
    int deleteFeedback(Integer id);
}
