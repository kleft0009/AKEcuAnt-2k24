package AKDAC;

import AKInfra.AKAppException;
import AKInfra.AKConfig;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AKHormigueroDAC {

    public void saveHorigueroToCSV(String fullDataHormiga) throws AKAppException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(AKConfig.DATAFILE))) {
            writer.println("id,tipo,sexo,estado,aComido");
            writer.println(fullDataHormiga);
        } catch (IOException e) {
            throw new AKAppException(e, this.getClass().toString(), "saveHormigueroToCSV(...)");
        }
    }
}
