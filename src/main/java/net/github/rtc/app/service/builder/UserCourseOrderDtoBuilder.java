package net.github.rtc.app.service.builder;

import net.github.rtc.app.model.dto.user.UserCourseOrderDto;
import net.github.rtc.app.model.entity.course.Course;
import net.github.rtc.app.model.entity.order.UserCourseOrder;

/**
 * Object that helps to build UserCourseOrderDto
 * @see net.github.rtc.app.model.dto.user.UserCourseOrderDto
 */
public class UserCourseOrderDtoBuilder {

    private UserCourseOrderDto courseOrderDto = new UserCourseOrderDto();

    /**
     * Instantiates a new builder.
     * @param order the order on what UserCourseOrderDto is based
     * @param course the course on what UserCourseOrderDto is based
     */
    public UserCourseOrderDtoBuilder(UserCourseOrder order, Course course) {
        buildCourseFields(order, course);
    }

    /**
     * Initialise course fields in dto
     * @param order the order that contains required fields
     * @param course the course on what UserCourseOrderDto is based
     * @return this object
     */
    public UserCourseOrderDtoBuilder buildCourseFields(UserCourseOrder order, Course course) {
        courseOrderDto.setCode(course.getCode());
        courseOrderDto.setName(course.getName());
        courseOrderDto.setExperts(course.getExperts());
        courseOrderDto.setStartDate(course.getStartDate());
        courseOrderDto.setEndDate(course.getEndDate());
        courseOrderDto.setStatus(order.getStatus());
        return this;
    }

    /**
     * Return current prebuilt UserCourseOrderDto object
     * @return the result dto
     */
    public UserCourseOrderDto build() {
        return courseOrderDto;
    }
}
