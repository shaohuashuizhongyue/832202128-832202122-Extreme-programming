package com.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 收藏
 * </p>
 *
 * @author admin
 * @since 2024-11-23
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class Keep implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * id
     */
        @TableId(value = "id", type = IdType.AUTO)
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
      private LocalDateTime pubtime;


}
