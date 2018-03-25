package ru.otus.rik.web.jaxws.sunsetrise.test;

import ru.otus.rik.web.jaxws.sunsetrise.RiseSetDateHolder;
import ru.otus.rik.web.jaxws.sunsetrise.SunRiseSetServiceAdapter;
import java.util.Calendar;

public class SunSetRiseTest {
    public static void main(String[] args) {
        SunRiseSetServiceAdapter localService = new SunRiseSetServiceAdapter();
        RiseSetDateHolder dateHolder = localService.getTodaySunRiseSet((float)43.675819, (float)7.289429);
        System.out.println(String.format("Today in Nice sunrise time %02d:%02d, sunset time %02d:%02d",
                dateHolder.getRise().get(Calendar.HOUR_OF_DAY), dateHolder.getRise().get(Calendar.MINUTE),
                dateHolder.getSet().get(Calendar.HOUR_OF_DAY), dateHolder.getSet().get(Calendar.MINUTE)));
    }
}
