package com.demo.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.demo.common.Constants;
 
/**
 * 
 * 
 * 通用拦截器
 * 
 * 
 * 池超凡
 * 
 * 2015年6月4日 上午12:24:28
 * 
 * @version 1.0.0
 *
 */
 
public class CommonInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (StringUtils.isEmpty(request.getParameter(Constants.BACK_URL))&&request.getAttribute(Constants.BACK_URL) == null) {
            request.setAttribute(Constants.BACK_URL, extractBackURL(request));
        }
        return true;
        
    }
    private String extractBackURL(HttpServletRequest request) {
        String url = request.getParameter(Constants.BACK_URL);

  

        if (StringUtils.isEmpty(url)) {
            url = request.getHeader("Referer");
        }

        if(!StringUtils.isEmpty(url) && (url.startsWith("http://") || url.startsWith("https://"))) {
            return url;
        }

        if (!StringUtils.isEmpty(url) && url.startsWith(request.getContextPath())) {
            url = getBasePath(request) + url;
        }
        return url;
    }
    private String getBasePath(HttpServletRequest req) {
        StringBuffer baseUrl = new StringBuffer();
        String scheme = req.getScheme();
        int port = req.getServerPort();

        //String        servletPath = req.getServletPath ();
        //String        pathInfo = req.getPathInfo ();

        baseUrl.append(scheme);        // http, https
        baseUrl.append("://");
        baseUrl.append(req.getServerName());
        if ((scheme.equals("http") && port != 80) || (scheme.equals("https") && port != 443)) {
            baseUrl.append(':');
            baseUrl.append(req.getServerPort());
        }
        return baseUrl.toString();
    }
}
