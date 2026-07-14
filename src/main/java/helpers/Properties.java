package helpers;

import org.aeonbits.owner.ConfigFactory;

/**
 * Класс для работы с проперти
 */
public class Properties {
    public static TestProperties testsProperties = ConfigFactory.create(TestProperties.class);
}
