import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Paths;

public class RandProductMaker extends JFrame {

    private JTextField idField, nameField, descField, costField, countField;
    private JButton addBtn, quitBtn;
    private int recordCount;
    private RandomAccessFile file;

    private static final int RECORD_SIZE =
            Product.ID_LEN + Product.NAME_LEN + Product.DESC_LEN + 8; // double = 8 bytes

    public RandProductMaker() {
        super("Random Product Maker");

        String path = Paths.get("").toAbsolutePath() + File.separator + "products.bin";
        System.out.println("File: " + path);

        try {
            file = new RandomAccessFile(path, "rw");
            recordCount = (int) (file.length() / RECORD_SIZE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Cannot open file: " + e.getMessage());
            System.exit(1);
        }

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));

        inputPanel.add(new JLabel("Product ID (6 chars):"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Name (≤ 35 chars):"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Description (≤ 75 chars):"));
        descField = new JTextField();
        inputPanel.add(descField);

        inputPanel.add(new JLabel("Cost:"));
        costField = new JTextField();
        inputPanel.add(costField);

        inputPanel.add(new JLabel("Record Count:"));
        countField = new JTextField("" + recordCount);
        countField.setEditable(false);
        inputPanel.add(countField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        addBtn = new JButton("Add Product");
        quitBtn = new JButton("Quit");

        addBtn.addActionListener(e -> addProduct());
        quitBtn.addActionListener(e -> System.exit(0));

        buttonPanel.add(addBtn);
        buttonPanel.add(quitBtn);

        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setSize(500, 260);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addProduct() {
        try {
            String id = idField.getText().trim();
            if (id.length() != 6) {
                JOptionPane.showMessageDialog(this, "ID must be exactly 6 characters.");
                return;
            }

            String name = nameField.getText().trim();
            String desc = descField.getText().trim();

            double cost;
            try {
                cost = Double.parseDouble(costField.getText());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid cost.");
                return;
            }

            Product p = new Product(id, name, desc, cost);

            writeRecord(p, recordCount);
            recordCount++;

            countField.setText("" + recordCount);
            clearFields();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        descField.setText("");
        costField.setText("");
    }

    private void writeRecord(Product p, int recordNum) throws IOException {
        file.seek(recordNum * RECORD_SIZE);
        file.writeBytes(p.getPaddedID());
        file.writeBytes(p.getPaddedName());
        file.writeBytes(p.getPaddedDescription());
        file.writeDouble(p.getCost());
    }

    public static void main(String[] args) {
        new RandProductMaker();
    }
}