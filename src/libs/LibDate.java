/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libs;

import com.mysql.fabric.xmlrpc.base.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author NHI
 */
public class LibDate {
	private static final String DATE_FORMAT = "dd/MM/yyyy";
	public static Date getDate(String sdate) {
		if(sdate.isEmpty()) return new Date();
		          SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		try {
			return sdf.parse(sdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}
	public static LocalDate getLocalDate(java.sql.Date date){
		// convert from date util -> date sql
                if(date == null){
                    return LocalDate.now();
                }
		return date.toLocalDate();
	}
	
}
