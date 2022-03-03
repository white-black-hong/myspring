package com.whz.config;

import com.whz.CustomSessionManager;
import com.whz.shiro.filter.FormFilter;
import com.whz.shiro.realm.CustomRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @auther whz
 * @create 2022-02-28 09:26
 * 把CustomReal和SecurotyManager等注册到spring容器中
 */
@Configuration
public class ShiroConfig {

//    @Value("$spring.redis.host")
//    private String host;
//    @Value("$spring.redis.port")
//    private int port;

    /**
     * redis的控制器，获取权限，操作redis 配置shiro redisManager
     * @return
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("127.0.0.1");
        redisManager.setPort(6379);
        redisManager.setPassword("XpVFvpTuo4T0q8P8JQWnRdTz2EymvnU08WAWvwbw");
        return redisManager;
    }

    /**
     * 缓存管理器 CacheManager缓存 redis实现
     */
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * @return
     */
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * 会话管理器
     * @return
     */
    public DefaultWebSessionManager sessionManager() {
        CustomSessionManager sessionManager = new CustomSessionManager("sid");
//        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }



    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defalutAPP = new DefaultAdvisorAutoProxyCreator();
        defalutAPP.setProxyTargetClass(true);
        return defalutAPP;
    }

    /**
     * 1. 将自己的验证方式加入容器；创建Realm
     */
    @Bean
    public CustomRealm myShiroRealm() {
        CustomRealm customRealm = new CustomRealm();
        customRealm.setCachingEnabled(true); // 开启缓存
        customRealm.setCacheManager(redisCacheManager()); // 注入缓存管理器
        return customRealm;
    }

    /**
     * 2. 权限管理，配置主要是Realm的管理认证；创建安全管理器
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        // 将自定义的会话管理器注册到安全管理器中
        securityManager.setSessionManager(sessionManager());
        // 将自定义的redis缓存管理器注册到安全管理器中
        securityManager.setCacheManager(redisCacheManager());
        return securityManager;
    }

    /**
     * 3. Filter工厂，设置对应的过滤条件和跳转条件
     * 在web程序中，shiro进行权限控制全部是通过一族过滤器集合进行控制
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 自定义拦截器
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        filters.put("authc", new FormFilter());

        Map<String, String> map = new LinkedHashMap<>();
        // 登出
        map.put("/logout", "logout");
        map.put("/login/**", "anon");
        shiroFilterFactoryBean.setLoginUrl("/login");
        //首页
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //错误页面，认证不通过跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
        // 对所有用户认证
        map.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    /**
     * 开启Shiro注解支持
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 启动Shiro自定义过滤器
     */
    @Bean
    public FilterRegistrationBean shiroFilterRegistration() {

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new DelegatingFilterProxy("shiroFilter")); // 注册自定义过滤器
        registrationBean.addInitParameter("targetFilterLifecycle", "true");
        registrationBean.setEnabled(true);
        registrationBean.setOrder(Integer.MAX_VALUE); // 优先级，越低越优先
        registrationBean.addUrlPatterns("/*"); // 过滤所有路径
        return registrationBean;
    }

}
