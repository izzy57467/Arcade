import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.util.concurrent.TimeUnit;

public class DonkeyKong
{
	public static void main(String [] args)throws Exception
	{
		JFrame j = new JFrame();  //JFrame is the window; window is a depricated class
		MyPanelb m = new MyPanelb();
		j.setSize(m.getSize());
		j.add(m); //adds the panel to the frame so that the picture will be drawn
		      //use setContentPane() sometimes works better then just add b/c of greater efficiency.
	
		j.setVisible(true); //allows the frame to be shown.
	
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //makes the dialog box exit when you click the "x" button.
	}

}

class MyPanelb extends JPanel implements ActionListener,MouseListener, KeyListener//add mouseClicks
{
	Timer time;
	int x,y,y2;
	int add;
	Image startUp, startUp2, BeginScreen; 
	boolean isClicked = false, afterThought = false; //shows the next start screen
	Graphics g;
	int highScore; //get the High Score

	private int startX, startY;
	String mystery="Mystery";
	int left;
	int numclicks;
	int mousex;
	int mousey;
	
	MyPanelb()
	{
		time = new Timer(15, this); //sets delay to 15 millis and calls the actionPerformed of this class.
		setSize(1440, 860);
		setVisible(true); //it's like calling the repaint method.
		time.start();
		add=1;
		x=y=600;
		y2=0;
		
		startX = startY = 10;
		    left=0;
		setFocusable(true);
		addKeyListener(this);
		addMouseListener(this);
	
	}
	
	public void paintComponent(Graphics g)
	{
	///Donkey Kong game
	}
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_UP)
		mystery="UP";
		else if(e.getKeyCode()==KeyEvent.VK_DOWN)
		mystery="DOWN";
		else if(e.getKeyCode()==KeyEvent.VK_LEFT)
		mystery="LEFT";
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		mystery="RIGHT";
		repaint();
	}
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
	
	public void actionPerformed(ActionEvent e)
	{
	
		y+=add;
		if (y >=400)
		y=0;
		
		
		if (y%2==0)	
		y2+=add;
		if (y2>=500)
		y2=0;
		
		x+=add;
		
		//////////
	
		/*
		if (x >=1000)
		x=0;
		
		x+=add;
		y+=add;
		if(x==200 && y==200)
		add*=-1;
		if(x==10 && y==10)
		add*=-1;	
		*/	
	
	repaint();
	}
	public void mouseClicked(MouseEvent e)
	{
		numclicks++;
		System.out.println(numclicks);
		isClicked = true; //call another method to create another screen
		mousex=e.getX();
   		mousey=e.getY();
   		System.out.println(mousex+","+mousey);
		repaint();
	} 
	public void mouseEntered(MouseEvent e)
	{
		
	}
	public void mouseExited(MouseEvent e) 
	{
		
	}
	public void mousePressed(MouseEvent e)  
	{
		
	}
	public void mouseReleased(MouseEvent e)  
	{
		
	}
} 