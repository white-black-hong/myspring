package com.whz;

/**
 * @auther whz
 * @create 2022-02-28 11:25
 */

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * 自定义的SessionManager
 * DefaultSessionManager:用于JavaSE环境
 * ServletContainerSessionManager:用于Web环境，直接使用servlet容器的会话。
 * DefaultWebSessionManager:用于web环境，自己维护会话(自己维护着会话，直接废弃了Servlet容器的
 * 会话管理)。
 */
public class CustomSessionManager extends DefaultWebSessionManager {

    private String sessionId;

    public CustomSessionManager(String sessionId)
    {
        this.sessionId = sessionId;
    }

    /**
     * 头信息中具有sessionId
     * 请求头：Authorization:sessionid
     * 指定sessionId的获取方法
     */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        // 获取请求头Authorization的数据
        String id = WebUtils.toHttp(request).getHeader(sessionId);
        if (StringUtils.isEmpty(id)) {
            // 如果没有携带，生成新的sessionId
            return super.getSessionId(request, response);
        } else {
            // 返回sessionId;
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "header");
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return id;
        }
    }
}
