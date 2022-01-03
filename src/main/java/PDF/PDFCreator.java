package PDF;

import Model.Person;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PDFCreator {

    //end contract
    private static final Coordinate EC_EMPL_NAME_SURNAME = new Coordinate(70, 753);
    private static final Coordinate EC_EMPL_STREET = new Coordinate(70, 715);
    private static final Coordinate EC_EMPL_CITY = new Coordinate(70, 678);
    private static final Coordinate EC_EMPL_DATE_OF_END = new Coordinate(115, 440);
    private static final Coordinate EC_EMPL_DATE_OF_END_2 = new Coordinate(220, 180);
    //SOKA BAU
    private static final Coordinate SOKA_EMPL_NAME = new Coordinate(75, 730);
    private static final Coordinate SOKA_EMPL_SURNAME = new Coordinate(75, 703);
    private static final Coordinate SOKA_EMPL_BIRTHDAY = new Coordinate(393, 705);
    private static final Coordinate SOKA_EMPL_STREET = new Coordinate(65, 626);
    private static final Coordinate SOKA_EMPL_CITY = new Coordinate(270, 600);
    private static final Coordinate SOKA_EMPL_CITY_CODE = new Coordinate(150, 599);
    private static final Coordinate SOKA_PL_STAMP = new Coordinate(40, 598);
    private static final Coordinate SOKA_FIRST_STAMP = new Coordinate(60, 245);
    private static final Coordinate SOKA_SECOND_STAMP = new Coordinate(280, 245);
    //BIS BAU
    private static final Coordinate BIS_EMPL_NAME = new Coordinate(80, 710);
    private static final Coordinate BIS_EMPL_SURNAME = new Coordinate(80, 685);
    private static final Coordinate BIS_EMPL_BIRTHDAY = new Coordinate(397, 686);
    private static final Coordinate BIS_EMPL_STREET = new Coordinate(80, 611);
    private static final Coordinate BIS_EMPL_CITY = new Coordinate(280, 588);
    private static final Coordinate BIS_EMPL_CITY_CODE = new Coordinate(150, 585);
    private static final Coordinate BIS_PL_STAMP = new Coordinate(62, 585);
    private static final Coordinate BIS_FIRST_STAMP = new Coordinate(75, 248);
    private static final Coordinate BIS_SECOND_STAMP = new Coordinate(285, 249);
    //TUNNEL BAU
    private static final Coordinate TUNNEL_EMPL_NAME = new Coordinate(80, 711);
    private static final Coordinate TUNNEL_EMPL_SURNAME = new Coordinate(80, 685);
    private static final Coordinate TUNNEL_EMPL_BIRTHDAY = new Coordinate(398, 686);
    private static final Coordinate TUNNEL_EMPL_STREET = new Coordinate(80, 611);
    private static final Coordinate TUNNEL_EMPL_CITY = new Coordinate(280, 586);
    private static final Coordinate TUNNEL_EMPL_CITY_CODE = new Coordinate(150, 585);
    private static final Coordinate TUNNEL_PL_STAMP = new Coordinate(65, 585);
    private static final Coordinate TUNNEL_FIRST_STAMP = new Coordinate(80, 247);
    private static final Coordinate TUNNEL_SECOND_STAMP = new Coordinate(285, 247);

    private static final String STAMP_TEXT = "PL";
    private static final String FIRST_TEXT = "02 - Beton und Stahlarbeiten";
    private static final String SECOND_TEXT = "Schallungs ellemente monteur";
    private static final String PDF_EXTENSION = ".pdf";
    private static final String CONTRACT_END_TUNNEL = "Rozwiązanie umowy o pracę - WPBK-TUNNELLING";
    private static final String CONTRACT_END_FILE_NAME_TUNNEL = CONTRACT_END_TUNNEL + PDF_EXTENSION;
    private static final String NEW_CONTRACT_END_FILE_NAME = "%s %s " + CONTRACT_END_FILE_NAME_TUNNEL;

    private static final String SOKA_BAU = "SOKA-BAU - PBKR";
    private static final String SOKA_BAU_FILE_NAME = SOKA_BAU + PDF_EXTENSION;

    private static final String BIS_BAU = "SOKA-BAU - WPBK-BIS-EXPORT";
    private static final String BIS_FILE_NAME = BIS_BAU + PDF_EXTENSION;

    private static final String TUNNEL = "SOKA-BAU-WPBK-TUNNELLING";
    private static final String TUNNEL_FILE_NAME = TUNNEL + PDF_EXTENSION;

    private static final String NEW_SOKA_BAU_FILE_NAME = "%s %s " + SOKA_BAU_FILE_NAME;
    private static final String NEW_BIS_FILE_NAME = "%s %s " + BIS_FILE_NAME;
    private static final String NEW_TUNNEL_FILE_NAME = "%s %s " + TUNNEL_FILE_NAME;

    private static final String DD_MM_YYYY = "dd.MM.yyyy";
    private static final String PDF_DATE_FORMAT = "dd MM yyyy";
    private static final String REPORT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SS";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DD_MM_YYYY);
    private static final DateTimeFormatter PDF_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(PDF_DATE_FORMAT);
    private static final DateTimeFormatter REPORT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(REPORT_DATE_FORMAT);
    private static final String EC_GENERATE_INFO = "GENERATE END CONTRACT FOR: %s %s WITH DATE: %s";
    private static final String SOKA_GENERATE_INFO = "GENERATE SOKA BAU CONTRACT FOR: %s %s ";
    private static final String BIS_GENERATE_INFO = "GENERATE BIS BAU CONTRACT FOR: %s %s ";
    private static final String TUNNEL_GENERATE_INFO = "GENERATE TUNNEL BAU CONTRACT FOR: %s %s ";

    public void generateTerminationOfEmploymentContracts(List<Person> people, Path dataFolder, StringBuilder reportText) throws IOException, DocumentException {
        for (Person person : people) {
            if (person.isEndOfContract()) {
                if (person.isDataValid()) {
                    reportText.append(LocalDateTime.now().format(REPORT_DATE_TIME_FORMATTER)).append(" ").append(String.format(EC_GENERATE_INFO, person.getSurname(), person.getName(), person.getEndOfContractDate())).append("\n").append(" -> ");
                    System.out.println(String.format(EC_GENERATE_INFO, person.getSurname(), person.getName(), person.getEndOfContractDate()));
                    generateTerminationOfEmploymentContract(person, dataFolder);
                    reportText.append(LocalDateTime.now().format(REPORT_DATE_TIME_FORMATTER)).append(" FINISHED").append("\n");
                } else {
                    reportText.append(LocalDateTime.now().format(REPORT_DATE_TIME_FORMATTER)).append(" WRONG DATA").append("\n");
                }
            }
        }
    }


    private void generateTerminationOfEmploymentContract(Person person, Path contractPath) throws IOException, DocumentException {

        String inputFilePath = contractPath + "\\Templates\\" + CONTRACT_END_FILE_NAME_TUNNEL;

        File outputDirectory = new File(contractPath + "\\" + CONTRACT_END_TUNNEL);
        if (!outputDirectory.exists()) {
            outputDirectory.mkdir();
        }
        String outputFilePath = outputDirectory + "\\" + String.format(NEW_CONTRACT_END_FILE_NAME, person.getSurname(), person.getName());

        OutputStream fos = new FileOutputStream(new File(outputFilePath));

        PdfReader pdfReader = new PdfReader(inputFilePath);
        PdfStamper pdfStamper = new PdfStamper(pdfReader, fos);

        PdfContentByte pdfContentByte = pdfStamper.getOverContent(1);
        addValueToPDF(pdfContentByte, person.getSurname() + " " + person.getName(), EC_EMPL_NAME_SURNAME, 16);
        addValueToPDF(pdfContentByte, person.getAddress().getStreet(), EC_EMPL_STREET, 16);
        addValueToPDF(pdfContentByte, person.getAddress().getCityCode() + " " + person.getAddress().getCity(), EC_EMPL_CITY, 16);
        addValueToPDF(pdfContentByte, person.getEndOfContractDate().format(DATE_TIME_FORMATTER), EC_EMPL_DATE_OF_END, 16);
        addValueToPDF(pdfContentByte, person.getEndOfContractDate().format(DATE_TIME_FORMATTER), EC_EMPL_DATE_OF_END_2, 16);

        pdfStamper.close(); //close pdfStamper
    }

    public void generateContracts(List<Person> people, Path dataFolder, StringBuilder reportText) throws IOException, DocumentException {

        for (Person person : people) {
            if (person.isPbkrContract()) {
                if (person.isDataValid()) {
                    reportText.append(LocalDateTime.now().format(REPORT_DATE_TIME_FORMATTER)).append(" ").append(String.format((SOKA_GENERATE_INFO) + "%n", person.getSurname(), person.getName())).append(" -> ");
                    System.out.printf((SOKA_GENERATE_INFO) + "%n", person.getSurname(), person.getName());
                    generateSokaBauContract(person, dataFolder);
                    reportText.append(LocalDateTime.now().format(REPORT_DATE_TIME_FORMATTER)).append(" FINISHED").append("\n");
                } else {
                    reportText.append(LocalDateTime.now().format(REPORT_DATE_TIME_FORMATTER)).append(" WRONG DATA").append("\n");
                }
            }
            if (person.isBisContract()) {
                if (person.isDataValid()) {
                    reportText.append(LocalDateTime.now().format(REPORT_DATE_TIME_FORMATTER)).append(" ").append(String.format((BIS_GENERATE_INFO) + "%n", person.getSurname(), person.getName())).append(" -> ");
                    System.out.printf((BIS_GENERATE_INFO) + "%n", person.getSurname(), person.getName());
                    generateBisContract(person, dataFolder);
                    reportText.append(LocalDateTime.now().format(REPORT_DATE_TIME_FORMATTER)).append(" FINISHED").append("\n");
                } else {
                    reportText.append(LocalDateTime.now().format(REPORT_DATE_TIME_FORMATTER)).append(" WRONG DATA").append("\n");
                }
            }
            if (person.isTunnelContract()) {
                if (person.isDataValid()) {
                    reportText.append(LocalDateTime.now().format(REPORT_DATE_TIME_FORMATTER)).append(" ").append(String.format((TUNNEL_GENERATE_INFO) + "%n", person.getSurname(), person.getName())).append(" -> ");
                    System.out.printf((TUNNEL_GENERATE_INFO) + "%n", person.getSurname(), person.getName());
                    generateTunnelContract(person, dataFolder);
                    reportText.append(LocalDateTime.now().format(REPORT_DATE_TIME_FORMATTER)).append(" FINISHED").append("\n");
                } else {
                    reportText.append(LocalDateTime.now().format(REPORT_DATE_TIME_FORMATTER)).append(" WRONG DATA").append("\n");
                }
            }
        }
    }

    private void generateSokaBauContract(Person person, Path documentPath) throws IOException, DocumentException {

        String inputFilePath = documentPath + "\\Templates\\" + SOKA_BAU_FILE_NAME;

        File outputDirectory = new File(documentPath + "\\" + SOKA_BAU);
        if (!outputDirectory.exists()) {
            outputDirectory.mkdir();
        }
        String outputFilePath = outputDirectory + "\\" + String.format(NEW_SOKA_BAU_FILE_NAME, person.getSurname(), person.getName());

        OutputStream fos = new FileOutputStream(new File(outputFilePath));

        PdfReader pdfReader = new PdfReader(inputFilePath);
        PdfStamper pdfStamper = new PdfStamper(pdfReader, fos);

        PdfContentByte pdfContentByte = pdfStamper.getOverContent(1);
        addValueToPDF(pdfContentByte, person.getName(), SOKA_EMPL_NAME, 16);
        addValueToPDF(pdfContentByte, person.getSurname(), SOKA_EMPL_SURNAME, 16);
        addValueToPDF(pdfContentByte, person.getBirthday().format(PDF_DATE_TIME_FORMATTER).toString().replace(" ", ""), SOKA_EMPL_BIRTHDAY, 14, 18);
        addValueToPDF(pdfContentByte, person.getAddress().getStreet(), SOKA_EMPL_STREET, 16);
        addValueToPDF(pdfContentByte, person.getAddress().getCity(), SOKA_EMPL_CITY, 16);
        addValueToPDF(pdfContentByte, person.getAddress().getCityCode(), SOKA_EMPL_CITY_CODE, 16);
        addValueToPDF(pdfContentByte, STAMP_TEXT, SOKA_PL_STAMP, 15, 18);
        addValueToPDF(pdfContentByte, FIRST_TEXT, SOKA_FIRST_STAMP, 12);
        addValueToPDF(pdfContentByte, SECOND_TEXT, SOKA_SECOND_STAMP, 12);

        pdfStamper.close(); //close pdfStamper
    }

    private void generateBisContract(Person person, Path documentPath) throws IOException, DocumentException {

        String inputFilePath = documentPath + "\\Templates\\" + BIS_FILE_NAME;

        File outputDirectory = new File(documentPath + "\\" + BIS_BAU);
        if (!outputDirectory.exists()) {
            outputDirectory.mkdir();
        }
        String outputFilePath = outputDirectory + "\\" + String.format(NEW_BIS_FILE_NAME, person.getSurname(), person.getName());

        OutputStream fos = new FileOutputStream(new File(outputFilePath));

        PdfReader pdfReader = new PdfReader(inputFilePath);
        PdfStamper pdfStamper = new PdfStamper(pdfReader, fos);

        PdfContentByte pdfContentByte = pdfStamper.getOverContent(1);
        addValueToPDF(pdfContentByte, person.getName(), BIS_EMPL_NAME, 16);
        addValueToPDF(pdfContentByte, person.getSurname(), BIS_EMPL_SURNAME, 16);
        addValueToPDF(pdfContentByte, person.getBirthday().format(PDF_DATE_TIME_FORMATTER).toString().replace(" ", ""), BIS_EMPL_BIRTHDAY, 13, 17);
        addValueToPDF(pdfContentByte, person.getAddress().getStreet(), BIS_EMPL_STREET, 16);
        addValueToPDF(pdfContentByte, person.getAddress().getCity(), BIS_EMPL_CITY, 16);
        addValueToPDF(pdfContentByte, person.getAddress().getCityCode(), BIS_EMPL_CITY_CODE, 16);
        addValueToPDF(pdfContentByte, STAMP_TEXT, BIS_PL_STAMP, 13, 18);
        addValueToPDF(pdfContentByte, FIRST_TEXT, BIS_FIRST_STAMP, 11);
        addValueToPDF(pdfContentByte, SECOND_TEXT, BIS_SECOND_STAMP, 11);

        pdfStamper.close(); //close pdfStamper
    }

    private void generateTunnelContract(Person person, Path documentPath) throws IOException, DocumentException {

        String inputFilePath = documentPath + "\\Templates\\" + TUNNEL_FILE_NAME;

        File outputDirectory = new File(documentPath + "\\" + TUNNEL);
        if (!outputDirectory.exists()) {
            outputDirectory.mkdir();
        }
        String outputFilePath = outputDirectory + "\\" + String.format(NEW_TUNNEL_FILE_NAME, person.getSurname(), person.getName());

        OutputStream fos = new FileOutputStream(new File(outputFilePath));

        PdfReader pdfReader = new PdfReader(inputFilePath);
        PdfStamper pdfStamper = new PdfStamper(pdfReader, fos);

        PdfContentByte pdfContentByte = pdfStamper.getOverContent(1);
        addValueToPDF(pdfContentByte, person.getName(), TUNNEL_EMPL_NAME, 16);
        addValueToPDF(pdfContentByte, person.getSurname(), TUNNEL_EMPL_SURNAME, 16);
        addValueToPDF(pdfContentByte, person.getBirthday().format(PDF_DATE_TIME_FORMATTER).toString().replace(" ", ""), TUNNEL_EMPL_BIRTHDAY, 13, 17);
        addValueToPDF(pdfContentByte, person.getAddress().getStreet(), TUNNEL_EMPL_STREET, 16);
        addValueToPDF(pdfContentByte, person.getAddress().getCity(), TUNNEL_EMPL_CITY, 16);
        addValueToPDF(pdfContentByte, person.getAddress().getCityCode(), TUNNEL_EMPL_CITY_CODE, 16);
        addValueToPDF(pdfContentByte, STAMP_TEXT, TUNNEL_PL_STAMP, 13, 18);
        addValueToPDF(pdfContentByte, FIRST_TEXT, TUNNEL_FIRST_STAMP, 11);
        addValueToPDF(pdfContentByte, SECOND_TEXT, TUNNEL_SECOND_STAMP, 11);

        pdfStamper.close(); //close pdfStamper
    }

    private void addValueToPDF(PdfContentByte pdfContentByte, String text, Coordinate coordinate, int FontSize) throws IOException, DocumentException {
        pdfContentByte.beginText();
        BaseFont bf = BaseFont.createFont
                (
                        BaseFont.TIMES_BOLD, //Font name
                        BaseFont.CP1250,  //Font encoding
                        BaseFont.EMBEDDED
                );
        pdfContentByte.setFontAndSize(bf, FontSize); // set font and size
        pdfContentByte.setTextMatrix(coordinate.getX(), coordinate.getY());
        pdfContentByte.showText(text); // add the text
        pdfContentByte.endText();
    }

    private void addValueToPDF(PdfContentByte pdfContentByte, String text, Coordinate coordinate, int FontSize, int karning) throws IOException, DocumentException {
        pdfContentByte.beginText();
        char[] textCharsArray = text.toCharArray();

        BaseFont bf = BaseFont.createFont
                (
                        BaseFont.TIMES_BOLD, //Font name
                        BaseFont.CP1250,  //Font encoding
                        BaseFont.EMBEDDED
                );
        pdfContentByte.setFontAndSize(bf, FontSize); // set font and size

        for (int i = 1; i <= textCharsArray.length; i++) {
            pdfContentByte.setTextMatrix(coordinate.getX() + i * karning, coordinate.getY());
            pdfContentByte.showText(String.valueOf(textCharsArray[i - 1])); // add the text
            pdfContentByte.endText();
        }

    }


}
