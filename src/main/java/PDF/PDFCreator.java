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

public class PDFCreator {

    private static final Coordinate EMPL_NAME_SURNAME = new Coordinate(70, 753);
    private static final Coordinate EMPL_STREET = new Coordinate(70, 715);
    private static final Coordinate EMPL_CITY = new Coordinate(70, 678);
    private static final Coordinate EMPL_DATE_OF_END = new Coordinate(115, 440);
    private static final String PDF_EXTENSION = ".pdf";
    private static final String CONTRACT_END = "Rozwiązanie umowy o pracę";

    public void generateTerminationOfEmploymentContracy(Path contractPath, Person person) throws IOException, DocumentException {

        String inputFilePath = contractPath+ "\\" + CONTRACT_END + PDF_EXTENSION;
        String outputFilePath = contractPath +"\\"+ person.getSurname() + " " + person.getName() + " " + CONTRACT_END + PDF_EXTENSION;

        OutputStream fos = new FileOutputStream(new File(outputFilePath));

        PdfReader pdfReader = new PdfReader(inputFilePath);
        PdfStamper pdfStamper = new PdfStamper(pdfReader, fos);

        PdfContentByte pdfContentByte = pdfStamper.getOverContent(1);
        addValueToPDF(pdfContentByte, person.getSurname() + " " + person.getName(), EMPL_NAME_SURNAME);
        addValueToPDF(pdfContentByte, person.getAddress().getStreet(), EMPL_STREET);
        addValueToPDF(pdfContentByte, person.getAddress().getCityCode() + " " + person.getAddress().getCity(), EMPL_CITY);
        addValueToPDF(pdfContentByte, person.getEndOfContractDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) , EMPL_DATE_OF_END);


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
