import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class ToDoListGUI extends JFrame implements ActionListener {

    public JPanel taskPanel, taskComponentPanel;
    public ToDoListGUI() {
        super("To Do List Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 560));
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        addGuiComponents();
    }

    public void addGuiComponents() {
        JLabel headerLabel = new JLabel("To Do List", JLabel.CENTER);
        headerLabel.setFont(new Font("Courier New", Font.BOLD, 36));
        headerLabel.setPreferredSize(new Dimension(0, 50));

        taskPanel = new JPanel();

        taskComponentPanel = new JPanel();
        taskComponentPanel.setLayout(new BoxLayout(taskComponentPanel, BoxLayout.Y_AXIS));
        taskPanel.add(taskComponentPanel);

        JScrollPane scrollPane = new JScrollPane(taskPanel);
        scrollPane.setPreferredSize(new Dimension(600, 500));
        scrollPane.setMaximumSize(new Dimension(600, 500));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(20);

        JButton addTaskButton = new JButton("Add Task");
        addTaskButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addTaskButton.setPreferredSize(new Dimension(600, 60));
        addTaskButton.addActionListener(this);



        this.getContentPane().add(headerLabel, BorderLayout.NORTH);
        this.getContentPane().add(scrollPane, BorderLayout.CENTER);
        this.getContentPane().add(addTaskButton, BorderLayout.SOUTH);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equalsIgnoreCase("Add Task")){
            // create a task component
            TaskComponent taskComponent = new TaskComponent(taskComponentPanel);
            taskComponentPanel.add(taskComponent);

            // make the previous task appear disabled
            if(taskComponentPanel.getComponentCount() > 1){
                TaskComponent previousTask = (TaskComponent) taskComponentPanel.getComponent(
                        taskComponentPanel.getComponentCount() - 2);
                previousTask.getTaskField().setBackground(null);
            }

            // make the task field request focus after creation
            taskComponent.getTaskField().requestFocus();
            repaint();
            revalidate();
        }
    }
    public static ImageIcon loadImage(String imagePath) {
        try {

            BufferedImage image = ImageIO.read(new File(imagePath));

            return new ImageIcon(image);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
