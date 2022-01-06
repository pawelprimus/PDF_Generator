package PDF.Documents;

import Enums.FONT;
import Model.Person;
import PDF.DTO.Coordinate;
import PDF.DTO.PdfContent;

public class SokaBis extends PdfDocument {

    private static final Coordinate BIS_EMPL_NAME = new Coordinate(80, 710);
    private static final Coordinate BIS_EMPL_SURNAME = new Coordinate(80, 685);
    private static final Coordinate BIS_EMPL_BIRTHDAY = new Coordinate(397, 686);
    private static final Coordinate BIS_EMPL_STREET = new Coordinate(80, 611);
    private static final Coordinate BIS_EMPL_CITY = new Coordinate(280, 588);
    private static final Coordinate BIS_EMPL_CITY_CODE = new Coordinate(150, 585);
    private static final Coordinate BIS_PL_STAMP = new Coordinate(62, 585);
    private static final Coordinate BIS_FIRST_STAMP = new Coordinate(75, 248);
    private static final Coordinate BIS_SECOND_STAMP = new Coordinate(285, 249);

    private static final String BIS_BAU = "SOKA-BAU - WPBK-BIS-EXPORT";

    public SokaBis(Person person) {
        fillContent(person);
    }

    @Override
    public void fillContent(final Person person) {

        pdfName = BIS_BAU;

        PdfContent namePdfContent = new PdfContent(BIS_EMPL_NAME, person.getName(), FONT.TIMES_BOLD, 16);
        PdfContent surnamePdfContent = new PdfContent(BIS_EMPL_SURNAME, person.getSurname(), FONT.TIMES_BOLD, 16);
        PdfContent birthdayPdfContent = new PdfContent(BIS_EMPL_BIRTHDAY, person.getBirthday().format(PDF_DATE_TIME_FORMATTER).toString().replace(" ", ""), FONT.TIMES_BOLD, 13, 17);
        PdfContent streetPdfContent = new PdfContent(BIS_EMPL_STREET, person.getAddress().getStreet(), FONT.TIMES_BOLD, 16);
        PdfContent cityPdfContent = new PdfContent(BIS_EMPL_CITY, person.getAddress().getCity(), FONT.TIMES_BOLD, 16);
        PdfContent cityCodePdfContent = new PdfContent(BIS_EMPL_CITY_CODE, person.getAddress().getCityCode(), FONT.TIMES_BOLD, 16);
        PdfContent stampPLPdfContent = new PdfContent(BIS_PL_STAMP , STAMP_TEXT, FONT.TIMES_BOLD , 13, 18);
        PdfContent stampFirstPdfContent = new PdfContent(BIS_FIRST_STAMP, FIRST_TEXT , FONT.TIMES_BOLD , 12);
        PdfContent stampSecondPdfContent = new PdfContent(BIS_SECOND_STAMP, SECOND_TEXT , FONT.TIMES_BOLD, 12);


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
