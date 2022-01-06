package PDF.Documents;

import Enums.FONT;
import Model.Person;
import PDF.DTO.Coordinate;
import PDF.DTO.PdfContent;

public class SokaTunnel extends PdfDocument {

    private static final Coordinate TUNNEL_EMPL_NAME = new Coordinate(80, 711);
    private static final Coordinate TUNNEL_EMPL_SURNAME = new Coordinate(80, 685);
    private static final Coordinate TUNNEL_EMPL_BIRTHDAY = new Coordinate(398, 686);
    private static final Coordinate TUNNEL_EMPL_STREET = new Coordinate(80, 611);
    private static final Coordinate TUNNEL_EMPL_CITY = new Coordinate(280, 586);
    private static final Coordinate TUNNEL_EMPL_CITY_CODE = new Coordinate(150, 585);
    private static final Coordinate TUNNEL_PL_STAMP = new Coordinate(65, 585);
    private static final Coordinate TUNNEL_FIRST_STAMP = new Coordinate(80, 247);
    private static final Coordinate TUNNEL_SECOND_STAMP = new Coordinate(285, 247);

    private static final String TUNNEL = "SOKA-BAU-WPBK-TUNNELLING";

    public SokaTunnel(Person person) {
        fillContent(person);
    }

    @Override
    public void fillContent(final Person person) {

        pdfName = TUNNEL;

        PdfContent namePdfContent = new PdfContent(TUNNEL_EMPL_NAME, person.getName(), FONT.TIMES_BOLD, 16);
        PdfContent surnamePdfContent = new PdfContent(TUNNEL_EMPL_SURNAME, person.getSurname(), FONT.TIMES_BOLD, 16);
        PdfContent birthdayPdfContent = new PdfContent(TUNNEL_EMPL_BIRTHDAY, person.getBirthday().format(PDF_DATE_TIME_FORMATTER).toString().replace(" ", ""), FONT.TIMES_BOLD, 14, 17);
        PdfContent streetPdfContent = new PdfContent(TUNNEL_EMPL_STREET, person.getAddress().getStreet(), FONT.TIMES_BOLD, 16);
        PdfContent cityPdfContent = new PdfContent(TUNNEL_EMPL_CITY, person.getAddress().getCity(), FONT.TIMES_BOLD, 16);
        PdfContent cityCodePdfContent = new PdfContent(TUNNEL_EMPL_CITY_CODE, person.getAddress().getCityCode(), FONT.TIMES_BOLD, 16);
        PdfContent stampPLPdfContent = new PdfContent(TUNNEL_PL_STAMP, STAMP_TEXT, FONT.TIMES_BOLD, 13,18);
        PdfContent stampFirstPdfContent = new PdfContent(TUNNEL_FIRST_STAMP, FIRST_TEXT, FONT.TIMES_BOLD, 12);
        PdfContent stampSecondPdfContent = new PdfContent(TUNNEL_SECOND_STAMP, SECOND_TEXT, FONT.TIMES_BOLD, 12);


        pdfContents.add(namePdfContent);
        pdfContents.add(surnamePdfContent);
        pdfContents.add(birthdayPdfContent);
        pdfContents.add(streetPdfContent);
        pdfContents.add(cityPdfContent);
        pdfContents.add(cityCodePdfContent);
        pdfContents.add(stampPLPdfContent);
        pdfContents.add(stampFirstPdfContent);
        pdfContents.add(stampSecondPdfContent);
    }
}
