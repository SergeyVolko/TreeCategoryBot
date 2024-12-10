package org.example.treecategorybot.bot.services;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

public interface GenerateInputStreamService {

    InputStream getInputStream(String param) throws TelegramApiException, IOException;
}
