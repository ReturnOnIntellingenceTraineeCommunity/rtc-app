package net.github.rtc.app.service.social;

import net.github.rtc.app.model.entity.user.User;
import net.github.rtc.app.service.social.user.VkUserFactoryImpl;
import org.springframework.social.connect.Connection;
import org.springframework.social.vkontakte.api.VKontakte;
import org.springframework.stereotype.Component;

@Component
public class SocialUserProvider {


    @SuppressWarnings("unchecked")
    public User getUser(Connection<?> connection) {
        User newUser = null;
        if (connection.getApi() instanceof VKontakte) {
            final VkUserFactoryImpl vkUserFactory = new VkUserFactoryImpl();
            newUser = vkUserFactory.getUser((Connection<VKontakte>) connection);
        }
        return newUser;
    }
}
