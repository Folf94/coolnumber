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
    private String TITLE_message = "Ошибка";

    public JPanel getMainPanel() {
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
                for (; ; ) {
                    if (collapseButton.getLabel().equals("Expand")) {
                        if (!nameText.isEmpty() && !surnameText.isEmpty()) {
                            JOptionPane.showMessageDialog(mainPanel,
                                    nameText + " " + surnameText + " " + middleNameText);
                            collapseButton.setLabel("Collapse");

                        } else if (nameText.isEmpty() && surnameText.isEmpty()) {
                            JOptionPane.showMessageDialog( mainPanel,"Exception, please write your Name and Surname!", TITLE_message,
                                    JOptionPane.ERROR_MESSAGE);

                        }
                        break;
                    }

                    if (!nameText.isEmpty() && !surnameText.isEmpty()) {
                        JOptionPane.showMessageDialog(mainPanel, nameText + " " + surnameText + " " + middleNameText);
                        collapseButton.setLabel("Expand");
                    } else if (nameText.isEmpty() && surnameText.isEmpty()) {
                        JOptionPane.showMessageDialog( mainPanel,"Exception, please write your Name and Surname!", TITLE_message,
                                JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }
            }
        });

    }

    private void createUIComponents() {

    }
}
