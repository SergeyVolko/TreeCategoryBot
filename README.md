_# Java Intern Developer
## Разработка Telegram-бота для управления деревом категорий на базе Spring Boot и PostgreSQL
### Ссылка на документацию бота:[Документация](https://sergeyvolko.github.io/TreeCategoryBot/)
**Цель:** Создать Telegram-бота, который позволит пользователям
создавать, просматривать и удалять дерево категорий.<br><br>
**Основные требования:**
- Проект должен быть реализован на Spring Boot.
- Для хранения информации о категориях используется база данных
  PostgreSQL.
- Документирование кода.
- Для работы с Telegram API рекомендуется использовать
  библиотеку TelegramBots.
- Для работы с базой данных можно использовать Spring Data JPA.
- Для обработки команд рекомендуется использовать шаблон
  проектирования Command.
- Придерживайтесь принципов SOLID.<br><br>
  **Критерии оценки:**
- Корректность выполнения команд.
- Структура кода, наличие комментариев и документации.
- Обработка возможных исключений и ошибок ввода пользователя.
- Использование принципов ООП и шаблонов проектирования.<br><br>
  **После завершения задачи:**
- Загрузите весь исходный код проекта на GitHub
- Отправьте ссылку на репозиторий на почту hr@pandev.kz
- После того как вы отправите тестовое задание в течении 1
  недели, мы обязательно вернемся с фидбеком по вашему заданию.
  
  ## Основные функциональные возможности
  **Команда:** <font color="blue">/viewTree</font><br>
  Дерево должно отображаться в структурированном виде.<br><br>
  **Команда:** <font color="blue">/addElement</font> <название элемента><br>
  Этот элемент будет корневым, если у него нет родителя.
  Добавление дочернего элемента к существующему элементу:<br><br>
  **Команда:** <font color="blue">/addElement</font> <родительский элемент> <дочерний элемент><br>
  Если родительский элемент не существует, выводить соответствующее
  сообщение.
  Просмотр всего дерева категорий:<br><br>
  **Команда:** <font color="blue">/viewTree</font><br>
  Дерево должно отображаться в структурированном виде.<br><br>
  **Команда:** <font color="blue">/removeElement</font> <название элемента><br>
  При удалении родительского элемента, все дочерние элементы также
  должны быть удалены. Если элемент не найден, выводить
  соответствующее сообщение.<br><br>
  **Команда:** <font color="blue">/help</font><br>
  Выводит список всех доступных команд и краткое их описание.<br><br>
  ## Дополнительные команды (Будет плюсом)
  **Команда:** <font color="blue">/download</font><br>
  Скачивает Excel документ с деревом категорий, формат на ваше
  усмотрение<br><br>
  **Команда:** <font color="blue">/upload</font><br>
  Принимает Excel документ с деревом категорий и сохраняет все
  элементы в базе данных

# Отчет о работе бота.
## Запуск бота по команде <font color="blue">/start</font>:
![Команда](https://github.com/SergeyVolko/TreeCategoryBot/blob/master/scan/%D0%9A%D0%BE%D0%BC%D0%B0%D0%BD%D0%B4%D0%B0%20start.png?raw=true)<br>
## Работа команды <font color="blue">/help</font>:
![Команда](https://github.com/SergeyVolko/TreeCategoryBot/blob/master/scan/%D0%9A%D0%BE%D0%BC%D0%B0%D0%BD%D0%B4%D0%B0%20help.png?raw=true)<br>
## Вывод пустого дерева по команде <font color="blue">/viewTree</font>:
![Команда](https://github.com/SergeyVolko/TreeCategoryBot/blob/master/scan/%D0%92%D1%8B%D0%B2%D0%BE%D0%B4%20%D0%BF%D1%83%D1%81%D1%82%D0%BE%D0%B3%D0%BE%20%D0%B4%D0%B5%D1%80%D0%B5%D0%B2%D0%B0.png?raw=true)<br>
## Начало загрузки документа по команде <font color="blue">/upload</font>:
![Команда](https://github.com/SergeyVolko/TreeCategoryBot/blob/master/scan/%D0%9A%D0%BE%D0%BC%D0%B0%D0%BD%D0%B4%D0%B0%20upload.png?raw=true)<br>
## Выход из режима загрузки по команде <font color="blue">/exitUpload</font>:
![Команда](https://github.com/SergeyVolko/TreeCategoryBot/blob/master/scan/%D0%92%D1%8B%D1%85%D0%BE%D0%B4%20%D0%B8%D0%B7%20%D1%80%D0%B5%D0%B6%D0%B8%D0%BC%D0%B0%20%D0%B7%D0%B0%D0%B3%D1%80%D1%83%D0%B7%D0%BA%D0%B8.png?raw=true)<br>
## Фрагмент загружаемого документа:
![Команда](https://github.com/SergeyVolko/TreeCategoryBot/blob/master/scan/%D0%A4%D1%80%D0%B0%D0%B3%D0%BC%D0%B5%D0%BD%D1%82%20%D0%B7%D0%B0%D0%B3%D1%80%D1%83%D0%B6%D0%B0%D0%B5%D0%BC%D0%BE%D0%B3%D0%BE%20%D0%B4%D0%BE%D0%BA%D1%83%D0%BC%D0%B5%D0%BD%D1%82%D0%B0.png?raw=true)<br>
## Результат загрузки:
![Команда](https://github.com/SergeyVolko/TreeCategoryBot/blob/master/scan/%D0%97%D0%B0%D0%B3%D1%80%D1%83%D0%B7%D0%BA%D0%B0%20%D0%B4%D0%BE%D0%BA%D1%83%D0%BC%D0%B5%D0%BD%D1%82%D0%B0.png?raw=true)<br>
## Дерево категорий после загрузки документа:
![Команда](https://github.com/SergeyVolko/TreeCategoryBot/blob/master/scan/%D0%9E%D1%82%D0%BE%D0%B1%D1%80%D0%B0%D0%B6%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B4%D0%B5%D1%80%D0%B5%D0%B2%D0%B0%20%D0%BA%D0%B0%D1%82%D0%B5%D0%B3%D0%BE%D1%80%D0%B8%D0%B9%20%D0%BF%D0%BE%D1%81%D0%BB%D0%B5%20%D0%B7%D0%B0%D0%B3%D1%80%D1%83%D0%B7%D0%BA%D0%B8.png?raw=true)<br>
## Добавление категории "Орехи" командой <font color="blue">/addElement</font> <название элемента>:
![Команда](https://github.com/SergeyVolko/TreeCategoryBot/blob/master/scan/%D0%94%D0%BE%D0%B1%D0%B0%D0%B2%D0%BB%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%BA%D0%B0%D1%82%D0%B5%D0%B3%D0%BE%D1%80%D0%B8%D0%B8%20%D0%BE%D1%80%D0%B5%D1%85%D0%B8.png?raw=true)<br>
## Добавление дополнительных категорий командой <font color="blue">/addElement</font> <название элемента> и командой <font color="blue">/addElement</font> <родительский элемент> <дочерний элемент>:
![Команда](https://github.com/SergeyVolko/TreeCategoryBot/blob/master/scan/%D0%94%D0%BE%D0%B1%D0%B0%D0%B2%D0%BB%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B4%D0%BE%D0%BF%D0%BE%D0%BB%D0%BD%D0%B8%D1%82%D0%B5%D0%BB%D1%8C%D0%BD%D1%8B%D1%85%20%D0%BA%D0%B0%D1%82%D0%B5%D0%B3%D0%BE%D1%80%D0%B8%D0%B9.png?raw=true)<br>
## Результат добавления категорий предыдущими командами в виде дерева:
![Команда](https://github.com/SergeyVolko/TreeCategoryBot/blob/master/scan/%D0%A0%D0%B5%D0%B7%D1%83%D0%BB%D1%8C%D1%82%D0%B0%D1%82%20%D0%B4%D0%BE%D0%B1%D0%B0%D0%B2%D0%BB%D0%B5%D0%BD%D0%B8%D1%8F%20%D0%BE%D1%80%D0%B5%D1%85%D0%BE%D0%B2.png?raw=true)<br>
## Добавление "Груш" и результат добавления в виде дерева:
![Команда](https://github.com/SergeyVolko/TreeCategoryBot/blob/master/scan/%D0%94%D0%BE%D0%B1%D0%B0%D0%B2%D0%BB%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B3%D1%80%D1%83%D1%88.png?raw=true)<br>
## Выгрузка добавленных категорий в документ excel командой <font color="blue">/download</font>:
![Команда](https://github.com/SergeyVolko/TreeCategoryBot/blob/master/scan/%D0%92%D1%8B%D0%B3%D1%80%D1%83%D0%B7%D0%BA%D0%B0%20%D0%BA%D0%B0%D1%82%D0%B5%D0%B3%D0%BE%D1%80%D0%B8%D0%B9%20%D0%B2%20%D0%B4%D0%BE%D0%BA%D1%83%D0%BC%D0%B5%D0%BD%D1%82%20excel.png?raw=true)<br>
## Фрагмент документа, полученный после выгрузки:
![Команда](https://github.com/SergeyVolko/TreeCategoryBot/blob/master/scan/%D0%A0%D0%B5%D0%B7%D1%83%D0%BB%D1%8C%D1%82%D0%B0%D1%82%20%D0%B2%D1%8B%D0%B3%D1%80%D1%83%D0%B7%D0%BA%D0%B8.png?raw=true)<br>
## Работа команды <font color="blue">/removeElement</font> <название элемента> по удалению "Кешью":
![Команда](https://github.com/SergeyVolko/TreeCategoryBot/blob/master/scan/%D0%A3%D0%B4%D0%B0%D0%BB%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%9A%D0%B5%D1%88%D1%8C%D1%8E.png?raw=true)<br>
## Работа команды <font color="blue">/removeElement</font> <название элемента> по удалению категории "Фрукты":
![Команда](https://github.com/SergeyVolko/TreeCategoryBot/blob/master/scan/%D0%A3%D0%B4%D0%B0%D0%BB%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%BA%D0%B0%D1%82%D0%B5%D0%B3%D0%BE%D1%80%D0%B8%D0%B8%20%D0%A4%D1%80%D1%83%D0%BA%D1%82%D1%8B.png?raw=true)<br>
## Очистка дерева категорий командой <font color="blue">/removeAll</font>:
![Команда](https://github.com/SergeyVolko/TreeCategoryBot/blob/master/scan/%D0%9E%D1%87%D0%B8%D1%81%D1%82%D0%BA%D0%B0%20%D0%B4%D0%B5%D1%80%D0%B5%D0%B2%D0%B0%20%D0%BA%D0%B0%D1%82%D0%B5%D0%B3%D0%BE%D1%80%D0%B8%D0%B9.png?raw=true)<br>
## Восстановление дерева категорий из ранее выгруженного файла: 
![Команда](https://github.com/SergeyVolko/TreeCategoryBot/blob/master/scan/%D0%92%D0%BE%D1%81%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BB%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B4%D0%B5%D1%80%D0%B5%D0%B2%D0%B0%20%D0%BA%D0%B0%D1%82%D0%B5%D0%B3%D0%BE%D1%80%D0%B8%D0%B9%20%D0%B8%D0%B7%20%D1%80%D0%B0%D0%BD%D0%B5%D0%B5%20%D0%B2%D1%8B%D0%B3%D1%80%D1%83%D0%B6%D0%B5%D0%BD%D0%BD%D0%BE%D0%B3%D0%BE%20%D1%84%D0%B0%D0%B9%D0%BB%D0%B0.png?raw=true)<br>
