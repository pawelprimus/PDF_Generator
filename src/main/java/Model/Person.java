package Model;

import java.time.LocalDate;

public class Person {
    private String name;
    private String surname;
    private LocalDate birthday;
    private Address address;
    private String phoneNumber;
    private boolean endOfContract;
    private LocalDate endOfContractDate;
    private boolean sokaBauContract;

    public Person() {
        this.endOfContract = false;
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
        this.endOfContract = endOfContract;
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
        return endOfContract;
    }

    public boolean isSokaBauContract() {
        return sokaBauContract;
    }

    public void setSokaBauContract(boolean sokaBauContract) {
        this.sokaBauContract = sokaBauContract;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday=" + birthday +
                ", address=" + address +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", endOfContract=" + endOfContract +
                ", endOfContractDate=" + endOfContractDate +
                ", sokaBauContract=" + sokaBauContract +
                '}';
    }
}
