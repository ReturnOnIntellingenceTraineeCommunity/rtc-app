package net.github.rtc.app.controller.admin;

import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.course.CourseStatus;
import net.github.rtc.app.model.course.CourseType;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.CourseService;
import net.github.rtc.app.service.UserService;
import net.github.rtc.app.utils.datatable.search.CourseSearchFilter;
import net.github.rtc.app.utils.datatable.search.SearchResults;
import net.github.rtc.app.utils.propertyeditors.CustomStringEditor;
import net.github.rtc.app.utils.propertyeditors.CustomTagsEditor;
import net.github.rtc.util.converter.ValidationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Controller for {@link net.github.rtc.app.model.course.Course}
 *
 * @author Vladislav Pikus
 */
@Controller("coursesController")
@RequestMapping("admin/course")
public class CoursesController {

    private static final int COURSES_PER_PAGE = 5;
    private static final String ROOT = "portal/admin";
    private static final String STRING_COURSE = "course";
    private static final String PATH_PAGE_LISTCONTENT = "/page/listcontent";
    private static final String STRING_TYPES = "types";
    private static final String STRING_STATUSES = "statuses";
    private static final String STRING_FILTER_COURSE = "filterCourse";
    private static final String REDIRECT = "redirect:/";
    private static final String STRING_ADMIN = "admin";
    private static final String STRING_VALIDATION_RULES = "validationRules";


    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    private ValidationContext validationContext;

    /**
     * Processes the request to view all courses page
     *
     * @return modelAndView("admin/courses/courses")
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {
        final CourseSearchFilter filter = getFilterCourse();
        filter.setPage(1);
        return switchPage(filter);
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public ModelAndView filter(@ModelAttribute(STRING_FILTER_COURSE) final
                               CourseSearchFilter filterCourse) {
        return switchPage(filterCourse);
    }


    @RequestMapping(value = "/switch", method = RequestMethod.POST)
    public @ResponseBody ModelAndView switchPage(@ModelAttribute(STRING_FILTER_COURSE) final CourseSearchFilter filterCourse) {
        final ModelAndView mav = new ModelAndView(ROOT + PATH_PAGE_LISTCONTENT);
        final SearchResults<Course> results = courseService.search(filterCourse);
        mav.addAllObjects(results.getPageModel());
        mav.addObject("courses", results.getResults());
        mav.addObject(STRING_TYPES, CourseType.findAll());
        mav.addObject(STRING_STATUSES, getStatuses());
        mav.addObject(STRING_FILTER_COURSE, filterCourse);
        return mav;
    }

    /**
     * Processes the request to delete by id
     * Url example: "/delete/1". Parse by pattern: "/delete/{courseId}"
     * If all is well, we get redirected to "course"
     *
     * @param courseCode course ID
     * @return redirect to "/admin/courses"
     */
    @RequestMapping(value = "/delete/{courseCode}", method = RequestMethod.GET)
    public String delete(@PathVariable final String courseCode) {
        courseService.delete(courseCode);
        return REDIRECT
                + STRING_ADMIN;
    }

    @RequestMapping(value = "/publish/{courseCode}", method = RequestMethod.GET)
    public String publish(@PathVariable final String courseCode) {
        courseService.publish(courseService.findByCode(courseCode));
        return REDIRECT
                + STRING_ADMIN;
    }

    /**
     * Process the request to get details about course by selected code
     * URL example: "/1". Parse by pattern: "/{code}"
     * if success go to view "admin/courses/course")
     *
     * @param courseCode course code
     * @return modelAndView("admin/courses/course")
     */
    @RequestMapping(value = "view/{courseCode}", method = RequestMethod.GET)
    public ModelAndView single(@PathVariable final String courseCode) {
        final ModelAndView mav = new ModelAndView(ROOT
                + "/page/courseContent");
        final Course course = courseService.findByCode(courseCode);
        mav.addObject(STRING_COURSE, course);
        return mav;
    }

    /**
     * Process the request to get create course form
     *
     * @return modelAndView("admin/courses/layout")
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        final ModelAndView mav = new ModelAndView(ROOT
                + "/page/createContent");
        mav.addObject(STRING_VALIDATION_RULES, validationContext.get(Course
                .class));
        return mav;
    }

    /**
     * Process the request to post entered course in the form
     *
     * @param course course object
     * @return if all is OK the redirect to view new course or return to edit
     * course
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(
            @ModelAttribute(STRING_COURSE) final Course course,
            @RequestParam(value = "expertList",
                    required = false) final List<String> expertList) {
        course.setExperts(bindExperts(expertList));
        courseService.create(course);
        return "redirect:/admin/course/";
    }

    /**
     * Process the request to get edit course form
     *
     * @return modelAndView("admin/courses/layout")
     */
    @RequestMapping(value = "/{courseCode}/update", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable final String courseCode) {
        final ModelAndView mav = new ModelAndView(ROOT
                + "/page/updateContent");
        mav.getModelMap().addAttribute(STRING_COURSE,
                courseService.findByCode(courseCode));
        mav.addObject(STRING_VALIDATION_RULES, validationContext.get(Course
                .class));
        return mav;
    }

    /**
     * Process the request to post entered course in the form
     *
     * @param course course object
     * @return if all is OK the redirect to view course or return to edit course
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(
            @ModelAttribute(STRING_COURSE) final Course course,
            @RequestParam(value = "expertList",
                    required = false) final List<String> expertList) {
        course.setId(courseService.findByCode(course.getCode()).getId());
        // TO DO: id must be already present
        course.setExperts(bindExperts(expertList));
        courseService.update(course);
        return "redirect: view/"
                + course.getCode();
    }

    private Set<User> bindExperts(final List<String> experts) {
        if (experts
                == null) {
            return null;
        }
        final Set<User> courseExperts = new HashSet<>();
        for (final String expert : experts) {
            final String[] params = expert.split(" ");
            courseExperts.add(userService.loadUserByUsername(params[2]));
        }
        return courseExperts;
    }

    /**
     * Binding course conditions for entry into the form conclusions
     *
     * @param binder
     */
    @InitBinder(STRING_COURSE)
    public void initBinder(final WebDataBinder binder) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(List.class, "tags", new CustomTagsEditor());
        binder.registerCustomEditor(List.class, STRING_TYPES, new CustomStringEditor());
    }

    @InitBinder(STRING_FILTER_COURSE)
    public void initFilterBinder(final WebDataBinder binder) {
        initBinder(binder);
    }

    /**
     * Prepare collection categories as model attribute
     *
     * @return collection categories
     */
    @ModelAttribute("categories")
    public Collection<String> getCategories() {
        return CourseType.findAll();
    }

    @ModelAttribute(STRING_STATUSES)
    public Collection<String> getStatuses() {
        return CourseStatus.findAll();
    }


    @ModelAttribute("currentUser")
    public String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @ModelAttribute(STRING_FILTER_COURSE)
    public CourseSearchFilter getFilterCourse() {
        return new CourseSearchFilter();
    }

    /**
     * Prepare course as model attribute
     *
     * @return course object
     */
    @ModelAttribute(value = STRING_COURSE)
    public Course getCommandObject() {
        return new Course();
    }
}


