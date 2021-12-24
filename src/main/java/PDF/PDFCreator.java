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
    private static final Coordinate STAMP = new Coordinate(40, 598);

    private static final String STAMP_TEXT = "PL";
    private static final String PDF_EXTENSION = ".pdf";
    private static final String CONTRACT_END = "Rozwiązanie umowy o pracę";
    private static final String CONTRACT_END_FILE_NAME = CONTRACT_END + PDF_EXTENSION;
    private static final String NEW_CONTRACT_END_FILE_NAME = "%s %s " + CONTRACT_END_FILE_NAME;
    private static final String SOKA_BAU = "SOKA-BAU - PBKR";
    private static final String SOKA_BAU_FILE_NAME = SOKA_BAU + PDF_EXTENSION;
    private static final String NEW_SOKA_BAU_FILE_NAME = "%s %s " + SOKA_BAU_FILE_NAME;

    private static final String DD_MM_YYYY = "dd.MM.yyyy";
    private static final String PDF_DATE_FORMAT = "dd MM yyyy";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DD_MM_YYYY);
    private static final DateTimeFormatter PDF_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(PDF_DATE_FORMAT);
    private static final String EC_GENERATE_INFO = "GENERATE END CONTRACT FOR: %s %s WITH DATE: %s";
    private static final String SOKA_GENERATE_INFO = "GENERATE SOKA BAU CONTRACT FOR: %s %s ";

    public void generateTerminationOfEmploymentContracts(List<Person> people, Path dataFolder) throws IOException, DocumentException {
        for (Person person : people) {
            if (person.isEndOfContract()) {
                System.out.println(String.format(EC_GENERATE_INFO, person.getSurname(), person.getName(), person.getEndOfContractDate()));
                generateTerminationOfEmploymentContract(person, dataFolder);
            }
        }
    }


    private void generateTerminationOfEmploymentContract(Person person, Path contractPath) throws IOException, DocumentException {

        String inputFilePath = contractPath + "\\" + CONTRACT_END_FILE_NAME;

        File outputDirectory = new File(contractPath + "\\" + CONTRACT_END);
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

    public void generateSokaBauContracts(List<Person> people, Path dataFolder) throws IOException, DocumentException {

        for (Person person : people) {
            if (person.isSokaBauContract()) {
                System.out.println(String.format(SOKA_GENERATE_INFO, person.getSurname(), person.getName()));
                generateSokaBauContract(person, dataFolder);
            }
        }
    }

    private void generateSokaBauContract(Person person, Path documentPath) throws IOException, DocumentException {

        String inputFilePath = documentPath + "\\" + SOKA_BAU_FILE_NAME;

        File outputDirectory = new File(documentPath + "\\" + SOKA_BAU);
        if (!outputDirectory.exists()) {
            outputDirectory.mkdir();
        }
        String outputFilePath = outputDirectory + "\\" + String.format(NEW_SOKA_BAU_FILE_NAME, person.getSurname(), person.getName());

        OutputStream fos = new FileOutputStream(new File(outputFilePath));

        PdfReader pdfReader = new PdfReader(inputFilePath);
        PdfStamper pdfStamper = new PdfStamper(pdfReader, fos);

        PdfContentByte pdfContentByte = pdfStamper.getOverContent(1);
        addValueToPDF(pdfContentByte, person.getName(), SOKA_EMPL_NAME,16);
        addValueToPDF(pdfContentByte, person.getSurname(), SOKA_EMPL_SURNAME,16);
        addValueToPDF(pdfContentByte, person.getBirthday().format(PDF_DATE_TIME_FORMATTER).toString().replace(" ", ""), SOKA_EMPL_BIRTHDAY,14, 18);
        addValueToPDF(pdfContentByte, person.getAddress().getStreet(), SOKA_EMPL_STREET,16);
        addValueToPDF(pdfContentByte, person.getAddress().getCity(), SOKA_EMPL_CITY,16);
        addValueToPDF(pdfContentByte, person.getAddress().getCityCode(), SOKA_EMPL_CITY_CODE,16);
        addValueToPDF(pdfContentByte, STAMP_TEXT, STAMP,15, 18);

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

    private void addValueToPDF(PdfContentByte pdfContentByte, String text, Coordinate coordinate,int FontSize, int karning) throws IOException, DocumentException {
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
