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

    public static final Color RACOLOR_FONT = new Color(220, 0, 0); //(218, 8, 40)
    public static final Color RACOLOR_LABEL = new Color(150, 150, 150); //(218, 8, 40)
    public static final Color RACOLOR_FONT_LIGHT = new Color(173, 216, 230);
    public static final Color RACOLOR_CURSOR = Color.black;
    public static final Color RACOLOR_BORDER = Color.lightGray;
    public static final Font RAFONT = new Font("JetBrains Mono", Font.PLAIN, 14);
    public static final Font RAFONT_BOLD = new Font("JetBrains Mono", Font.BOLD | Font.PLAIN, 14);
    public static final Font RAFONT_SMALL = new Font("JetBrains Mono", Font.PLAIN | Font.PLAIN, 10);
    public static final Font RATABLE_STYLE = new Font("JetBrains Mono", Font.BOLD | Font.PLAIN, 14);

    public static final int RAALIGNMENT_LEFT = SwingConstants.LEFT;
    public static final int RAALIGNMENT_RIGHT = SwingConstants.RIGHT;
    public static final int RAALIGNMENT_CENTER = SwingConstants.CENTER;

    public static final Cursor RACURSOR_HAND = new Cursor(Cursor.HAND_CURSOR);
    public static final Cursor RACURSOR_DEFAULT = new Cursor(Cursor.DEFAULT_CURSOR);

    public static final URL AKURL_MAIN = AKStyle.class.getResource("/AKUIC/AKResource/Login.png");
    public static final URL AKURL_LOGO = AKStyle.class.getResource("/AKUIC/AKResource/preview.png");
    public static final URL AKURL_SPLASH = AKStyle.class.getResource("/AKUIC/AKResource/preview.png");

    public static final CompoundBorder raCreateBorderRect() {
        return BorderFactory.createCompoundBorder(new LineBorder(Color.lightGray),
                new EmptyBorder(5, 5, 5, 5));
    }

    public static Font raHeaderFont() {
        return new Font("JetBrains Mono", Font.BOLD | Font.PLAIN, 14); // Establece un tamaño y negrita
    }

    public static final void showMsg(String msg) {
        JOptionPane.showMessageDialog(null, msg, "😏 IABot", JOptionPane.INFORMATION_MESSAGE);
    }

    public static final void showMsgError(String msg) {
        JOptionPane.showMessageDialog(null, msg, "💀 IABot", JOptionPane.OK_OPTION);
    }

    public static final boolean showConfirmYesNo(String msg) {
        return (JOptionPane.showConfirmDialog(null, msg, "😞 IABot", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
    }
}
