package org.utils;

import com.google.gson.Gson;
import org.apache.commons.dbutils.BeanProcessor;
import org.constants.ConfigConstants;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

public class ParserUtils {
    public static <T> List<T> parseResultSetToListOfObjects(ResultSet resultSet, Class<T> classOfT) {
        List<T> listOfObjects = new ArrayList<>();
        try {
            while (resultSet.next()) {
                BeanProcessor beanProcessor = new BeanProcessor();
                listOfObjects.add(beanProcessor.toBean(resultSet, classOfT));
            }
        } catch (SQLException e) {
            LoggerUtils.logError("Ошибка при парсинге ResultSet в обьект", new RuntimeException(e));
        }
        return listOfObjects;
    }

    public static Date parseStringToDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat(ConfigConstants.DATA_FORMAT);
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            LoggerUtils.logError("Ошибка при парсинге строки в тип данных Date", new RuntimeException(e));
        }
        return null;
    }

    public static String parseImageToString(String pathToImage) {
        try (FileInputStream imageStream = new FileInputStream(pathToImage)) {
            byte[] imageData = imageStream.readAllBytes();
            return Base64.getEncoder().encodeToString(imageData);
        } catch (IOException e) {
            LoggerUtils.logError("Ошибка при кодировке изображения", new RuntimeException(e));
        }
        return null;
    }

    public static <T> T parseJsonToObject(String pathToTestDataFile, Class<T> classOfT) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(pathToTestDataFile)) {
            return gson.fromJson(reader, classOfT);
        } catch (IOException e) {
            LoggerUtils.logError("Ошибка при парсинге json в объект", new RuntimeException(e));
        }
        return null;
    }
}