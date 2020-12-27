package io.lazyegg.auth.app;

import com.alibaba.cola.dto.Response;
import io.lazyegg.auth.app.executor.SysUserDeleteCmdExe;
import io.lazyegg.auth.app.executor.SysUserSaveCmdExe;
import io.lazyegg.auth.app.executor.SysUserUpdateCmdExe;
import io.lazyegg.auth.app.executor.query.SysUserGetByUserIdQueryExe;
import io.lazyegg.auth.app.executor.query.SysUserListQueryExe;
import io.lazyegg.auth.client.SysUserServiceI;
import io.lazyegg.auth.client.dto.SysUserDeleteCmd;
import io.lazyegg.auth.client.dto.SysUserSaveCmd;
import io.lazyegg.auth.client.dto.SysUserUpdateCmd;
import io.lazyegg.auth.client.dto.query.SysUserGetByUserIdQuery;
import io.lazyegg.auth.client.dto.query.SysUserListQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author DifferentW  nsmeng3@163.com 2020/12/20 4:00 下午
 */
@Service
public class SysUserServiceImpl implements SysUserServiceI {

    @Resource
    private SysUserGetByUserIdQueryExe sysUserGetByUserIdQueryExe;
    @Resource
    private SysUserListQueryExe sysUserListQueryExe;
    @Resource
    private SysUserUpdateCmdExe sysUserUpdateCmdExe;
    @Resource
    private SysUserSaveCmdExe sysUserSaveCmdExe;
    @Resource
    private SysUserDeleteCmdExe sysUserDeleteCmdExe;

    @Override
    public Response get(SysUserGetByUserIdQuery query) {
        return sysUserGetByUserIdQueryExe.execute(query);
    }

    @Override
    public Response list(SysUserListQuery sysUserListQuery) {

        return sysUserListQueryExe.execute(sysUserListQuery);
    }

    @Override
    public Response update(SysUserUpdateCmd sysUserUpdateCmd) {
        return sysUserUpdateCmdExe.execute(sysUserUpdateCmd);
    }

    @Override
    public Response save(SysUserSaveCmd sysUserSaveCmd) {

        return sysUserSaveCmdExe.execute(sysUserSaveCmd);
    }

    @Override
    public Response delete(SysUserDeleteCmd sysUserDeleteCmd) {
        return sysUserDeleteCmdExe.execute(sysUserDeleteCmd);
    }
}
