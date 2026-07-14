package helpers;
import org.aeonbits.owner.Config;

/**
 * * интерфейс для получения проперти
 */
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"file:src/main/resources/tests.properties"})
public interface TestProperties extends Config {

    /**
     * Метод получения URL
     */
    @Config.Key("url")
    String defaultUrl();

}
