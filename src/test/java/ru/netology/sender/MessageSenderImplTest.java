package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

//    Написать тесты для проверки языка отправляемого сообщения (класс MessageSender) с использованием Mockito
//    1. Поверить, что MessageSenderImpl всегда отправляет только русский текст, если ip относится к российскому
//    сегменту адресов.
//    2. Поверить, что MessageSenderImpl всегда отправляет только английский текст, если ip относится к
//            американскому сегменту адресов.

public class MessageSenderImplTest {

    @Test
    void sendWhenIpIsRussian() {
        //создаём объект, который определяет по ip и возвращает локацию (4 параметра)
        //Нужно сделать так, чтобы он всегда возвращал локацию только РФ
        GeoService geoService = Mockito.mock(GeoService.class);
        Location russianLocation = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        Mockito.when(geoService.byIp(Mockito.anyString()))
                .thenReturn(russianLocation);

        //объект, который принимает страну и отправляет в ответ либо русское, либо англ.сообщение
        //Надо сделать, чтобы он всегда возвращал сообщение для РФ
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        String rusMessage = "Добро пожаловать";
        Mockito.when(localizationService.locale(Mockito.<Country>anyObject())).thenReturn(rusMessage);

        //отправщик сообщений
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        //создаём мапу, в которую закинем потом наш ip
        Map<String, String> headers = new HashMap<String, String>();

        //клаёдм в мапу ip. Теперь сюда можно положить иностранный IP, всё равно отправится сообщение на русском
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, null);

        String actualMessage = messageSender.send(headers);
        String expected = "Добро пожаловать";
        Assertions.assertEquals(actualMessage, expected);

    }


    @Test
    void sendWnenIpIsntRussian() {
        GeoService geoService = Mockito.mock(GeoService.class);
        Location usaLocation = new Location("New York", Country.USA, " 10th Avenue", 32);
        Mockito.when(geoService.byIp(Mockito.anyString()))
                .thenReturn(usaLocation);

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        String engMessage = "Welcome";
        Mockito.when(localizationService.locale(Mockito.<Country>anyObject())).thenReturn(engMessage);

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER,null);

        String actualMessage = messageSender.send(headers);
        String expected = "Welcome";
        Assertions.assertEquals(actualMessage, expected);
    }

}
