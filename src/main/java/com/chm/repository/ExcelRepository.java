package com.chm.repository;

import com.chm.entity.Excel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA：
 *  自定义接口若继承了JpaRepository接口，就会具有CRUD操作/分页排序/批量操作等功能。
 */
//指定的泛型：JpaRepository<操作的实体类, 主键的数据类型>
//@Repository
public interface ExcelRepository extends JpaRepository<Excel, Integer> {
}
