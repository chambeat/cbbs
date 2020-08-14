package com.chm.service;

import com.chm.entity.Booking;

import java.util.List;

public interface BookingService {

    /**
     * 查询我的订单列表（扩展：根据使用时间模糊查询）
     * @param campusId
     * @return
     */
    List<Booking> getBookings(String campusId);//传入对象作为参数，便于扩展功能

    /*
     * admin权限：查询所有用户提交的订单
     * @return
     */
    List<Booking> allBookings(Booking booking);

    /*
     * admin权限：查看'待审核'的订单
     * @return
     */
    List<Booking> defaultBookings(Booking booking);

    /*
     * admin权限：查看'审核通过'的订单
     * @return
     */
    List<Booking> successBookings(Booking booking);

    /*
     * admin权限：查看'审核不通过'的订单
     * @return
     */
    List<Booking> failBookings(Booking booking);

    /**
     * 查询一个订单
     * @param bid
     * @return
     */
    Booking getBookingByBid(Integer bid);

    /**
     * 提交订单
     * @param booking
     * @return
     */
    int saveBooking(Booking booking);

    /**
     * 删除订单
     * @param bid
     * @return
     */
    int deleteBookingByBid(Integer bid);

    /*
     * amdin权限：修改订单状态
     * @param bid
     * @return
     */
    int updateBooking(Booking booking);

    /*
     * 查询所有 "已被预约的时间"
     * @return
     */
    Boolean isBookedTime(String useTime);
}
