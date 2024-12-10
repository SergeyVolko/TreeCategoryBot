package org.example.treecategorybot.bot.services;

import org.example.treecategorybot.bot.dto.CategoryDTO;
import org.example.treecategorybot.bot.logic.exceptions.DownloadDocumentException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface ExcelReaderService {
    List<CategoryDTO> readExcelFile(InputStream inputStream) throws IOException, DownloadDocumentException;
}
