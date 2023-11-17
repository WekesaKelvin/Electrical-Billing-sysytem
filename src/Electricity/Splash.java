package Electricity;

import java.awt.*;
import javax.swing.*;

public class Splash {
    public static void main(String[] args){
        fframe frame1 = new fframe();
        frame1.setVisible(true);
        int i;
        int x=1;
        for(i=2; i<=600; i+=4, x+=1){
            frame1.setLocation(800 - ((i+x)/2), 500 - (i/2));
            frame1.setSize(i+x,i);
            try{
                Thread.sleep(10);
            }catch(Exception e){}
        }
        
    }
}
class fframe extends JFrame implements Runnable{
    Thread thread;
    fframe(){
        super("Electricity Billing System");
        setLayout(new FlowLayout());
        ImageIcon c1 = new ImageIcon(ClassLoader.getSystemResource("icon/elect.jpg"));
        Image i1 = c1.getImage().getScaledInstance(730, 550,Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i1);
        
        
        JLabel l1 = new JLabel(i2);
        add(l1);
        thread = new Thread(this);
        thread.start();
    }
    public void run(){
        try{
            Thread.sleep(7000);
            this.setVisible(false);
            
            Login l = new Login();
            l.setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
