package Electricity;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.Font;
import javax.swing.JFrame;

public class About extends JFrame implements ActionListener {

    String string;

    public About() {

        setLayout(null);
        JButton button = new JButton("Exit");
        add(button);
        button.setBounds(180, 430, 120, 20);
        button.addActionListener(this);

        Font f = new Font("RALEWAY", Font.BOLD, 180);
        setFont(f);

        string = "                               About Project          \n   "
                + "It mainly focuses on the calculation of units consumed during the "
                + "specified time and the money to be paid to electricity offices. "
                + "This will make the overall billing system more easily "
                + "accessible, comfortable and effective for consumers.\n\n"
                ;

        TextArea text = new TextArea(string, 10, 40, Scrollbar.VERTICAL);
        text.setEditable(false);
        text.setBounds(20, 100, 450, 300);

        add(text);

        Font font = new Font("RALEWAY", Font.BOLD, 16);
        text.setFont(font);

        JLabel label = new JLabel("About Project");
        add(label);
        label.setBounds(170, 10, 180, 80);
        label.setForeground(Color.red);

        Font font2 = new Font("RALEWAY", Font.BOLD, 20);
        label.setFont(font2);

        setBounds(700, 220, 500, 550);

        setLayout(null);
        setVisible(true);
        
    }

    public void actionPerformed(ActionEvent e) {
        dispose();
    }

    public static void main(String args[]) {
        new About().setVisible(true);
    }

}
