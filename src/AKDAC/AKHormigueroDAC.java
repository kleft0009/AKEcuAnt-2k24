package AKDAC;

import AKInfra.AKAppException;
import AKInfra.AKConfig;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AKHormigueroDAC {

    public void saveHormigueroToCSV(List<String> dataToSave) throws AKAppException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(AKConfig.DATAFILE))) {
            writer.println("id,tipo,sexo,estado,aComido");
            writer.println(dataToSave);
        } catch (IOException e) {
            throw new AKAppException(e, this.getClass().toString(), "saveHormigueroToCSV(...)");
        }
    }
}
