package com.halfsay.flower.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Loginuser implements Serializable {
    private Integer userid;
    private String username;
    private String usertype;
    private String jwtToken;
}
