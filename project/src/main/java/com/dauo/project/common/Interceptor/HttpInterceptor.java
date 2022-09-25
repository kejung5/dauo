package com.dauo.project.common.Interceptor;

import com.dauo.project.common.code.ErrorCode;
import com.dauo.project.common.config.properties.ApplicationProps;
import com.dauo.project.common.exception.ApiException;
import com.dauo.project.common.utils.IpUtils;
import com.dauo.project.domain.authenticate.LimitRateService;
import io.github.bucket4j.Bucket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
@RequiredArgsConstructor
public class HttpInterceptor implements HandlerInterceptor {
    private final ApplicationProps props;

    private LimitRateService limitRateService = new LimitRateService();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestIp = IpUtils.getClientIp(request);
        if (isAccessible(requestIp)) {
            Bucket bucket = limitRateService.resolveBucket(requestIp);
            if (bucket.tryConsume(1)) {
                return true;
            } else {
                throw new ApiException(ErrorCode.TOO_MANY_REQUESTS);
            }
        }

        throw new ApiException(ErrorCode.ACCESS_DENIED);
    }

    /**
     * 허용된 ip인지 확인
     * @param ipAddress
     * @return
     */
    private boolean isAccessible(String ipAddress) {
        List<String> ipAddressMatchers = props.getAllowIps();
        return ipAddressMatchers.stream().anyMatch(matcher -> matcher.matches(ipAddress));
    }
}
