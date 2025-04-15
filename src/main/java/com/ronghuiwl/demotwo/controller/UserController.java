package com.ronghuiwl.demotwo.controller;

import com.ronghuiwl.demotwo.dto.UserDTO;
import com.ronghuiwl.demotwo.dto.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/index")
@Slf4j
public class UserController {
    public UserController() {
    }


    @GetMapping("/user")
    public List<UserDTO> getUser() {
        List<UserDTO> list = new ArrayList<>();
        for(int i=0,j=200;i<j;i++){
            UserDTO user = new UserDTO();
            user.setPhone(String.valueOf(System.currentTimeMillis()));
            user.setBankCard("6225880123456789"+System.currentTimeMillis()+i);
            user.setPassword("1234523326"+i);
            user.setName("跳转吵佃菜注解"+i);
            list.add(user);
        }
        return list;
    }

    @GetMapping("/vo")
    public UserVO getUserVO() {
        UserVO userVO = new UserVO();
        userVO.setPhone(String.valueOf(System.currentTimeMillis()));
        userVO.setBankCard("6225880123456789"+System.currentTimeMillis());
        userVO.setPassword("1234523326");
        userVO.setName("long");
        return userVO;
    }
}
