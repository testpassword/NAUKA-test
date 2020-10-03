package interaction;

import java.io.IOException;
import java.util.List;

/**
 * Предоставляет методы для чтения и записи строк из файлов.
 * @author Кульбако Артемий.
 * @version 1.2
 */
public interface DataInteraction {

    /**
     * Загружает данные файла в строковом виде.
     * @param pathToFile путь к файлу.
     * @return коллекцию строк, считанных из файла.
     * @throws IOException
     */
    List<String> readData(String pathToFile) throws IOException;

    /**
     * Записывает строки в файл.
     * @param pathToFile путь к файлу.
     * @param content строки для записи.
     * @throws IOException
     */
    void writeData(String pathToFile, String... content) throws IOException;
}