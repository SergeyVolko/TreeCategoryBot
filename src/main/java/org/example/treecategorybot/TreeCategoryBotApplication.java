package org.example.treecategorybot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Основной класс приложения TreeCategoryBot.
 * <p>
 * Это приложение разработано с использованием Spring Boot и служит точкой входа для запуска
 * бота, связанного с деревом категорий. Аннотация {@link SpringBootApplication}
 * включает автоматическую конфигурацию, сканирование компонентов и определение бинов.
 *
 * <p>
 * Для запуска приложения используйте метод {@link #main(String[])}.
 * </p>
 */
@SpringBootApplication
public class TreeCategoryBotApplication {
    /**
     * Точка входа в приложение TreeCategoryBot.
     *
     * @param args аргументы командной строки, которые могут быть переданы при запуске приложения.
     */
    public static void main(String[] args) {
        SpringApplication.run(TreeCategoryBotApplication.class, args);
    }
}
