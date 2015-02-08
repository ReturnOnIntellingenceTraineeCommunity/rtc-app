package net.github.rtc.app.controller.user;

import net.github.rtc.app.controller.common.MenuItem;
import net.github.rtc.app.model.dto.user.UserCourseDTO;
import net.github.rtc.app.model.entity.course.CourseStatus;
import net.github.rtc.app.model.entity.course.CourseType;
import net.github.rtc.app.model.entity.user.UserCourseOrder;
import net.github.rtc.app.service.course.CourseService;
import net.github.rtc.app.service.user.UserCourseOrderService;
import net.github.rtc.app.utils.AuthorizedUserProvider;
import net.github.rtc.app.utils.datatable.search.CourseSearchFilter;
import net.github.rtc.app.utils.datatable.search.SearchResults;
import net.github.rtc.util.converter.ValidationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/user/courses")
public class CourseController implements MenuItem {

    private static final String ROOT = "portal/user";
    private static final String USER = "user";
    private static final String COURSE = "course";
    private static final String VALIDATION_RULES = "validationRules";
    private static final String COURSES = "courses";
    private static final int COURSES_PER_PAGE = 9;

    @Autowired
    private CourseService courseService;
    @Autowired
    private UserCourseOrderService userCourseOrderService;
    @Autowired
    private ValidationContext validationContext;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView userCourses() throws Exception {
        final ModelAndView mav = new ModelAndView(ROOT + "/course/courses");
        return mav;
    }

    @RequestMapping(value = "/courseDetails/{courseCode}", method = RequestMethod.GET)
    public ModelAndView courseDetails(@PathVariable final String courseCode) {
        final ModelAndView mav = new ModelAndView(ROOT + "/course/userCourseDetails");
        mav.addObject(COURSE, courseService.getUserCourseDTObyCode(courseCode));
        mav.addObject(USER, AuthorizedUserProvider.getAuthorizedUser());
        return mav;
    }

    @RequestMapping(value = "/sendOrder", method = RequestMethod.POST)
    public String sendCourseOrder(@ModelAttribute("order") final UserCourseOrder order) {
        userCourseOrderService.create(order);
        return "redirect:/user/courses";
    }

    @RequestMapping(value = "/courseTable", method = RequestMethod.POST)
    public @ResponseBody ModelAndView getCourseTable(@ModelAttribute("courseFilter") final CourseSearchFilter courseFilter,
                                                     @RequestParam(required = false) final boolean withArchived) {
        final ModelAndView mav = new ModelAndView(ROOT + "/course/courseTable");
        final SearchResults<UserCourseDTO> results = courseService.searchCoursesForUser(withArchived, courseFilter);
        mav.addAllObjects(results.getPageModel());
        mav.addObject(COURSES, results.getResults());
        mav.addObject(VALIDATION_RULES, validationContext.get(UserCourseOrder.class));
        return mav;
    }

    @RequestMapping(value = "/position/{courseCode}", method = RequestMethod.GET)
    public @ResponseBody
    Set<CourseType> getPositions(@PathVariable String courseCode) {
        return courseService.findByCode(courseCode).getTypes();
    }

    @Override
    public String getMenuItem() {
        return COURSE;
    }

    @ModelAttribute("courseTypes")
    public CourseType[] getTypes() {
        return CourseType.values();
    }

    @ModelAttribute("courseFilter")
    public CourseSearchFilter getCourseSearchFilter() {
        final CourseSearchFilter filter = new CourseSearchFilter();
        filter.setPerPage(COURSES_PER_PAGE);
        filter.setStatus(new HashSet<>(Arrays.asList(CourseStatus.PUBLISHED)));
        return filter;
    }

    @ModelAttribute("order")
    public UserCourseOrder courseOrder() {
        return new UserCourseOrder();
    }
}
