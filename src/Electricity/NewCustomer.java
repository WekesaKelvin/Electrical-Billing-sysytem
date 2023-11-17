
package Electricity;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class NewCustomer extends JFrame implements ActionListener{
    JLabel label1,label2,label3,label4,label5,label6,label7,label8,label9;
    JTextField text1,text2,text3,text4,text5,text6;
    JButton button1,button2;
    NewCustomer(){
        setLocation(600,200);
        setSize(700,500);
        
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(Color.WHITE);
        p.setBackground(new Color(173,216,230));
        
        JLabel title = new JLabel("New Customer");
        title.setBounds(180, 10, 200, 26);
        title.setFont(new Font("Tahoma", Font.PLAIN, 24));
        p.add(title);
        
        label1 = new JLabel("Customer Name");
        label1.setBounds(100, 80, 100, 20);
        
        text1 = new JTextField();
        text1.setBounds(240, 80, 200, 20);
        p.add(label1);
        p.add(text1);
        label2 = new JLabel("Meter No.");
        label2.setBounds(100, 120, 100, 20);
        label9 = new JLabel();
        label9.setBounds(240, 120, 200, 20);
        p.add(label2);
        p.add(label9);
        label3 = new JLabel("Address");
        label3.setBounds(100, 160, 100, 20);
        text2 = new JTextField();
        text2.setBounds(240, 160, 200, 20);
        p.add(label3);
        p.add(text2);
        label5 = new JLabel("City");
        label5.setBounds(100, 200, 100, 20);
        text4 = new JTextField();
        text4.setBounds(240, 200, 200, 20);
        p.add(label5);
        p.add(text4);
        label4 = new JLabel("State");
        label4.setBounds(100, 240, 100, 20);
        text3 = new JTextField();
        text3.setBounds(240, 240, 200, 20);
        p.add(label4);
        p.add(text3);
        
        label6 = new JLabel("Email");
        label6.setBounds(100, 280, 100, 20);
        text5 = new JTextField();
        text5.setBounds(240, 280, 200, 20);
        p.add(label6);
        p.add(text5);
        label7 = new JLabel("Phone Number");
        label7.setBounds(100, 320, 100, 20);
        text6 = new JTextField();
        text6.setBounds(240, 320, 200, 20);
        p.add(label7);
        p.add(text6);
        
        button1 = new JButton("Next");
        button1.setBounds(120, 390, 100, 25);
        button2 = new JButton("Cancel");
        button2.setBounds(250, 390, 100, 25);
        
        button1.setBackground(Color.BLACK);
        button1.setForeground(Color.WHITE);
        
        button2.setBackground(Color.BLACK);
        button2.setForeground(Color.WHITE);
        
        p.add(button1);
        p.add(button2);
        setLayout(new BorderLayout());
        
        add(p,"Center");
        
        ImageIcon ic1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i3 = ic1.getImage().getScaledInstance(150, 300,Image.SCALE_DEFAULT);
        ImageIcon ic2 = new ImageIcon(i3);
        label8 = new JLabel(ic2);
        
        
        add(label8,"West");
        //for changing the color of the whole Frame
        getContentPane().setBackground(Color.WHITE);
        
        button1.addActionListener(this);
        button2.addActionListener(this);
        
        Random ran = new Random();
        long first = (ran.nextLong() % 1000000);
        label9.setText(""+Math.abs(first));
        
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == button1){
            String name = text1.getText();
            String meter = label9.getText();
            String address = text2.getText();
            String state = text3.getText();
            String city = text4.getText();
            String email = text5.getText();
            String phone = text6.getText();

            String q1 = "insert into customer values('"+name+"','"+meter+"','"+address+"','"+city+"','"+state+"','"+email+"','"+phone+"')";
            String q2 = "insert into login values('"+meter+"', '', '', '', '')";
            try{
                Conn c1 = new Conn();
                c1.s.executeUpdate(q1);
                c1.s.executeUpdate(q2);
                JOptionPane.showMessageDialog(null,"Customer Details Added Successfully");
                this.setVisible(false);
                new MeterInfo(meter).setVisible(true);

            }catch(Exception ex){
                 ex.printStackTrace();
            }
        }else if(ae.getSource() == button2){
                this.setVisible(false);
                }
    }
    
    
    public static void main(String[] args){
        new NewCustomer().setVisible(true);
    }
}
