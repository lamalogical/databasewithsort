import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginDialog extends JDialog {
    private JTextField tfURL, tfLogin,tfPassword;
    public JButton btnLog, btnCancel;

    public LoginDialog(){
        super((Frame) null, "Database connection");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });
        getContentPane().add(createGUI());
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private JPanel createGUI(){
        JPanel panel = BoxLayoutUtils.createVerticalPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));
        //Создание панели для размещения метки и текстового поля для URL
        JPanel urlPanel = BoxLayoutUtils.createHorizontalPanel();
        JLabel urlLabel = new JLabel("URL");
        urlPanel.add(urlLabel);
        urlPanel.add(Box.createHorizontalStrut(12));
        tfURL = new JTextField(20);
        tfURL.setText("jdbc:mysql://127.0.0.1:3306/playlist");
        urlPanel.add(tfURL);

        //Создание панели для размещения метки и текстового поля для Login
        JPanel logPanel = BoxLayoutUtils.createHorizontalPanel();
        JLabel logLabel = new JLabel("Login");
        logPanel.add(logLabel);
        logPanel.add(Box.createHorizontalStrut(12));
        tfLogin = new JTextField("admin");
        logPanel.add(tfLogin);

        //Создание панели для размещения метки и текстового поля для Password
        JPanel passPanel = BoxLayoutUtils.createHorizontalPanel();
        JLabel passLabel = new JLabel("Password");
        passPanel.add(passLabel);
        passPanel.add(Box.createHorizontalStrut(12));
        tfPassword = new JTextField("admin");
        passPanel.add(tfPassword);

        //Панель для размещения кнопок управления
        JPanel flow = new JPanel(new FlowLayout(FlowLayout.RIGHT,0,0));
        JPanel grid = new JPanel(new GridLayout(1,2,5,0));
        btnLog = new JButton();
        btnCancel = new JButton("Cancel");
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        };
        btnCancel.addActionListener(actionListener);
        grid.add(btnLog);
        grid.add(btnCancel);
        flow.add(grid);

        // Выравнивание вложенных панелей по горизонтали
        BoxLayoutUtils.setGroupAlignmentX(new JComponent[] { urlPanel, logPanel, passPanel, flow },
                Component.LEFT_ALIGNMENT);
        // Выравнивание вложенных панелей по вертикали
        BoxLayoutUtils.setGroupAlignmentY(new JComponent[] { tfLogin, tfPassword, urlLabel, logLabel,
                passLabel}, Component.CENTER_ALIGNMENT);
        // Определение размеров надписей к текстовым полям
        GUITools.makeSameSize(new JComponent[] { urlLabel, logLabel, passLabel } );
        // Определение стандартного вида для кнопок
        GUITools.createRecommendedMargin(new JButton[] { btnLog, btnCancel } );
        // Устранение "бесконечной" высоты текстовых полей
        GUITools.fixTextFieldSize(tfLogin   );
        GUITools.fixTextFieldSize(tfPassword);

        //сборка интерфейса
        panel.add(urlPanel);
        panel.add(Box.createVerticalStrut(15));
        panel.add(logPanel);
        panel.add(Box.createVerticalStrut(15));
        panel.add(passPanel);
        panel.add(Box.createVerticalStrut(15));
        panel.add(flow);

        return panel;
    }

    public String getURL(){return tfURL.getText();}
    public String getLog(){return tfLogin.getText();}
    public String getPass(){return tfPassword.getText();}
    }

