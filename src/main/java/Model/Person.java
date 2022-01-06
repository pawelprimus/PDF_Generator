package Model;

import PDF.Documents.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Person {
    private int rowIndex;
    private String name;
    private String surname;
    private LocalDate birthday;
    private Address address;
    private String phoneNumber;
    private StringBuilder comment = new StringBuilder();
    private LocalDate endOfContractDate;

    private boolean endOfContractTunnel;
    private boolean endOfContractPbkr;
    private boolean endOfContractBis;
    private boolean pbkrContract;
    private boolean bisContract;
    private boolean tunnelContract;
    private boolean isDataValid = true;

    List<PdfDocument> pdfDocuments;

    public Person(int rowIndex) {
        this.rowIndex = rowIndex;
        this.endOfContractTunnel = false;
        pdfDocuments = new ArrayList<>();
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(final int rowIndex) {
        this.rowIndex = rowIndex;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public StringBuilder getComment() {
        return comment;
    }

    public void setComment(final StringBuilder comment) {
        this.comment = comment;
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

    public boolean isEndOfContractTunnel() {
        return endOfContractTunnel;
    }

    public void setEndOfContractTunnel(final boolean endOfContractTunnel) {
        this.endOfContractTunnel = endOfContractTunnel;
    }

    public boolean isEndOfContractPbkr() {
        return endOfContractPbkr;
    }

    public void setEndOfContractPbkr(final boolean endOfContractPbkr) {
        this.endOfContractPbkr = endOfContractPbkr;
    }

    public boolean isEndOfContractBis() {
        return endOfContractBis;
    }

    public void setEndOfContractBis(final boolean endOfContractBis) {
        this.endOfContractBis = endOfContractBis;
    }

    public boolean isPbkrContract() {
        return pbkrContract;
    }

    public void setPbkrContract(final boolean pbkrContract) {
        this.pbkrContract = pbkrContract;
    }

    public boolean isBisContract() {
        return bisContract;
    }

    public void setBisContract(final boolean bisContract) {
        this.bisContract = bisContract;
    }

    public boolean isTunnelContract() {
        return tunnelContract;
    }

    public void setTunnelContract(final boolean tunnelContract) {
        this.tunnelContract = tunnelContract;
    }

    public boolean isDataValid() {
        return isDataValid;
    }

    public void setDataValid(final boolean dataValid) {
        isDataValid = dataValid;
    }

    public List<PdfDocument> getPdfDocuments() {
        return pdfDocuments;
    }

    public void setPdfDocuments(final List<PdfDocument> pdfDocuments) {
        this.pdfDocuments = pdfDocuments;
    }

    public void fillTheDocumentsData() {

        if (isDataValid) {

            if (endOfContractBis) {
                PdfDocument pdfDocument = new EndContractBis(this);
                this.pdfDocuments.add(pdfDocument);
            }
            if (endOfContractPbkr) {
                PdfDocument pdfDocument = new EndContractPbkr(this);
                this.pdfDocuments.add(pdfDocument);
            }
            if (endOfContractTunnel) {
                PdfDocument pdfDocument = new EndContractTunnel(this);
                this.pdfDocuments.add(pdfDocument);
            }
            if (pbkrContract) {
                PdfDocument pdfDocument = new SokaPbkr(this);
                this.pdfDocuments.add(pdfDocument);
            }
            if (bisContract) {
                PdfDocument pdfDocument = new SokaBis(this);
                this.pdfDocuments.add(pdfDocument);
            }
            if (tunnelContract) {
                PdfDocument pdfDocument = new SokaTunnel(this);
                this.pdfDocuments.add(pdfDocument);
            }
        }
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
                ", endOfContractTunnel=" + endOfContractTunnel +
                ", endOfContractPbkr=" + endOfContractPbkr +
                ", endOfContractBis=" + endOfContractBis +
                ", pbkrContract=" + pbkrContract +
                ", bisContract=" + bisContract +
                ", tunnelContract=" + tunnelContract +
                ", isDataValid=" + isDataValid +
                '}';
    }
}
