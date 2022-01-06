package PDF.Documents;

import Model.Person;
import PDF.DTO.PdfContent;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class PdfDocument {

    protected static final String PDF_EXTENSION = ".pdf";
    protected static final String DD_MM_YYYY = "dd.MM.yyyy";
    protected static final String PDF_DATE_FORMAT = "dd MM yyyy";
    protected static final String REPORT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SS";
    protected static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DD_MM_YYYY);
    protected static final DateTimeFormatter PDF_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(PDF_DATE_FORMAT);
    protected static final DateTimeFormatter REPORT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(REPORT_DATE_FORMAT);

    protected static final String STAMP_TEXT = "PL";
    protected static final String FIRST_TEXT = "02 - Beton und Stahlarbeiten";
    protected static final String SECOND_TEXT = "Schallungs ellemente monteur";

    public String pdfName;
    public List<PdfContent> pdfContents = new ArrayList<>();

    public abstract void fillContent(Person person);


    public String getPdfName() {
        return pdfName;
    }

    public List<PdfContent> getPdfContents() {
        return pdfContents;
    }

    @Override
    public String toString() {
        return "PdfDocument{" +
                "pdfName='" + pdfName + '\'' +
                '}';
    }
}
