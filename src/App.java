
import AKUIC.AKForm.AKHormigueroPanel;
import AKUIC.AKForm.AKSplashForm;
import javax.swing.JFrame;

public class App {

    public static void main(String[] args) throws Exception {
        System.out.println("Haz iniciado el Hormiguero");

        AKSplashForm.show();
        JFrame frame = new JFrame("Hormiguero Experimental");

        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AKHormigueroPanel panel = new AKHormigueroPanel();

        frame.add(panel);

        frame.setVisible(true);
    }
}
