package com.gioov.nimrod.common.security;

import com.gioov.common.web.http.SuccessEntity;
import com.gioov.nimrod.common.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author godcheese
 */
@Component
public class AuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {

    @Autowired
    private Common common;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        SimpleUser simpleUser = (SimpleUser) authentication.getPrincipal();
        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        PrintWriter printWriter = httpServletResponse.getWriter();
        printWriter.write(common.objectToJson(new SuccessEntity(simpleUser)));
        printWriter.flush();
        printWriter.close();
    }
}
