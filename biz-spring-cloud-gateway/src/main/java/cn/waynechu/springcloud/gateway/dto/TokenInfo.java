package cn.waynechu.springcloud.gateway.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author zhuwei
 * @date 2020-03-08 17:53
 */
@Data
public class TokenInfo {

    private boolean active;

    private String client_id;

    private String[] scope;

    private String user_name;

    private String[] aud;

    private Date exp;

    private String[] authorities;

}
