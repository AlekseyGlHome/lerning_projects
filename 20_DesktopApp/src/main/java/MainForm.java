import javax.swing.*;

public class MainForm {
    private JPanel panel;
    private JButton btn;
    private JTextField lastName;
    private JTextField firstName;
    private JTextField patronymic;
    private JFrame jFrame;


    public JPanel getPanel() {
        return panel;
    }

    public MainForm(JFrame jFrame) {
        this.jFrame = jFrame;
        btn.addActionListener(e -> {
            if (isLastName() && isFirstName()) {
                this.jFrame.setContentPane(new TwoForm(this.jFrame, getFullName()).getPanel());
                this.jFrame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Незаполнены обязательные поля (Фамилия, Имя)", "Ошибка", JOptionPane.ERROR_MESSAGE);
                lastName.requestFocusInWindow();
            }
        });
    }

    private String getFullName() {
        String fullName = lastName.getText().trim() +
                " " +
                firstName.getText().trim() +
                " " + patronymic.getText().trim();
        return fullName;
    }

    public MainForm(JFrame jFrame, String fullName) {
        this(jFrame);
        init(fullName);
        jFrame.repaint();
    }

    private void init(String fullName) {
        String[] name = fullName.split(" ");
        lastName.setText(name[0]);
        firstName.setText(name[1]);
        if (name.length >= 3) {
            patronymic.setText(name[2]);
        }
    }

    private boolean isFirstName() {
        if (!firstName.getText().isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean isLastName() {
        if (!lastName.getText().isEmpty()) {
            return true;
        }
        return false;

    }


}
