package com.ronghuiwl.demotwo.dto;

import com.ronghuiwl.demotwo.annotation.Sensitive;
import com.ronghuiwl.demotwo.enums.SensitiveType;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {
    @Sensitive(type = SensitiveType.PHONE)
    private String phone;

    @Sensitive(left = 4, right = 3) // 自定义规则：保留前2位和后1位
    private String bankCard;
    @Sensitive
    private String password;

    @Sensitive(left = -1, right = 2)
    private String name;


}
