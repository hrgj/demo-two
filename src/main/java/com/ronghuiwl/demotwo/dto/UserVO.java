package com.ronghuiwl.demotwo.dto;

import com.ronghuiwl.demotwo.annotation.Sensitive;
import com.ronghuiwl.demotwo.enums.SensitiveType;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserVO implements Serializable {
    private String phone;

    private String bankCard;
    private String password;

    private String name;


}
