package PDF.Documents;

import Enums.FONT;
import Model.Person;
import PDF.DTO.Coordinate;
import PDF.DTO.PdfContent;

import java.util.ArrayList;
import java.util.List;

class EndContractTunnel implements PdfDocument {

    private static final Coordinate EC_EMPL_NAME_SURNAME = new Coordinate(70, 753);
    private static final Coordinate EC_EMPL_STREET = new Coordinate(70, 715);
    private static final Coordinate EC_EMPL_CITY = new Coordinate(70, 678);
    private static final Coordinate EC_EMPL_DATE_OF_END = new Coordinate(115, 440);
    private static final Coordinate EC_EMPL_DATE_OF_END_2 = new Coordinate(220, 180);
    private static final String CONTRACT_END_TUNNEL = "Rozwiązanie umowy o pracę - WPBK-TUNNELLING";
    private static final String CONTRACT_END_FILE_NAME_TUNNEL = CONTRACT_END_TUNNEL + PDF_EXTENSION;
    List<PdfContent> pdfContents = new ArrayList<>();

     EndContractTunnel(Person person) {
        setPdfContent(person);
    }

    @Override
    public String getPdfName() {
        return CONTRACT_END_TUNNEL;
    }

    @Override
    public void setPdfContent(Person person) {

        PdfContent fullNamePdfContent = new PdfContent(EC_EMPL_NAME_SURNAME, person.getSurname() + " " + person.getName(), FONT.TIMES_BOLD, 16);
        PdfContent streetPdfContent = new PdfContent(EC_EMPL_STREET, person.getAddress().getStreet(), FONT.TIMES_BOLD, 16);
        PdfContent cityAndCodePdfContent = new PdfContent(EC_EMPL_CITY, person.getAddress().getCityCode() + " " + person.getAddress().getCity(), FONT.TIMES_BOLD, 16);
        PdfContent firstDateOfEndContent = new PdfContent(EC_EMPL_DATE_OF_END, person.getEndOfContractDate().format(DATE_TIME_FORMATTER), FONT.TIMES_BOLD, 16);
        PdfContent secondDateOfEndContent = new PdfContent(EC_EMPL_DATE_OF_END_2, person.getEndOfContractDate().format(DATE_TIME_FORMATTER), FONT.TIMES_BOLD, 16);

        pdfContents.add(fullNamePdfContent);
        pdfContents.add(streetPdfContent);
        pdfContents.add(cityAndCodePdfContent);
        pdfContents.add(firstDateOfEndContent);
        pdfContents.add(secondDateOfEndContent);
    }

    @Override
    public List<PdfContent> getPdfContents() {
        return pdfContents;
    }
}
