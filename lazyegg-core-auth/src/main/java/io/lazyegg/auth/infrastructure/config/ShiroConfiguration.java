package io.lazyegg.auth.infrastructure.config;

import io.lazyegg.auth.infrastructure.config.oauth.OAuth2Filter;
import io.lazyegg.auth.infrastructure.config.simple.UsernamePasswordCredentialMatcher;
import io.lazyegg.auth.infrastructure.config.simple.UsernamePasswordFilter;
import io.lazyegg.exception.GlobalFilter;
import io.lazyegg.exception.GlobalHandlerExceptionResolver;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ShiroConfig
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/15 9:12 下午
 */
@Slf4j
public class ShiroConfiguration {

    @Resource
    private UsernamePasswordCredentialMatcher usernamePasswordCredentialMatcher;

    /**
     * 注入安全管理器
     * @return SecurityManager
     */
    @Bean("securityManager")
    public SecurityManager securityManager(AuthorizingRealm realm) {
        // 将自定义 Realm 加进来
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(realm);
        log.info("securityManager注册完成");
        return securityManager;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 默认登录url
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后跳转页面
        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 未授权界面，权限认证失败会访问该 URL
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");

        LinkedHashMap<String, Filter> filters = new LinkedHashMap<>();

//        filters.put("global", new GlobalFilter());
        filters.put("simple", new UsernamePasswordFilter());
        filters.put("oauth2", new OAuth2Filter());
        shiroFilterFactoryBean.setFilters(filters);
        // LinkedHashMap 是有序的，进行顺序拦截器配置
        Map<String, String> filterChainMap = new LinkedHashMap<>();
        // 配置可以匿名访问的地址，可以根据实际情况自己添加，放行一些静态资源等，anon 表示放行
        filterChainMap.put("/css/**", "anon");
        filterChainMap.put("/img/**", "anon");
        filterChainMap.put("/js/**", "anon");
        filterChainMap.put("/swagger-*/**", "anon");
        filterChainMap.put("/swagger-ui.html/**", "anon");
        filterChainMap.put("/oauth/**", "anon");
        // 登录 URL 放行
        filterChainMap.put("/login", "anon");
        // 配置 logout 过滤器
        filterChainMap.put("/logout", "anon");

        filterChainMap.put("/**", "simple");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        return shiroFilterFactoryBean;
    }

    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }


}
