package com.example.demoapp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Utils {

    public static List<String> convertStringToList(String csv, String delimiter) {
        String[] values;
        List<String> tagsList = new ArrayList<String>();
        if (csv == null || csv.trim().isEmpty()) {
            return tagsList;
        }
        csv = csv.replace(" ", "");
        values = csv.split(delimiter);
        tagsList.addAll(Arrays.asList(values));
        return tagsList;
    }
}
