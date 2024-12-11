package org.example.treecategorybot.bot.services;

import org.example.treecategorybot.bot.dto.CategoryDTO;
import org.example.treecategorybot.bot.logic.exceptions.DownloadDocumentException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.List;

import static org.example.treecategorybot.bot.logic.constants.ExceptionsTextMessages.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExcelReaderServiceImplTest {

    private final ExcelReaderServiceImpl excelReaderService;

    @Autowired
    ExcelReaderServiceImplTest(ExcelReaderServiceImpl excelReaderService) {
        this.excelReaderService = excelReaderService;
    }


    @Test
    void readExcelFile() throws DownloadDocumentException {
        File file = new File("src/test/java/files/categories.xlsx");
        try(InputStream inputStream = new FileInputStream(file)) {
            List<CategoryDTO> categoryDTOS = excelReaderService.readExcelFile(inputStream);
            int expectedSize = 15;
            int actualSize = categoryDTOS.size();
            assertEquals(expectedSize, actualSize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void readExcelFileNotTitle() {
        File file = new File("src/test/java/files/categories_not_title.xlsx");
        try(InputStream inputStream = new FileInputStream(file)) {
            Exception exception = assertThrows(DownloadDocumentException.class, () ->
                    excelReaderService.readExcelFile(inputStream));
            assertEquals(EMPTY_VALUE_IN_CELL_TITLE_MESSAGE, exception.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void readExcelFileNotCellTitleCategory() {
        File file = new File("src/test/java/files/categories_not_title_category.xlsx");
        try(InputStream inputStream = new FileInputStream(file)) {
            Exception exception = assertThrows(DownloadDocumentException.class, () ->
                    excelReaderService.readExcelFile(inputStream));
            assertEquals(EMPTY_VALUE_IN_CELL_TITLE_MESSAGE, exception.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void readExcelFileNotCellTitleParent() {
        File file = new File("src/test/java/files/categories_not_tittle_parent.xlsx");
        try(InputStream inputStream = new FileInputStream(file)) {
            Exception exception = assertThrows(DownloadDocumentException.class, () ->
                    excelReaderService.readExcelFile(inputStream));
            assertEquals(EMPTY_VALUE_IN_CELL_TITLE_MESSAGE, exception.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void readExcelFileWrongNameTitleCategory() {
        File file = new File("src/test/java/files/categories_wrong_name_title_categories.xlsx");
        try(InputStream inputStream = new FileInputStream(file)) {
            Exception exception = assertThrows(DownloadDocumentException.class, () ->
                    excelReaderService.readExcelFile(inputStream));
            assertEquals(HEADER_EXCEPTION_MESSAGE, exception.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void readExcelFileWrongNameTitleParent() {
        File file = new File("src/test/java/files/categories_wrong_title_parent.xlsx");
        try(InputStream inputStream = new FileInputStream(file)) {
            Exception exception = assertThrows(DownloadDocumentException.class, () ->
                    excelReaderService.readExcelFile(inputStream));
            assertEquals(HEADER_EXCEPTION_MESSAGE, exception.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void readExcelFileEmptyCategory() {
        File file = new File("src/test/java/files/categories_empty_category.xlsx");
        try(InputStream inputStream = new FileInputStream(file)) {
            Exception exception = assertThrows(DownloadDocumentException.class, () ->
                    excelReaderService.readExcelFile(inputStream));
            assertEquals(EMPTY_VALUE_IN_CELL_MESSAGE, exception.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void readExcelFileNotParentCategory() {
        File file = new File("src/test/java/files/categories_not_parent.xlsx");
        try(InputStream inputStream = new FileInputStream(file)) {
            Exception exception = assertThrows(DownloadDocumentException.class, () ->
                    excelReaderService.readExcelFile(inputStream));
            assertEquals(PARENT_EXCEPTION_MESSAGE, exception.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void readExcelFileRepeatCategory() {
        File file = new File("src/test/java/files/categories_repeat.xlsx");
        try(InputStream inputStream = new FileInputStream(file)) {
            Exception exception = assertThrows(DownloadDocumentException.class, () ->
                    excelReaderService.readExcelFile(inputStream));
            assertEquals(REPEAT_EXCEPTION_MESSAGE, exception.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}