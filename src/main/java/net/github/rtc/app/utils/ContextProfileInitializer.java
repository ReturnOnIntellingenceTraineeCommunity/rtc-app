package net.github.rtc.app.utils;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.context.ConfigurableWebApplicationContext;

public class ContextProfileInitializer implements ApplicationContextInitializer<ConfigurableWebApplicationContext> {

    //workaround to use tomcat7:run coz it don't use maven filtering in web.xml
    public void initialize(ConfigurableWebApplicationContext ctx) {
        ConfigurableEnvironment environment = ctx.getEnvironment();
        String environmentType = ctx.getServletContext().getInitParameter("environment.type");
        if(!environmentType.equals("${environment.type}")) {
            environment.setActiveProfiles(environmentType);
        } else {
            environment.setActiveProfiles("dev");
        }
    }
}