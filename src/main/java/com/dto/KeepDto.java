package com.dto;

import org.apache.poi.ss.formula.functions.T;
import lombok.Data;


@Data
public class KeepDto extends PageDto {



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
     * 用户
     */
    private Integer uid;

    /**
     * 联系人
     */
    private Integer cid;

    /**
     * 类型
     */
    private String ctype;

    /**
     * 添加时间
     */
    private String pubtime;



}
