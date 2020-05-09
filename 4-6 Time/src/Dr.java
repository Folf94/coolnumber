import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Dr {
    public static void main(String[] args) {
        DateFormat dateFormat = new SimpleDateFormat(" dd.MM.yyyy - EEE", Locale.ENGLISH);
        Calendar calendar = Calendar.getInstance();
        calendar.set(1994, Calendar.AUGUST, 23);
        Date date = new Date();
        Date me = calendar.getTime();
        int i = 0;
        while (date.compareTo(me) > 0) {
            System.out.println(i + dateFormat.format(me));
            calendar.add(Calendar.YEAR, 1);
            me = calendar.getTime();
            i++;

        }
    }
}
