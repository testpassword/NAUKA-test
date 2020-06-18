package interaction;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Реализация интерфейса {@link DataInteraction} для подсчёта вхождений строки template.
 */
public class DataInteractionImpl implements DataInteraction, Serializable {

    private static int appsLifecycleWritesCounter = 0;
    static final long serialVersionUID = 4L;

    /** @return количество записей за время жизни приложения (лучше заменить на MBean). */
    public static int getAppsLifecycleWritesCounter() { return appsLifecycleWritesCounter; }

    /**
     * Загружает данные файла в строковом виде.
     * @param pathToFile путь к файлу.
     * @return коллекцию строк, считанных из файла.
     * @throws IOException
     */
    @Override
    public List<String> readData(String pathToFile) throws IOException {
        return Files.readAllLines(Paths.get(pathToFile));
    }

    /**
     * Записывает строки в файл.
     * @param pathToFile путь к файлу.
     * @param content строки для записи.
     * @throws IOException
     */
    @Override
    public void writeData(String pathToFile, String... content) throws IOException {
        Path p = Paths.get(pathToFile);
        String prefix = appsLifecycleWritesCounter + " ";
        for (String s: content) Files.write(p, (prefix + s).getBytes());
        appsLifecycleWritesCounter++;
    }

    /**
     * Подсчитывает суммы числе в строке, содержащей строку-шаблон.
     * @param template строка-шаблон.
     * @param data строки для сравнения.
     * @return сумму чисел во всех строках после строки-шаблона.
     * @throws IllegalArgumentException если {@code if (template == null || data == null)}.
     */
    public int getSum(String template, List<String> data) throws IllegalArgumentException {
        if (template == null || data == null) throw new IllegalArgumentException("Строка для сравнения или данные не задана");
        else {
            int sum = 0;
            for (String s: data) {
                String[] splits = s.split(" ");
                /*
                Не понимаю, почему нужны именно индексы 2 и 3, поэтому переделывать не стал,
                лишь добавил проверки и обработку возможного исключения.
                */
                if (splits.length >= 3) {
                    try {
                        if (splits[2].contains(template)) sum += Integer.parseInt(splits[3]);
                    } catch (NumberFormatException e) { System.err.println("Неккоректные данные " + e.toString()); }
                }
            }
            return sum;
        }
    }

    @Override
    public String toString() { return "DataInteractionImpl{" + super.toString() + "}"; }
}
