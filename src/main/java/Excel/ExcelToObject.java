package Excel;

import Enums.DocumentType;
import Model.Address;
import Model.Person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExcelToObject {

    private static final String CITY_CODE_REGEX = ("[0-9]{2}-[0-9]{3}");
    private static final Pattern CITY_CODE_PATTERN = Pattern.compile(CITY_CODE_REGEX);

    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern((DATE_FORMAT));
    private static final String WRONG_DATA_COMMENT = "WRONG %s DATA | ";

    public List<Person> excelObjectToPerson(List<ExcelPerson> excelPersonList) {


        ArrayList<Person> people = new ArrayList<>();

        for (ExcelPerson excelPerson : excelPersonList) {
            Person person = new Person(excelPerson.getRowIndex());

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
                person.addComment(String.format(WRONG_DATA_COMMENT, "NAME"));
                person.setDataValid(false);
            }

            String birthday = excelPerson.getBirthDate();
            if (isStringNotEmpty(birthday)) {


                try {
                    LocalDate localDate = LocalDate.parse(birthday, dateTimeFormatter);
                    person.setBirthday(localDate);
                } catch (DateTimeParseException e) {
                    person.addComment(String.format(WRONG_DATA_COMMENT, "BIRTHDAY"));
                }

            } else {
                person.addComment("EMPTY BIRTHDAY DATE");
                person.setDataValid(false);
            }

            String address = excelPerson.getAddress();
            if (isStringNotEmpty(address)) {
                person.setAddress(getAddress(address));
            } else {
                person.addComment(String.format(WRONG_DATA_COMMENT, "ADDRESS"));
                person.setDataValid(false);
            }

            String phoneNumber = excelPerson.getPhoneNumber();
            if (isStringNotEmpty(phoneNumber)) {
                person.setPhoneNumber(phoneNumber);
            }

            String endOfContractTunnel = excelPerson.getEndOfContract();
            if (isStringNotEmpty(endOfContractTunnel)) {
                if (endOfContractTunnel.equals("x")) {
                    person.addSelectedDocumentType(DocumentType.ENDCONTRACT_TUNNEl);
                }
            }

            String endOfContractPbkr = excelPerson.getEndOfContract();
            if (isStringNotEmpty(endOfContractPbkr)) {
                if (endOfContractPbkr.equals("x")) {
                    person.addSelectedDocumentType(DocumentType.ENDCONTRACT_PKBR);
                }
            }

            String endOfContractBis = excelPerson.getEndOfContract();
            if (isStringNotEmpty(endOfContractBis)) {
                if (endOfContractBis.equals("x")) {
                    person.addSelectedDocumentType(DocumentType.ENDCONTRACT_BIS);
                }
            }

            if (person.getSelectedDocumentsToGenerate().contains(DocumentType.ENDCONTRACT_BIS) ||
                    person.getSelectedDocumentsToGenerate().contains(DocumentType.ENDCONTRACT_PKBR) ||
                    person.getSelectedDocumentsToGenerate().contains(DocumentType.ENDCONTRACT_TUNNEl)
            ) {
                String dateEndOfContract = excelPerson.getDateOfEndContractTunnel();
                if (isStringNotEmpty(dateEndOfContract)) {
                    try {
                        LocalDate localDate = LocalDate.parse(dateEndOfContract, dateTimeFormatter);
                        person.setEndOfContractDate(localDate);
                    } catch (DateTimeParseException e) {
                        person.addComment(String.format(WRONG_DATA_COMMENT, "END CONTRACT DATE"));
                    }
                } else {
                    person.setDataValid(false);
                    person.addComment("EMPTY END CONTRACT DATE ");
                }
            }

            String pkbrContract = excelPerson.getPkbrContract();
            if (isStringNotEmpty(pkbrContract)) {
                if (pkbrContract.equals("x")) {
                    person.addSelectedDocumentType(DocumentType.SOKA_PKBR);
                }
            }

            String bisContract = excelPerson.getBisContract();
            if (isStringNotEmpty(bisContract)) {
                if (bisContract.equals("x")) {
                    person.addSelectedDocumentType(DocumentType.SOKA_BIS);
                }
            }

            String tunnelContract = excelPerson.getTunnelContract();
            if (isStringNotEmpty(tunnelContract)) {
                if (tunnelContract.equals("x")) {
                    person.addSelectedDocumentType(DocumentType.SOKA_TUNNEl);
                }
            }

            person.fillTheDocumentsData();

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
