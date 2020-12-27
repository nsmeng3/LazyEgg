package io.lazyegg.auth.domain.model;

import com.alibaba.cola.domain.Entity;
import lombok.Data;

/**
 * 系统角色
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/19 10:35 下午
 */
@Data
@Entity
public class SysRole {

   private Long roleId;
   private String roleName;
}
