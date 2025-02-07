package AKUIC;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public abstract class AKStyle {

    //  PALETA DE COLORES
    /**
     * Color principal para fuentes y textos destacados (Rojo oscuro)
     */
    public static final Color RACOLOR_FONT = new Color(218, 8, 40); // Rojo intenso

    /**
     * Color de fondo para etiquetas o títulos (Rojo menos saturado)
     */
    public static final Color RACOLOR_LABEL = new Color(200, 8, 40); // Rojo más suave

    /**
     * Color para fuentes en temas claros o destacados (Azul claro)
     */
    public static final Color RACOLOR_FONT_LIGHT = new Color(173, 216, 230); // Azul pastel

    /**
     * Color del cursor cuando está en modo activo (Negro)
     */
    public static final Color RACOLOR_CURSOR = Color.BLACK;

    /**
     * Color de los bordes de los componentes (Gris claro)
     */
    public static final Color RACOLOR_BORDER = Color.LIGHT_GRAY;

    //  FUENTES PERSONALIZADAS
    /**
     * Fuente principal para texto general
     */
    public static final Font RAFONT = new Font("JetBrains Mono", Font.PLAIN, 14);

    /**
     * Fuente en negrita para encabezados y títulos
     */
    public static final Font RAFONT_BOLD = new Font("JetBrains Mono", Font.BOLD, 14);

    /**
     * Fuente más pequeña para etiquetas o detalles secundarios
     */
    public static final Font RAFONT_SMALL = new Font("JetBrains Mono", Font.PLAIN, 10);

    /**
     * Fuente utilizada en tablas para mejorar la legibilidad
     */
    public static final Font RATABLE_STYLE = new Font("JetBrains Mono", Font.BOLD, 14);

    //  ALINEACIONES
    /**
     * Alineación izquierda para texto
     */
    public static final int RAALIGNMENT_LEFT = SwingConstants.LEFT;

    /**
     * Alineación derecha para texto
     */
    public static final int RAALIGNMENT_RIGHT = SwingConstants.RIGHT;

    /**
     * Alineación centrada para texto
     */
    public static final int RAALIGNMENT_CENTER = SwingConstants.CENTER;

    //  CURSORES PERSONALIZADOS
    /**
     * Cursor tipo "mano" para elementos interactivos
     */
    public static final Cursor RACURSOR_HAND = new Cursor(Cursor.HAND_CURSOR);

    /**
     * Cursor predeterminado del sistema
     */
    public static final Cursor RACURSOR_DEFAULT = new Cursor(Cursor.DEFAULT_CURSOR);

    //  RECURSOS DE IMÁGENES
    /**
     * URL del icono principal de la aplicación
     */
    public static final URL AKURL_MAIN = loadResource("/AKUIC/AKResource/Login.png");

    /**
     * URL del logotipo de la aplicación
     */
    public static final URL AKURL_LOGO = loadResource("/AKUIC/AKResource/preview.png");

    /**
     * URL de la imagen de splash (pantalla de carga)
     */
    public static final URL AKURL_SPLASH = loadResource("/AKUIC/AKResource/preview.png");

    //  BORDES Y ESTILOS
    /**
     * Crea un borde compuesto con un borde de línea y un espacio de 5 píxeles.
     *
     * @return Un {@link CompoundBorder} con estilo rectangular.
     */
    public static CompoundBorder raCreateBorderRect() {
        return BorderFactory.createCompoundBorder(new LineBorder(RACOLOR_BORDER),
                new EmptyBorder(5, 5, 5, 5));
    }

    /**
     * Retorna la fuente para encabezados.
     *
     * @return Una {@link Font} con estilo de encabezado.
     */
    public static Font raHeaderFont() {
        return RAFONT_BOLD;
    }

    //  MÉTODOS PARA DIÁLOGOS
    /**
     * Muestra un mensaje de información.
     *
     * @param msg Mensaje a mostrar.
     */
    public static void showMsg(String msg) {
        JOptionPane.showMessageDialog(null, msg, "😏 IABot", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Muestra un mensaje de error con un ícono de alerta.
     *
     * @param msg Mensaje de error a mostrar.
     */
    public static void showMsgError(String msg) {
        JOptionPane.showMessageDialog(null, msg, "💀 IABot", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Muestra un cuadro de diálogo de confirmación con opciones "Sí" y "No".
     *
     * @param msg Mensaje a mostrar en el cuadro de diálogo.
     * @return `true` si el usuario selecciona "Sí", `false` en caso contrario.
     */
    public static boolean showConfirmYesNo(String msg) {
        return JOptionPane.showConfirmDialog(null, msg, "😞 IABot", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    //  MÉTODO PARA CARGAR RECURSOS
    /**
     * Carga un recurso desde la ruta especificada.
     *
     * @param path Ruta del recurso.
     * @return La {@link URL} del recurso o `null` si no se encuentra.
     */
    private static URL loadResource(String path) {
        URL resource = AKStyle.class.getResource(path);
        if (resource == null) {
            System.err.println("⚠️ Recurso no encontrado: " + path);
        }
        return resource;
    }
}
