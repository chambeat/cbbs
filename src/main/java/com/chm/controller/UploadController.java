package com.chm.controller;

import com.chm.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

    @Autowired
    private ExcelService excelService;

    @PostMapping("/upload")
    public String upload(MultipartFile file, Model model) throws Exception {
        boolean flag = excelService.getExcel(file);

        if (flag) {
            model.addAttribute("RespMessage", "上传成功");
        } else {
            model.addAttribute("RespMessage", "上传失败");
        }

        return "redirect:/users";
    }
}
