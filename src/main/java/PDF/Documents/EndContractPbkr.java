package PDF.Documents;

import Enums.FONT;
import Model.Person;
import PDF.DTO.Coordinate;
import PDF.DTO.PdfContent;

public class EndContractPbkr extends PdfDocument {

    //end contract
    private static final Coordinate EC_EMPL_NAME_SURNAME = new Coordinate(70, 753);
    private static final Coordinate EC_EMPL_STREET = new Coordinate(70, 715);
    private static final Coordinate EC_EMPL_CITY = new Coordinate(70, 678);
    private static final Coordinate EC_EMPL_DATE_OF_END = new Coordinate(115, 440);
    private static final Coordinate EC_EMPL_DATE_OF_END_2 = new Coordinate(220, 180);
    private static final String CONTRACT_END_TUNNEL = "Rozwiązanie umowy o pracę - PBKR-EXPORT";
    private static final String CONTRACT_END_FILE_NAME_TUNNEL = CONTRACT_END_TUNNEL + PDF_EXTENSION;
    private static final String NEW_CONTRACT_END_FILE_NAME = "%s %s " + CONTRACT_END_FILE_NAME_TUNNEL;

    public EndContractPbkr(Person person) {
        fillContent(person);
    }

    @Override
    public void fillContent(final Person person) {
        pdfName = CONTRACT_END_TUNNEL;

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
}
