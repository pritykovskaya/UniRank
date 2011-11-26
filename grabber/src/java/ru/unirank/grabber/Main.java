package ru.unirank.grabber;
import org.apache.log4j.Logger;
import org.webharvest.definition.ScraperConfiguration;
import org.webharvest.runtime.Scraper;
import ru.unirank.core.db.Database;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) throws IOException, SQLException {


        String configFilePath = "grabber/configs/topcoder.xml";
        ScraperConfiguration config = new ScraperConfiguration(configFilePath);
        Database database = new Database();

        try {
            database.connectToDB();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        TopCoderListener listener = new TopCoderListener(database);
        Scraper scraper = new Scraper(config, ".");
        scraper.addRuntimeListener(listener);
        scraper.execute();

        log.info("Everything gonna be all right");
        database.closeConnection();
    }
}

