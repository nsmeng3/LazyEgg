package io.lazyegg.auth.client;

import com.alibaba.cola.dto.Response;
import io.lazyegg.auth.client.dto.SysUserDeleteCmd;
import io.lazyegg.auth.client.dto.SysUserSaveCmd;
import io.lazyegg.auth.client.dto.SysUserUpdateCmd;
import io.lazyegg.auth.client.dto.query.SysUserGetByUserIdQuery;
import io.lazyegg.auth.client.dto.query.SysUserListQuery;

/**
 * @author DifferentW  nsmeng3@163.com 2020/12/20 3:55 下午
 */
public interface SysUserServiceI {
    Response get(SysUserGetByUserIdQuery query);

    Response list(SysUserListQuery sysUserListQuery);

    Response update(SysUserUpdateCmd sysUserUpdateCmd);

    Response save(SysUserSaveCmd sysUserSaveCmd);

    Response delete(SysUserDeleteCmd sysUserDeleteCmd);
}
