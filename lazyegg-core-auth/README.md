# LazyEgg auth

- xss
- shiro

```markdown
LazyEgg-auth
├── adapter      适配器
├── app       项目启动工程
├── client       项目启动工程
├── domain   
│   ├── logger     业务系统日志
│   |   ├── xxxx     xxx
│   └── xxxx   
└──infrastructure 基础设施
    ├── config     配置
    |   ├── xxxx     xxx
    └── xxxx   
```

## 开发注意事项
1. Controller中，请求注解要写在其他注解之前，否则访问日志有可能无法拦截到请求。
```java
@GetMapping("/user/{userId}")
@RequiresRoles("admin")
@RequiresPermissions("sys:user:info")
public Response get(@PathVariable Long userId) {
    SysUserGetByUserIdQuery query = new SysUserGetByUserIdQuery();
    query.setUserId(userId);
    return sysUserService.get(query);
}

```


```java
用户    前端     后端
login  -> 页面
xxx为登录 -> login?xxxx

```
