package net.github.rtc.app.dao.message;

import net.github.rtc.app.dao.generic.AbstractGenericDaoImpl;
import net.github.rtc.app.model.entity.message.Message;
import net.github.rtc.app.model.entity.message.MessageStatus;
import net.github.rtc.app.model.entity.user.User;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class MessageDaoImpl extends AbstractGenericDaoImpl<Message> implements MessageDao {

    @Override
    public int getMessageCountByUserAndStatus(User user, MessageStatus status) {
        return ((Long) getCurrentSession().createCriteria(Message.class).
                add(Restrictions.eq("receiver", user)).add(Restrictions.eq("isRead", false)).
                setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }
}
