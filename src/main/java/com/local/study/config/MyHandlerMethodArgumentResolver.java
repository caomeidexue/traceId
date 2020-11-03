package com.local.study.config;

import com.local.study.annotation.RequestUser;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/8/17 6:17 下午
 */

public class MyHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        boolean b = methodParameter.getParameterAnnotation(RequestUser.class) != null;
        return b;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        Object paramVal = null;

        if (methodParameter.hasParameterAnnotation(RequestUser.class)) {
            RequestUser paramCombin = methodParameter.getParameterAnnotation(RequestUser.class);

            String paramName = paramCombin.name();

            if (!Optional.ofNullable(paramName)
                    .isPresent() || paramName.isEmpty()) {
                paramName = methodParameter.getParameterName();
            }
            if (paramName == null) {
                throw new IllegalArgumentException(
                        "Specified name must not resolve to null: [" + paramName + "]");
            }
            HttpServletRequest request = nativeWebRequest
                    .getNativeRequest(HttpServletRequest.class);
            paramVal = request.getHeader(paramName);

            if (webDataBinderFactory != null) {
                WebDataBinder binder = webDataBinderFactory.createBinder(nativeWebRequest, null, paramName);
                try {
                    paramVal = binder.convertIfNecessary((Object) paramVal, methodParameter.getParameterType(), methodParameter);
                } catch (Exception ex) {
                    System.out.println((String.format("转化数据出现错误，原因:%s！", ex.getMessage())));
                    throw ex;
                }
            }

            if (paramVal == null && paramCombin.required()) {
                throw new Exception("");
            }
        }

        return paramVal;
    }
}
