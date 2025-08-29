package com.scm.scm20.components.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;
/**
 * This class is to be loaded as a bean. It is used to remove the alert from the session object. This method will be called from the thymeleaf block of alert.html
 */
@Component
public class SessionHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionHelper.class);
    public static void removeMessage(){
        try {
            HttpSession session = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
            session.removeAttribute("alert");
            LOGGER.info("Message removed successfully");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}
