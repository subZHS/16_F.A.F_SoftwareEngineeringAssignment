package presentation.roomui.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 实现有关日期的类LocalDate，Date和String之间相互转化的方法
 * @author 双
 * @version 
 * @see
 */
public class LocalDateAdapter {
    
    static private String pattern="yyyy-MM-dd";
    static private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
    static private SimpleDateFormat simpleDateFormat=new SimpleDateFormat(pattern);
    
    //LocalDate转化成String
    static public String toString(LocalDate date) {
        if (date != null) {
            return dateFormatter.format(date);
        } else {
            return "";
        }
    }
    
    //string转化成LocalDate
    static public LocalDate fromString(String string) {
        if (string != null && !string.isEmpty()) {
            return LocalDate.parse(string, dateFormatter);
        } else {
            return null;
        }
    }
    
    //localDate转化成Date
    static public Date toDate(LocalDate date){
        String str=toString(date);
        if(str==""){
            return null;
        }
        Date result = null;
        try {
            result=simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    //Date转化成LocalDate
    static public LocalDate fromDate(Date date){
        String str=simpleDateFormat.format(date);
        return fromString(str);
    }
}
