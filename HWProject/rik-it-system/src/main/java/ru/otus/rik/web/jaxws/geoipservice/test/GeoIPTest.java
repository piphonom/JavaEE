package ru.otus.rik.web.jaxws.geoipservice.test;

import ru.otus.rik.web.jaxws.geoipservice.GeoIpServiceAdapter;

public class GeoIPTest {
    public static void main(String[] args) {
        GeoIpServiceAdapter serviceAdapter = new GeoIpServiceAdapter();
        String ip = "208.68.37.38";
        System.out.println(String.format("IP %s are located in %s", ip, serviceAdapter.getCountryByIP(ip)));
    }
}
