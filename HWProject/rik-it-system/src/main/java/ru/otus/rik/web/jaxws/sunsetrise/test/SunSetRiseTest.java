package ru.otus.rik.web.jaxws.sunsetrise.test;

import ru.otus.rik.web.jaxws.sunsetrise.RiseSetDateHolder;
import ru.otus.rik.web.jaxws.sunsetrise.SunRiseSetServiceAdapter;
import java.util.Calendar;

public class SunSetRiseTest {
    public static void main(String[] args) {
        SunRiseSetServiceAdapter localService = new SunRiseSetServiceAdapter();
        RiseSetDateHolder dateHolder = localService.getTodaySunRiseSet((float)43.675819, (float)7.289429);
        System.out.println(String.format("Today in Nice sunrise time %s, sunset time %s",
                dateHolder.riseAsString(),
                dateHolder.setAsString()));
    }
}
