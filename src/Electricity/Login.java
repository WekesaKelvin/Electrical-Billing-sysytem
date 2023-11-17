package Electricity;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    JLabel label1,label2,label3, label4;
    JTextField tf1;
    JPasswordField pf2;
    JButton button1,button2,button3;
    Choice choice;
    Login(){
        super("Login Page");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        
        label1 = new JLabel("Username");
        label1.setBounds(300, 20, 100, 20);
        add(label1);
        label2 = new JLabel("Password");
        label2.setBounds(300, 60, 100, 20);
        add(label2);
        
        tf1 = new JTextField(15);
        tf1.setBounds(400, 20, 150, 20);
        add(tf1);
        pf2 = new JPasswordField(15);
        pf2.setBounds(400, 60, 150, 20);
        add(pf2);
        
        label4 = new JLabel("Logging in as");
        label4.setBounds(300, 100, 100, 20);
        add(label4);
        
        choice = new Choice();
        choice.add("Admin");
        choice.add("Customer");
        choice.setBounds(400, 100, 150, 20);
        add(choice);
        
        ImageIcon ichoice = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image i1 = ichoice.getImage().getScaledInstance(16, 16,Image.SCALE_DEFAULT);
        button1 = new JButton("Login", new ImageIcon(i1));
        button1.setBounds(330, 160, 100, 20);
        add(button1);
        
        ImageIcon ic2 = new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
        Image i2 = ic2.getImage().getScaledInstance(16, 16,Image.SCALE_DEFAULT);
        button2 = new JButton("Cancel",new ImageIcon(i2));
        button2.setBounds(450, 160, 100, 20);
        add(button2);
        
        ImageIcon ic4 = new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image i4 = ic4.getImage().getScaledInstance(16, 16,Image.SCALE_DEFAULT);
        button3 = new JButton("Signup",new ImageIcon(i4));
        button3.setBounds(380, 200, 130, 20);
        add(button3);
        
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);


        ImageIcon ic3 = new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
        Image i3 = ic3.getImage().getScaledInstance(250, 250,Image.SCALE_DEFAULT);
        ImageIcon icc3 = new ImageIcon(i3);
        label3 = new JLabel(icc3);
        label3.setBounds(0, 0, 250, 250);
        add(label3);
        
        setLayout(new BorderLayout());
    
     
        setSize(640,300);
        setLocation(600,300);
        setVisible(true);
        

        
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == button1){
            try{        
                Conn c = new Conn();
                String a  = tf1.getText();
                String b  = pf2.getText();
                String user = choice.getSelectedItem();
                String q  = "select * from login where username = '"+a+"' and password = '"+b+"' and user = '"+user+"'";
                ResultSet rs = c.s.executeQuery(q);
                if(rs.next()){
                    String meter = rs.getString("meter_no");
                    new Project(meter, user).setVisible(true);
                    this.setVisible(false);

                }else{
                    JOptionPane.showMessageDialog(null, "Invalid login");
                    tf1.setText("");
                    pf2.setText("");
                }
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("error: "+e);
            }
        }else if(ae.getSource() == button2){
            this.setVisible(false);
        }else if(ae.getSource() == button3){
            this.setVisible(false);
            new Signup().setVisible(true);
            
        }
    }
    
    public static void main(String[] args){
        new Login().setVisible(true);
    }

    
}



