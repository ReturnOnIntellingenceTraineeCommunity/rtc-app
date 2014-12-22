package net.github.rtc.app.controller.common;

import net.github.rtc.app.model.user.Role;
import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class LoginControllerTest {

    @InjectMocks
    private LoginController controller;

    @Mock
    private UserService userService;

    private User user;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.user = new User();
        user.setName("name");
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(RoleType.ROLE_ADMIN));
        user.setAuthorities(roles);
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null);
        SecurityContextHolder.getContext().setAuthentication(auth);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testLogin() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("content"));
    }

    @Test
    public void testLoginError() throws Exception {
        mockMvc.perform(get("/loginfailed"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("error"));
    }

    @Test
    public void testLogout() throws Exception {
        mockMvc.perform(get("/logout"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("content"));
    }


    @Test
    public void testLoginAttempt() throws Exception {
        when(userService.loadUserByUsername(SecurityContextHolder
                .getContext().getAuthentication().getName())).thenReturn(user);
        mockMvc.perform(get("/login_attempt"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/admin"));
    }
}
