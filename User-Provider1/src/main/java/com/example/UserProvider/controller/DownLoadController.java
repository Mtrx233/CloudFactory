package com.example.UserProvider.controller;

import com.example.UserProvider.bean.CommonExcel;
import com.example.UserProvider.bean.User;
import com.example.UserProvider.dao.UserDao;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DownLoadController {
    @Autowired
    UserDao userDao;

    @RequestMapping("/downUserInfo")
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<User> userList =userDao.userList();
        System.out.println(userList);
        String title = "人员用户信息";
        //表头
        String[] rowsName = new String[]{"序号","姓名","账号","用户类别","联系方式","邮箱"};
        //主体内容
        List<Object[]>  dataList = new ArrayList<Object[]>();
        Object[] objs = null;
        for (int i = 0; i < userList.size(); i++) {
            objs = new Object[rowsName.length];
            objs[0] = i;
            objs[1] = userList.get(i).getUser_name();
            objs[2] = userList.get(i).getAccount();
            objs[3] = userList.get(i).getKind();
            objs[4] = userList.get(i).getPhone();
            objs[5] = userList.get(i).getEmail();
            dataList.add(objs);
        }
        // 定义Excel文件名
        String fileName="用户信息.xls";
        CommonExcel ex = new CommonExcel(title, rowsName, dataList,fileName);
        HSSFWorkbook workbook=ex.getExcel();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=\""+new String(fileName.getBytes("gb2312"),"ISO8859-1"));
        OutputStream out = response.getOutputStream();
        workbook.write(out);
        out.close();
    }


}
