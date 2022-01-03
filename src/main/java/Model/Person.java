package Model;

import java.time.LocalDate;

public class Person {
    private String name;
    private String surname;
    private LocalDate birthday;
    private Address address;
    private String phoneNumber;
    private boolean endOfContractTunnel;
    private boolean endOfContractPbkr;
    private boolean endOfContractBis;
    private LocalDate endOfContractDate;
    private boolean pbkrContract;
    private boolean bisContract;
    private boolean tunnelContract;
    private boolean isDataValid = true;

    public Person() {
        this.endOfContractTunnel = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }


    public void setEndOfContract(boolean endOfContract) {
        this.endOfContractTunnel = endOfContract;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getEndOfContractDate() {
        return endOfContractDate;
    }

    public void setEndOfContractDate(LocalDate endOfContractDate) {
        this.endOfContractDate = endOfContractDate;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isEndOfContract() {
        return endOfContractTunnel;
    }

    public boolean isPbkrContract() {
        return pbkrContract;
    }

    public void setPbkrContract(boolean pbkrContract) {
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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday=" + birthday +
                ", address=" + address +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", endOfContract=" + endOfContractTunnel +
                ", endOfContractDate=" + endOfContractDate +
                ", sokaBauContract=" + pbkrContract +
                '}';
    }
}
