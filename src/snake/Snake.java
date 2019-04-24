package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Snake {
    public static void main(String[] args) {
        
        
        JFrame frame=new JFrame("Snake the greatest game in da world");
        frame.setBounds(200, 200, 820, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        
        KeyListener key=new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
               if(e.getKeyChar()=='w'){
                   if(!Panel.down){
                      Panel.down=false;
                      Panel. up=true;
                      Panel. right=false;
                      Panel. left=false;
                      frame.removeKeyListener(this);
                   }
                   }else if(e.getKeyChar()=='d'){
                       if(!Panel.left){
                      Panel. down=false;
                      Panel. up=false;
                      Panel. right=true;
                      Panel. left=false;
                      frame.removeKeyListener(this);
                       }
                   }else if(e.getKeyChar()=='a'){
                       if(!Panel.right){
                      Panel. down=false;
                      Panel. up=false;
                      Panel. right=false;
                      Panel. left=true;
                       frame.removeKeyListener(this);
                       }
                   }else if(e.getKeyChar()=='s'){
                       if(!Panel.up){
                      Panel. down=true;
                      Panel. up=false;
                      Panel. right=false;
                      Panel. left=false;
                   frame.removeKeyListener(this); 
                       } 
                   }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

           
           
    };
        
        
           Panel panel=new Panel(frame,key);
        
        
        frame.add(panel);
    }
}
     class Panel extends JPanel{
         ArrayList<Point> list=new ArrayList(); 
         private int x=0;
         private int y=0;
         public static boolean up=false,down=false,right=true,left=false;
          int boxx=40;
          int boxy=40;
          public int score=0;
         
          @Override
      public void paintComponent(Graphics f){
          super.paintComponent(f);
//          f.setColor(Color.white);
         
          f.drawRect(boxx, boxy, 40, 40);
          
           for(int n=0;n<list.size();n++){
               if(n==list.size()-1){
                 f.setColor(Color.DARK_GRAY);
               }else if(n==0){
                   f.setColor(Color.red);
               }else{
                f.setColor(Color.black);
               }
          f.fillRect(list.get(n).x,list.get(n).y, 40, 40);
              
          }
      }
      JLabel label;
          boolean puase=false;
        public Panel(JFrame frame, KeyListener key){
           list.add(new Point(x,y));
            label=new JLabel(""+score);

           this.add(label);
//             setBackground(Color.black);
             setBounds(0,0,820,400);
             Timer timer=new Timer();
             
             
                 TimerTask task=new TimerTask() {
                 @Override
                 public void run() {
                     if(!puase){
                      repaint();
                     frame.addKeyListener(key);
                  if(up){
                      y=y-40;
                  }
                  else if(down){
                      y=y+40;
                  }
                  else if(left){
                      x=x-40;
                  }
                  else if(right){
                      x=x+40;
                  }
                   if(x>760){
                      x=0;
                  }
                  if(x<0){
                      x=760;
                  }
                  if(y>320){
                      y=0;
                  }
                  if(y<0){
                      y=360;
                  }
                  
                  for(int f=0;f<list.size();f++){
                      if(x==list.get(f).x&&y==list.get(f).y){
                          puase=true;
                          System.out.println("loser");
                         KeyListener key=new KeyListener() {
                              @Override
                              public void keyTyped(KeyEvent e) {
                                  if(e.getKeyChar()=='r'){
                                 list.clear();
                                 score=0;
                                 list.add(new Point(0,0));
                                 puase=false;
                                  System.out.println("restarted");
                              }else if(e.getKeyChar()=='c'){
                               puase=false; 
                              }
                              }

                              @Override
                            public void keyPressed(KeyEvent e) {
                            }

                            @Override
                            public void keyReleased(KeyEvent e) {
                                }
                          };
                         frame.addKeyListener(key);
                      }
                  }
                  if(list.get(list.size()-1).x==boxx&&list.get(list.size()-1).y==boxy){
                      Random random = new Random();
                      boxx=random.nextInt(18)*40  ;
                      boxy=random.nextInt(9)*40  ;
                      score++;
                      list.add(new Point(x,y));
                      label.setText(score+"");
                  }
                  list.add(new Point(x,y));
                  list.remove(0);
                 }
                 

                 }
             };
             
              timer.schedule(task,100,100);

            

}
}