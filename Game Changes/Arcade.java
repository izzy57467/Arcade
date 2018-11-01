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

public class Arcade
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
		
	try
	{
		startUp= ImageIO.read(new File("abc.png"));
		startUp2= ImageIO.read(new File("START-UP.png"));
		BeginScreen = ImageIO.read(new File("BeginScreen.png"));
		
		/*
		Muzak = AudioSystem.getClip();
		Muzak.open(AudioSystem.getAudioInputStream(new File("music.wav")));
		Muzak.loop(Clip.LOOP_CONTINUOUSLY);
		Muzak.start();
		*/
	
	}
	catch(Exception e){}
	
	}
	
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.WHITE);
		/*
		g.fillRect(0,0,1440,860);
		Color myNewBlue = new Color	(135,206,250); 
		g.setColor(myNewBlue);
		g.fillRect(0,0,2000,700);
		Color myNewGreen = new Color(154,205,80); 
		g.setColor(myNewGreen);
		g.fillRect(0,700,2000,1000);
		*/
		
		paintStart(g);
		
		if(isClicked == true)
		{
			paintBegin(g);
			g.fillRect(368,395,166,16);
			g.fillRect(356,411,166,33);
			g.fillRect(344,444,166,49);
			g.fillRect(332,493,166,25);
			
			g.fillRect(605,395,178,16);
			g.fillRect(592,411,179,33);
			g.fillRect(580,444,179,49);
			g.fillRect(570,493,178,25);
			
			g.fillRect(854,395,178,16);
			g.fillRect(842,411,179,33);
			g.fillRect(829,444,180,49);
			g.fillRect(819,493,178,25);
		}
		afterThought = true;
	//Space Invaders
		if(afterThought == true)
		if (mousex>368&&mousey>395)
			if(mousex<534&&mousey<411)
				{
					g.fillRect(0,0,1440,860);
				}
				
		if (mousex>356&&mousey>411)
			if(mousex<521&&mousey<443)
				{
					g.fillRect(0,0,1440,860);
				}
		
		if (mousex>344&&mousey>444)
			if(mousex<509&&mousey<491)
				{
					g.fillRect(0,0,1440,860);
				}
				
		if (mousex>332&&mousey>493)
			if(mousex<497&&mousey<516)
				{
					g.fillRect(0,0,1440,860);
				}
		
	//Mortal Kombat 
		if(afterThought == true)
		if (mousex>605&&mousey>395)
			if(mousex<782&&mousey<410)
				{
					g.fillRect(0,0,1440,860);
				}
				
		if (mousex>592&&mousey>411)
			if(mousex<770&&mousey<443)
				{
					g.fillRect(0,0,1440,860);
				}
		
		if (mousex>580&&mousey>444)
			if(mousex<758&&mousey<492)
				{
					g.fillRect(0,0,1440,860);
				}
				
		if (mousex>570&&mousey>493)
			if(mousex<747&&mousey<517)
				{
					g.fillRect(0,0,1440,860);
				}
				
	//Donkey Kong
		if(afterThought == true)
		if (mousex>854&&mousey>395)
			if(mousex<1031&&mousey<410)
				{
					DonkeyKong game3 = new DonkeyKong
				}
				
		if (mousex>842&&mousey>411)
			if(mousex<1020&&mousey<443)
				{
					g.fillRect(0,0,1440,860);
				}
		
		if (mousex>829&&mousey>444)
			if(mousex<1008&&mousey<492)
				{
					g.fillRect(0,0,1440,860);
				}
				
		if (mousex>819&&mousey>493)
			if(mousex<996&&mousey<517)
				{
					g.fillRect(0,0,1440,860);
				}
		
	}
	
	public void paintBegin(Graphics g)
	{
		g.drawImage(BeginScreen,0,0,1424,822,null);
	}
	
	public void paintStart(Graphics g)
	{
		if(x%30<15)
			g.drawImage(startUp, 0,0,1424,822,null); //start up screen (image, startx,starty,length,width,null)
		else
			g.drawImage(startUp2, 0,0,1424,822,null);
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