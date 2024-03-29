package com.express.utils;

import com.express.bean.Express;
import com.express.bean.NewExpress;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ToNewExpress {

    public NewExpress convertExpress(Express express) {
        NewExpress newExpress = new NewExpress();

        if (express == null) {
            return null;
        }

        // 替换status成员值
        if (express.getStatus() == 0) {
            newExpress.setStatus("未取件");
        } else if (express.getStatus() == 1) {
            newExpress.setStatus("已取件");
        }

        // 将时间格式化
        SimpleDateFormat inputFormat = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            if (express.getTime()!=null){
            String format = inputFormat.format(express.getTime());
            Date time = inputFormat.parse(format);
            newExpress.setTime(outputFormat.format(time));}
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            if (express.getIn_time()!=null){
            String format = inputFormat.format(express.getIn_time());
            Date inTime = inputFormat.parse(format);
            newExpress.setIn_time(outputFormat.format(inTime));}
        } catch (ParseException e) {
            e.printStackTrace();
        }
        newExpress.setId(express.getId());
        newExpress.setExpress_id(express.getExpress_id());
        newExpress.setName(express.getName());
        newExpress.setPhone(express.getPhone());
        newExpress.setCompany(express.getCompany());
        newExpress.setCode(express.getCode());
        newExpress.setCourier_phone(express.getCourier_phone());
        return newExpress;
    }
}
