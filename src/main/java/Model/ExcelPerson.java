package Model;

import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelRow;
import javafx.scene.control.DateCell;
import javafx.util.converter.LocalDateTimeStringConverter;
import sun.util.calendar.BaseCalendar;

import java.security.Timestamp;
import java.sql.Date;
import java.sql.Time;
import java.time.*;
import java.time.format.DateTimeFormatter;




public class ExcelPerson {

    private static final String datePattern = "dd/MM/yyyy";
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(datePattern);

    @ExcelRow
    private int rowIndex;

    @ExcelCellName("Nazwisko, Imie")
    private String name;

    @ExcelCellName("Data ur.")
    public String birthDate;

    @ExcelCellName("Adres zamieszkania")
    private String address;

    @ExcelCellName("Nr. Telefonu")
    private String phoneNumber;

    @ExcelCellName("Rozwiązanie umowy?")
    private String endOfContract;

   @ExcelCellName("Data rozwiązania")
    private String dateOfEndOfContracy;

    @ExcelCellName("SOKA BAU?")
    private String sokaBauContract;



    public int getRowIndex() {
        return rowIndex;
    }

    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEndOfContract() {
        return endOfContract;
    }

    public String getDateOfEndOfContract() {
        return dateOfEndOfContracy;
    }

    public String getSokaBauContract() {
        return sokaBauContract;
    }

    @Override
    public String toString() {
        return "ExcelPerson{" +
                "rowIndex=" + rowIndex +
                ", name='" + name + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", endOfContract='" + endOfContract + '\'' +
                ", dateOfEndOfContracy='" + dateOfEndOfContracy + '\'' +
                '}';
    }
}
