package io.lazyegg.auth.infrastructure.config;

import io.lazyegg.auth.infrastructure.shiro.filter.OAuth2Filter;
import io.lazyegg.auth.infrastructure.shiro.filter.StatelessFilter;
import io.lazyegg.auth.infrastructure.shiro.filter.TokenFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.*;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.util.*;

/**
 * ShiroConfig
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/15 9:12 下午
 */
@Slf4j
public class ShiroConfiguration {

    @Resource
    private StatelessFilter statelessFilter;
    @Resource
    private TokenFilter tokenFilter;

    @Bean("sessionManager")
    public DefaultSessionManager defaultSessionManager() {
        DefaultSessionManager defaultSessionManager = new DefaultSessionManager();
        defaultSessionManager.setSessionValidationSchedulerEnabled(false);
        return defaultSessionManager;
    }

    @Bean("subjectDAO")
    public DefaultSubjectDAO defaultSubjectDAO() {
        DefaultSubjectDAO defaultSubjectDAO = new DefaultSubjectDAO();
        return defaultSubjectDAO;
    }

    /**
     * 注入安全管理器
     *
     * @return SecurityManager
     */
    @Bean("securityManager")
    public SecurityManager securityManager(Collection<Realm> realm, SessionManager sessionManager) {
        // 将自定义 Realm 加进来
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(realm);
        SubjectDAO subjectDAO = securityManager.getSubjectDAO();
        if (subjectDAO instanceof DefaultSubjectDAO) {
            DefaultSubjectDAO defaultSubjectDAO = (DefaultSubjectDAO) subjectDAO;
            SessionStorageEvaluator sessionStorageEvaluator = defaultSubjectDAO.getSessionStorageEvaluator();
            if (sessionStorageEvaluator instanceof DefaultSessionStorageEvaluator) {
                DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = (DefaultSessionStorageEvaluator) sessionStorageEvaluator;
                defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
            }
        }
        // 禁止创建会话
        securityManager.setSubjectFactory(subjectContext -> {
            subjectContext.setSessionCreationEnabled(false);
            return new DefaultWebSubjectFactory().createSubject(subjectContext);
        });
        securityManager.setSessionManager(sessionManager);

        log.info("realm注入securityManager完成");
        return securityManager;
    }


    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        LinkedHashMap<String, Filter> filters = new LinkedHashMap<>();
        filters.put("statelessAuthc", statelessFilter);
        filters.put("oauth2", new OAuth2Filter());
        filters.put("token", tokenFilter);
        shiroFilterFactoryBean.setFilters(filters);
        // LinkedHashMap 是有序的，进行顺序拦截器配置
        Map<String, String> filterChainMap = new LinkedHashMap<>();
        // 配置可以匿名访问的地址，可以根据实际情况自己添加，放行一些静态资源等，anon 表示放行
        // 登录放行
//        filterChainMap.put("/ssl", "ssl");
        filterChainMap.put("/session", "anon");
        filterChainMap.put("/index", "anon");
        // 退出放行
        filterChainMap.put("/logout", "logout");
        filterChainMap.put("/oauth", "oauth2");

        filterChainMap.put("/**", "token");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        return shiroFilterFactoryBean;
    }


    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean(SecurityManager securityManager) {
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        methodInvokingFactoryBean.setArguments(securityManager);
        return methodInvokingFactoryBean;
    }

}
