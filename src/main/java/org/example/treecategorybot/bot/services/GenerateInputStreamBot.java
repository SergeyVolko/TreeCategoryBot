package org.example.treecategorybot.bot.services;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Service
public class GenerateInputStreamBot implements GenerateInputStreamService{
    private final TelegramLongPollingBot bot;

    public GenerateInputStreamBot(@Lazy TelegramLongPollingBot bot) {
        this.bot = bot;
    }

    @Override
    public InputStream getInputStream(String param) throws TelegramApiException, IOException {
        GetFile getFile = new GetFile();
        getFile.setFileId(param);
        File file = bot.execute(getFile);
        String filePath = file.getFilePath();
        return new URL("https://api.telegram.org/file/bot" + bot.getBotToken() + "/" + filePath).openStream();
    }
}
