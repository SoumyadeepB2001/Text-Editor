import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Insets;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.undo.UndoManager;
import javax.swing.*;

public class TextEditor extends JFrame {
    UndoManager undoManager = new UndoManager();
    File filename; // this variable stores the current file in usage
    String currentFontName = "Arial";
    int currentFontStyle = Font.PLAIN;
    int currentFontSize = 12;
    JFrame findrepFrame;
    int n, z = 0; // int n and int z is used in 'Find' operation
    String s = "", s2 = ""; // s is used to store the 'Find' text value and s2 is used to store the 'Replace' text value    

    // Variable declaration
    private JMenuItem arial;
    private JMenuItem bold;
    private JMenuItem bolditalics;
    private JCheckBoxMenuItem wrapTextCheckBox;
    private JMenuItem close;
    private JMenuItem copy;
    private JMenuItem cut;
    private JMenu edit;
    private JMenu file;
    private JMenu help;
    private JMenuItem italics;
    private JMenu format;
    private JMenu bgc;
    private JMenu fontcol;
    private JMenu font;
    private JMenu fontStyle;
    private JMenu fontSize;
    private JMenuBar menuBar;
    private JMenuItem algerian;
    private JMenuItem yellowbgc;
    private JMenuItem redbgc;
    private JMenuItem pinkbgc;
    private JMenuItem blackbgc;
    private JMenuItem whitebgc;
    private JMenuItem orangebgc;
    private JMenuItem cyanbgc;
    private JMenuItem magentabgc;
    private JMenuItem graybgc;
    private JMenuItem lgraybgc;
    private JMenuItem cambria;
    private JMenuItem dgraybgc;
    private JMenuItem calibri;
    private JMenuItem verdana;
    private JMenuItem segoeui;
    private JMenuItem size8;
    private JMenuItem size9;
    private JMenuItem size10;
    private JMenuItem size11;
    private JMenuItem size12;
    private JMenuItem size14;
    private JMenuItem contact;
    private JMenuItem size16;
    private JMenuItem size18;
    private JMenuItem size20;
    private JMenuItem size24;
    private JMenuItem bluefc;
    private JMenuItem cyanfc;
    private JMenuItem magentafc;
    private JMenuItem yellowfc;
    private JMenuItem greenfc;
    private JMenuItem redfc;
    private JMenuItem about;
    private JMenuItem pinkfc;
    private JMenuItem orangefc;
    private JMenuItem blackfc;
    private JMenuItem grayfc;
    private JMenuItem lgrayfc;
    private JMenuItem dgrayfc;
    private JMenuItem whitefc;
    private JMenuItem size26;
    private JMenuItem size28;
    private JMenuItem size36;
    private JMenuItem greenbgc;
    private JMenuItem size48;
    private JMenuItem size72;
    private JMenuItem tahoma;
    private JMenuItem size13;
    private JMenuItem dateandtime;
    private JMenuItem bluebgc;
    private JScrollPane jScrollPane2;
    private JMenuItem newfile;
    private JMenuItem open;
    private JMenuItem paste;
    private JMenuItem plain;
    private JMenuItem print;
    private JMenuItem redomenu;
    private JMenuItem save;
    private JMenuItem saveas;
    private JTextArea textArea;
    private JMenuItem timesnewroman;
    private JMenuItem undomenu;
    private JMenuItem findRep;

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TextEditor(null).setVisible(true);
            }
        });
    }

    TextEditor(File file) {
        initComponents();
        setLocationRelativeTo(null);

        if(file == null)
            setTitle("Untitled");

        else
            filename = file;
        
        setResizable(true);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                int r = JOptionPane.showConfirmDialog(null,
                        "Do you want to exit the application? All unsaved changes will be lost.", "Exit",
                        JOptionPane.YES_NO_OPTION);
                if (r == JOptionPane.YES_OPTION)
                    dispose();
            }

            public void windowOpened(WindowEvent evt) {
                textArea.setFont(new Font(currentFontName, currentFontStyle, currentFontSize));
            }
        });
        textArea.getDocument().addUndoableEditListener((UndoableEditEvent e) -> { // For Undo and Redo
            undoManager.addEdit(e.getEdit());
        });
    }

    void initComponents() {
        jScrollPane2 = new JScrollPane();
        textArea = new JTextArea(); // The main text area
        // panel = new JPanel(); // The main panel
        menuBar = new JMenuBar(); // Menu Bar
        file = new JMenu("File"); // File Menu
        newfile = new JMenuItem("New File"); // sub-menu
        open = new JMenuItem("Open File"); // sub-menu
        save = new JMenuItem("Save"); // sub-menu
        saveas = new JMenuItem("Save As"); // sub-menu
        print = new JMenuItem("Print"); // sub-menu
        close = new JMenuItem("Close"); // sub-menu
        edit = new JMenu("Edit"); // Edit Menu
        copy = new JMenuItem("Copy"); // sub-menu
        cut = new JMenuItem("Cut"); // sub-menu
        paste = new JMenuItem("Paste"); // sub-menu
        undomenu = new JMenuItem("Undo"); // sub-menu
        redomenu = new JMenuItem("Redo"); // sub-menu
        findRep = new JMenuItem("Find/Replace"); // sub-menu
        dateandtime = new JMenuItem("Date/Time"); // sub-menu
        format = new JMenu("Format"); // Format Menu
        wrapTextCheckBox = new JCheckBoxMenuItem("Wrap Text"); // Wrap text sub-menu checkbox
        font = new JMenu("Font"); // sub-menu
        algerian = new JMenuItem("Algerian"); // sub-menu font
        arial = new JMenuItem("Arial"); // sub-menu font
        calibri = new JMenuItem("Calibri"); // sub-menu font
        cambria = new JMenuItem("Cambria"); // sub-menu font
        segoeui = new JMenuItem("Segoe UI"); // sub-menu font
        tahoma = new JMenuItem("Tahoma"); // sub-menu font
        timesnewroman = new JMenuItem("Times New Roman"); // sub-menu font Times New Roman
        verdana = new JMenuItem("Verdana"); // sub-menu font
        fontStyle = new JMenu("Font Style"); // sub-menu
        plain = new JMenuItem("Plain"); // sub-menu font style
        bold = new JMenuItem("Bold"); // sub-menu font style
        italics = new JMenuItem("Italic"); // sub-menu font style
        bolditalics = new JMenuItem("Bold Italic"); // sub-menu font style
        fontSize = new JMenu("Font Size"); // sub-menu
        size8 = new JMenuItem("8"); // Font Size
        size9 = new JMenuItem("9"); // Font Size
        size10 = new JMenuItem("10"); // Font Size
        size11 = new JMenuItem("11"); // Font Size
        size12 = new JMenuItem("12"); // Font Size
        size13 = new JMenuItem("13"); // Font Size
        size14 = new JMenuItem("14"); // Font Size
        size16 = new JMenuItem("16"); // Font Size
        size18 = new JMenuItem("18"); // Font Size
        size20 = new JMenuItem("20"); // Font Size
        size24 = new JMenuItem("24"); // Font Size
        size26 = new JMenuItem("26"); // Font Size
        size28 = new JMenuItem("28"); // Font Size
        size36 = new JMenuItem("36"); // Font Size
        size48 = new JMenuItem("48"); // Font Size
        size72 = new JMenuItem("72"); // Font Size
        bgc = new JMenu("Background Color"); // Background Color sub-menu
        bluebgc = new JMenuItem("Blue"); // Background color option
        cyanbgc = new JMenuItem("Cyan"); // Background color option
        magentabgc = new JMenuItem("Magenta"); // Background color option
        yellowbgc = new JMenuItem("Yellow"); // Background color option
        greenbgc = new JMenuItem("Green"); // Background color option
        redbgc = new JMenuItem("Red"); // Background color option
        pinkbgc = new JMenuItem("Pink"); // Background color option
        orangebgc = new JMenuItem("Orange"); // Background color option
        blackbgc = new JMenuItem("Black"); // Background color option
        graybgc = new JMenuItem("Gray"); // Background color option
        lgraybgc = new JMenuItem("Light Gray"); // Background color option
        dgraybgc = new JMenuItem("Dark Gray"); // Background color option
        whitebgc = new JMenuItem("White"); // Background color option
        fontcol = new JMenu("Font Color"); // Font Color sub-menu
        bluefc = new JMenuItem("Blue"); // Font color option
        cyanfc = new JMenuItem("Cyan"); // Font color option
        magentafc = new JMenuItem("Magenta"); // Font color option
        yellowfc = new JMenuItem("Yellow"); // Font color option
        greenfc = new JMenuItem("Green"); // Font color option
        redfc = new JMenuItem("Red"); // Font color option
        pinkfc = new JMenuItem("Pink"); // Font color option
        orangefc = new JMenuItem("Orange"); // Font color option
        blackfc = new JMenuItem("Black"); // Font color option
        grayfc = new JMenuItem("Gray"); // Font color option
        lgrayfc = new JMenuItem("Light Gray"); // Font color option
        dgrayfc = new JMenuItem("Dark Gray"); // Font color option
        whitefc = new JMenuItem("White"); // Font color option
        help = new JMenu("Help"); // Help Menu
        contact = new JMenuItem("Contact"); // Contact Me sub-menu
        about = new JMenuItem("About"); // About sub-menu

        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(format);
        menuBar.add(help);
        setJMenuBar(menuBar);

        file.add(newfile);
        file.add(open);
        file.add(save);
        file.add(saveas);
        file.add(print);
        file.add(close);

        edit.add(copy);
        edit.add(cut);
        edit.add(paste);
        edit.add(undomenu);
        edit.add(redomenu);
        edit.add(findRep);
        edit.add(dateandtime);

        format.add(wrapTextCheckBox);
        format.add(font);
        format.add(fontStyle);
        format.add(fontSize);
        format.add(fontcol);
        format.add(bgc);

        help.add(about);
        help.add(contact);

        fontSize.add(size8);
        fontSize.add(size9);
        fontSize.add(size10);
        fontSize.add(size11);
        fontSize.add(size12);
        fontSize.add(size13);
        fontSize.add(size14);
        fontSize.add(size16);
        fontSize.add(size18);
        fontSize.add(size20);
        fontSize.add(size24);
        fontSize.add(size26);
        fontSize.add(size28);
        fontSize.add(size36);
        fontSize.add(size48);
        fontSize.add(size72);

        plain.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        bold.setFont(new Font("Segoe UI", Font.BOLD, 12));
        italics.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        bolditalics.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));

        fontStyle.add(plain);
        fontStyle.add(bold);
        fontStyle.add(italics);
        fontStyle.add(bolditalics);

        arial.setFont(new Font("Arial", Font.PLAIN, 12));
        algerian.setFont(new Font("Algerian", Font.PLAIN, 12));
        calibri.setFont(new Font("Calibri", Font.PLAIN, 12));
        cambria.setFont(new Font("Cambria", Font.PLAIN, 12));
        segoeui.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tahoma.setFont(new Font("Tahoma", Font.PLAIN, 12));
        timesnewroman.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        verdana.setFont(new Font("Verdana", Font.PLAIN, 12));

        font.add(algerian);
        font.add(arial);
        font.add(calibri);
        font.add(cambria);
        font.add(segoeui);
        font.add(tahoma);
        font.add(timesnewroman);
        font.add(verdana);

        fontcol.add(bluefc);
        fontcol.add(cyanfc);
        fontcol.add(magentafc);
        fontcol.add(yellowfc);
        fontcol.add(greenfc);
        fontcol.add(redfc);
        fontcol.add(pinkfc);
        fontcol.add(orangefc);
        fontcol.add(blackfc);
        fontcol.add(grayfc);
        fontcol.add(lgrayfc);
        fontcol.add(dgrayfc);
        fontcol.add(whitefc);

        bgc.add(bluebgc);
        bgc.add(cyanbgc);
        bgc.add(magentabgc);
        bgc.add(yellowbgc);
        bgc.add(greenbgc);
        bgc.add(redbgc);
        bgc.add(pinkbgc);
        bgc.add(orangebgc);
        bgc.add(blackbgc);
        bgc.add(graybgc);
        bgc.add(lgraybgc);
        bgc.add(dgraybgc);
        bgc.add(whitebgc);

        setShortCutKeys();
        setFileMenuActionListeners();
        setEditMenuActionListeners();
        setFormatMenuActionListeners();
        setHelpMenuActionListeners();

        textArea.setColumns(20);
        textArea.setRows(5);
        textArea.setBorder(null);
        textArea.setMargin(new Insets(0, 0, 0, 0));
        jScrollPane2.setViewportView(textArea);

        setJMenuBar(menuBar); // Set the menu bar
        add(jScrollPane2); // Add the JScrollPane to the JFrame

        pack();
    }

    void setShortCutKeys() {
        newfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));        
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
        undomenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
        redomenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK));
        textArea.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK), "none");
        findRep.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK));
    }

    void newFileActionPerformed(ActionEvent evt) {
        int YesOrNo = JOptionPane.showConfirmDialog(null, "Do you want to create a new file?", "New File",
                JOptionPane.YES_NO_OPTION);
        if (YesOrNo == 0) {
            new TextEditor(null).setVisible(true);
        }
    }

    void openFileActionPerformed(ActionEvent evt) {
        int YesOrNo = JOptionPane.showConfirmDialog(null,
                "Do you want to open another file?\nThe current text will be overwritten.", "New File",
                JOptionPane.YES_NO_OPTION);
        if (YesOrNo == 1) {
            return;
        }

        JFileChooser j = new JFileChooser();
        int r = j.showOpenDialog(null);
        if (r == JFileChooser.APPROVE_OPTION) {
            File fi = new File(j.getSelectedFile().getAbsolutePath());
            filename = fi;

            try {
                FileReader fr = new FileReader(fi);
                BufferedReader br = new BufferedReader(fr);
                textArea.read(br, null);
                br.close();
                textArea.requestFocus();
                this.setTitle(filename.getName());
            } catch (Exception ex) {
                Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    void saveActionPerformed(ActionEvent evt) {
        if (filename == null)
            saveasActionPerformed(evt);
        else {
            try {
                // Create a file writer
                FileWriter wr = new FileWriter(filename, false);

                // Create buffered writer to write
                BufferedWriter w = new BufferedWriter(wr);
                textArea.write(w);

                w.flush();
                w.close();
            } catch (IOException ex) {
                Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    void saveasActionPerformed(ActionEvent evt) {
        JFileChooser j = new JFileChooser();
        int r = j.showSaveDialog(null);
        if (r == JFileChooser.APPROVE_OPTION) {

            // Set the label to the path of the selected directory
            File fi = new File(j.getSelectedFile().getAbsolutePath());
            filename = fi;
            try {
                // Create a file writer
                FileWriter wr = new FileWriter(fi, false);

                // Create buffered writer to write
                BufferedWriter w = new BufferedWriter(wr);
                textArea.write(w);

                w.flush();
                w.close();
                this.setTitle(filename.getName());
            } catch (IOException ex) {
                Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    void printActionPerformed(ActionEvent evt)
    {
        try {

            textArea.print();
        } catch (PrinterException ex) {
            Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void closeActionPerformed(ActionEvent evt)
    {
        int r = JOptionPane.showConfirmDialog(null,
                        "Do you want to exit the application? All unsaved changes will be lost.", "Exit",
                        JOptionPane.YES_NO_OPTION);
                if (r == JOptionPane.YES_OPTION)
                    dispose();
    }

    void setFileMenuActionListeners() {
        // Creates a new file
        newfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                newFileActionPerformed(evt);
            }
        });

        // Opens an existing file
        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                openFileActionPerformed(evt);
            }
        });

        // save file
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        // Save file as
        saveas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                saveasActionPerformed(evt);
            }
        });

        // Print file
        print.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                printActionPerformed(evt);
            }
        });

        // Close file
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });

    }

    void setEditMenuActionListeners() {
        // copy button
        copy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                textArea.copy();
            }
        });

        // cut button
        cut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                textArea.cut();
            }
        });

        // paste button
        paste.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                textArea.paste();
            }
        });

        // undo menu
        undomenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (undoManager.canUndo())
                    undoManager.undo();
            }
        });

        // redo menu
        redomenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (undoManager.canRedo())
                    undoManager.redo();
            }
        });

        // date time insertion
        dateandtime.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy h:mm a");
                LocalDateTime now = LocalDateTime.now();
                textArea.insert(dtf.format(now), textArea.getCaretPosition());
            }
        });

        // find and replace frame
        findRep.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                findRepActionPerformed(evt);
            }
        });
    }

    void findRepActionPerformed(ActionEvent evt)
    {
        findrepFrame = new JFrame("Find and Replace");
        findrepFrame.setSize(470, 200);
        findrepFrame.setVisible(true);
        findrepFrame.setResizable(false);
        findrepFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        findrepFrame.setLayout(null);
        findrepFrame.setLocationRelativeTo(this);
        JCheckBox matchCase = new JCheckBox("Match case");
        JLabel findBoxLabel = new JLabel("Find: ");
        JLabel replaceBoxLabel = new JLabel("Replace: ");
        JButton findButton = new JButton("Find next");
        JButton replaceButton = new JButton("Replace");
        JButton replaceAllButton = new JButton("Replace all");
        JTextField findTextField = new JTextField();
        JTextField replaceTextField = new JTextField();

        findBoxLabel.setBounds(20, 20, 100, 25); // x, y, width, height
        findTextField.setBounds(120, 20, 315, 25); // x, y, width, height

        replaceBoxLabel.setBounds(20, 60, 100, 25); // x, y, width, height
        replaceTextField.setBounds(120, 60, 315, 25); // x, y, width, height

        matchCase.setBounds(20, 100, 100, 25); // x, y, width, height
        
        // Set bounds for buttons to fit in frame width
        findButton.setBounds(130, 100, 90, 25); // x, y, width, height
        replaceButton.setBounds(230, 100, 90, 25); // x, y, width, height
        replaceAllButton.setBounds(330, 100, 97, 25); // x, y, width, height


        findrepFrame.add(findBoxLabel);
        findrepFrame.add(findTextField);
        findrepFrame.add(replaceBoxLabel);
        findrepFrame.add(replaceTextField);
        findrepFrame.add(findButton);
        findrepFrame.add(replaceButton);
        findrepFrame.add(matchCase);
        findrepFrame.add(replaceAllButton);

        findButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                findButtonActionPerformed(evt, findTextField, matchCase);
            }
        });

        replaceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                replaceButtonActionPerformed(evt, findTextField, replaceTextField, matchCase);
            }
        });

        replaceAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                replaceAllButtonActionPerformed(evt, findTextField, replaceTextField, matchCase);
            }
        });
    }

    void findButtonActionPerformed(ActionEvent evt, JTextField findTextField, JCheckBox matchCase)
    {
        String text = textArea.getText();
        String searchString = findTextField.getText();
    
        if (text.length() == 0 || searchString.length() == 0) {
            JOptionPane.showMessageDialog(null, "Nothing to search");
            return;
        }
    
        // Reset search if new search string is entered
        if (z == 0 || !searchString.equals(s)) {
            z = 1;  // Indicate that search has begun
            n = 0;  // Reset starting position
            s = searchString;  // Store current search string for next search comparison
        }
    
        boolean match = matchCase.isSelected();
        if (!match) {
            text = text.toLowerCase();
            searchString = searchString.toLowerCase();
        }
    
        // Check if the search string is found in text
        if (!text.contains(searchString)) {
            JOptionPane.showMessageDialog(null, "Search key not found");
            return;
        }
    
        // Find the next occurrence
        int start = text.indexOf(searchString, n);
        if (start >= 0) {
            textArea.requestFocus();
            textArea.select(start, start + searchString.length());
            n = start + searchString.length(); // Update position for next search
        } else {
            JOptionPane.showMessageDialog(null, "No more occurrences found");
            n = 0; // Reset position to start over if needed
        }
    }

    void replaceButtonActionPerformed(ActionEvent evt, JTextField findTextField, JTextField replaceTextField, JCheckBox matchCaseCheckBox) {
    String replacementText = replaceTextField.getText();
    String searchText = findTextField.getText();
    String content = textArea.getText();
    
    // Check if there's text in both the search and text area fields
    if (searchText.length() == 0 || content.length() == 0) {
        JOptionPane.showMessageDialog(null, "Nothing to search and replace");
        return;
    }
    
    // Determine if case-sensitive or case-insensitive matching is needed
    boolean matchCase = matchCaseCheckBox.isSelected();
    if (!matchCase) {
        // Convert both text and search term to lowercase for case-insensitive search
        content = content.toLowerCase();
        searchText = searchText.toLowerCase();
    }
    
    // Check if the text area contains the search text
    if (content.contains(searchText)) {
        // Find the current selection, then replace if it matches
        int start = textArea.getSelectionStart();
        int end = textArea.getSelectionEnd();
        String selectedText = textArea.getSelectedText();
        
        // Check if selected text matches the search term based on case sensitivity
        if (selectedText != null) {
            boolean isMatch = matchCase ? selectedText.equals(searchText) : selectedText.equalsIgnoreCase(searchText);

            if (isMatch) {
                // Replace the selected text with replacement text
                textArea.replaceRange(replacementText, start, end);
            } else {
                JOptionPane.showMessageDialog(null, "Selected text does not match the search term.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nothing to search and replace");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Search key not found");
    }
}
    
    void replaceAllButtonActionPerformed(ActionEvent evt, JTextField findTextField, JTextField replaceTextField, JCheckBox matchCase) {
        String replacementText = replaceTextField.getText();
        String searchText = findTextField.getText();
        String content = textArea.getText();

        if (searchText.length() > 0 && content.length() > 0) {
            // Determine case-sensitivity
            boolean match = matchCase.isSelected();
            if (!match) {
                content = content.toLowerCase();
                searchText = searchText.toLowerCase();
            }

            // Check if the search term exists in the text
            if (!content.contains(searchText)) {
                JOptionPane.showMessageDialog(null, "Search key not found");
                return;
            }

            // Start replacing all occurrences
            int start = 0;
            int index = content.indexOf(searchText, start);

            while (index >= 0) {
                textArea.requestFocus();
                textArea.select(index, index + searchText.length());
                textArea.replaceRange(replacementText, index, index + searchText.length());

                // Update content and find the next occurrence
                content = textArea.getText();
                if (!match) {
                    content = content.toLowerCase(); // Update case-lowered content
                }
                start = index + replacementText.length(); // Move past the replaced text
                index = content.indexOf(searchText, start);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nothing to search and replace");
        }

    }

    void setFormatMenuActionListeners() {

        // Setting the line wrap
        wrapTextCheckBox.addActionListener(e -> {
            // Toggle line wrap based on checkbox state
            textArea.setLineWrap(wrapTextCheckBox.isSelected());
            textArea.setWrapStyleWord(wrapTextCheckBox.isSelected());
        });

        // Setting font name for each menu item in the font menu
        for (int i = 0; i < font.getItemCount(); i++) {
            JMenuItem menuItem = font.getItem(i); // Get the submenu item
            if (menuItem != null) {
                menuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        currentFontName = menuItem.getText();
                        textArea.setFont(new Font(currentFontName, currentFontStyle, currentFontSize));
                    }
                });
            }
        }

        // Setting font style for each menu item in the font style menu
        for (int i = 0; i < fontStyle.getItemCount(); i++) {
            JMenuItem menuItem = fontStyle.getItem(i); // Get the submenu item
            if (menuItem != null) {
                menuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Reset current font style based on selected style
                        if (menuItem == plain) {
                            currentFontStyle = Font.PLAIN;
                        } else if (menuItem == bold) {
                            currentFontStyle = Font.BOLD;
                        } else if (menuItem == italics) {
                            currentFontStyle = Font.ITALIC;
                        } else if (menuItem == bolditalics) {
                            currentFontStyle = Font.BOLD | Font.ITALIC;
                        }

                        textArea.setFont(new Font(currentFontName, currentFontStyle, currentFontSize));
                    }
                });
            }
        }

        // Setting font size for each menu item in the font menu
        for (int i = 0; i < fontSize.getItemCount(); i++) {
            JMenuItem menuItem = fontSize.getItem(i); // Get the submenu item
            if (menuItem != null) {
                menuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        currentFontSize = Integer.parseInt(menuItem.getText());
                        textArea.setFont(new Font(currentFontName, currentFontStyle, currentFontSize));
                    }
                });
            }
        }

        // Map to associate color names with Color objects for font and background
        // colors
        Map<String, Color> colorMap = new HashMap<>();
        colorMap.put("BLUE", Color.BLUE);
        colorMap.put("CYAN", Color.CYAN);
        colorMap.put("MAGENTA", Color.MAGENTA);
        colorMap.put("YELLOW", Color.YELLOW);
        colorMap.put("GREEN", Color.GREEN);
        colorMap.put("RED", Color.RED);
        colorMap.put("PINK", Color.PINK);
        colorMap.put("ORANGE", Color.ORANGE);
        colorMap.put("BLACK", Color.BLACK);
        colorMap.put("GRAY", Color.GRAY);
        colorMap.put("LIGHT GRAY", Color.LIGHT_GRAY);
        colorMap.put("DARK GRAY", Color.DARK_GRAY);
        colorMap.put("WHITE", Color.WHITE);

        for (int i = 0; i < fontcol.getItemCount(); i++) {
            JMenuItem menuItem = fontcol.getItem(i); // Get the submenu item
            if (menuItem != null) {
                menuItem.addActionListener(e -> {
                    // Get the color name in uppercase
                    String colorName = menuItem.getText().toUpperCase();
                    // Set the font color using the map
                    Color color = colorMap.get(colorName);
                    if (color != null) {
                        textArea.setForeground(color);
                    }
                });
            }
        }

        for (int i = 0; i < bgc.getItemCount(); i++) {
            JMenuItem menuItem = bgc.getItem(i); // Get the submenu item
            if (menuItem != null) {
                menuItem.addActionListener(e -> {
                    // Get the background color name in uppercase
                    String bgColorName = menuItem.getText().toUpperCase();
                    // Set the background color using the map
                    Color bgColor = colorMap.get(bgColorName);
                    if (bgColor != null) {
                        textArea.setBackground(bgColor);
                    }
                });
            }
        }

    }

    void setHelpMenuActionListeners() {
        about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                JOptionPane.showMessageDialog(null,
                        "Text-Editor Version: 2.0.2\nCreated by Soumyadeep Banerjee\nBSc. (Hons) Computer Science, 1st Year");
            }
        });

        contact.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    Desktop.getDesktop().browse(new URL("https://x.com/SoumyadeepB2001").toURI());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Browser not found");
                }
            }
        });
    }

}
