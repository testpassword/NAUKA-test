import interaction.DataInteractionFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для класса {@link interaction.DataInteractionFactory}.
 * @author Кульбако Артемий.
 * @version 1.0
 */
public class DataInteractionFactoryTest {

    private final static String className = DataInteractionFactory.class.getName();

    @BeforeAll
    public static void init() { System.out.println("тестирование " + className + " запущено"); }

    @AfterAll
    public static void done() { System.out.println("тестирование " + className + " завершено"); }

    @Test
    public void create() { assertNotNull(DataInteractionFactory.create()); }
}
