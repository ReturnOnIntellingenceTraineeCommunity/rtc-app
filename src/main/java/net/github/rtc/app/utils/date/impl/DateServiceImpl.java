package net.github.rtc.app.utils.date.impl;

import net.github.rtc.app.utils.date.DateService;
import org.joda.time.*;
import org.springframework.stereotype.Component;


import java.util.Calendar;
import java.util.Date;

@Component("DateService")
public class DateServiceImpl implements DateService {

    @Override
    public Date getCurrentDate() {
        return DateTime.now().toLocalDateTime().toDate();
    }

    @Override
    public Date addDays(Date oldDate, final int days) {
        return new DateTime(oldDate).plusDays(days).toDate();
    }

    @Override
    public int getMothPeriod(final Date startDate, final Date endDate) {
        int months = new Period(new DateTime(startDate), new DateTime(endDate), PeriodType.months()).getMonths();
        if (months == 0) {
            ++months;
        } else {
            int days = new Period(new DateTime(startDate), new DateTime(endDate), PeriodType.days()).getDays();
            final Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            for (int i = 0; i < months; i++) {
                final int currentMonthDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                if (days > currentMonthDays) {
                    days = days - currentMonthDays;
                    calendar.add(Calendar.MONTH, 1);
                }
            }

            final int term = 14;
            if (days > term) {
                ++months;
            }
        }
        return months;
    }
}
