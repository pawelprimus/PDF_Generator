package Model;

public class Address {

    private String cityCode;
    private String city;
    private String street;

    public Address(String cityCode, String city, String street) {
        this.cityCode = cityCode;
        this.city = city;
        this.street = street;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    @Override
    public String toString() {
        return "Address{" +
                "cityCode='" + cityCode + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
