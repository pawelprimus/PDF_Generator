package PDF.Documents;

import Model.Person;
import PDF.DTO.PdfContent;

import java.util.ArrayList;
import java.util.List;

class EmptyDocument implements PdfDocument {

    private static final String EMPTY_DOC = "EMPTY_DOC";
    List<PdfContent> pdfContents = new ArrayList<>();

    EmptyDocument() {
    }

    @Override
    public String getPdfName() {
        return EMPTY_DOC;
    }

    @Override
    public void setPdfContent(Person person) {

    }
    @Override
    public List<PdfContent> getPdfContents() {
        return pdfContents;
    }

}
