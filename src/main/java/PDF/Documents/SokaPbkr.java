package PDF.Documents;

import Enums.FONT;
import Model.Person;
import PDF.DTO.Coordinate;
import PDF.DTO.PdfContent;

import java.util.ArrayList;
import java.util.List;

class SokaPbkr implements PdfDocument {

    private static final Coordinate PBKR_EMPL_NAME = new Coordinate(75, 730);
    private static final Coordinate PBKR_EMPL_SURNAME = new Coordinate(75, 703);
    private static final Coordinate PBKR_EMPL_BIRTHDAY = new Coordinate(393, 705);
    private static final Coordinate PBKR_EMPL_STREET = new Coordinate(65, 626);
    private static final Coordinate PBKR_EMPL_CITY = new Coordinate(270, 600);
    private static final Coordinate PBKR_EMPL_CITY_CODE = new Coordinate(150, 599);
    private static final Coordinate PBKR_PL_STAMP = new Coordinate(40, 598);
    private static final Coordinate PBKR_FIRST_STAMP = new Coordinate(60, 245);
    private static final Coordinate PBKR_SECOND_STAMP = new Coordinate(280, 245);

    private static final String SOKA_BAU = "SOKA-BAU - PBKR";

    public SokaPbkr(Person person) {
        setPdfContent(person);
    }

    List<PdfContent> pdfContents = new ArrayList<>();

    @Override
    public String getPdfName() {
        return SOKA_BAU;
    }

    @Override
    public void setPdfContent(Person person) {
        PdfContent namePdfContent = new PdfContent(PBKR_EMPL_NAME, person.getName(), FONT.TIMES_BOLD, 16);
        PdfContent surnamePdfContent = new PdfContent(PBKR_EMPL_SURNAME, person.getSurname(), FONT.TIMES_BOLD, 16);
        PdfContent birthdayPdfContent = new PdfContent(PBKR_EMPL_BIRTHDAY, person.getBirthday().format(PDF_DATE_TIME_FORMATTER).toString().replace(" ", ""), FONT.TIMES_BOLD, 14, 18);
        PdfContent streetPdfContent = new PdfContent(PBKR_EMPL_STREET, person.getAddress().getStreet(), FONT.TIMES_BOLD, 16);
        PdfContent cityPdfContent = new PdfContent(PBKR_EMPL_CITY, person.getAddress().getCity(), FONT.TIMES_BOLD, 16);
        PdfContent cityCodePdfContent = new PdfContent(PBKR_EMPL_CITY_CODE, person.getAddress().getCityCode(), FONT.TIMES_BOLD, 16);
        PdfContent stampPLPdfContent = new PdfContent(PBKR_PL_STAMP, PL_TEXT, FONT.TIMES_BOLD, 15, 18);
        PdfContent stampFirstPdfContent = new PdfContent(PBKR_FIRST_STAMP, FIRST_TEXT, FONT.TIMES_BOLD, 12);
        PdfContent stampSecondPdfContent = new PdfContent(PBKR_SECOND_STAMP, SECOND_TEXT, FONT.TIMES_BOLD, 12);

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

    @Override
    public List<PdfContent> getPdfContents() {
        return pdfContents;
    }


}
