package AKUIC.AKForm;

import AKUIC.AKStyle;
import java.awt.BorderLayout;
import javax.swing.*;

public abstract class AKSplashForm {

    private static JFrame frmSplash;
    private static JProgressBar prbLoaging;
    private static ImageIcon icoImagen;
    private static JLabel lblSplash;

    public static void show() {
        try {
            icoImagen = new ImageIcon(AKStyle.AKURL_SPLASH);
            lblSplash = new JLabel(icoImagen);
            prbLoaging = new JProgressBar(0, 100);
            prbLoaging.setStringPainted(true);

            frmSplash = new JFrame();
            frmSplash.setUndecorated(true);
            frmSplash.getContentPane().add(lblSplash, BorderLayout.CENTER);
            frmSplash.add(prbLoaging, BorderLayout.SOUTH);
            frmSplash.setSize(icoImagen.getIconWidth(), icoImagen.getIconHeight());
            frmSplash.setLocationRelativeTo(null);
            frmSplash.setVisible(true);

            for (int i = 0; i <= 100; i++) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                prbLoaging.setValue(i);
            }
            frmSplash.setVisible(false);
        } catch (Exception e) {
            AKStyle.showMsgError("Tenemos problemas con el spash ");

            e.printStackTrace();
        }
    }
}
