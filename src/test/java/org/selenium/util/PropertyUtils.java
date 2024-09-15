package org.selenium.util;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

//   public static Properties propertyLoader(String filePath){
//       Properties properties = new Properties();
//       BufferedReader reader;
//       try{
//           reader = new BufferedReader(new FileReader(filePath));
//           try{
//               properties.load(reader);
//               reader.close();
//           } catch (IOException e){
//               e.printStackTrace();
//               throw new RuntimeException("Failed to load properties "+filePath);
//           }
//       } catch(FileNotFoundException e){
//           e.printStackTrace();
//           throw new RuntimeException("File not found "+filePath);
//       }
//       return properties;
//   }

    public static Properties propertyLoader (String filePath){
        Properties properties = new Properties();
        FileReader reader;
        try{
            reader = new FileReader(filePath);
            properties.load(reader);
            reader.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
            throw new RuntimeException("File not found "+filePath);
        } catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("Failed to load properties "+filePath);
        }
        return properties;
    }
}
