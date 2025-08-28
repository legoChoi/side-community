package com.side.community.common.resolver;

import com.side.community.common.annotation.UserPrincipal;
import com.side.community.common.constants.RequestAttributeConstants;
import com.side.community.common.exception.CustomRuntimeException;
import com.side.community.common.exception.type.UserExceptionType;
import com.side.community.user.entity.User;
import com.side.community.user.repository.UserJpaRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class UserPrincipalArgumentResolver implements HandlerMethodArgumentResolver {

    private final UserJpaRepository userJpaRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(UserPrincipal.class)
                && parameter.getParameterType().equals(User.class);
    }

    @Override
    public User resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        Long userId = (Long) request.getAttribute(RequestAttributeConstants.ATTRIBUTE_USER_ID);

        return userJpaRepository.findById(userId)
                .orElseThrow(() -> new CustomRuntimeException(UserExceptionType.USER_NOT_FOUND));
    }
}
