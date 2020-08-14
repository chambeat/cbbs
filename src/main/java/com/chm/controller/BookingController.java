package com.chm.controller;

import com.chm.entity.Booking;
import com.chm.entity.User;
import com.chm.service.BookingService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Map;

/**
 * 订单的控制层(表现层)
 */
@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    /*
     * 查看我的订单列表/根据使用时间模糊查询
     * @param map
     * @param authentication
     * @return
     */
    @GetMapping("/booking")
    public String list(Map<String, Object> map, Authentication authentication, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) throws NoSuchFieldException, IllegalAccessException {
        PageHelper.startPage(pageNum, 10);
        List<Booking> myList = bookingService.getBookings(authentication.getName());
        PageInfo<Booking> myBookings = new PageInfo<>(myList);
        map.put("booking", myBookings);//传入bookings，用于在html中遍历订单列表
        return "user/booking/list";
    }


    /*
     * 订单审核通过后，获取邮件凭证
     * @param booking
     * @return
     */
    @GetMapping("/sendMail")
    public String sendMail(Booking booking) {
        System.out.println("准备发送邮件......");

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            //标题
            helper.setSubject("教工之家在线预订平台");
            //发件人
            helper.setFrom("1204295973@qq.com");
            //收件人
            helper.setTo(booking.getEmail());
            Context context = new Context();
            context.setVariable("bid", booking.getBid());
            context.setVariable("school", booking.getSchool());
            context.setVariable("applicant", booking.getApplicant());
            context.setVariable("campusId", booking.getCampusId());
            context.setVariable("useTime", booking.getUseTime());
            context.setVariable("reason", booking.getReason());
            context.setVariable("status", booking.getStatus());
            String process = templateEngine.process("mail.html", context);
            //正文内容
            helper.setText(process, true);
            javaMailSender.send(mimeMessage);
            System.out.println("邮件发送成功！");
            return "redirect:/afterEmail";
        } catch (MessagingException e) {
            System.out.println("发送失败！");
            e.printStackTrace();
            return "error/5xx";
        }
    }


    /*
     * 1. 查看订单详情(admin可以仍然用此方法!!!!!!)
     * AND
     * 2. 前往修改页面(可以通过动态修改'type'的值达到使此controller复用的目的)
     * @param bid
     * @param type
     * @param map
     * @return
     */
    @GetMapping("/booking/{bid}")
    public String view(@PathVariable("bid") Integer bid,
                       @RequestParam(name = "type", defaultValue = "view") String type,
                       Map<String, Object> map) {
        Booking booking = bookingService.getBookingByBid(bid);
        map.put("booking", booking);
        return "user/booking/" + type;
    }


    /*
     * 前往订单填写页面
     * @return
     */
    @GetMapping("/toBooking")
    public String toAddPage(Authentication authentication, Map<String, Object> map) {
        System.out.println("填写订单...");
        /* 获取当前登录用户的个人信息，用于自动填充预订表单 */
        User user = (User) authentication.getPrincipal();
        map.put("user", user);
        return "user/booking/add";
    }


    /*
     * 发送AJAX请求，查看时间是否冲突
     * @param useTime
     * @return
     */
    @ResponseBody
    @GetMapping("/toBooking/{useTime}")
    public Boolean checkTime(@PathVariable("useTime") String useTime) {
        System.out.println("查看预约时间是否冲突...");
        return bookingService.isBookedTime(useTime);
    }


    /*
     * 提交订单
     * @param booking
     * @return
     */
    @PostMapping("/booking")
    public String add(Booking booking) {
        try {
            //保存操作
            bookingService.saveBooking(booking);
            System.out.println("订单提交成功！");
            return "redirect:/gotoList";//自动跳转 => 展示我的订单列表
        } catch (Exception e) {
            System.out.println("很抱歉，订单提交失败！");
            e.printStackTrace();
            return "error/5xx";
        }
    }


    /*
     * 取消订单(注意：admin不能复用此方法！必须另写一个controller！)
     * @param bid
     * @return
     */
    @DeleteMapping("/booking/{bid}")
    public String delete(@PathVariable("bid") Integer bid) {
        //删除操作
        bookingService.deleteBookingByBid(bid);
        return "redirect:/booking";//回到我的订单列表
    }


    /* ****************************** admin权限方法如下 ****************************** */

    /*
     * 查询所有订单
     * @param map
     * @return
     */
    @GetMapping("/bookings")
    public String listAll(Map<String, Object> map,
                          Booking booking,
                          @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<Booking> allList = bookingService.allBookings(booking);
        PageInfo<Booking> allBookings = new PageInfo<>(allList);
        map.put("bookings", allBookings);//传入bookings，用于在html中遍历订单列表
        map.put("campusId", booking.getCampusId());//将搜索条件(学号或工号)回显到输入框
        map.put("useTime", booking.getUseTime());//将搜索条件(申请使用的时间)回显到输入框
        return "user/booking/list";
    }

    /*
     * 查询'待审核'的订单
     * @param map
     * @return
     */
    @GetMapping("/defaultBookings")
    public String listDefault(Map<String, Object> map,
                              Booking booking,
                              @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<Booking> allList = bookingService.defaultBookings(booking);
        PageInfo<Booking> allBookings = new PageInfo<>(allList);
        map.put("bookings", allBookings);//传入bookings，用于在html中遍历订单列表
        map.put("campusId", booking.getCampusId());//将搜索条件(学号或工号)回显到输入框
        map.put("useTime", booking.getUseTime());//将搜索条件(申请使用的时间)回显到输入框
        return "admin/defaultBookings";
    }

    /*
     * 查询'审核通过'的订单
     * @param map
     * @return
     */
    @GetMapping("/successBookings")
    public String listSuccess(Map<String, Object> map,
                              Booking booking,
                              @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<Booking> allList = bookingService.successBookings(booking);
        PageInfo<Booking> allBookings = new PageInfo<>(allList);
        map.put("bookings", allBookings);//传入bookings，用于在html中遍历订单列表
        map.put("useTime", booking.getUseTime());//将搜索条件(申请使用的时间)回显到输入框
        map.put("campusId", booking.getCampusId());//将搜索条件(学号或工号)回显到输入框
        return "admin/successBookings";
    }

    /*
     * 查询'审核不通过'的订单
     * @param map
     * @return
     */
    @GetMapping("/failBookings")
    public String listFail(Map<String, Object> map,
                           Booking booking,
                           @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<Booking> allList = bookingService.failBookings(booking);
        PageInfo<Booking> allBookings = new PageInfo<>(allList);
        map.put("bookings", allBookings);//传入bookings，用于在html中遍历订单列表
        map.put("useTime", booking.getUseTime());//将搜索条件(申请使用的时间)回显到输入框
        map.put("campusId", booking.getCampusId());//将搜索条件(学号或工号)回显到输入框
        return "admin/failBookings";
    }


    /*
     * 修改订单状态（可以灵活定制修改成功后跳转的页面）
     * @param bid
     * @return
     */
    @PutMapping("/booking")
    public String update(Booking booking,
                         @RequestParam(name = "page", defaultValue = "bookings") String page) {
        bookingService.updateBooking(booking);
//        return "redirect:/bookings";
        return "redirect:/" + page;
    }


    /*
     * 取消订单
     * @param bid
     * @return
     */
    @DeleteMapping("/bookings/{bid}")
    public String deleteWithAdminAccess(@PathVariable("bid") Integer bid) {
        //删除操作
        bookingService.deleteBookingByBid(bid);
        return "redirect:/bookings";//回到所有订单列表
    }
}
