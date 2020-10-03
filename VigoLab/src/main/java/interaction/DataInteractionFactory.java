package interaction;

import java.io.Serializable;

/**
 * Фабрика классов, реализующих интерфейс {@link DataInteraction}.
 * @author Кульбако Артемий.
 * @version 1.0
 */
public class DataInteractionFactory implements Serializable {

    static final long serialVersionUID = 4L;

    /**
     * Создаёт экземпляр класса {@link DataInteractionImpl}.
     * @return экземпляр класса.
     */
    public static DataInteractionImpl create() { return new DataInteractionImpl(); }

    @Override
    public String toString() { return "DataInteractionFactory{" + super.toString() + "}"; }
}