package PDF.Documents;

import Model.Person;
import PDF.DTO.PdfContent;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public interface PdfDocument {

    String PDF_EXTENSION = ".pdf";
    String DD_MM_YYYY = "dd.MM.yyyy";
    String PDF_DATE_FORMAT = "dd MM yyyy";
    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DD_MM_YYYY);
    DateTimeFormatter PDF_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(PDF_DATE_FORMAT);

    String PL_TEXT = "PL";
    String FIRST_TEXT = "02 - Beton und Stahlarbeiten";
    String SECOND_TEXT = "Schallungs ellemente monteur";

    String getPdfName();

    void setPdfContent(Person person);

    List<PdfContent> getPdfContents();

}
