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

    private static final Coordinate EMPL_NAME_SURNAME = new Coordinate(70, 753);
    private static final Coordinate EMPL_STREET = new Coordinate(70, 715);
    private static final Coordinate EMPL_CITY = new Coordinate(70, 678);
    private static final Coordinate EMPL_DATE_OF_END = new Coordinate(115, 440);

    private static final String PDF_EXTENSION = ".pdf";
    private static final String CONTRACT_END = "Rozwiązanie umowy o pracę";
    private static final String CONTRACT_END_FILE_NAME = CONTRACT_END + PDF_EXTENSION;

    private static final String DD_MM_YYYY = "dd.MM.yyyy";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DD_MM_YYYY);
    private static final String GENERATE_INFO = "GENERATE END CONTRACT FOR: %s %s WITH DATE: %s";
    private static final String NEW_CONTRACT_END_NAME = "%s %s " + CONTRACT_END_FILE_NAME;

    public void generateTerminationOfEmploymentContracts(List<Person> people, Path dataFolder) throws IOException, DocumentException {

        for (Person person : people) {
            if (person.isEndOfContract()) {
                System.out.println(String.format(GENERATE_INFO, person.getSurname(), person.getName(), person.getEndOfContractDate()));
                generateTerminationOfEmploymentContract(dataFolder, person);
            }
        }
    }


    private void generateTerminationOfEmploymentContract(Path contractPath, Person person) throws IOException, DocumentException {

        String inputFilePath = contractPath + "\\" + CONTRACT_END_FILE_NAME;

        File outputDirectory = new File(contractPath + "\\" + CONTRACT_END);
        if (! outputDirectory.exists()){
            outputDirectory.mkdir();
        }
        String outputFilePath = outputDirectory + "\\" + String.format(NEW_CONTRACT_END_NAME, person.getSurname(), person.getName());

        OutputStream fos = new FileOutputStream(new File(outputFilePath));

        PdfReader pdfReader = new PdfReader(inputFilePath);
        PdfStamper pdfStamper = new PdfStamper(pdfReader, fos);

        PdfContentByte pdfContentByte = pdfStamper.getOverContent(1);
        addValueToPDF(pdfContentByte, person.getSurname() + " " + person.getName(), EMPL_NAME_SURNAME);
        addValueToPDF(pdfContentByte, person.getAddress().getStreet(), EMPL_STREET);
        addValueToPDF(pdfContentByte, person.getAddress().getCityCode() + " " + person.getAddress().getCity(), EMPL_CITY);
        addValueToPDF(pdfContentByte, person.getEndOfContractDate().format(DATE_TIME_FORMATTER), EMPL_DATE_OF_END);

        pdfStamper.close(); //close pdfStamper
    }

    public void addValueToPDF(PdfContentByte pdfContentByte, String text, Coordinate coordinate) throws IOException, DocumentException {
        pdfContentByte.beginText();
        pdfContentByte.setFontAndSize(BaseFont.createFont
                        (BaseFont.TIMES_BOLD, //Font name
                                BaseFont.CP1257, //Font encoding
                                BaseFont.EMBEDDED //Font embedded
                        )
                , 16); // set font and size
        pdfContentByte.setTextMatrix(coordinate.getX(), coordinate.getY());
        pdfContentByte.showText(text); // add the text

        pdfContentByte.endText();
    }


}
