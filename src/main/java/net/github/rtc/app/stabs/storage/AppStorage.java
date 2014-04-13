package net.github.rtc.app.stabs.storage;

import net.github.rtc.app.model.Author;
import net.github.rtc.app.model.Course;
import net.github.rtc.app.model.Tag;
import net.github.rtc.app.profile.Static;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Vladislav Pikus
 */
@Static
@Component
public class AppStorage {
    private static Long YEAR_MS = 31536000000l;

    private List<Author> authors = new LinkedList<Author>();

    private Map<String, Integer> courseEmailMap = new HashMap<String, Integer>();

    private List<Tag> tags = new LinkedList<Tag>();

    private List<Course> courses = new LinkedList<Course>();

    private Map<String, Integer> courseCodeMap = new HashMap<String, Integer>();

    private List<String> types = Arrays.asList("DEV", "BA", "QA");

    private Random rnd = new Random();

    public AppStorage() {
        int idx;
        //prepare authors
        for (idx = 0; idx < 20; idx++) {
            String email = String.format("email%s@rtcapp.dp.ua", idx);
            Author author = new Author("FirstName" + idx, "LastName" + idx, email);
            authors.add(author);
            courseEmailMap.put(email, idx);
        }
        //prepare tags
        for (idx = 0; idx < 20; idx++) {
            Tag tag = new Tag("Tag" + idx);
            tags.add(tag);
        }
        int typesSize = types.size();
        int authorsSize = authors.size();
        Long monthMs1 = YEAR_MS / 12;
        Long monthMs3 = monthMs1 * 3;
        Long monthMs4 = monthMs1 * 4;
        //prepare courses
        for (idx = 0; idx < 50; idx++) {
            String code;
            do {
                code = UUID.randomUUID().toString();
            } while (courseCodeMap.containsKey(code));
            long timeInMillis = buildTimeInMillis();
            Course course = new Course(code, "CourseName" + idx, types.get(rnd.nextInt(typesSize)),
                    authors.get(rnd.nextInt(authorsSize)), new Date(timeInMillis), new Date(timeInMillis + randBetween(monthMs4, YEAR_MS)),
                    new Date(timeInMillis - randBetween(monthMs1, monthMs3)), new Long(randBetween(3l, 15l)).intValue(), randDesc(), "PUBLISHED");
            courses.add(course);
            courseCodeMap.put(code, idx);
        }
    }

    private String randDesc() {
        StringBuffer sb = new StringBuffer();
        int idx;
        int size = new Long(randBetween(10, 100)).intValue();
        for (idx = 0; idx < size; idx++) {
            sb.append("descrition").append(" ");
        }
        return sb.toString();
    }

    private long buildTimeInMillis() {
        GregorianCalendar gc = new GregorianCalendar();
        gc.set(gc.YEAR, new Long(randBetween(2012, 2014)).intValue());
        int dayOfYear = new Long(randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR))).intValue();
        gc.set(gc.DAY_OF_YEAR, dayOfYear);
        return gc.getTimeInMillis();
    }

    private long randBetween(long start, long end) {
        return start + Math.round(rnd.nextDouble() * (end - start));
    }
}
