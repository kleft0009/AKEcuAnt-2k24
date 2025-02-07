package AKUIC.AKForm;

import AKBLC.AKCatalogo;
import AKDAC.AKDAO.AKHormigaDAO;
import AKDAC.AKDTO.AKHormigaDTO;
import AKDAC.AKDataHelperSQLite;
import AKDAC.AKHormigueroDAC;
import AKInfra.AKConfig;
import AKUIC.AKStyle;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public AKHormigueroPanel() {
        setLayout(new BorderLayout());

        ImageIcon imageIcon = new ImageIcon(AKStyle.AKURL_MAIN);
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(400, 250, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(scaledImage);

        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(imageLabel, BorderLayout.NORTH);

        JPanel titleAndTablePanel = new JPanel();
        titleAndTablePanel.setLayout(new BorderLayout());

        titleLabel = new JLabel("GENO HORMIGUERO EXPERIMENTAL", SwingConstants.CENTER);
        titleLabel.setForeground(AKStyle.RACOLOR_CURSOR);
        titleAndTablePanel.add(titleLabel, BorderLayout.NORTH);

        tablePanel = new JPanel(new BorderLayout());
        titleAndTablePanel.add(tablePanel, BorderLayout.CENTER);

        add(titleAndTablePanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        JPanel comboPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        comboBox1 = new JComboBox<>();
        comboBox2 = new JComboBox<>();
        comboBox1.setPreferredSize(new Dimension(150, 30));
        comboBox2.setPreferredSize(new Dimension(150, 30));
        comboPanel.add(comboBox1);
        comboPanel.add(comboBox2);
        bottomPanel.add(comboPanel);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        btnCrearLarva = new JButton("Crear Larva");
        btnCrearLarva.setBackground(AKStyle.RACOLOR_FONT_LIGHT);

        btnAlimentar = new JButton("Alimentar");
        btnAlimentar.setBackground(AKStyle.RACOLOR_FONT_LIGHT);

        btnEntrenar = new JButton("Entrenar");
        btnEntrenar.setBackground(AKStyle.RACOLOR_FONT_LIGHT);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(AKStyle.RACOLOR_FONT);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(AKStyle.RACOLOR_FONT_LIGHT);

        btnCrearLarva.addActionListener(e -> raCrearLarva());
        btnAlimentar.addActionListener(e -> raAlimentar());
        btnEntrenar.addActionListener(e -> raEntrenarHormiga());
        btnEliminar.addActionListener(e -> raEliminarHormiga());
        btnGuardar.addActionListener(e -> raGuardarHormiguero());

        buttonPanel.add(btnCrearLarva);
        buttonPanel.add(btnAlimentar);
        buttonPanel.add(btnEntrenar);
        buttonPanel.add(btnEliminar);
        buttonPanel.add(btnGuardar);
        bottomPanel.add(buttonPanel);

        footerLabel = new JLabel("PROGRAMACION II | Cedula: 1755053251 | Kevin Azana");
        footerLabel.setForeground(AKStyle.RACOLOR_LABEL);
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        footerPanel.add(footerLabel);
        bottomPanel.add(footerPanel);

        add(bottomPanel, BorderLayout.SOUTH);

        try {
            AKShowDataTable();
            AKLoadComboBoxes();
            AKUpdateComboBoxesFromTable();
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println(e);
        }
    }

    private void AKShowDataTable() throws Exception {
        String[] columnNames = {"IdHormiga", "TipoHormiga", "Sexo", "Alimentacion", "Estado", "Entrenada"};

        List<AKHormigaDTO> hormigasList = new AKHormigaDAO().readHormigasFromView();

        Object[][] data = new Object[hormigasList.size()][6];

        int index = 0;
        for (AKHormigaDTO a : hormigasList) {
            data[index][0] = a.getIdHormiga();
            data[index][1] = a.getNombre();
            data[index][2] = a.getSexo();
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

        JTableHeader header = tableHormigas.getTableHeader();
        header.setFont(AKStyle.raHeaderFont());

        tableHormigas.getSelectionModel().clearSelection();
        AKUpdateComboBoxesFromTable();

        tablePanel.revalidate();
        tablePanel.repaint();
        raRestoreEvolucionadoState();
    }

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

    private void AKUpdateComboBoxesFromTable() {
        tableHormigas.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && tableHormigas.getSelectedRow() != -1) {
                int selectedRow = tableHormigas.getSelectedRow();

                String sexo = (String) tableHormigas.getValueAt(selectedRow, 2);
                String alimentacion = (String) tableHormigas.getValueAt(selectedRow, 3);
                String genoAlimento;
                switch (sexo.toLowerCase()) {
                    case "asexual":
                        genoAlimento = "X";
                        break;
                    case "macho":
                        genoAlimento = "XX";
                        break;
                    case "hembra":
                        genoAlimento = "XY";
                        break;
                    default:
                        genoAlimento = "";
                }

                comboBox1.setSelectedItem(genoAlimento);
                comboBox2.setSelectedItem(alimentacion);
            }
        });
    }

    private void raCrearLarva() {
        if (!AKConfig.showConfirmYesNo("¿Está seguro de crear una Hormiga larva?")) {
            return;
        }

        try {
            AKHormigaDAO dao = new AKHormigaDAO();
            AKHormigaDTO nuevaLarva = new AKHormigaDTO();

            nuevaLarva.setIdCatalogoTipo(6);
            nuevaLarva.setIdCatalogoSexo(10);
            nuevaLarva.setIdCatalogoEstado(11);
            nuevaLarva.setIdCatalogoGenoAllimento(13);
            nuevaLarva.setIdCatalogoIngestaNativa(20);
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

    private void raAlimentar() {
        try {
            int selectedRow = tableHormigas.getSelectedRow();
            if (selectedRow == -1) {
                AKConfig.showMsg("Por favor, selecciona una hormiga.");
                return;
            }

            int idHormiga = (Integer) tableHormigas.getValueAt(selectedRow, 0);
            System.out.println("ID de la hormiga seleccionada: " + idHormiga);

            AKHormigaDAO dao = new AKHormigaDAO();
            AKHormigaDTO hormiga = dao.readBy(idHormiga);

            if (hormiga == null) {
                AKConfig.showMsgError("No se encontró la hormiga con el ID seleccionado.");
                return;
            }

            if (hormiga.getIdCatalogoEstado() == 12) {
                AKConfig.showMsgError("No se puede alimentar a una hormiga muerta.");
                return;
            }

            String genoAlimento = (String) comboBox1.getSelectedItem();
            String ingestaNativa = (String) comboBox2.getSelectedItem();

            if (hormiga.getIdCatalogoTipo() == 6) {
                if ("Herbívoro".equals(ingestaNativa) && "XX".equals(genoAlimento)) {
                    hormiga.setIdCatalogoTipo(7);
                    hormiga.setIdCatalogoIngestaNativa(17);
                    AKConfig.showMsg("La larva ha evolucionado a Rastreadora.");
                } else if ("Nectarívoro".equals(ingestaNativa)) {
                    hormiga.setIdCatalogoIngestaNativa(20);
                    AKConfig.showMsg("Alimentaste a la larva.");
                } else {
                    hormiga.setIdCatalogoEstado(12);
                    AKConfig.showMsgError("La larva ha muerto.");
                }
            } else if (hormiga.getIdCatalogoTipo() == 7) {
                if ("Herbívoro".equals(ingestaNativa) && "XX".equals(genoAlimento)) {
                    AKConfig.showMsg("La Rastreadora comio.");
                } else {
                    hormiga.setIdCatalogoEstado(12);
                    AKConfig.showMsgError("La rastreadora ha muerto.");
                }
            }

            if (dao.update(hormiga)) {
                AKShowDataTable();
            } else {
                AKConfig.showMsgError("Error al alimentar la hormiga.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            AKConfig.showMsgError("Error: " + e.getMessage());
        }
    }

    private void raEntrenarHormiga() {
        try {
            int selectedRow = tableHormigas.getSelectedRow();
            if (selectedRow == -1) {
                AKConfig.showMsgError("Por favor, selecciona una hormiga.");
                return;
            }

            int idHormiga = (Integer) tableHormigas.getValueAt(selectedRow, 0);
            String tipoHormiga = (String) tableHormigas.getValueAt(selectedRow, 1);

            AKHormigaDAO dao = new AKHormigaDAO();
            AKHormigaDTO hormiga = dao.readBy(idHormiga);

            if (hormiga == null) {
                AKConfig.showMsgError("No se encontró la hormiga con el ID seleccionado.");
                return;
            }

            if (hormiga.getIdCatalogoTipo() == 6) {
                AKConfig.showMsgError("Las larvas no pueden ser entrenadas.");
                return;
            }
            if (hormiga.getIdCatalogoEstado() != 12) {
                tableHormigas.setValueAt("SI", selectedRow, 5);
                //hormiga.setEvolucionado("SI");

                if (dao.update(hormiga)) {
                    AKConfig.showMsg(tipoHormiga + " entrenada y sumisa.");
                } else {
                    AKConfig.showMsgError("Error al entrenar la hormiga.");
                }
            } else {
                AKConfig.showMsgError("La hormiga está muerta y no puede ser entrenada.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            AKConfig.showMsgError("Error: " + ex.getMessage());
        }
    }

    private void raEliminarHormiga() {
        try {
            int selectedRow = tableHormigas.getSelectedRow();
            if (selectedRow == -1) {
                AKConfig.showMsgError("Por favor, selecciona una hormiga.");
                return;
            }

            int idHormiga = (Integer) tableHormigas.getValueAt(selectedRow, 0);
            String tipoHormiga = (String) tableHormigas.getValueAt(selectedRow, 1);
            String estadoActual = (String) tableHormigas.getValueAt(selectedRow, 4);

            if ("MUERTA".equalsIgnoreCase(estadoActual)) {
                AKConfig.showMsgError("La hormiga ya está muerta.");
                return;
            }

            int response = JOptionPane.showConfirmDialog(
                    null,
                    "¿Está seguro de eliminar la " + tipoHormiga + "?",
                    "Confirmar Eliminación",
                    JOptionPane.YES_NO_OPTION
            );

            if (response == JOptionPane.NO_OPTION) {
                return;
            }

            AKHormigaDAO dao = new AKHormigaDAO();
            AKHormigaDTO hormiga = dao.readBy(idHormiga);

            if (hormiga == null) {
                AKConfig.showMsgError("No se encontró la hormiga.");
                return;
            }

            hormiga.setIdCatalogoEstado(12);
            if (dao.update(hormiga)) {
                AKConfig.showMsg("Hormiga eliminada correctamente.");
                AKShowDataTable();
            } else {
                AKConfig.showMsgError("Error al eliminar la hormiga.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            AKConfig.showMsgError("Error: " + e.getMessage());
        }
    }

    private void raGuardarHormiguero() {
        try {
            if (tableHormigas.getRowCount() == 0) {
                AKConfig.showMsgError("No hay datos para guardar.");
                return;
            }

            AKHormigueroDAC hormigueroDAC = new AKHormigueroDAC();
            List<String> dataToSave = new ArrayList<>();

            for (int i = 0; i < tableHormigas.getRowCount(); i++) {
                int id = (Integer) tableHormigas.getValueAt(i, 0);
                String tipo = (String) tableHormigas.getValueAt(i, 1);
                String sexo = (String) tableHormigas.getValueAt(i, 2);
                String alimentacion = (String) tableHormigas.getValueAt(i, 3);
                String estado = (String) tableHormigas.getValueAt(i, 4);
                String entrenada = (String) tableHormigas.getValueAt(i, 5);

                String rowData = String.format("%d,%s,%s,%s,%s,%s", id, tipo, sexo, alimentacion, estado, entrenada);
                dataToSave.add(rowData);
            }

            hormigueroDAC.saveHormigueroToCSV(dataToSave);
            AKConfig.showMsg("Datos guardados en CSV correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
            AKConfig.showMsgError("Error al guardar: " + e.getMessage());
        }
    }

    private void raRestoreEvolucionadoState() {
        for (int i = 0; i < tableHormigas.getRowCount(); i++) {
            int idHormiga = (Integer) tableHormigas.getValueAt(i, 0);
            if (evolucionadoState.containsKey(idHormiga)) {
                tableHormigas.setValueAt(evolucionadoState.get(idHormiga) ? "SI" : "NO", i, 5);
            }
        }
    }

}
