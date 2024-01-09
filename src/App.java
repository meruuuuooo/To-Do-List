import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e){
            System.out.println("debug + " + e);
        }
        SwingUtilities.invokeLater(() -> new ToDoListGUI().setVisible(true));
    }
}
