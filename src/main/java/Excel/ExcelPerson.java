package Excel;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelRow;

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

    @ExcelCellName("Rozwiązanie WPBK-TUNNELLING?")
    private String endOfContract;

    @ExcelCellName("Data rozwiązania")
    private String dateOfEndContractTunnel;

    @ExcelCellName("Rozwiązanie PBKR-EXPORT?")
    private String dateOfEndContractPbkr;

    @ExcelCellName("Rozwiązanie BIS-EXPORT")
    private String dateOfEndContractBis;

    @ExcelCellName("SOKA BAU?")
    private String pkbrContract;

    @ExcelCellName("BIS?")
    private String bisContract;

    @ExcelCellName("TUNNEL?")
    private String tunnelContract;


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

    public String getDateOfEndContractTunnel() {
        return dateOfEndContractTunnel;
    }

    public String getDateOfEndContractPbkr() {
        return dateOfEndContractPbkr;
    }

    public String getDateOfEndContractBis() {
        return dateOfEndContractBis;
    }

    public String getPkbrContract() {
        return pkbrContract;
    }

    public String getBisContract() {
        return bisContract;
    }

    public String getTunnelContract() {
        return tunnelContract;
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
                ", dateOfEndOfContracy='" + dateOfEndContractTunnel + '\'' +
                ", sokaBauContract='" + pkbrContract + '\'' +
                ", bisContract='" + bisContract + '\'' +
                ", tunnelContract='" + tunnelContract + '\'' +
                '}';
    }
}
