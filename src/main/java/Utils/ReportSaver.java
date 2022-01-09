package Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class ReportSaver {

    public static void saveReport(Path dataFolder, String raportText, String raportName) {
        try {
            FileWriter myWriter = new FileWriter(dataFolder + "\\" + raportName);
            myWriter.write(raportText);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
