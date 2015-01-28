package net.github.rtc.app.service.impl.news;

import net.github.rtc.app.dao.GenericDao;
import net.github.rtc.app.dao.NewsDao;
import net.github.rtc.app.model.activity.ActivityAction;
import net.github.rtc.app.model.activity.ActivityEntity;
import net.github.rtc.app.model.news.News;
import net.github.rtc.app.service.impl.AbstractGenericServiceImpl;
import net.github.rtc.app.service.news.NewsService;
import net.github.rtc.app.utils.EventCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl extends AbstractGenericServiceImpl<News> implements NewsService {
    @Autowired
    private NewsDao newsDao;

    private EventCreator creator = new EventCreator(this, ActivityEntity.NEWS);

    @Override
    public News create(News news) {
        creator.createAndPublishEvent(news, ActivityAction.SAVED);
        return super.create(news);
    }

    @Override
    public News update(News news) {
        creator.createAndPublishEvent(news, ActivityAction.UPDATED);
        return super.update(news);
    }

    @Override
    public void deleteByCode(String code) {
        final News news = findByCode(code);
        creator.createAndPublishEvent(news, ActivityAction.REMOVED);
        super.deleteByCode(code);
    }

    protected GenericDao<News> getDao() {
        return newsDao;
    }

    public List<News> findPublishedNews() {
        return newsDao.findPublished();
    }
}
