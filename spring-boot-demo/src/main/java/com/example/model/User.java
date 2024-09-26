package com.example.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String data_id;
    private String user_id;
    private String username;
    private String password;
    private String remark;
    private String data_crt_user;
    private String data_crt_date;
    private String data_crt_time;
    private String data_chg_user;
    private String data_chg_date;
    private String data_chg_time;
    private String data_apv_user;
    private String data_apv_date;
    private String data_apv_time;
    private String rsv1;
    private String rsv2;
    private String rsv3;
    private String rsv4;
    private String rsv5;
}
