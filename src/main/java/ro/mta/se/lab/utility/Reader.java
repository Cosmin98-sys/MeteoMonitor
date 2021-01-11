package ro.mta.se.lab.utility;

import ro.mta.se.lab.model.CityInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {

    static public ArrayList<CityInfo> readCities(String filename){
        ArrayList<CityInfo> cities = new ArrayList<CityInfo>();
        try {
            File cityFile = new File(filename);
            Scanner reader = new Scanner(cityFile);
            while (reader.hasNextLine()) {
                Scanner readerWord = new Scanner(reader.nextLine());
                String id = readerWord.next();
                String name = readerWord.next();
                String lat = readerWord.next();
                String longit = readerWord.next();
                String state = readerWord.next();
                CityInfo city = new CityInfo(Integer.parseInt(id), name, Double.parseDouble(lat), Double.parseDouble(longit), state);
                cities.add(city);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not existent");
        }
        return cities;
    }
}
