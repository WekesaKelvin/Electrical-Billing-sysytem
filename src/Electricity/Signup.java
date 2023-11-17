 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Electricity;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class Signup extends JFrame implements ActionListener{
    JPanel panel1;
    JTextField text1, text2, text3, text4;
    Choice choice;
    JButton button1, button2;
    Signup(){
        setBounds(600, 250, 700, 400);
        
        panel1 = new JPanel();
        panel1.setBounds(30, 30, 650, 300);
        panel1.setLayout( null);
        panel1.setBackground(Color.WHITE);
        panel1.setForeground(new Color(34, 139, 34));
        panel1.setBorder(new TitledBorder(new LineBorder(new Color(173, 216, 230), 2), "Create-Account", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(173, 216, 230)));
        add(panel1);
        
        JLabel l1 = new JLabel("Username");
        l1.setForeground(Color.DARK_GRAY);
        l1.setFont(new Font("Tahoma", Font.BOLD, 14));
        l1.setBounds(100, 50, 100, 20);
        panel1.add(l1);
        
        text1 = new JTextField();
        text1.setBounds(260, 50, 150, 20);
        panel1.add(text1);
        
        JLabel l2 = new JLabel("Name");
        l2.setForeground(Color.DARK_GRAY);
        l2.setFont(new Font("Tahoma", Font.BOLD, 14));
        l2.setBounds(100, 90, 100, 20);
        panel1.add(l2);
        
        text2 = new JTextField();
        text2.setBounds(260, 90, 150, 20);
        panel1.add(text2);
        
        
        JLabel l3 = new JLabel("Password");
        l3.setForeground(Color.DARK_GRAY);
        l3.setFont(new Font("Tahoma", Font.BOLD, 14));
        l3.setBounds(100, 130, 100, 20);
        panel1.add(l3);
        
        text3 = new JTextField();
        text3.setBounds(260, 130, 150, 20);
        panel1.add(text3);
        
        
        JLabel l4 = new JLabel("Create Account As");
        l4.setForeground(Color.DARK_GRAY);
        l4.setFont(new Font("Tahoma", Font.BOLD, 14));
        l4.setBounds(100, 170, 140, 20);
        panel1.add(l4);
        
        
        JLabel l5 = new JLabel("Meter Number");
        l5.setForeground(Color.DARK_GRAY);
        l5.setFont(new Font("Tahoma", Font.BOLD, 14));
        l5.setBounds(100, 210, 100, 20);
        l5.setVisible(false);
        panel1.add(l5);
        
        text4 = new JTextField();
        text4.setBounds(260, 210, 150, 20);
        text4.setVisible(false);
        panel1.add(text4);
        
        choice = new Choice();
        choice.add("Admin");
        choice.add("Customer");
        choice.setBounds(260, 170, 150, 20);
        panel1.add(choice);
        
        choice.addItemListener(new ItemListener(){
           public void itemStateChanged(ItemEvent ae){
               String user = choice.getSelectedItem();
               if(user.equals("Customer")){
                   l5.setVisible(true);
                   text4.setVisible(true);
               }else{
                   l5.setVisible(false);
                   text4.setVisible(false);
               }
           } 
        });
        
        
        button1 = new JButton("Create");
        button1.setBackground(Color.BLACK);
        button1.setForeground(Color.WHITE);
        button1.setBounds(140, 290, 120, 30);
        button1.addActionListener(this);
        panel1.add(button1);
        
        button2 = new JButton("Back");
        button2.setBackground(Color.BLACK);
        button2.setForeground(Color.WHITE);
        button2.setBounds(300, 290, 120, 30);
        button2.addActionListener(this);
        panel1.add(button2);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l6 = new JLabel(i3);
        l6.setBounds(450, 30, 250, 250);
        panel1.add(l6);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == button1){
            String username = text1.getText();
            String name = text2.getText();
            String password = text3.getText();
            String user = choice.getSelectedItem();
            String meter = text4.getText();
            try{
                Conn c = new Conn();
                String str;
                if(user.equals("Admin")){
                    str = "Insert into login values('"+meter+"', '"+username+"', '"+name+"', '"+password+"', '"+user+"')";
                }else{
                    str = "Update login set username = '"+username+"', name = '"+name+"', password = '"+password+"', user = '"+user+"' where meter_no = '"+text4.getText()+"'";
                }
                
                c.s.executeUpdate(str);
                JOptionPane.showMessageDialog(null, "Account Created Successfully");
                this.setVisible(false);
                new Login().setVisible(true);
            }catch(Exception e){
                
            }
        } else if(ae.getSource()== button2){
            this.setVisible(false);
            new Login().setVisible(true);
        }
    }
    
    public static void main(String[] args){
        new Signup().setVisible(true);
    }
}
