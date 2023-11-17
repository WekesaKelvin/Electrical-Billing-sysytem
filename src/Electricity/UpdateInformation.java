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

public class UpdateInformation extends JFrame implements ActionListener{
    JTextField text1, text2, text3, text4, text5;
    JLabel label1, label2;
    JButton b1, b2;
    String meter;
    UpdateInformation(String meter){
        this.meter = meter;
        
        setBounds(500, 220, 1050, 450);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        
        JLabel title = new JLabel("UPDATE CUSTOMER INFORMATION");
        title.setBounds(110, 0, 400, 30);
        title.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(title);
        
        JLabel l1 = new JLabel("Name");
        l1.setBounds(30, 70, 100, 20);
        add(l1);
        
        label1 = new JLabel();
        label1.setBounds(230, 70, 200, 20);
        add(label1);
        
        JLabel l2 = new JLabel("Meter Number");
        l2.setBounds(30, 110, 100, 20);
        add(l2);
        
        label2 = new JLabel();
        label2.setBounds(230, 110, 200, 20);
        add(label2);
        
        JLabel l3 = new JLabel("Address");
        l3.setBounds(30, 150, 100, 20);
        add(l3);
        
        text1 = new JTextField();
        text1.setBounds(230, 150, 200, 20);
        add(text1);
        
        JLabel l4 = new JLabel("City");
        l4.setBounds(30, 190, 100, 20);
        add(l4);
        
        text2 = new JTextField();
        text2.setBounds(230, 190, 200, 20);
        add(text2);
        
        JLabel l5 = new JLabel("State");
        l5.setBounds(30, 230, 100, 20);
        add(l5);
        
        text3 = new JTextField();
        text3.setBounds(230, 230, 200, 20);
        add(text3);
        
        JLabel l6 = new JLabel("Email");
        l6.setBounds(30, 270, 100, 20);
        add(l6);
        
        text4 = new JTextField();
        text4.setBounds(230, 270, 200, 20);
        add(text4);
        
        JLabel l7 = new JLabel("Phone");
        l7.setBounds(30, 310, 100, 20);
        add(l7);
        
        text5 = new JTextField();
        text5.setBounds(230, 310, 200, 20);
        add(text5);
        
        b1 = new JButton("Update");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setBounds(70, 360, 100, 25);
        b1.addActionListener(this);
        add(b1);
        
        b2 = new JButton("Back");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.setBounds(230, 360, 100, 25);
        b2.addActionListener(this);
        add(b2);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter = '"+meter+"'");
            while(rs.next()){
                label1.setText(rs.getString(1));
                label2.setText(rs.getString(2));
                text1.setText(rs.getString(3));
                text2.setText(rs.getString(4));
                text3.setText(rs.getString(5));
                text4.setText(rs.getString(6));
                text5.setText(rs.getString(7));
                
            }
        }catch(Exception e){}
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/update.jpg"));
        Image i2  = i1.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l8  = new JLabel(i3);
        l8.setBounds(550, 50, 400, 300);
        add(l8);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            String string1 = text1.getText();
            String string2 = text2.getText();
            String string3 = text3.getText();
            String string4 = text4.getText();
            String string5 = text5.getText();
            
            try{
                Conn c = new Conn();
                c.s.executeUpdate("update customer set address = '"+string1+"', city = '"+string2+"', state = '"+string3+"', email = '"+string4+"', phone = '"+string5+"' where meter = '"+meter+"'");
                JOptionPane.showMessageDialog(null, "Details Updated Successfully");
                this.setVisible(false);
                
            }catch(Exception e){}
            
        }else if(ae.getSource() == b2){
            this.setVisible(false);
        }
    }
    
    public static void main(String[] args){
        new UpdateInformation("").setVisible(true);
        
    }
}
