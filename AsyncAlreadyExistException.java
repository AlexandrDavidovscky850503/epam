package App.web;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.logging.*;

public class AsyncAlreadyExistException extends RuntimeException implements AsyncUncaughtExceptionHandler {
    private static Logger logger = Logger.getLogger("AsyncAlreadyExistException");
    private static FileHandler fileHandler;

    static {
        try {
            fileHandler = new FileHandler("my_l.txt");
        } catch (IOException e) {
            logger.severe("Cannot create file handler");
        }
    }

    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
        logger.severe("The ticket with this id already exists");
        SimpleFormatter file = new SimpleFormatter();
        fileHandler.setFormatter(file);
        logger.addHandler(fileHandler);
    }

}
