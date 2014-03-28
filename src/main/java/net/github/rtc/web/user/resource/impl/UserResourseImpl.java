/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.github.rtc.web.user.resource.impl;

//import net.github.rtc.web.courses.resource.impl.*;
import net.github.rtc.web.user.model.User;
import java.util.Arrays;
import java.util.Collection;
import net.github.rtc.web.user.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Саша
 */
@Component("userDao")
public class UserResourseImpl implements UserResource{
    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String hostUserUrl;

    public void setHostUserUrl(String hostUserUrl) {
        this.hostUserUrl = hostUserUrl;
    }

    public String getHostUserUrl() {
        return hostUserUrl;
    }

    /**
     * @param id course ID
     * @return null if not found or course's object if found
     * @see net.github.rtc.web.courses.resource.CoursesResource
     */
    @Override
    public User findById(Integer id) {
        System.out.println();
        System.out.println("bgfbgf  " +hostUserUrl);
        User user = null;
        try{
            user = restTemplate.getForObject(hostUserUrl + "{id}", User.class, id);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return user;
       
    }

    /**
     * @return collection of courses
     * @see net.github.rtc.web.courses.resource.CoursesResource
     */
    @Override
    public Collection<User> findAll() {
        return Arrays.asList(restTemplate.getForObject(hostUserUrl, User[].class));
    }
    
    /**
     * @param id course ID
     * @see net.github.rtc.web.courses.resource.CoursesResource
     */
    @Override
    public void delete(Integer id) {
        restTemplate.delete(hostUserUrl + "user/{fio}", id);
    }

    @Override
    public User create(User user) {
        return restTemplate.postForObject(hostUserUrl, user, User.class);
    }

    @Override
    public void update(User user) {
        restTemplate.put(hostUserUrl + "user/{fio}", user, user.getFio());
    }
}
