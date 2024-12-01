package org.example.treecategorybot.configuration;

import org.example.treecategorybot.bot.TreeCategoriesBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class TreeCategoriesBotConfiguration {

    @Bean
    public TelegramBotsApi telegramBotsApi(TreeCategoriesBot treeCategoriesBot) throws TelegramApiException {
        var api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(treeCategoriesBot);
        return api;
    }

}
