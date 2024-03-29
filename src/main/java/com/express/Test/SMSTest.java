package com.express.Test;

import com.express.utils.SMSSender;

import java.util.Random;

public class SMSTest {
    public static void main(String[] args) {
        int code = new Random().nextInt(900000);
        SMSSender.send("18941267609",code+"");
    }
}
