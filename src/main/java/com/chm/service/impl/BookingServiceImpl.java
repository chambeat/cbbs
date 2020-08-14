package com.chm.service.impl;

import com.chm.entity.Booking;
import com.chm.mapper.BookingMapper;
import com.chm.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingMapper bookingMapper;

    Logger logger = LoggerFactory.getLogger(getClass());//日志

    @Override
    public List<Booking> getBookings(String campusId) {
        logger.info("我的订单列表..." + campusId);
        return bookingMapper.getBookings(campusId);
    }

    /* admin权限：查询所有用户提交的订单 */
    @Override
    public List<Booking> allBookings(Booking booking) {
        logger.info("admin查询所有订单...");
        return bookingMapper.allBookings(booking);
    }

    @Override
    public List<Booking> defaultBookings(Booking booking) {
        return bookingMapper.defaultBookings(booking);
    }

    @Override
    public List<Booking> successBookings(Booking booking) {
        return bookingMapper.successBookings(booking);
    }

    @Override
    public List<Booking> failBookings(Booking booking) {
        return bookingMapper.failBookings(booking);
    }

    @Override
    public Booking getBookingByBid(Integer bid) {
        logger.info("查看订单详情..." + bid);
        return bookingMapper.getBookingByBid(bid);
    }

    @Override
    public int saveBooking(Booking booking) {
        logger.info(booking.getApplicant() + " 正在提交订单...");
        bookingMapper.saveBooking(booking);
        return 0;
    }

    @Override
    public int deleteBookingByBid(Integer bid) {
        logger.info("删除订单..." + bid);
        bookingMapper.deleteBookingByBid(bid);
        return 0;
    }

    /* admin权限：修改订单状态 */
    @Override
    public int updateBooking(Booking booking) {
        logger.info("admin修改订单状态...");
        bookingMapper.updateBooking(booking);
        return 0;
    }

    @Override
    public Boolean isBookedTime(String useTime) {
        logger.info("查询已经被预约的时间...");
        String bookedTime = bookingMapper.isBookedTime(useTime);
        if (useTime.equals(bookedTime)) {
            return false;//时间冲突，不能预订
        }
        return true;//可以预订
    }
}
