package ro.mta.se.lab;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class is used to log all events
 * in log.txt file.
 *
 * @author Ciobanu Cosmin-Marian
 */

public class Logger {
    final static String filename = "logs.txt";

    static public void logEvent(String cityName, String countryCode) {
        try {
            File outFile = new File(filename);
            FileWriter out = new FileWriter(outFile, true);
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String messageToWrite = "[ " + formatter.format(date) + " ] : ";
            messageToWrite += "A fost cautat orasul " + cityName;
            messageToWrite += " care face parte din statul " + countryCode;
            out.append(messageToWrite + "\n");
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
