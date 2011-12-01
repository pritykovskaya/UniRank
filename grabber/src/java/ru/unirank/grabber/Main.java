package ru.unirank.grabber;

import org.webharvest.definition.ScraperConfiguration;
import org.webharvest.runtime.Scraper;
//import ru.compscicenter.schoolinfo.storage.Database;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {

        String configFilePath = "grabber/configs/topcoder.xml";
        ScraperConfiguration config = new ScraperConfiguration(configFilePath);

        TopCoderListener listener = new TopCoderListener();
        listener.cleanAllData();
        Scraper scraper = new Scraper(config, ".");
        scraper.addRuntimeListener(listener);
        scraper.execute();

    }
}
	
	