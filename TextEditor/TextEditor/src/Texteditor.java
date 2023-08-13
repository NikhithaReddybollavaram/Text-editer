import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Texteditor implements ActionListener {
    //declaring datamembers(properties of texteditor)
    JFrame frame;

    JMenuBar menubar;
    JMenu file, edit;
    //file Menu items
    JMenuItem newfile, openfile, savefile;
    //edit menu items
    JMenuItem cut, copy, paste, selectall, close;
    //declaring the text area
    JTextArea textarea;

    Texteditor() {
        //initialize the frame
        frame = new JFrame("Acciojobs Text Editor");
        //initialize menubar
        menubar = new JMenuBar();
        //initialize textarea
        textarea = new JTextArea();

        //initialize menus
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //initialize file menu items
        newfile = new JMenuItem("New File");
        openfile = new JMenuItem("Open File");
        savefile = new JMenuItem("Save File");

        //initialize edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectall = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        //adding action listener to the file menu items
        newfile.addActionListener(this);
        openfile.addActionListener(this);
        savefile.addActionListener(this);

        //adding action listener to the edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectall.addActionListener(this);
        close.addActionListener(this);

        //adding file menu items to file menu
        file.add(newfile);
        file.add(openfile);
        file.add(savefile);

        //adding edit menu items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectall);
        edit.add(close);

        //adding file and edit menus to menu bar
        menubar.add(file);
        menubar.add(edit);
        //create content pane
        JPanel panel=new JPanel();

        //add textarea to the frame
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        //add text area to the panel
        panel.add(textarea,BorderLayout.CENTER);
        //create scroll pane
        JScrollPane scrollpane=new JScrollPane(textarea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //add scroll pane to the panel
        panel.add(scrollpane);
        //add panel to the frame
        frame.add(panel);
        //frame.add(textarea);

        //adding menubar to frame
        frame.setJMenuBar(menubar);
        frame.setBounds(0, 0, 400, 400);
        frame.setVisible(true);
        frame.setLayout(null);
        //Action listener


    }

    @Override
    public void actionPerformed(ActionEvent actionevent) {
        //Action event performed on file menu items
     if(actionevent.getSource()==newfile){
         Texteditor texteditor = new Texteditor();
     }
     if(actionevent.getSource()==openfile){
       //open a file chooser
       JFileChooser filechooser=new JFileChooser("C:");
       int chooseoption=filechooser.showOpenDialog(null);
       if(chooseoption==JFileChooser.APPROVE_OPTION){
           //getting the selected file
           File file=filechooser.getSelectedFile();
           //get the path of the selected file
           String filepath=file.getPath();
           try{
            //initialize the file reader
               FileReader filereader=new FileReader(filepath);
            //initialize bufferreader
               BufferedReader bufferedreader=new BufferedReader(filereader);

               String intermediate="",output="";
               //read the file line by line
               while((intermediate = bufferedreader.readLine())!=null){
                   output+=intermediate+"\n";
               }
               //set the output string to text area
               textarea.setText(output);
           }
           catch (IOException ioexception){
               ioexception.printStackTrace();
           }
       }
     }
     if(actionevent.getSource()==savefile){
         //initialize the filechooser
         JFileChooser filechooser=new JFileChooser("C:");
         //get choose option from file chooser
         int chooseoption=filechooser.showSaveDialog(null);
         //check if we clicked on save button
         if(chooseoption==JFileChooser.APPROVE_OPTION){
             //create a new file with choosen directry path and file name
             File file=new File(filechooser.getSelectedFile().getAbsolutePath()+".txt");
             try{
                 //initialize file writer
              FileWriter filewriter=new FileWriter(file);
              //initialize buffer writer
                 BufferedWriter bufferedwriter=new BufferedWriter(filewriter);
                 //write contents in the textarea into file
                 textarea.write(bufferedwriter);
                 bufferedwriter.close();
             }catch(IOException ioexception){
                 ioexception.printStackTrace();

             }
         }

     }

     //Action event performed on edit menu items
        if(actionevent.getSource()==cut){
            textarea.cut();
        }
        if(actionevent.getSource()==copy){
            textarea.copy();
        }
        if(actionevent.getSource()==paste){
            textarea.paste();
        }
        if(actionevent.getSource()==selectall){
            textarea.selectAll();
        }
        if(actionevent.getSource()==close){
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        Texteditor texteditor = new Texteditor();

    }
}

