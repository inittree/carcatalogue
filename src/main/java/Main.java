import catalogue.CarCatalogueController;
import catalogue.CarCatalogueInfo;
import catalogue.CarCatalogueView;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;

public class Main {

    public static void main(String[] args) {

        //disable mongodb logs
        //https://stackoverflow.com/questions/30137564/how-to-disable-mongodb-java-driver-logging
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
        rootLogger.setLevel(Level.OFF);

        CarCatalogueController carCatalogueController = new CarCatalogueController(
                new CarCatalogueInfo(),
                new CarCatalogueView()
        );

        carCatalogueController.start();
    }
}
