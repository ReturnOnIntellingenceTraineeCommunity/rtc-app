package net.github.rtc.web.courses.resource;

import java.util.Collection;

/**
 * Data Access Object Interface for course category
 *
 * @author Vladislav Pikus
 */
public interface CategoryResource {

    /**
     * Find collection course categories
     *
     * @return collection course categories
     */
    Collection<String> findAll();
}
