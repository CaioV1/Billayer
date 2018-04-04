package com.digitalindividual.billayer.util;

import java.util.Date;
import java.util.Calendar;

/**
 * Created by 17170077 on 04/04/2018.
 */

public class ControleData {

    public Boolean dataAtual(Date date){

        Calendar calendar = Calendar.getInstance();

        Date dataAtual = calendar.getTime();

        if(dataAtual.compareTo(date) == 0 || dataAtual.compareTo(date) > 0){

            return true;

        } else {

            return false;

        }

    }

}
