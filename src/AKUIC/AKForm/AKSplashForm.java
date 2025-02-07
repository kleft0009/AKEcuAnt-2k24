package AKUIC.AKForm;

import AKUIC.AKStyle;
import java.awt.BorderLayout;
import javax.swing.*;

public abstract class AKSplashForm {

    private static JFrame frmSplash;
    private static JProgressBar prbLoading;
    private static JLabel lblSplash;

    public static void show() {
        SwingUtilities.invokeLater(() -> {
            try {
                // Carga la imagen de splash de manera segura
                ImageIcon icoImagen = (AKStyle.AKURL_SPLASH != null) ? new ImageIcon(AKStyle.AKURL_SPLASH) : null;
                if (icoImagen == null || icoImagen.getIconWidth() <= 0) {
                    AKStyle.showMsgError("⚠️ No se encontró la imagen de splash.");
                    return;
                }

                // Crear y configurar el JLabel con la imagen de splash
                lblSplash = new JLabel(icoImagen);

                // Configurar la barra de progreso
                prbLoading = new JProgressBar(0, 100);
                prbLoading.setStringPainted(true);

                // Configurar el JFrame del splash
                frmSplash = new JFrame();
                frmSplash.setUndecorated(true);
                frmSplash.setLayout(new BorderLayout());
                frmSplash.add(lblSplash, BorderLayout.CENTER);
                frmSplash.add(prbLoading, BorderLayout.SOUTH);
                frmSplash.setSize(icoImagen.getIconWidth(), icoImagen.getIconHeight());
                frmSplash.setLocationRelativeTo(null);
                frmSplash.setVisible(true);

                // Iniciar carga en segundo plano
                new SplashWorker().execute();

            } catch (Exception e) {
                AKStyle.showMsgError("❌ Error al cargar el splash.");
                e.printStackTrace();
            }
        });
    }

    /**
     * Clase interna para manejar la carga del splash en segundo plano.
     */
    private static class SplashWorker extends SwingWorker<Void, Integer> {

        @Override
        protected Void doInBackground() {
            for (int i = 0; i <= 100; i++) {
                try {
                    Thread.sleep(50);  // Simula la carga
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return null;
                }
                publish(i); // Publica el progreso
            }
            return null;
        }

        @Override
        protected void process(java.util.List<Integer> chunks) {
            // Actualiza la barra de progreso con el último valor publicado
            prbLoading.setValue(chunks.get(chunks.size() - 1));
        }

        @Override
        protected void done() {
            // Cierra el splash cuando termina la carga
            frmSplash.setVisible(false);
            frmSplash.dispose();
        }
    }
}
