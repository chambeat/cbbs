package com.chm.service;

import org.springframework.web.multipart.MultipartFile;

public interface ExcelService {

    boolean getExcel(MultipartFile file) throws Exception;
}
