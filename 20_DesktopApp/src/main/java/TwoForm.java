import javax.swing.*;

public class TwoForm {
    private JPanel panel;
    private JButton btn;
    private JTextField fullName;
    private JFrame jFrame;

    public JPanel getPanel() {
        return panel;
    }

    public TwoForm(JFrame jFrame) {
        this.jFrame = jFrame;
        btn.addActionListener(e -> {
            fullName.setText(fullName.getText().trim().replaceAll("\\s+", " "));
            if (isFullName()) {
                this.jFrame.setContentPane(new MainForm(this.jFrame, fullName.getText()).getPanel());
                this.jFrame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "В строке менее двух слов\n введите Фамилию и Имя", "Ошибка", JOptionPane.ERROR_MESSAGE);
                fullName.requestFocusInWindow();
            }
        });
    }

    public TwoForm(JFrame jFrame, String fullName) {
        this(jFrame);
        this.fullName.setText(fullName);
        jFrame.repaint();
    }

    private boolean isFullName() {
        String[] name = fullName.getText().split(" ");
        if (name.length >= 2) {
            return true;
        }
        return false;
    }


}
