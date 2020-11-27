import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class MianForm {
    private JPanel mainPanel;
    private JButton collapseButton;
    private JTextPane nameTextPane;
    private JTextPane surnameTextPane;
    private JTextPane middleNameTextPane;
    private JTextField name;
    private JTextField surname;
    private JTextField middleName;
    private JTextField textField1;
    private JLabel label;
    private String TITLE_message = "Ошибка";

    public JPanel getMainPanel() {
        label.setVisible(false);
        textField1.setVisible(false);
        return mainPanel;
    }

    public MianForm() {
        collapseButton.addActionListener(new Action() {

            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {

                String nameText = name.getText();
                String surnameText = surname.getText();
                String middleNameText = middleName.getText();
                String fullName = nameText + " " + surnameText + " " + " " + middleNameText;
                    if (collapseButton.getLabel().equals("Collapse")) {
                        if (nameText.isEmpty() && surnameText.isEmpty()) {
                            JOptionPane.showMessageDialog(mainPanel, "Exception, please write your Name and Surname!", TITLE_message, JOptionPane.ERROR_MESSAGE);

                        } else {
                            nameTextPane.setVisible(false);
                            surnameTextPane.setVisible(false);
                            middleNameTextPane.setVisible(false);
                            name.setVisible(false);
                            surname.setVisible(false);
                            middleName.setVisible(false);
                            collapseButton.setLabel("Expand");
                            label.setVisible(true);
                            textField1.setVisible(true);
                            textField1.setText(fullName);
                        }
                    } else {
                        label.setVisible(false);
                        textField1.setVisible(false);
                        nameTextPane.setVisible(true);
                        surnameTextPane.setVisible(true);
                        middleNameTextPane.setVisible(true);
                        name.setVisible(true);
                        surname.setVisible(true);
                        middleName.setVisible(true);
                        collapseButton.setLabel("Collapse");
                    }
                }

        });
    }
}