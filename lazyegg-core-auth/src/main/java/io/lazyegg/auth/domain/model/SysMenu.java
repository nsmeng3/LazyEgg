package io.lazyegg.auth.domain.model;

import com.alibaba.cola.domain.Entity;
import lombok.Data;

/**
 * 系统菜单
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/19 11:17 下午
 */
@Data
@Entity
public class SysMenu {

    /**
     * 菜单id
     */
    private Long menuId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单权限
     */
    private String menuPerms;
}
