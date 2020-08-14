package com.chm.service.impl;

import com.chm.entity.Feedback;
import com.chm.mapper.FeedbackMapper;
import com.chm.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<Feedback> getFeedbacks() {
        logger.info("查询所有问题反馈");
        return feedbackMapper.getFeedbacks();
    }

    @Override
    public int saveFeedback(Feedback feedback) {
        logger.info("提交问题反馈...");
        feedbackMapper.saveFeedback(feedback);
        return 0;
    }

    @Override
    public int deleteFeedback(Integer id) {
        logger.info("删除反馈信息...");
        return feedbackMapper.deleteFeedback(id);
    }
}
