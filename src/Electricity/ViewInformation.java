/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Electricity;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class ViewInformation extends JFrame implements ActionListener{
    JButton button;
    ViewInformation(String meter){
        setBounds(600,250, 850, 650);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel title = new JLabel("VIEW CUSTOMER INFORMATION");
        title.setBounds(250, 0, 500, 40);
        title.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(title);
        
        JLabel label1 = new JLabel("Name");
        label1.setBounds(70, 80, 100, 20);
        add(label1);
        
        JLabel label2 = new JLabel();
        label2.setBounds(250, 80, 100, 20);
        add(label2);
        
        JLabel label3 = new JLabel("Meter Number");
        label3.setBounds(70, 140, 100, 20);
        add(label3);
        
        JLabel label4 = new JLabel();
        label4.setBounds(250, 140, 100, 20);
        add(label4);

        JLabel label5 = new JLabel("Address");
        label5.setBounds(70, 200, 100, 20);
        add(label5);

        JLabel label6 = new JLabel();
        label6.setBounds(250, 200, 100, 20);
        add(label6);

        JLabel label7 = new JLabel("City");
        label7.setBounds(70, 260, 100, 20);
        add(label7);

        JLabel label8 = new JLabel();
        label8.setBounds(250, 260, 100, 20);
        add(label8);

        JLabel label9 = new JLabel("State");
        label9.setBounds(500, 80, 100, 20);
        add(label9);

        JLabel label10 = new JLabel();
        label10.setBounds(650, 80, 100, 20);
        add(label10);

        JLabel label11 = new JLabel("Email");
        label11.setBounds(500, 140, 100, 20);
        add(label11);

        JLabel label12 = new JLabel();
        label12.setBounds(650, 140, 150, 20);
        add(label12);

        JLabel label13 = new JLabel("Phone");
        label13.setBounds(500, 200, 100, 20);
        add(label13);

        JLabel label14 = new JLabel();
        label14.setBounds(650, 200, 100, 20);
        add(label14);

        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter = '"+meter+"'");
            while(rs.next()){
                label2.setText(rs.getString(1));
                label4.setText(rs.getString(2));
                label6.setText(rs.getString(3));
                label8.setText(rs.getString(4));
                label10.setText(rs.getString(5));
                label12.setText(rs.getString(6));
                label14.setText(rs.getString(7));
                
            }
        }catch(Exception e){}
        
        button = new JButton("Back");
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setBounds(350, 340, 100, 25);
        button.addActionListener(this);
        add(button);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/viewcustomer.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label15  = new JLabel(i3);
        label15.setBounds(20, 350, 600, 300);
        add(label15);
    }
    
    public void actionPerformed(ActionEvent ae){
        this.setVisible(false);
    }
    
    public static void main(String[] args){
        new ViewInformation("").setVisible(true);
    }
}
