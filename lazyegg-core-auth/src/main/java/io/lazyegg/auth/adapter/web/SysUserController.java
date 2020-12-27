package io.lazyegg.auth.adapter.web;

import com.alibaba.cola.dto.Response;
import io.lazyegg.auth.client.SysUserServiceI;
import io.lazyegg.auth.client.dto.SysUserDeleteCmd;
import io.lazyegg.auth.client.dto.SysUserSaveCmd;
import io.lazyegg.auth.client.dto.SysUserUpdateCmd;
import io.lazyegg.auth.client.dto.clientobject.SysUserCO;
import io.lazyegg.auth.client.dto.query.SysUserGetByUserIdQuery;
import io.lazyegg.auth.client.dto.query.SysUserListQuery;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户Controller
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/20 3:48 下午
 */
@RestController
@RequestMapping("/sys")
public class SysUserController {

    @Resource
    private SysUserServiceI sysUserService;

    @GetMapping("/user/{userId}")
    @RequiresRoles("admin")
    @RequiresPermissions("sys:user:info")
    public Response get(@PathVariable Long userId) {
        SysUserGetByUserIdQuery query = new SysUserGetByUserIdQuery();
        query.setUserId(userId);
        return sysUserService.get(query);
    }

    @GetMapping("/user")
    @RequiresPermissions("sys:user:list")
    public Response list() {
        SysUserListQuery sysUserListQuery = new SysUserListQuery();
        return sysUserService.list(sysUserListQuery);
    }

    @PutMapping(value = "/user")
    @RequiresPermissions("sys:user:update")
    public Response update(SysUserCO sysUserCO) {
        SysUserUpdateCmd sysUserUpdateCmd = new SysUserUpdateCmd();
        sysUserUpdateCmd.setSysUserCO(sysUserCO);
        return sysUserService.update(sysUserUpdateCmd);
    }

    @PostMapping(value = "/user")
    @RequiresPermissions("sys:user:save")
    public Response save(@RequestBody SysUserCO sysUserCO) {
        SysUserSaveCmd sysUserSaveCmd = new SysUserSaveCmd();
        sysUserSaveCmd.setSysUserCO(sysUserCO);
        Response save = sysUserService.save(sysUserSaveCmd);
        return save;
    }

    @DeleteMapping(value = "/user/{userId}")
    @RequiresPermissions("sys:user:delete")
    public Response delete(@PathVariable("userId") Long userId) {
        SysUserDeleteCmd sysUserDeleteCmd = new SysUserDeleteCmd();
        sysUserDeleteCmd.setUserId(userId);
        return sysUserService.delete(sysUserDeleteCmd);
    }
}
