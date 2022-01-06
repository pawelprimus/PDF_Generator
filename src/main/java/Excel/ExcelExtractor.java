package Excel;

import com.poiji.bind.Poiji;
import com.poiji.bind.mapping.PoijiLogCellFormat;
import com.poiji.bind.mapping.PoijiNumberFormat;
import com.poiji.exception.PoijiExcelType;
import com.poiji.option.PoijiOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;


public class ExcelExtractor {

    private static final String CITY_CODE_REGEX = ("[0-9]{2}-[0-9]{3}");
    private static final Pattern CITY_CODE_PATTERN = Pattern.compile(CITY_CODE_REGEX);
    private static final String DATE_REGEX = "[a-zA-Z]{1,4}(\\\\|-|\\/|.)[a-zA-Z]{1,4}(\\\\|-|\\/|.)[a-zA-Z]{1,4}$";

    private static final String DATE_FORMAT = "dd.MM.yyyy";


    public Set<Integer> getDateIndexes(File ordersExportFile) throws FileNotFoundException {
        Set<Integer> indexesHashSet = new HashSet<>();
        FileInputStream fileInputStream = new FileInputStream(ordersExportFile);
        PoijiLogCellFormat log = new PoijiLogCellFormat();
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings()
                .poijiLogCellFormat(log)
                .build();
        List<ExcelPerson> excelPersonList = Poiji.fromExcel(fileInputStream, PoijiExcelType.XLSX, ExcelPerson.class, options);
        List<PoijiLogCellFormat.InternalCellFormat> formats = log.formats();

        for (PoijiLogCellFormat.InternalCellFormat format : formats) {
            if (format.getFormatString() != null) {
                if (format.getFormatString().matches(DATE_REGEX)) {
                    indexesHashSet.add((int) format.getFormatIndex());
                }

            }

        }
        return indexesHashSet;
    }

    public PoijiNumberFormat getPoijiNumberFormatWithDates(Set<Integer> hashSetWithIndexesOfDatesCell) {
        PoijiNumberFormat numberFormat = new PoijiNumberFormat();

        for (Integer index : hashSetWithIndexesOfDatesCell) {
            numberFormat.putNumberFormat(Short.parseShort(String.valueOf(index)), DATE_FORMAT);
        }

        return numberFormat;
    }

    public List<ExcelPerson> generateExcelPersonList(File ordersExportFile, PoijiNumberFormat numberFormat) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(ordersExportFile);
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings()
                .poijiNumberFormat(numberFormat)
                .build();

        List<ExcelPerson> excelPersonList = Poiji.fromExcel(fileInputStream, PoijiExcelType.XLSX, ExcelPerson.class, options);
        return excelPersonList;
    }

}

