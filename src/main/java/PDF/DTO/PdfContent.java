package PDF.DTO;

import Enums.FONT;

public class PdfContent {

    String textContent;
    Coordinate coordinate;
    FONT fontType;
    int fontSize;
    int karning = 0;

    public PdfContent(final Coordinate coordinate, final String textContent, final FONT baseFont, final int fontSize, final int karning) {
        this.coordinate = coordinate;
        this.textContent = textContent;
        this.fontType = baseFont;
        this.fontSize = fontSize;
        this.karning = karning;
    }

    public PdfContent(final Coordinate coordinate, final String textContent, final FONT baseFont, final int fontSize) {
        this.coordinate = coordinate;
        this.textContent = textContent;
        this.fontType = baseFont;
        this.fontSize = fontSize;
    }

    public String getTextContent() {
        return textContent;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public FONT getFontType() {
        return fontType;
    }

    public int getFontSize() {
        return fontSize;
    }

    public int getKarning() {
        return karning;
    }
}
