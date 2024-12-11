package org.example.treecategorybot.configuration;

import org.example.treecategorybot.bot.TreeCategoriesBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 * Конфигурационный класс для настройки бота TreeCategoriesBot.
 *
 * Этот класс отвечает за создание и конфигурацию необходимых бинов для работы с
 * Telegram Bot API. Он использует аннотацию {@link Configuration} для обозначения
 * класса как источника конфигурации Spring, а также предоставляет метод для
 * регистрации бота в API Telegram.
 */
@Configuration
public class TreeCategoriesBotConfiguration {

    /**
     * Создает и настраивает экземпляр {@link TelegramBotsApi} для работы с ботом.
     *
     * @param treeCategoriesBot экземпляр бота, который будет зарегистрирован в API Telegram.
     * @return настроенный экземпляр {@link TelegramBotsApi}, готовый к использованию.
     * @throws TelegramApiException если возникает ошибка при регистрации бота в API.
     */
    @Bean
    public TelegramBotsApi telegramBotsApi(TreeCategoriesBot treeCategoriesBot) throws TelegramApiException {
        var api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(treeCategoriesBot);
        return api;
    }
}
