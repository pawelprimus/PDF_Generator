package Model;

import Enums.DocumentType;
import PDF.Documents.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Person {
    private final int rowIndex;
    private String name;
    private String surname;
    private LocalDate birthday;
    private Address address;
    private String phoneNumber;
    private StringBuilder comment = new StringBuilder();
    private LocalDate endOfContractDate;
    private List<DocumentType> selectedDocumentsToGenerate = new ArrayList<>();
    private boolean isDataValid = true;

    public Person(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(final LocalDate birthday) {
        this.birthday = birthday;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public StringBuilder getComment() {
        return comment;
    }

    public void addComment(String comment) {
        this.comment.append(comment);
    }

    public LocalDate getEndOfContractDate() {
        return endOfContractDate;
    }

    public void setEndOfContractDate(final LocalDate endOfContractDate) {
        this.endOfContractDate = endOfContractDate;
    }

    public boolean isDataValid() {
        return isDataValid;
    }

    public void setDataValid(final boolean dataValid) {
        isDataValid = dataValid;
    }

    public void addSelectedDocumentType(DocumentType documentType) {
        selectedDocumentsToGenerate.add(documentType);
    }

    public List<DocumentType> getSelectedDocumentsToGenerate() {
        return selectedDocumentsToGenerate;
    }



    @Override
    public String toString() {
        return "Person{" +
                "rowIndex=" + rowIndex +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday=" + birthday +
                ", address=" + address +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", comment=" + comment +
                ", endOfContractDate=" + endOfContractDate +
                ", isDataValid=" + isDataValid +
                '}';
    }
}
