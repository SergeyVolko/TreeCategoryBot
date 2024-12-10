package org.example.treecategorybot.bot.services;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.treecategorybot.bot.dto.CategoryDTO;
import org.example.treecategorybot.bot.logic.exceptions.DownloadDocumentException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static org.example.treecategorybot.bot.logic.constants.ExceptionsTextMessages.*;
import static org.example.treecategorybot.bot.logic.constants.TitleHeaders.HEADER_CATEGORY;
import static org.example.treecategorybot.bot.logic.constants.TitleHeaders.HEADER_PARENT_CATEGORY;

@Service
public class ExcelReaderServiceImpl implements ExcelReaderService {

    @Override
    public List<CategoryDTO> readExcelFile(InputStream inputStream) throws IOException, DownloadDocumentException {
        List<CategoryDTO> categories = new ArrayList<>();
        Set<String> categoriesName = new HashSet<>();
        try (Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Cell categoryTitleNameCell = row.getCell(0);
                Cell categoryTitleParentCell = row.getCell(1);
                if (categoryTitleNameCell == null || categoryTitleParentCell == null) {
                    throw new DownloadDocumentException(EMPTY_VALUE_IN_CELL_TITLE_MESSAGE);
                }
                String categoryTitleName = row.getCell(0).getStringCellValue().trim();
                String categoryTitleParent = row.getCell(1).getStringCellValue().trim();
                if (!HEADER_CATEGORY.equals(categoryTitleName)
                        || !HEADER_PARENT_CATEGORY.equals(categoryTitleParent)) {
                    throw new DownloadDocumentException(HEADER_EXCEPTION_MESSAGE);
                }
            }
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Cell cellCategory = row.getCell(0);
                if (cellCategory == null) {
                    throw new DownloadDocumentException(EMPTY_VALUE_IN_CELL_MESSAGE);
                }
                String categoryName = row.getCell(0).getStringCellValue().trim();
                if (categoriesName.contains(categoryName)) {
                    throw new DownloadDocumentException(REPEAT_EXCEPTION_MESSAGE);
                }
                Cell cellParent = row.getCell(1);
                String parent = null;
                if (cellParent != null) {
                    parent = row.getCell(1).getStringCellValue().trim();
                }
                if (parent != null && !categoriesName.contains(parent)) {
                    throw new DownloadDocumentException(PARENT_EXCEPTION_MESSAGE);
                }
                categoriesName.add(categoryName);
                if (parent == null) {
                    categories.add(new CategoryDTO(categoryName, null));
                } else {
                    categories.add(new CategoryDTO(categoryName, parent));
                }
            }
        }
        return categories;
    }
}
