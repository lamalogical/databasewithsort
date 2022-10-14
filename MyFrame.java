
import javax.swing.*;
import java.awt.*;

abstract class MyFrame
{
    JFrame frame;
    JPanel myPanel;

    public MyFrame(String name, Dimension dimension)
    {
        this.myPanel = new JPanel();
        this.myPanel.setPreferredSize(dimension);
        this.myPanel.setLayout(null);

        this.frame = new JFrame(name);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setPreferredSize(dimension);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);

        this.frame.add(this.myPanel);
    }

    public void display()
    {
        this.frame.setVisible(true);
    }

    public void hide()
    {
        this.frame.setVisible(false);
    }
}
