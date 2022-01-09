import Enums.WorkingType;
import Excel.ExcelExtractor;
import Excel.ExcelPerson;
import Excel.ExcelToObject;
import Model.Person;
import PDF.PDFCreator;
import Utils.ReportSaver;
import com.itextpdf.text.DocumentException;
import com.poiji.bind.mapping.PoijiNumberFormat;
import io.github.millij.poi.SpreadsheetReadException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;


public class Main {
    private static final String XLSX_EXTENSION = ".xlsx";

    private static final WorkingType workingType = WorkingType.PROD;
    private static final String EXCEL_DATA_NAME = "test" + XLSX_EXTENSION;
    private static final String RAPORT_NAME = "raport.txt";
    private static final Path WORKING_DIRECTORY = Paths.get(System.getProperty("user.dir"));

    public static void main(String[] args) throws IOException, DocumentException, SpreadsheetReadException {
        StringBuilder reportText = new StringBuilder();
        ExcelExtractor excelExtractor = new ExcelExtractor();
        ExcelToObject excelToObject = new ExcelToObject();
        PDFCreator pdfCreator = new PDFCreator();
        //try {

        Path dataFolder = setWorkingPath(WorkingType.PROD);
        File ordersExportFile = new File(dataFolder + "\\" + EXCEL_DATA_NAME);

        Set<Integer> indexesHashSet = excelExtractor.getDateIndexes(ordersExportFile);
        PoijiNumberFormat numberFormat = excelExtractor.getPoijiNumberFormatWithDates(indexesHashSet);

        List<ExcelPerson> excelPersonList = excelExtractor.generateExcelPersonList(ordersExportFile, numberFormat);
        List<Person> people = excelToObject.excelObjectToPerson(excelPersonList);

        for (Person person : people) {
            pdfCreator.generateAllDocuments(person, dataFolder, reportText);
        }
//        } catch (Exception e) {
//            reportText.append(e);
//        }


        ReportSaver.saveReport(dataFolder, reportText.toString(), RAPORT_NAME);
    }


    static Path setWorkingPath(WorkingType workingType) {
        Path dataFolder;
        if (workingType.equals(WorkingType.TEST)) {
            dataFolder = Paths.get((WORKING_DIRECTORY) + "\\data_test\\");
        } else {
            dataFolder = Paths.get(WORKING_DIRECTORY + "\\data\\");
        }
        return dataFolder;
    }


}

