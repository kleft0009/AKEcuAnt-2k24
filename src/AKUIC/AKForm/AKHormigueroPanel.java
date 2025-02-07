/**
 * Clase AKHormigueroPanel
 * Panel que gestiona el hormiguero virtual. Permite visualizar, crear, alimentar,
 * entrenar y eliminar hormigas, además de guardar los datos en un archivo CSV.
 */
package AKUIC.AKForm;

import AKBLC.AKCatalogo;
import AKDAC.AKDAO.AKHormigaDAO;
import AKDAC.AKDTO.AKHormigaDTO;
import AKDAC.AKDataHelperSQLite;
import AKDAC.AKHormigueroDAC;
import AKInfra.AKConfig;
import AKUIC.AKStyle;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class AKHormigueroPanel extends JPanel {

    private Map<Integer, Boolean> evolucionadoState = new HashMap<>();
    private JLabel titleLabel;
    private JTable tableHormigas;
    private JScrollPane scrollPane;
    private JComboBox<String> comboBox1, comboBox2;
    private JButton btnCrearLarva, btnAlimentar, btnEntrenar, btnEliminar, btnGuardar;
    private JLabel footerLabel;
    private JPanel tablePanel;

    /**
     * Constructor de la clase AKHormigueroPanel. Inicializa y configura la
     * interfaz gráfica.
     */
    public AKHormigueroPanel() {
        setLayout(new BorderLayout());

        // Imagen superior
        ImageIcon imageIcon = new ImageIcon(AKStyle.AKURL_MAIN);
        Image image = imageIcon.getImage().getScaledInstance(400, 250, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon, SwingConstants.CENTER);
        add(imageLabel, BorderLayout.NORTH);

        // Panel de título y tabla
        JPanel titleAndTablePanel = new JPanel(new BorderLayout());
        titleLabel = new JLabel("HORMIGUERO VIRTUAL", SwingConstants.CENTER);
        titleLabel.setForeground(AKStyle.RACOLOR_CURSOR);
        titleAndTablePanel.add(titleLabel, BorderLayout.NORTH);
        tablePanel = new JPanel(new BorderLayout());
        titleAndTablePanel.add(tablePanel, BorderLayout.CENTER);
        add(titleAndTablePanel, BorderLayout.CENTER);

        // Panel de botones y combos
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        JPanel comboPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        comboBox1 = new JComboBox<>();
        comboBox2 = new JComboBox<>();
        comboPanel.add(comboBox1);
        comboPanel.add(comboBox2);
        bottomPanel.add(comboPanel);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        btnCrearLarva = new JButton("Crear Larva");
        btnAlimentar = new JButton("Alimentar");
        btnEntrenar = new JButton("Entrenar");
        btnEliminar = new JButton("Eliminar");
        btnGuardar = new JButton("Guardar");

        buttonPanel.add(btnCrearLarva);
        buttonPanel.add(btnAlimentar);
        buttonPanel.add(btnEntrenar);
        buttonPanel.add(btnEliminar);
        buttonPanel.add(btnGuardar);
        bottomPanel.add(buttonPanel);

        footerLabel = new JLabel("PROGRAMACION II | Cedula: 1755053251 | Kevin Azana");
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        footerPanel.add(footerLabel);
        bottomPanel.add(footerPanel);

        add(bottomPanel, BorderLayout.SOUTH);

        try {
            AKShowDataTable();
            AKLoadComboBoxes();
            AKUpdateComboBoxesFromTable();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Carga los datos de las hormigas en la tabla.
     */
    private void AKShowDataTable() throws Exception {
        String[] columnNames = {"IdHormiga", "TipoHormiga", "Sexo", "Alimentacion", "Estado", "Entrenada"};
        List<AKHormigaDTO> hormigasList = new AKHormigaDAO().readHormigasFromView();
        Object[][] data = new Object[hormigasList.size()][6];
        int index = 0;
        for (AKHormigaDTO a : hormigasList) {
            data[index][0] = a.getIdHormiga();
            data[index][1] = a.getNombre();
            data[index][2] = a.getIdCatalogoSexo();
            data[index][3] = a.getIdCatalogoIngestaNativa();
            data[index][4] = a.getEstado();
            index++;
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        if (tableHormigas != null) {
            tableHormigas.setModel(model);
        } else {
            tableHormigas = new JTable(model);
            scrollPane = new JScrollPane(tableHormigas);
            tablePanel.add(scrollPane, BorderLayout.CENTER);
        }
    }

    /**
     * Carga los valores de los comboBoxes con datos del catálogo.
     */
    private void AKLoadComboBoxes() throws Exception {
        comboBox1.removeAllItems();
        comboBox2.removeAllItems();
        AKCatalogo catalogo = new AKCatalogo();
        Map<Integer, String> genoAlimentoMap = catalogo.AKGetAllHormigaTipoGeno();
        Map<Integer, String> ingestaNativaMap = catalogo.AKGetAllHormigaTipoIngesta();
        for (String geno : genoAlimentoMap.values()) {
            comboBox1.addItem(geno);
        }
        for (String ingesta : ingestaNativaMap.values()) {
            comboBox2.addItem(ingesta);
        }
    }

    /**
     * Crea una nueva hormiga en estado de larva.
     */
    private void AKCrearLarva() {
        if (!AKConfig.showConfirmYesNo("¿Está seguro de crear una Hormiga larva?")) {
            return;
        }
        try {
            AKHormigaDAO dao = new AKHormigaDAO();
            AKHormigaDTO nuevaLarva = new AKHormigaDTO();
            nuevaLarva.setIdCatalogoTipo(6);
            nuevaLarva.setIdCatalogoSexo(10);
            nuevaLarva.setIdCatalogoEstado(11);
            nuevaLarva.setFechaCrea(AKDataHelperSQLite.getDateTimeNow());
            nuevaLarva.setEstado("A");
            if (dao.create(nuevaLarva)) {
                AKConfig.showMsg("Larva creada exitosamente.");
                AKShowDataTable();
            } else {
                AKConfig.showMsgError("Error al crear la larva.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            AKConfig.showMsgError("Error: " + e.getMessage());
        }
    }
}
