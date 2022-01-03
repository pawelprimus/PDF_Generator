package Excel;

import Model.Address;
import Model.ExcelPerson;
import Model.Person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExcelToObject {

    private static final String CITY_CODE_REGEX = ("[0-9]{2}-[0-9]{3}");
    private static final Pattern CITY_CODE_PATTERN = Pattern.compile(CITY_CODE_REGEX);

    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern((DATE_FORMAT));

    public List<Person> excelObjectToPerson(List<ExcelPerson> excelPersonList) {


        ArrayList<Person> people = new ArrayList<>();

        for (ExcelPerson excelPerson : excelPersonList) {
            Person person = new Person();

            String name = excelPerson.getName();
            if (isStringNotEmpty(name)) {
                String[] nameAndSurname = name.toString().split(" ");
                if (isInBounds(nameAndSurname, 0)) {
                    person.setSurname(nameAndSurname[0]);
                }
                if (isInBounds(nameAndSurname, 1)) {
                    person.setName(nameAndSurname[1]);
                }
            } else {
                person.setDataValid(false);
            }

            String birthday = excelPerson.getBirthDate();
            if (isStringNotEmpty(birthday)) {
                LocalDate localDate = LocalDate.parse(birthday, dateTimeFormatter);
                person.setBirthday(localDate);
            } else {
                person.setDataValid(false);
            }

            String adress = excelPerson.getAddress();
            if (isStringNotEmpty(adress)) {
                person.setAddress(getAddress(adress));
            } else {
                person.setDataValid(false);
            }

            String phoneNumber = excelPerson.getPhoneNumber();
            if (isStringNotEmpty(phoneNumber)) {
                person.setPhoneNumber(phoneNumber);
            }

            String endOfContractTunnel = excelPerson.getEndOfContract();
            if (isStringNotEmpty(endOfContractTunnel)) {
                if (endOfContractTunnel.equals("x")) {
                    person.setEndOfContractTunnel(true);
                }
            }

            String endOfContractPbkr = excelPerson.getEndOfContract();
            if (isStringNotEmpty(endOfContractPbkr)) {
                if (endOfContractPbkr.equals("x")) {
                    person.setEndOfContractPbkr(true);
                }
            }

            String endOfContractBis = excelPerson.getEndOfContract();
            if (isStringNotEmpty(endOfContractBis)) {
                if (endOfContractBis.equals("x")) {
                    person.setEndOfContractBis(true);
                }
            }

            String dateEndOfContract = excelPerson.getDateOfEndContractTunnel();
            if (isStringNotEmpty(dateEndOfContract)) {
                LocalDate localDate = LocalDate.parse(dateEndOfContract, dateTimeFormatter);
                person.setEndOfContractDate(localDate);
            } else {
                person.setDataValid(false);
            }

            String sokaBauContract = excelPerson.getSokaBauContract();
            if (isStringNotEmpty(sokaBauContract)) {
                if (sokaBauContract.equals("x")) {
                    person.setPbkrContract(true);
                }
            }

            String bisContract = excelPerson.getBisContract();
            if (isStringNotEmpty(bisContract)) {
                if (bisContract.equals("x")) {
                    person.setBisContract(true);
                }
            }

            String tunnelContract = excelPerson.getTunnelContract();
            if (isStringNotEmpty(tunnelContract)) {
                if (tunnelContract.equals("x")) {
                    person.setTunnelContract(true);
                }
            }

            people.add(person);
        }

        return people;
    }

    private boolean isStringNotEmpty(String word) {
        return word != null && !word.trim().isEmpty();
    }

    private boolean isInBounds(String[] array, int index) {
        return (index >= 0) && (index < array.length);
    }

    public Address getAddress(String fullAddress) {

        String preparedRemarks = fullAddress.replaceAll(",", " ");
        String[] wordsArray = preparedRemarks.split("\\s+");
        String cityCode = "";
        String city = "";
        String street = "";
        for (String word : wordsArray) {
            if (valideCityCode(word)) {
                cityCode = word;
                break;
            }
        }
        String addressWithoutCode = fullAddress.replace(cityCode, "").trim();
        String[] splitedAddress = addressWithoutCode.split(",");

        if (isInBounds(splitedAddress, 0)) {
            city = splitedAddress[0].trim();
        }


        if (isInBounds(splitedAddress, 1)) {
            street = splitedAddress[1].trim();
        }

        return new Address(cityCode, city, street);
    }


    private boolean valideCityCode(String word) {
        Matcher matcher = CITY_CODE_PATTERN.matcher(word);
        return matcher.find();
    }

}
