package Enums;

import com.itextpdf.text.pdf.BaseFont;

public enum FONT {
    TIMES_BOLD(BaseFont.TIMES_BOLD),
    TIMES_ITALIC(BaseFont.TIMES_ITALIC);

    private String font;

    FONT(final String font) {
        this.font = font;
    }

    public String getFont() {
        return font;
    }
}
