package ru.otus.rik.web.jaxws.sunsetrise;

import ru.otus.rik.web.jaxws.sunsetrise.client.LatLonDate;
import ru.otus.rik.web.jaxws.sunsetrise.client.SunSetRiseService;
import ru.otus.rik.web.jaxws.sunsetrise.client.SunSetRiseServiceSoap;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class SunRiseSetServiceAdapter {
    private final SunSetRiseServiceSoap sunSetRiseService;

    public SunRiseSetServiceAdapter() {
        SunSetRiseService webService = new SunSetRiseService();
        sunSetRiseService = webService.getSunSetRiseServiceSoap();
    }

    public RiseSetDateHolder getTodaySunRiseSet(float latitude, float longitude) {
        Calendar today = new GregorianCalendar();

        LatLonDate latLonDate = new LatLonDate();
        latLonDate.setLatitude(latitude);
        latLonDate.setLongitude(longitude);
        latLonDate.setDay(today.get(Calendar.DAY_OF_MONTH));
        latLonDate.setMonth(today.get(Calendar.MONTH) + 1);
        latLonDate.setYear(today.get(Calendar.YEAR));
        latLonDate.setTimeZone(1);

        LatLonDate riseSetDate = sunSetRiseService.getSunSetRiseTime(latLonDate);
        return new RiseSetDateHolder(riseSetDate);
    }
}
