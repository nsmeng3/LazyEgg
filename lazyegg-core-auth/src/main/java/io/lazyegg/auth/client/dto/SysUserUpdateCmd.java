package io.lazyegg.auth.client.dto;

import com.alibaba.cola.dto.Command;
import io.lazyegg.auth.client.dto.clientobject.SysUserCO;
import lombok.Data;

/**
 * @author DifferentW  nsmeng3@163.com 2020/12/21 9:01 下午
 */
@Data
public class SysUserUpdateCmd extends Command {
    private SysUserCO sysUserCO;
}
