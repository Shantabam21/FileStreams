import javax.swing.*;
import java.awt.*;

public class RandProductMaker extends JFrame {
    JPanel mainPnl;
    JPanel productPnl;
    JPanel buttonPnl;

    public RandProductMaker() {
        super("RandProductMaker");
        setSize(700, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        createProductPanel();
        createButtonPanel();

        add(mainPnl);
        setVisible(true);

    }

    public void createProductPanel() {
        productPnl = new JPanel();

        mainPnl.add(productPnl, BorderLayout.CENTER);
    }

    public void createButtonPanel() {
        buttonPnl = new JPanel();
        mainPnl.add(buttonPnl, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        new RandProductMaker();
    }
}


