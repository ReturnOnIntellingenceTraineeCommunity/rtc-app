package net.github.rtc.app.dao;

import net.github.rtc.app.model.entity.user.UserCourseOrder;
import net.github.rtc.app.model.entity.user.UserRequestStatus;

import java.util.List;

public interface UserCourseOrderDao extends GenericDao<UserCourseOrder> {
    UserCourseOrder getUserOrder(String userCode);

    List<UserCourseOrder> getUserOrdersByCode(String userCode);

    List<UserCourseOrder> getOrderByStatus(UserRequestStatus status);

    int getAcceptedOrdersForCourse(String courseCode);
}
