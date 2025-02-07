package AKInfra;

import java.io.FileWriter;
import java.io.ObjectInputFilter.Config;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AKAppException extends Exception {

    public AKAppException(String message) {
        super(message);
        logErrorToFile(message, "", "");
    }

    public AKAppException(Exception e, String clase, String metodo) {
        super("UPS...! Hormiguero sin control...");
        logErrorToFile(e.getMessage(), clase, metodo);
    }

    private void logErrorToFile(String message, String clase, String metodo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(AKConfig.LOGFILE, true))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            System.out.println("AKAppException" + " - " + clase + "." + clase + ": " + message);
            writer.println(now.format(formatter) + " - " + clase + "." + clase + ": " + message);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("AKAppException" + " - " + clase + "." + clase + ": " + message);
        }
    }
}
