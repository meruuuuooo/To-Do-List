import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class TaskComponent extends JPanel implements ActionListener {
    public JCheckBox checkBox;
    public JTextField taskField;
    public JButton deleteButton;
    public Color color;
    public Random random;
    public JTextField getTaskField() {
        return taskField;
    }
    public JPanel parentPanel;

    public TaskComponent(JPanel parentPanel) {
        this.parentPanel = parentPanel;

        this.random = new Random();

        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);

        this.color = new Color(red, green, blue);

        taskField = new JTextField();
        taskField.setBorder(BorderFactory.createLineBorder(color, 1));
        taskField.setFont(new Font("Courier New", Font.PLAIN, 18));
        taskField.setPreferredSize(new Dimension(450, 50));


        checkBox = new JCheckBox();
        checkBox.setFocusable(false);
        checkBox.setToolTipText("Done Task");
        checkBox.setIcon(ToDoListGUI.loadImage("src/assets/icons8-check-25 (2).png"));
        checkBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
        checkBox.setPreferredSize(new Dimension(50, 50));
        checkBox.setHorizontalAlignment(SwingConstants.CENTER);
        checkBox.addActionListener(this);

        JPanel deletePanel = new JPanel();
        deletePanel.setPreferredSize(new Dimension(50, 50));
        deletePanel.setLayout(null);

        deleteButton = new JButton(ToDoListGUI.loadImage("src/assets/icons8-x-25.png"));
        deleteButton.setFont(new Font("Courier New", Font.BOLD, 12));
        deleteButton.setToolTipText("Delete Task");
        deleteButton.setFocusable(false);
        deleteButton.setBorder(BorderFactory.createEmptyBorder());
        deleteButton.setBounds(13, 12, 25,25);
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteButton.addActionListener(this);

        deletePanel.add(deleteButton);

        add(checkBox);
        add(taskField);
        add(deletePanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(checkBox.isSelected()){
            checkBox.setIcon(ToDoListGUI.loadImage("src/assets/icons8-check-25 (1).png"));

            String text = taskField.getText();
            taskField.setEditable(false);

            if (taskField.getText().isEmpty()){
                return;
            } else {
                taskField.setText("'"+text+"'");
                taskField.setForeground(Color.green);
            }

        }else if(!checkBox.isSelected()){
            String text = taskField.getText();

            taskField.setForeground(this.getForeground());
            taskField.setEditable(true);
            checkBox.setIcon(ToDoListGUI.loadImage("src/assets/icons8-check-25 (2).png"));

            taskField.setText(text.replaceAll("'", ""));


        }

        if(e.getSource() == deleteButton){
            parentPanel.remove(this);
            parentPanel.repaint();
            parentPanel.revalidate();
        }
    }
}
