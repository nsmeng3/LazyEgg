package io.lazyegg.auth.client.dto;

import com.alibaba.cola.dto.Command;
import lombok.Data;

/**
 * @author DifferentW  nsmeng3@163.com 2020/12/21 9:55 下午
 */
@Data
public class SysUserDeleteCmd extends Command {
    private Long userId;
}
