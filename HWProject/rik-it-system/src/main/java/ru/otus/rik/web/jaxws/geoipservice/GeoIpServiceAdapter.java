package ru.otus.rik.web.jaxws.geoipservice;

import ru.otus.rik.web.jaxws.geoipservice.client.GeoIP;
import ru.otus.rik.web.jaxws.geoipservice.client.GeoIPService;
import ru.otus.rik.web.jaxws.geoipservice.client.GeoIPServiceHttpPost;
import ru.otus.rik.web.jaxws.geoipservice.client.GeoIPServiceSoap;

public class GeoIpServiceAdapter {
    private final GeoIPServiceSoap soapService;

    public GeoIpServiceAdapter() {
        GeoIPService webService = new GeoIPService();
        soapService = webService.getGeoIPServiceSoap();
    }

    public String getCountryByIP(String ip) {
        GeoIP geoIP = soapService.getGeoIP(ip);
        return geoIP.getCountryName();
    }
}
