import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// Interfaz Documento
interface Document {
    String getInfo();
}

// Clases de documentos
class ElectronicInvoice implements Document {
    String country, format;
    public ElectronicInvoice(String country, String format) { this.country = country; this.format = format; }
    public String getInfo() { return "Electronic Invoice | " + country + " | " + format; }
}

class LegalContract implements Document {
    String country, format;
    public LegalContract(String country, String format) { this.country = country; this.format = format; }
    public String getInfo() { return "Legal Contract | " + country + " | " + format; }
}

class FinancialReport implements Document {
    String country, format;
    public FinancialReport(String country, String format) { this.country = country; this.format = format; }
    public String getInfo() { return "Financial Report | " + country + " | " + format; }
}

class DigitalCertificate implements Document {
    String country, format;
    public DigitalCertificate(String country, String format) { this.country = country; this.format = format; }
    public String getInfo() { return "Digital Certificate | " + country + " | " + format; }
}

class TaxDeclaration implements Document {
    String country, format;
    public TaxDeclaration(String country, String format) { this.country = country; this.format = format; }
    public String getInfo() { return "Tax Declaration | " + country + " | " + format; }
}

// Factory Method
class DocumentFactory {
    public static Document createDocument(String type, String country, String format) {
        switch(type) {
            case "Electronic Invoice": return new ElectronicInvoice(country, format);
            case "Legal Contract": return new LegalContract(country, format);
            case "Financial Report": return new FinancialReport(country, format);
            case "Digital Certificate": return new DigitalCertificate(country, format);
            case "Tax Declaration": return new TaxDeclaration(country, format);
        }
        return null;
    }
}

// GUI con lotes independientes
public class Main extends JFrame {

    JComboBox<String> typeBox, formatBox;
    JButton addButton;
    JPanel tabsPanel;
    
    // Lotes independientes por país
    DefaultListModel<String> colombiaModel = new DefaultListModel<>();
    DefaultListModel<String> mexicoModel = new DefaultListModel<>();
    DefaultListModel<String> argentinaModel = new DefaultListModel<>();
    DefaultListModel<String> chileModel = new DefaultListModel<>();

    public Main() {

        setTitle("Document System - Independent Country Batches");
        setSize(600,450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior con controles
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        controlPanel.add(new JLabel("Document Type:"));
        typeBox = new JComboBox<>(new String[]{
            "Electronic Invoice","Legal Contract","Financial Report","Digital Certificate","Tax Declaration"
        });
        controlPanel.add(typeBox);

        controlPanel.add(new JLabel("Format:"));
        formatBox = new JComboBox<>(new String[]{"pdf","docx","xml","csv","xlsx"});
        controlPanel.add(formatBox);

        addButton = new JButton("Add Document");
        controlPanel.add(addButton);

        add(controlPanel, BorderLayout.NORTH);

        // Tabs para cada país
        tabsPanel = new JTabbedPane();

        tabsPanel.addTab("Colombia", new JScrollPane(new JList<>(colombiaModel)));
        tabsPanel.addTab("Mexico", new JScrollPane(new JList<>(mexicoModel)));
        tabsPanel.addTab("Argentina", new JScrollPane(new JList<>(argentinaModel)));
        tabsPanel.addTab("Chile", new JScrollPane(new JList<>(chileModel)));

        add(tabsPanel, BorderLayout.CENTER);

        // Acción botón
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String type = typeBox.getSelectedItem().toString();
                String format = formatBox.getSelectedItem().toString();

                int selectedTab = tabsPanel.getSelectedIndex();
                String country = tabsPanel.getTitleAt(selectedTab);

                Document doc = DocumentFactory.createDocument(type, country, format);

                // Agregar al modelo correspondiente
                switch(country) {
                    case "Colombia": colombiaModel.addElement(doc.getInfo()); break;
                    case "Mexico": mexicoModel.addElement(doc.getInfo()); break;
                    case "Argentina": argentinaModel.addElement(doc.getInfo()); break;
                    case "Chile": chileModel.addElement(doc.getInfo()); break;
                }
            }
        });
    }

    public static void main(String[] args) {
        new Main().setVisible(true);
    }
}