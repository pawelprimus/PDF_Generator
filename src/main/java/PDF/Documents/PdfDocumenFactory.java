package PDF.Documents;

import Enums.DocumentType;
import Model.Person;

public class PdfDocumenFactory {

    public static PdfDocument getPdfDocument(DocumentType documentType, Person person) {
        switch (documentType) {
            case ENDCONTRACT_BIS:
                return new EndContractBis(person);
            case ENDCONTRACT_PKBR:
                return new EndContractPbkr(person);
            case ENDCONTRACT_TUNNEl:
                return new EndContractTunnel(person);
            case SOKA_BIS:
                return new SokaBis(person);
            case SOKA_PKBR:
                return new SokaPbkr(person);
            case SOKA_TUNNEl:
                return new SokaTunnel(person);
            default:
                return new EmptyDocument();
        }
    }
}
