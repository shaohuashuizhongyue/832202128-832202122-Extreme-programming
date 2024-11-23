package com.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 联系人
 * </p>
 *
 * @author admin
 * @since 2024-11-23
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class Liaison implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * id
     */
        @TableId(value = "id", type = IdType.AUTO)
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
