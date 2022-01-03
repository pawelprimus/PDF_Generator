import Enums.WorkingType;
import Excel.ExcelExtractor;
import Excel.ExcelToObject;
import Model.ExcelPerson;
import Model.Person;
import PDF.PDFCreator;
import com.itextpdf.text.DocumentException;
import com.poiji.bind.mapping.PoijiNumberFormat;
import io.github.millij.poi.SpreadsheetReadException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;


public class Main {
    private static final String datePattern = "dd.MM.yyyy";
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(datePattern);
    private static final Path WORKING_DIRECTORY = Paths.get(System.getProperty("user.dir"));
    private static final String XLSX_EXTENSION = ".xlsx";
    private static final WorkingType workingType = WorkingType.PROD;

    public static void main(String[] args) throws IOException, DocumentException, SpreadsheetReadException {
        StringBuilder reportText = new StringBuilder();
        Path dataFolder;
        if (workingType.equals(WorkingType.TEST)) {
            dataFolder = Paths.get((WORKING_DIRECTORY) + "\\data_test\\");
        } else {
            dataFolder = Paths.get(WORKING_DIRECTORY + "\\data\\");
        }

        try {


        ExcelExtractor excelExtractor = new ExcelExtractor();
        ExcelToObject excelToObject = new ExcelToObject();
        PDFCreator pdfCreator = new PDFCreator();
        String excelDataName = "test" + XLSX_EXTENSION;


        File ordersExportFile = new File(dataFolder + "\\" + excelDataName);

        Set<Integer> indexesHashSet = excelExtractor.getDateIndexes(ordersExportFile);
        PoijiNumberFormat numberFormat = excelExtractor.getPoijiNumberFormatWithDates(indexesHashSet);
        List<ExcelPerson> excelPersonList = excelExtractor.generateExcelPersonList(ordersExportFile, numberFormat);
        List<Person> people = excelToObject.excelObjectToPerson(excelPersonList);

        pdfCreator.generateTerminationOfEmploymentContracts(people, dataFolder, reportText);
        pdfCreator.generateContracts(people, dataFolder, reportText);
        } catch (Exception e){
            reportText.append(e);
        }


        try {
            FileWriter myWriter = new FileWriter(dataFolder+"\\raport.txt");
            myWriter.write(reportText.toString());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }


}

