package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

public class GeoServiceTest {

//    Написать тесты для проверки определения локации по ip (класс GeoServiceImpl)
//    Проверить работу метода public Location byIp(String ip)

    @Test
    void locationIsLocal (){
        GeoService geoService = new GeoServiceImpl();
        Location actualLocation = geoService.byIp(GeoServiceImpl.LOCALHOST);
        Location expectedLocation = new Location(null, null, null, 0);
        Assertions.assertEquals(expectedLocation, actualLocation);
    }

    @Test
    void locationIsRus() {
        GeoService geoService = new GeoServiceImpl();
        Location actualLocation = geoService.byIp(GeoServiceImpl.MOSCOW_IP);
        Country actualCountry = actualLocation.getCountry();
        Country expecteedCountry = Country.RUSSIA;
        Assertions.assertEquals(expecteedCountry, actualCountry);
    }

    @Test
    void locationIsStartFron172() {
        GeoService geoService = new GeoServiceImpl();
        Location actualLocation = geoService.byIp("172.0.000.00");
        Country actualCountry = actualLocation.getCountry();
        Country expecteedCountry = Country.RUSSIA;
        Assertions.assertEquals(expecteedCountry, actualCountry);
    }

    @Test
    void locationIsNY() {
        GeoService geoService = new GeoServiceImpl();
        Location actualLocation = geoService.byIp(GeoServiceImpl.NEW_YORK_IP);
        Country actualCountry = actualLocation.getCountry();
        Country expecteedCountry = Country.USA;
        Assertions.assertEquals(expecteedCountry, actualCountry);
    }

    @Test
    void locationIsStartFron96() {
        GeoService geoService = new GeoServiceImpl();
        Location actualLocation = geoService.byIp("96.0.000.00");
        Country actualCountry = actualLocation.getCountry();
        Country expecteedCountry = Country.USA;
        Assertions.assertEquals(expecteedCountry, actualCountry);
    }

}
