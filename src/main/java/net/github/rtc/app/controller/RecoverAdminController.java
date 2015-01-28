package net.github.rtc.app.controller;


import net.github.rtc.app.model.user.EnglishLevel;
import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.model.user.UserStatus;
import net.github.rtc.app.service.date.DateService;
import net.github.rtc.app.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;

@Controller("recoverAdminController")
@RequestMapping("recover")
public class RecoverAdminController {

    private static final String STRING_ADMIN = "admin";
    private static final String ADMIN_EMAIL = "admin@email.com";

    @Autowired
    private UserService userService;
    @Autowired
    private DateService dateService;

    @RequestMapping(method = RequestMethod.GET)
    public String recoverAdmin() {
        final List<User> admins = userService.getUserByRole(RoleType.ROLE_ADMIN);
        if (admins.get(0).isForRemoval()) {
            userService.deleteByCode(admins.get(0).getCode());
        }
        if (admins.size() == 0 || admins.get(0).isForRemoval()) {
            final User admin = new User("TestName", "TestMiddlename", "TestSurname", ADMIN_EMAIL, STRING_ADMIN);
            admin.setAuthorities(Arrays.asList(userService.getRoleByType(RoleType.ROLE_ADMIN)));
            admin.setRegisterDate(dateService.getCurrentDate());
            admin.setGender("Male");
            admin.setPhone("123456");
            admin.setNote("note");
            admin.setBirthDate(dateService.getCurrentDate());
            admin.setStatus(UserStatus.ACTIVE);
            admin.setEnglish(EnglishLevel.INTERMEDIATE);
            admin.setCity("Dp");
            admin.setUniversity("");
            admin.setFaculty("");
            admin.setSpeciality("");
            userService.create(admin);
        }
        return "redirect:/";
    }

}
