package com.kulbako.backend.models;

/**
 * Список дней для отметки в календаре {@see WorkingCalendar}.
 * @author Артемий Кульбако
 * @version 1.0
 */
public enum WorkDayCode {
    FULL_DAY {
        //полный рабочий день
        @Override public String toString() { return "Я"; }
        },
    UNKNOWN_LACK {
        //отсутствие на рабочее место по невыясненным причинам
        @Override public String toString() { return "Н"; }
        },
    WEEKEND {
        //выходные и праздничные дни
        @Override public String toString() { return "В"; }
        },
    WEEKEND_WORK {
        //работа в праздничные и выходные дни; а также работа в праздничные и выходные дни, при нахождении в командировке
        @Override public String toString() { return "Рв"; }
    },
    TEMP_INOPERABILITY {
        //дни временной нетрудоспособности
        @Override public String toString() { return "Б"; }
    },
    BUSSINESS_TRIP {
        /*
        командировочные дни; а также, выходные (нерабочие) дни при нахождении в командировке,
        когда сотрудник отдыхает, в соответствии с графиком работы ООО «Наука» в командировке
         */
        @Override public String toString() { return "К"; }
    },
    PAID_HOLIDAY {
        //ежегодный основной оплаченный отпуск
        @Override public String toString() { return "ОТ"; }
    },
    UNPAID_HOLIDAY {
        //неоплачиваемый отпуск
        @Override public String toString() { return "До"; }
    },
    BUSINESS_DAY {
        //хозяйственный день
        @Override public String toString() { return "Хд"; }
    },
    STUDY_HOLIDAY {
        //отпуск на период обучения
        @Override public String toString() { return "У"; }
    },
    MATERNITY_LEAVE {
        //Отпуск по уходу за ребенком
        @Override public String toString() { return "Ож"; }
    }
}
