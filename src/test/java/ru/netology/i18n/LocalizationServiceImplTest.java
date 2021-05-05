package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.EnumOptions;
import ru.netology.entity.Country;

import static ru.netology.entity.Country.*;

//    Написать тесты для проверки возвращаемого текста (класс LocalizationServiceImpl)
//    Проверить работу метода public String locale(Country country)

public class LocalizationServiceImplTest {

    @Test
    void localeRussia() {
        LocalizationService localizationService = new LocalizationServiceImpl();
        Country country = RUSSIA;
        String actual = localizationService.locale(country);
        String expected = "Добро пожаловать";
        Assertions.assertEquals(actual,expected);
    }

    @Test
    //как сделать параметризированный тест для Enum? Лазил по библиотеке, не нашёл. Плохо искал?
    void localeNotRussia() {
        LocalizationService localizationService = new LocalizationServiceImpl();
        Country[] countries = {USA,GERMANY,BRAZIL};
        for ( Country country : countries) {
            String actual = localizationService.locale(country);
            String expected = "Welcome";
            Assertions.assertEquals(actual, expected);
        }
    }
}
