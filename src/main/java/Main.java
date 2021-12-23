import Excel.ExcelExtractor;
import Excel.ExcelToObject;
import Model.ExcelPerson;
import Model.Person;
import PDF.PDFCreator;
import com.itextpdf.text.DocumentException;
import com.poiji.bind.mapping.PoijiNumberFormat;
import io.github.millij.poi.SpreadsheetReadException;

import java.io.File;
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

    public static void main(String[] args) throws IOException, DocumentException, SpreadsheetReadException {
        ExcelExtractor excelExtractor = new ExcelExtractor();
        ExcelToObject excelToObject = new ExcelToObject();
        PDFCreator pdfCreator = new PDFCreator();

        String excelDataName = "test" + XLSX_EXTENSION;
        Path dataFolder = Paths.get(WORKING_DIRECTORY + "\\data\\");

        //uncomment for use test data
        //dataFolder = Paths.get(String.valueOf(WORKING_DIRECTORY) + "\\data_test\\");

        File ordersExportFile = new File(dataFolder + "\\" + excelDataName);

        Set<Integer> indexesHashSet = excelExtractor.getDateIndexes(ordersExportFile);
        PoijiNumberFormat numberFormat = excelExtractor.getPoijiNumberFormatWithDates(indexesHashSet);
        List<ExcelPerson> excelPersonList = excelExtractor.generateExcelPersonList(ordersExportFile, numberFormat);
        List<Person> people = excelToObject.excelObjectToPerson(excelPersonList);

        pdfCreator.generateTerminationOfEmploymentContracts(people, dataFolder);
        pdfCreator.generateSokaBauContracts(people, dataFolder);
    }

}

