/*
 * Copyright (C) 2021 Ruslan Lopez Carro
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.javapro.quizadmin;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFrame;

/**
 * @author Ruslan Lopez Carro
 */
public class Adminstarter extends JFrame {

    public Adminstarter() {
        super("Quiz Admin");
        setSize(300, 120);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        add(mainPanel);

        JLabel label = new JLabel("¿Cargar archivo existente?");
        JButton yesButton = new JButton("Sí");
        yesButton.addActionListener(new ButtonPressed());
        JButton noButton = new JButton("No");
        noButton.addActionListener(new ButtonPressed());
        mainPanel.add(label, c);
        mainPanel.add(yesButton, c);
        mainPanel.add(noButton, c);

    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(QuizAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        Adminstarter mainWindow = new Adminstarter();
        mainWindow.setVisible(true);
    }
}

class ButtonPressed implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        //Button from event
        JButton button = (JButton) e.getSource();

        //get frame
        Window frame = SwingUtilities.windowForComponent((Component) e.getSource());

        if(button.getText().equals("Sí")){
            //Close dialog
            frame.setVisible(false);
            frame.dispose();

            QuizAdmin admin = new QuizAdmin();
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Text files", "txt");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                final File selectedFile = chooser.getSelectedFile();
                System.out.println("You chose to open this file: "
                        + selectedFile);
                try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))
                ) {
                    admin.parseQuestions(reader);
                    admin.setVisible(true);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }else{
            frame.setVisible(false);
            frame.dispose();
        }
    }
}
