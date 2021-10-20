import javax.swing.*;

public class Start {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Sving form");
        jFrame.setContentPane(new MainForm(jFrame).getPanel());
        jFrame.setLocationRelativeTo(null);
        jFrame.setSize(500, 300);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);


    }
}
