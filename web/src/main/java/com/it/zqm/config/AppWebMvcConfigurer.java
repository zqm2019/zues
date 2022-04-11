package com.it.zqm.config;

import com.alibaba.fastjson.JSON;
import com.it.zqm.result.Result;
import com.it.zqm.enums.ResultCodeEnum;
import com.it.zqm.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Configuration
@Slf4j
public class AppWebMvcConfigurer implements WebMvcConfigurer {

    /**
     * 统一异常处理方式
     *
     * @param resolvers
     */
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add((request, response, handler, e) -> {
            Result result = new Result();
            if (e instanceof MethodArgumentNotValidException) {
                result.setCode(ResultCodeEnum.FAIL_ERROR.getCode());
                result.setMessage(handlerNotValidException((MethodArgumentNotValidException) e));
            } else if (e instanceof IllegalArgumentException) {
                result.setCode(ResultCodeEnum.FAIL_ERROR.getCode());
                result.setMessage(e.getMessage());
            } else if (e instanceof BaseException) {
                int code = ((BaseException) e).getCode() > 0 ? ((BaseException) e).getCode() : ResultCodeEnum.CUSTOM_ERROR.getCode();
                result.setCode(code).setMessage(e.getMessage());
            } else if (e instanceof HttpRequestMethodNotSupportedException) {
                result.setCode(ResultCodeEnum.NOT_SUPPORT_METHOD.getCode()).setMessage("接口：" + request.getRequestURI() +
                        ((HttpRequestMethodNotSupportedException) e).getMethod()
                        + e.getMessage());
            } else if (e instanceof NoHandlerFoundException) {
                result.setCode(ResultCodeEnum.NOT_FOUND.getCode()).setMessage("接口 [" + request.getRequestURI() + "] 不存在!");
            } else {
                result.setCode(ResultCodeEnum.INTERNAL_SERVER_ERROR.getCode()).setMessage(
                        "接口[" + request.getRequestURI() + "]内部错误，请联系管理员!");
                String message;
                if (handler instanceof HandlerMethod) {
                    HandlerMethod handlerMethod = (HandlerMethod) handler;
                    message = String.format("接口 [%s] 异常，方法是 %s.%s,异常摘要: %s",
                            request.getRequestURI(),
                            handlerMethod.getBean().getClass().getName(),
                            handlerMethod.getMethod().getName(),
                            e.getMessage());
                } else {
                    message = e.getMessage();
                }
                log.error(message, e);
            }
            handlerResponseResult(response, result);
            return new ModelAndView();
        });

    }

    private void handlerResponseResult(HttpServletResponse response, Object result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 处理参数校验异常的处理
     *
     * @param e
     * @return
     */
    private String handlerNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        StringBuilder errorBuilder = new StringBuilder("参数异常：");
        fieldErrors.forEach(errors -> {
                    errorBuilder.append(errors.getField());
                    errorBuilder.append(errors.getDefaultMessage());
                    errorBuilder.append(";");
                }
        );
        return errorBuilder.toString();
    }

    // 解决跨域问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowCredentials(true).allowedMethods("*");
    }
}
