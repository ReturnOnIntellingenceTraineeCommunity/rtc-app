package net.github.rtc.app.controller.common;

import net.github.rtc.app.model.entity.user.EnglishLevel;
import net.github.rtc.app.model.entity.user.User;
import net.github.rtc.app.service.social.SocialUserProvider;
import net.github.rtc.app.service.user.UserService;
import net.github.rtc.app.utils.enums.EnumHelper;
import net.github.rtc.app.utils.propertyeditors.CustomTypeEditor;
import net.github.rtc.util.converter.ValidationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private static final String USER = "user";
    private static final String REDIRECT_LOGIN = "redirect:/login/";
    private static final String VALIDATION_RULES = "validationRules";
    private static final String VIEW_NAME = "register/register";
    private static final String ENGLISH = "english";

    @Autowired
    private ValidationContext validationContext;
    @Autowired
    private UserService userService;
    @Autowired
    private SocialUserProvider socialUserProvider;
    private final  ProviderSignInUtils providerSignInUtils = new ProviderSignInUtils();

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView openRegisterPage() {
        final ModelAndView mav = new ModelAndView(VIEW_NAME);
        mav.addObject(VALIDATION_RULES, validationContext.get(User.class));
        return mav;
    }

    @RequestMapping(value = "/implicit", method = RequestMethod.GET)
    public ModelAndView signupForm(WebRequest request) {
        final Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
        if (connection != null) {
            final ModelAndView mav = new ModelAndView("/portal/user/profile/profile");

            final User user = socialUserProvider.getUser(connection);
            final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, null);
            SecurityContextHolder.getContext().setAuthentication(token);
            mav.addObject(USER, user);
            mav.addObject("courses", new ArrayList<>());
            return mav;
        } else {
            return new ModelAndView("redirect: /register");
        }
    }

    @RequestMapping(value = "/save", headers = "content-type=multipart/*", method = RequestMethod.POST)
    public String save(@ModelAttribute(USER) final User user,
                       @RequestParam(value = "uploadPhoto", required = false) MultipartFile img,
                       RedirectAttributes redirectAttributes) {
        userService.registerUser(user, img);
        return REDIRECT_LOGIN;
    }

    @ModelAttribute(USER)
    public User getCommandObject() {
        return new User();
    }

    @InitBinder(USER)
    public void initBinder(final WebDataBinder binder) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(Collection.class, new CustomTypeEditor());
    }

    @ModelAttribute(ENGLISH)
    public List<EnglishLevel> getEnglish() {
        return EnumHelper.findAll(EnglishLevel.class);
    }
}
