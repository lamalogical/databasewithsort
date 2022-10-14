import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorkWithData extends MyFrame {
    JLabel jlName;
    JLabel jlAuthor;
    JLabel jlAlbum;
    JLabel jlYearsOfIssue;
    JLabel jlGenre;
    JLabel jlNumberInAlbum;

    JTextField tfName;
    JTextField tfAuthor;
    JTextField tfAlbum;
    JTextField tfYearsOfIssue;
    JTextField tfGenre;
    JTextField tfNumberInAlbum;

    JButton btConfirm;

    public WorkWithData() {
        super("Add data", new Dimension(300,250));
        //Надписи

        this.jlName = new JLabel("Name");
        this.jlName.setBounds(15, 15, 120, 20);

        this.jlAuthor = new JLabel("Author");
        this.jlAuthor.setBounds(15, 40, 120, 20);

        this.jlAlbum = new JLabel("Album");
        this.jlAlbum.setBounds(15, 65, 120, 20);

        this.jlYearsOfIssue = new JLabel("Years Of Issue");
        this.jlYearsOfIssue.setBounds(15, 90, 120, 20);

        this.jlGenre = new JLabel("Genre");
        this.jlGenre.setBounds(15, 115, 120, 20);

        this.jlNumberInAlbum = new JLabel("Number In Album");
        this.jlNumberInAlbum.setBounds(15, 140, 120, 20);

        //Поля ввода и комбобоксы
        this.tfName = new JTextField();
        this.tfName.setBounds(160, 15, 100, 20);

        this.tfAuthor = new JTextField();
        this.tfAuthor.setBounds(160, 40, 100, 20);

        this.tfAlbum = new JTextField();
        this.tfAlbum.setBounds(160, 65, 100, 20);

        this.tfYearsOfIssue = new JTextField();
        this.tfYearsOfIssue.setBounds(160, 90, 100, 20);

        this.tfGenre = new JTextField();
        this.tfGenre.setBounds(160, 115, 100, 20);

        this.tfNumberInAlbum = new JTextField();
        this.tfNumberInAlbum.setBounds(160, 140, 100, 20);


        //Кнопка добавления данных
        this.btConfirm = new JButton();
        this.btConfirm.setBounds(160, 165, 100, 30);

        //Добавление компонентов на панель
        this.myPanel.add(this.jlName);
        this.myPanel.add(this.jlAuthor);
        this.myPanel.add(this.jlAlbum);
        this.myPanel.add(this.jlYearsOfIssue);
        this.myPanel.add(this.jlGenre);
        this.myPanel.add(this.jlNumberInAlbum);
        this.myPanel.add(this.tfName);
        this.myPanel.add(this.tfAuthor);
        this.myPanel.add(this.tfAlbum);
        this.myPanel.add(this.tfYearsOfIssue);
        this.myPanel.add(this.tfGenre);
        this.myPanel.add(this.tfNumberInAlbum);
        this.myPanel.add(this.btConfirm);

        this.frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }

}
