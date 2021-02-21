package configReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    static Properties p = null;

    public ConfigReader(){ // file initialization and loading we are doing here
        try {
            p = new Properties(); // Properties p is getting initialized

            File file = new File(System.getProperty("user.dir")+"\\Resources\\ConfigFiles\\Environment.properties");
            FileInputStream fis = new FileInputStream(file); // reading of any file


            p.load(fis); }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key) {

        if (p == null) {
            new ConfigReader(); // calling the constructor. creating the object of ConfigReader class
        }

        String value = p.getProperty(key);
        return value;
    }
}

