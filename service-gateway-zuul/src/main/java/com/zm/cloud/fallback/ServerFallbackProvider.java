package com.zm.cloud.fallback;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * zuul 服务回退(服务降级)
 * spring cloud Edgware 版本之前实现 ZuulFallbackProvider 接口即可实现回退功能
 * 其中fallbackResponse()方法允许程序员在回退处理中重建输出对象，
 * 通常是输出“xxx服务不可用，请稍候重试”之类的提示，但是无法捕获到更详细的出错信息，排错很不方便。
 *
 * （猜测）为了解决这个问题spring-cloud团队在Edgware版本中将该接口标记为过时@Deprecated，
 * 同时在它下面派生出了一个新接口: FallbackProvider。
 * FallbackProvider接口注释：Extension of ZuulFallbackProvider which adds possibility to choose proper response
 *                          based on the exception which caused the main method to fail.
 *                          扩展自ZuulFallbackProvider接口，增加了导致主要方法异常，选择更多错误信息响应的可能性。
 */
@Component
public class ServerFallbackProvider implements FallbackProvider {

    private static final Logger logger = LoggerFactory.getLogger(ServerFallbackProvider.class);

    /**
     * 返回服务id
     * 如果需要所有调用都支持回退
     * 则return "*" 或 return null
     * @return string
     * @see FallbackProvider#getRoute()
     */
    @Override
    public String getRoute() {
        return "*";
    }

    /**
     * 请求服务失败回退处理逻辑
     * @return response
     * @see FallbackProvider#fallbackResponse()
     */
    @Override
    public ClientHttpResponse fallbackResponse() {
        return null;
    }

    /**
     * 请求服务失败回退处理逻辑
     * @return response
     * @see FallbackProvider#fallbackResponse(Throwable)
     */
    @Override
    public ClientHttpResponse fallbackResponse(final Throwable cause) {
        logger.error("Error:", cause);
        return new ClientHttpResponse() {

            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;// 状态码
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return this.getStatusCode().value(); //状态码数值 200 404
            }

            @Override
            public String getStatusText() throws IOException {
                return this.getStatusCode().getReasonPhrase(); ///状态码描述短语
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                String reason = "请求失败";
                if (cause != null && cause.getCause() != null) {
                    reason = cause.getCause().getMessage();

                }
                JSONObject json = new JSONObject();
                json.put("status", "ERROR");
                json.put("code", "500");
                json.put("message", reason);
                return new ByteArrayInputStream(json.toJSONString().getBytes("utf-8"));
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                MediaType type = new MediaType("application", "json", Charset.forName("utf-8"));
                headers.setContentType(type);
                return headers;
            }
        };
    }
}
