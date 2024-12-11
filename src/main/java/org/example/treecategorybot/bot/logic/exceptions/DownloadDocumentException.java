package org.example.treecategorybot.bot.logic.exceptions;

/**
 * Исключение, возникающее при ошибках, связанных с загрузкой документа.
 *
 * <p>
 * Этот класс расширяет стандартный класс {@link Exception} и предназначен для
 * обработки ситуаций, когда не удается загрузить документ по каким-либо причинам.
 * </p>
 */
public class DownloadDocumentException extends Exception{
    public DownloadDocumentException(String message) {
        super(message);
    }
}
