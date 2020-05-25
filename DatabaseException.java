package App.web;

import java.io.IOException;
import java.util.logging.*;

public class DatabaseException extends RuntimeException{
    private static Logger logger = Logger.getLogger("DatabaseException");
    private static FileHandler fileHandler;

    static {
        try {
            fileHandler = new FileHandler("my_l.txt");
        } catch (IOException e) {
            logger.severe("Cannot create file handler");
        }
    }

    public DatabaseException(){
        logger.severe("Database exception.");
        SimpleFormatter file = new SimpleFormatter();
        fileHandler.setFormatter(file);
        logger.addHandler(fileHandler);
    }
}
