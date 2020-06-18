import interaction.DataInteractionFactory;
import interaction.DataInteractionImpl;
import java.util.List;

//TODO: тесты
//TODO: README.md

/**
 * "Скелетный" класс приложения, содержащий глобальные переменные и метод запуска.
 * @author Кульбако Артемий.
 * @version 1.0
 */
public class Main {

    /** Информация о приложении.*/
    static final String APP_INFO = "app v.1.14";
    /** Путь к файлу с входными данными. */
    final static String INPUT_PATH = "1.txt";
    /** Путь к файлу для выходных данных. */
    final static String OUTPUT_PATH = "statistika.txt";

    /**
     * Точка выполнения приложения.
     * @param args аргументы командой строки.
     */
    public static void main(String[] args) {
        try {
            final int START_YEAR = 1990;
            final int END_YEAR = 2020;
            System.out.println(APP_INFO);
            StringBuilder builder = new StringBuilder();
            DataInteractionImpl fdi = DataInteractionFactory.create();
            for (int i = START_YEAR; i < END_YEAR; i++) {
                builder.append(i);
                List<String> data = fdi.readData(INPUT_PATH);
                int sum = fdi.getSum(builder.toString(), data);
                double periodicity = sum > 0 ? ((double) sum / data.size()) : 0;
                if (periodicity > 0) System.out.println(i + " " + periodicity);
                fdi.writeData(OUTPUT_PATH, i + " " + (int) periodicity + "\n");
            }
            System.out.println("SUCCESS");
        } catch (Exception e) { System.err.println(e.toString()); }
    }
}
