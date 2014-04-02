package net.github.rtc.web.courses.service.impl;

import net.github.rtc.web.courses.exception.ServiceProcessingException;
import net.github.rtc.web.courses.model.Courses;
import net.github.rtc.web.courses.resource.CoursesResource;
import net.github.rtc.web.courses.service.CoursesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

/**
 * Service Implementation
 * This's a wrapper for {@link CoursesService}
 *
 * @author Vladislav Pikus
 * @author Dmitry Pritula
 */
@Service("coursesService")
public class CoursesServiceImpl implements CoursesService {

    private static Logger LOG = LoggerFactory.getLogger(CoursesServiceImpl.class.getName());

    @Autowired
    public void setResource(CoursesResource resource) {
        this.resource = resource;
    }

    private CoursesResource resource;

    /**
     * @see CoursesService#findAll()
     */
    @Override
    public Collection<Courses> findAll() {
        return resource.findAll();
    }

    /**
     * Check course code for null
     *
     * @param code course code
     * @throws ServiceProcessingException
     */
    private void checkCode(String code) {
        if (code == null) {
            RuntimeException ex = new ServiceProcessingException("Course code can't be null");
            LOG.error("Exception: ", ex);
            throw ex;
        }
    }

    /**
     * @see CoursesService#delete(String)
     */
    @Override
    public void delete(String code) {
        checkCode(code);
        resource.delete(code);
    }

    /**
     * @see CoursesService#findByCode(String)
     */
    @Override
    public Courses findByCode(String code) {
        checkCode(code);
        return resource.findByCode(code);
    }

    /**
     * @see CoursesService#create(Courses)
     */
    @Override
    public Courses create(Courses course) {
        return resource.create(course);
    }

    /**
     * @see CoursesService#update(Courses)
     */
    @Override
    public void update(Courses course) {
        resource.update(course);
    }

    /**
     * @see CoursesService#findByFilter(Map)
     */
    @Override
    public Collection<Courses> findByFilter(Map<String, String> filter) {
        return resource.findByFilter(filter);
    }

    @Override
    public int getCount() {
        return resource.getCount();
    }
}
