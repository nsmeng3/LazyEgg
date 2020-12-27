package io.lazyegg.auth.client.dto.query;

import com.alibaba.cola.dto.Query;
import lombok.Data;

/**
 * @author DifferentW  nsmeng3@163.com 2020/12/20 4:01 下午
 */
@Data
public class SysUserGetByUserIdQuery extends Query {
    private Long userId;
}
