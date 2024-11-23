package com.dto;

import org.apache.poi.ss.formula.functions.T;
import lombok.Data;


@Data
public class LiaisonDto extends PageDto {



  /**
     * 业务上的成功或失败
     */
    private boolean success = true;

    /**
     * 返回码
     */
    private String code;
    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回泛型数据，自定义类型
     */
    private T content;


    /**
     * id
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private String age;

    /**
     * 用户
     */
    private String uid;

    /**
     * 电话号码
     */
    private String tel;

    /**
     * 电子邮件地址
     */
    private String email;

    /**
     * 社交媒体账号
     */
    private String maccount;

    /**
     * 物理地址
     */
    private String address;

    /**
     * 备注
     */
    private String note;

    /**
     * 添加时间
     */
    private String pubtime;



}
