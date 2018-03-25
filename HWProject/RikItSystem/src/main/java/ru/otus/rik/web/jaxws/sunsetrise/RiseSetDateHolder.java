package ru.otus.rik.web.jaxws.sunsetrise;

import lombok.Getter;
import ru.otus.rik.web.jaxws.sunsetrise.client.LatLonDate;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Getter
public final class RiseSetDateHolder {
    private Calendar rise;
    private Calendar set;

    RiseSetDateHolder(LatLonDate lonLanDate) {
        int hours = (int)lonLanDate.getSunRiseTime();
        int minutes = (int)(60 * (lonLanDate.getSunRiseTime() - hours));
        rise = new GregorianCalendar(lonLanDate.getYear(), lonLanDate.getMonth(), lonLanDate.getDay(), hours, minutes);

        hours = (int)lonLanDate.getSunSetTime();
        minutes = (int)(60 * (lonLanDate.getSunSetTime() - hours));
        set = new GregorianCalendar(lonLanDate.getYear(), lonLanDate.getMonth(), lonLanDate.getDay(), hours, minutes);
    }
}
