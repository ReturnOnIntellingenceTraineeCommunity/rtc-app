package net.github.rtc.app.stabs.resource;

import net.github.rtc.app.model.Course;
import net.github.rtc.app.model.CourseDto;
import net.github.rtc.app.profile.Static;
import net.github.rtc.app.resource.CoursesResource;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author Vladislav Pikus
 */
@Component("coursesDao")
@Static
public class CourseResourceImpl implements CoursesResource {
    @Override
    public Course findByCode(String code) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(String code) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Course create(Course course) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(Course course) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public CourseDto findByFilter(String query) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
