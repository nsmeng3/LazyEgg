package io.lazyegg.auth.app.executor.query;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import io.lazyegg.auth.client.dto.query.SysUserListQuery;
import io.lazyegg.auth.domain.model.SysUser;
import io.lazyegg.auth.infrastructure.mapper.SysUserDO;
import io.lazyegg.auth.infrastructure.mapper.SysUserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DifferentW  nsmeng3@163.com 2020/12/21 8:36 下午
 */
@Component
public class SysUserListQueryExe {

    @Resource
    private SysUserMapper sysUserMapper;

    public Response execute(SysUserListQuery sysUserListQuery) {
        List<SysUserDO> sysUserDOList = sysUserMapper.list();
        List<SysUser> sysUserList = new ArrayList<>();
        sysUserDOList.forEach(sysUserDO -> {
            SysUser target = new SysUser();
            BeanUtils.copyProperties(sysUserDO, target);
            // 清除密码
            target.clearPassword();
            sysUserList.add(target);
        });
        return SingleResponse.of(sysUserList);
    }
}
