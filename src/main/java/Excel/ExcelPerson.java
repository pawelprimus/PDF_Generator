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

    @ExcelCellName("Data rozwiązania")
    private String dateOfEndContractTunnel;

    @ExcelCellName("Rozwiązanie WPBK-TUNNELLING?")
    private String endOfContractTunnel;

    @ExcelCellName("Rozwiązanie PBKR-EXPORT?")
    private String endOfContractPkbr;

    @ExcelCellName("Rozwiązanie BIS-EXPORT")
    private String endOfContractBis;

    @ExcelCellName("SOKA BAU?")
    private String pkbrContract;

    @ExcelCellName("BIS?")
    private String bisContract;

    @ExcelCellName("TUNNEL?")
    private String tunnelContract;


    int getRowIndex() {
        return rowIndex;
    }

    String getName() {
        return name;
    }

    String getBirthDate() {
        return birthDate;
    }

    String getAddress() {
        return address;
    }

    String getPhoneNumber() {
        return phoneNumber;
    }

    String getDateOfEndContractTunnel() {
        return dateOfEndContractTunnel;
    }

    String getEndOfContractTunnel() {
        return endOfContractTunnel;
    }

    String getEndOfContractPkbr() {
        return endOfContractPkbr;
    }

    String getEndOfContractBis() {
        return endOfContractBis;
    }

    String getPkbrContract() {
        return pkbrContract;
    }

    String getBisContract() {
        return bisContract;
    }

    String getTunnelContract() {
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
                ", endOfContract='" + endOfContractTunnel + '\'' +
                ", dateOfEndOfContracy='" + dateOfEndContractTunnel + '\'' +
                ", sokaBauContract='" + pkbrContract + '\'' +
                ", bisContract='" + bisContract + '\'' +
                ", tunnelContract='" + tunnelContract + '\'' +
                '}';
    }
}
