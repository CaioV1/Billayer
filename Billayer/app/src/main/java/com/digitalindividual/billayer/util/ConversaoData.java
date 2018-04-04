package com.digitalindividual.billayer.util;

import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 17170077 on 20/03/2018.
 */

public class ConversaoData {

    public Date sqliteParaDate(int dataSQLite){

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        String dataString = String.valueOf(dataSQLite);

        if(dataString.length() == 7) {

            dataString = "0" + dataString;

        }

        String dataArray[] = new String[3];

        dataArray[0] = dataString.substring(0, 2);

        dataArray[1] = dataString.substring(2, 4);

        dataArray[2] = dataString.substring(4, 8);

        dataString = dataArray[0] + "/" + dataArray[1] + "/" + dataArray[2];

        Date date = null;

        try {

            date = dateFormat.parse(dataString);

        } catch (ParseException e) {

            e.printStackTrace();

        }

        return date;

    }

    public String sqliteParaString(int dataSQLite){

        String dataString = String.valueOf(dataSQLite);

        String dataArray[] = new String[3];

        dataArray[0] = dataString.substring(0, 2);

        dataArray[1] = dataString.substring(2, 4);

        dataArray[2] = dataString.substring(4, 8);

        dataString = dataArray[0] + "/" + dataArray[1] + "/" + dataArray[2];

        return dataString;

    }

    public Date stringParaDate(String data){

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date date = null;

        try {

            date = dateFormat.parse(data);

        } catch (ParseException e) {

            e.printStackTrace();

        }

        return date;

    }

    public int dateParaSQLite(Date dataJava){

        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");

        String dataInterface = dateFormat.format(dataJava);

        int dataSQLite = Integer.parseInt(dataInterface);

        return dataSQLite;

    }

    public String dateParaString(Date date) {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        String data = dateFormat.format(date);

        return data;

    }

}
