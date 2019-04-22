/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 *
 * @author NHI
 */
public class DateUtils {
    public static LocalDate convertSdatatoLDate(Date sDate){
        if(sDate == null){
            return null;
        }
        return sDate.toLocalDate();
    }
     public static java.sql.Date convertLDateToSDate(LocalDate ldate) {
        if (ldate == null) {
            return null;
        }
        return java.sql.Date.valueOf(ldate);
    }
     public static LocalDate convertUdatatoLDate(java.util.Date UDate){
        if(UDate == null){
            return null;
        }
        return UDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
     public static java.util.Date convertLdatatoUDate(LocalDate LDate){
        if(LDate == null){
            return null;
        }
        return java.util.Date.from(LDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }
}
