package org.example.treecategorybot.bot.services;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.treecategorybot.bot.entities.Category;
import org.example.treecategorybot.bot.logic.constants.TitleHeaders;
import org.springframework.stereotype.Component;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import static org.example.treecategorybot.bot.logic.constants.TitleHeaders.HEADER_CATEGORY;
import static org.example.treecategorybot.bot.logic.constants.TitleHeaders.HEADER_PARENT_CATEGORY;

/**
 * Генератор Excel файлов для экспорта данных о категориях.
 *
 * <p>
 * Этот класс отвечает за создание Excel файла, содержащего информацию о категориях
 * и их родительских категориях. Данные извлекаются из сервиса категорий.
 * </p>
 */
@Component
public class ExcelGenerator {
    /**
     * Сервис для работы с деревом категорий, используемый для извлечения данных о категориях.
     *
     * <p>
     * Это поле инициализируется через конструктор и используется в методе {@link #generateExcel()}
     * для получения списка всех категорий, которые затем экспортируются в Excel файл.
     * </p>
     */
    private final CategoryTreeServicesImpl categoryTreeServices;

    /**
     * Конструктор для инициализации генератора Excel с заданным сервисом категорий.
     *
     * <p>
     * Этот конструктор принимает экземпляр {@link CategoryTreeServicesImpl}, который
     * используется для извлечения данных о категориях, необходимых для генерации
     * Excel файла.
     * </p>
     *
     * @param categoryTreeServices Сервис для работы с деревом категорий.
     *                             Он предоставляет методы для получения информации
     *                             о категориях и их родительских категориях.
     */
    public ExcelGenerator(CategoryTreeServicesImpl categoryTreeServices) {
        this.categoryTreeServices = categoryTreeServices;
    }

    /**
     * Генерирует Excel файл, содержащий список категорий и их родительских категорий.
     *
     * <p>
     * Этот метод извлекает все категории из сервиса {@link CategoryTreeServicesImpl},
     * определяет максимальную длину названий категорий и их родительских категорий,
     * создает новый Excel файл и заполняет его данными. Заголовки столбцов
     * устанавливаются в соответствии с заданными константами {@link  TitleHeaders#HEADER_CATEGORY}
     * и {@link  TitleHeaders#HEADER_PARENT_CATEGORY}. Файл сохраняется по умолчанию с именем
     * "categories.xlsx" в текущей директории.
     * </p>
     *
     * @return Путь к созданному Excel файлу.
     * @throws IOException Если произошла ошибка ввода-вывода при создании или записи файла.
     */
    public String generateExcel() throws IOException {
        List<Category> categories = categoryTreeServices.getAllCategories();
        int maxSymbolsNameCategory = categories.stream()
                .mapToInt(e -> e.getName().length()).max().orElse(0);
        int maxSymbolsNameParent = categories.stream()
                .mapToInt(e -> {
                    Category parent = e.getParent();
                    if (parent == null) {
                        return 0;
                    }
                    return parent.getName().length();
                }).max().orElse(0);
        String titleNameCategory = HEADER_CATEGORY;
        String titleNameParent = HEADER_PARENT_CATEGORY;
        int maxCategory = Math.max(maxSymbolsNameCategory, titleNameCategory.length());
        int maxParent = Math.max(maxSymbolsNameParent, titleNameParent.length());
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sample Sheet");
        sheet.setColumnWidth(0, 256 * (maxCategory + 1));
        sheet.setColumnWidth(1, 256 * (maxParent + 1));
        Row header = sheet.createRow(0);
        Cell titleCategory = header.createCell(0);
        titleCategory.setCellValue(titleNameCategory);
        Cell titleParent = header.createCell(1);
        titleParent.setCellValue(titleNameParent);
        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            Row line = sheet.createRow(i + 1);
            Cell nameCategory = line.createCell(0);
            nameCategory.setCellValue(category.getName());
            Category parent = category.getParent();
            if (parent != null) {
                Cell nameParent = line.createCell(1);
                nameParent.setCellValue(parent.getName());
            }
        }
        String filePath = "categories.xlsx";
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }
        workbook.close();
        return filePath;
    }
}
