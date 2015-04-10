package net.github.rtc.app.service.social.user;

import net.github.rtc.app.model.entity.user.User;
import org.springframework.social.connect.Connection;
import org.springframework.social.vkontakte.api.VKontakte;
import org.springframework.social.vkontakte.api.VKontakteProfile;

public class VkUserFactoryImpl implements SocialUserFactory<VKontakte> {

    @Override
    public User getUser(Connection<VKontakte> connection) {
        final User user = new User();
        final VKontakteProfile vKontakteProfile = connection.getApi().usersOperations().getUser();
        user.setName(vKontakteProfile.getFirstName());
        user.setSurname(vKontakteProfile.getLastName());
        user.setPhoto(vKontakteProfile.getPhoto200());
        return user;
    }
}
