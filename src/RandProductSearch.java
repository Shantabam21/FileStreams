import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Paths;

public class RandProductSearch extends JFrame {

    private JTextField searchField;
    private JTextArea resultsArea;
    private RandomAccessFile file;

    private static final int RECORD_SIZE =
            Product.ID_LEN + Product.NAME_LEN + Product.DESC_LEN + 8;

    public RandProductSearch() {
        super("Random Product Search");


        String path = Paths.get("src", "product.bin").toAbsolutePath().toString();          try {
            file = new RandomAccessFile(path, "r");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Cannot open file: " + e.getMessage());
            System.exit(1);
        }

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.add(new JLabel("Enter partial product name:"), BorderLayout.WEST);

        searchField = new JTextField();
        topPanel.add(searchField, BorderLayout.CENTER);

        JButton searchBtn = new JButton("Search");
        topPanel.add(searchBtn, BorderLayout.EAST);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        resultsArea = new JTextArea();
        resultsArea.setEditable(false);
        mainPanel.add(new JScrollPane(resultsArea), BorderLayout.CENTER);

        searchBtn.addActionListener(e -> searchProducts());

        add(mainPanel);
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    private void searchProducts() {
        String query = searchField.getText().trim().toLowerCase();
        resultsArea.setText("");

        if (query.isEmpty()) {
            resultsArea.setText("Please enter a search term.");
            return;
        }

        try {
            long numRecords = file.length() / RECORD_SIZE;

            for (int i = 0; i < numRecords; i++) {
                Product p = readRecord(i);

                if (p.getName().toLowerCase().contains(query)) {
                    resultsArea.append(
                            "ID: " + p.getIDNum() + "\n" +
                            "Name: " + p.getName() + "\n" +
                            "Description: " + p.getDescription() + "\n" +
                            "Cost: " + p.getCost() + "\n" +
                            "---------------------------\n"
                    );
                }
            }

            if (resultsArea.getText().isEmpty()) {
                resultsArea.setText("No matching products found.");
            }

        } catch (IOException ex) {
            resultsArea.setText("Error reading file: " + ex.getMessage());
        }
    }


    private Product readRecord(int recordNum) throws IOException {
        file.seek(recordNum * RECORD_SIZE);

        byte[] idBytes = new byte[Product.ID_LEN];
        file.readFully(idBytes);

        byte[] nameBytes = new byte[Product.NAME_LEN];
        file.readFully(nameBytes);

        byte[] descBytes = new byte[Product.DESC_LEN];
        file.readFully(descBytes);

        double cost = file.readDouble();

        String id = new String(idBytes).trim();
        String name = new String(nameBytes).trim();
        String description = new String(descBytes).trim();

        return new Product(id, name, description, cost);
    }

    public static void main(String[] args) {
        new RandProductSearch();
    }
}