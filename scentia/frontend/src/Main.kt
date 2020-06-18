import javafx.application.Application
import javafx.fxml.FXMLLoader.load
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage

/**
 *Управляет загрузкой приложения.
 * @author Артемий Кульбако
 * @version 1.0
 */
class Main : Application() {
    /**
     * Отвечает за построение главной сцены JavaFX.
     * @param primaryStage сцена приложения.
     */
    override fun start(primaryStage: Stage?) {
        primaryStage?.let {
            it.scene = Scene(load(javaClass.getResource("/resources/main.fxml")))
            it.icons.add(Image("/resources/logo.png"))
            it.title = "Табель"
            it.show()
        }
    }

    companion object {
        /**
         * Определяет метод взаимодействия с пользователем.
         * @param args - аргументы командной строки.
         */
        @JvmStatic
        fun main(args: Array<String>) = launch(Main::class.java)
    }
}