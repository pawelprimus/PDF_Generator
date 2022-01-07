package PDF;

import Model.Person;
import PDF.DTO.PdfContent;
import PDF.Documents.PdfDocument;
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


    private static final String PDF_EXTENSION = ".pdf";

    private static final String DD_MM_YYYY = "dd.MM.yyyy";
    private static final String PDF_DATE_FORMAT = "dd MM yyyy";
    private static final String REPORT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    private static final String NOT_VALID_DATA_RAPORT = "\nROW: [%s] NOT VALID DATA\n";


    public void generateAllDocuments(Person person, Path docuPath, StringBuilder raportText) throws DocumentException, IOException {
        if (person.isDataValid()) {
            for (PdfDocument pdfDocument : person.getPdfDocuments()) {

                raportText.append("\n").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern(REPORT_DATE_FORMAT)))
                        .append(" PERSON ROW INDEX [").append(person.getRowIndex()).append("]")
                        .append(" Started generate document [").append(pdfDocument.pdfName).append("] ->\n");
                String personFullname = person.getSurname() + " " + person.getName() + " ";
                generateDocument(pdfDocument, docuPath, personFullname);
                raportText.append(LocalDateTime.now().format(DateTimeFormatter.ofPattern(REPORT_DATE_FORMAT))).append(" FINISHED\n");
            }
        } else {
            raportText.append(String.format(NOT_VALID_DATA_RAPORT, person.getRowIndex()));
            raportText.append(person.getComment().toString()).append("\n");
        }
    }

    public void generateDocument(PdfDocument pdfDocument, Path documentPath, String personFullName) throws IOException, DocumentException {

        String inputFilePath = documentPath + "\\Templates\\" + pdfDocument.getPdfName() + PDF_EXTENSION;

        File outputDirectory = new File(documentPath + "\\" + pdfDocument.getPdfName());
        if (!outputDirectory.exists()) {
            outputDirectory.mkdir();
        }
        String outputFilePath = outputDirectory + "\\" + personFullName + pdfDocument.getPdfName() + PDF_EXTENSION;
        OutputStream fos = new FileOutputStream(new File(outputFilePath));
        PdfReader pdfReader = new PdfReader(inputFilePath);
        PdfStamper pdfStamper = new PdfStamper(pdfReader, fos);
        PdfContentByte pdfContentByte = pdfStamper.getOverContent(1);
        addPdfContentsToPDF(pdfContentByte, pdfDocument.getPdfContents());
        pdfStamper.close(); //close pdfStamper
    }

    private static void addPdfContentsToPDF(PdfContentByte pdfContentByte, List<PdfContent> pdfContents) throws IOException, DocumentException {

        for (PdfContent pdfContent : pdfContents) {
            pdfContentByte.beginText();
            BaseFont bf = BaseFont.createFont
                    (
                            //pdfContent.getFontType().toString(), //Font name
                            BaseFont.TIMES_BOLD, //Font name
                            BaseFont.CP1250,  //Font encoding
                            BaseFont.EMBEDDED
                    );
            pdfContentByte.setFontAndSize(bf, pdfContent.getFontSize()); // set font and size

            if (pdfContent.getKarning() > 0) {
                addContentWithKerninng(pdfContentByte, pdfContent);
            } else {
                pdfContentByte.setTextMatrix(pdfContent.getCoordinate().getX(), pdfContent.getCoordinate().getY());
                pdfContentByte.showText(pdfContent.getTextContent()); // add the text
                pdfContentByte.endText();
            }
        }
    }

    private static void addContentWithKerninng(PdfContentByte pdfContentByte, PdfContent pdfContent) {
        char[] textCharsArray = pdfContent.getTextContent().toCharArray();
        for (int i = 1; i <= textCharsArray.length; i++) {
            pdfContentByte.setTextMatrix(pdfContent.getCoordinate().getX() + i * pdfContent.getKarning(), pdfContent.getCoordinate().getY());
            pdfContentByte.showText(String.valueOf(textCharsArray[i - 1])); // add the text
            pdfContentByte.endText();
        }

    }


}
