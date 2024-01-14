import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.border.EmptyBorder;
import java.io.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

//we can implemnt the 'ActionListener' class to use the 'actionPerformed Method'
public class TextEditor implements ActionListener {

    //first thing is req. is JFrame.
    //this is main frame in which all component are contained.
    JFrame frame;

    //we req. the MINUBAR as well.
    JMenuBar menuBar;

    //textarea. object.
    JTextArea textArea;

    //for menubar we req. menuIte.
    JMenu file, edit;

    //object for JMenuIte.
    //File menu Item.
    JMenuItem newFile, openFile, saveFile;

    //Edit menu ite.
    JMenuItem copy, cut, paste, selectAll, close;

    //created the constructor
    TextEditor()
    {
        //initilize the JFrame =
        frame = new JFrame();

        //initilize  Menubar.
        menuBar = new JMenuBar();

        //initialize JTextArea.
        textArea = new JTextArea();

        //initilize the menus.
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //initialize file menu.
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        //before adding to file we must apply the 'ActionListener'
        //initialize file menu.
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //adding item to file menu.
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //initialize Edit item
        //copy, cut, paste, selectAll, close;
        copy = new JMenuItem("Copy");
        cut = new JMenuItem("Cut");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        //before adding to the edit menu we add 'ActionListener'
        copy.addActionListener(this);
        cut.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //adding this menuitem to edit menu.
        edit.add(copy);
        edit.add(cut);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);


        //adding menus to 'menuBar'
        menuBar.add(file);
        menuBar.add(edit);

        //set menubar to frame.
        frame.setJMenuBar(menuBar);

//        //adding 'textarea' to the frame.
//        frame.add(textArea);

        //now we deal with new struture.

        //creted the pane.
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0, 0));

        //adding textarea of panel.
        panel.add(textArea, BorderLayout.CENTER);

        //CREATED the scrool panel.
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //adding JScrollPane to the panel.
        panel.add(scrollPane);

        //add panel to frame.
        frame.add(panel);




        //Set the bound.
        frame.setBounds(100, 100, 400, 400 );

        //make set visible is true.
        frame.setVisible(true);

        //add title.
        frame.setTitle("Text Editor");
        
        //add setLayout to be null.
        frame.setLayout(null);


    }

    //overide the method 'actionPerfored'
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        //first apply on the File Menus.
        //initialize file menu.

        if(ae.getSource() == cut)
        {
            //perform the cut operation.
            textArea.cut();
        }

        if(ae.getSource() == copy)
        {
            //perform copy operation.
            textArea.copy();
        }

        if(ae.getSource() == paste)
        {
            //perform paste operation.
            textArea.paste();
        }

        if(ae.getSource() == selectAll)
        {
            //pefrom the selectAll opereation.
            textArea.selectAll();
        }

        if(ae.getSource() == close)
        {
            //perform the colse operation.
            System.exit(0);
        }

        //when we click on the 'open file'
        if(ae.getSource() == openFile)
        {
            //a file chooser is open.
            JFileChooser fileChooser = new JFileChooser("C:");

            int chooseOption = fileChooser.showOpenDialog(null);

            //if we chooser the open button.
            if(chooseOption == JFileChooser.APPROVE_OPTION)
            {
                //getiing the selected file.
                File file = fileChooser.getSelectedFile();

                //get path of selected file.
                String filePath = file.getPath();

                try
                {
                    //initialize the file reader.
                    FileReader fileReader = new FileReader(filePath);

                    //initialize bufferReader.
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    String intermediate = "";
                    String output = "";

                    //read content of file line by lien.
                    while((intermediate = bufferedReader.readLine()) != null)
                    {
                        output+=intermediate+"\n";
                    }

                    //set text to textarea.
                    textArea.setText(output);
                }
                catch(FileNotFoundException fileNotFoundException)
                {
                    fileNotFoundException.printStackTrace();
                }
                catch(IOException ioException)
                {
                    ioException.printStackTrace();
                }
            }
        }

        //when we click on the save button.
        if(ae.getSource() == saveFile)
        {
            //open file piceker.
            JFileChooser fileChooser = new JFileChooser("C:");

            //chooseroption.
            int chooserOption = fileChooser.showSaveDialog(null);

            //chcek if we click on the save option or nor.t
            if(chooserOption == JFileChooser.APPROVE_OPTION)
            {
                //create new file with chosen directory path and filename.
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");


               try {
                   //now we copy teh 'texteditor' data into the crated file.
                   //now we just write the created file.

                   //initialize FileWriter.
                   FileWriter fileWriter = new FileWriter(file);

                   //initialize the BufferedWriter.
                   BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                   //write the content of textares to the file created.
                   textArea.write(bufferedWriter);

                   bufferedWriter.close();

               }
               catch (IOException ioException)
               {
                   ioException.printStackTrace();
               }



            }

        }

        //if we click on the new window.
        if(ae.getSource() == newFile)
        {
            TextEditor newtextEditor = new TextEditor();
        }
    }
    public static void main(String[] args) {

        //creating the instance of TextEditor.
        TextEditor textEditor = new TextEditor();



    }
}