package Electricity;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Paytm extends JFrame implements ActionListener{
    String meter;
    JButton button1;
    Paytm(String meter){
        this.meter = meter;
        JEditorPane j = new JEditorPane();
        j.setEditable(false);   
        
        button1 = new JButton("Back");
        button1.setBackground(Color.BLACK);
        button1.setForeground(Color.WHITE);
        button1.setBounds(500, 20, 120, 25);
        button1.addActionListener(this);
        j.add(button1);

        try {
            j.setPage("https://www.torontohydro.com/for-home/ways-to-pay");
        }catch (Exception e) {
            j.setContentType("text/html");
            j.setText("<html>Could not load</html>");
        } 

        JScrollPane scrollPane = new JScrollPane(j);     
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(scrollPane);
        setPreferredSize(new Dimension(800,600));
        setSize(800,800);
        setLocation(250,120);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        this.setVisible(false);
        new PayBill(meter).setVisible(true);
    }
    public static void main(String[] args){
        new Paytm("").setVisible(true);
    }
}
