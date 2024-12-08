package org.example.treecategorybot.bot.services;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.treecategorybot.bot.entities.Category;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static org.example.treecategorybot.bot.logic.constants.TitleHeaders.HEADER_CATEGORY;
import static org.example.treecategorybot.bot.logic.constants.TitleHeaders.HEADER_PARENT_CATEGORY;

@Component
public class ExcelGenerator {
    private final CategoryTreeServicesImpl categoryTreeServices;

    public ExcelGenerator(CategoryTreeServicesImpl categoryTreeServices) {
        this.categoryTreeServices = categoryTreeServices;
    }

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
