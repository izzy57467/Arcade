import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.util.concurrent.TimeUnit;

public class StackAttack{
	
	public static void main(String...args) throws Exception
	{
		JFrame j = new JFrame();  //JFrame is the window; window is a depricated class
		MyPanel m = new MyPanel();
		j.setSize(1200, 706);

		j.add(m); //adds the panel to the frame so that the picture will be drawn
			      //use setContentPane() sometimes works better then just add b/c of greater efficiency.
		 
		j.setVisible(true); //allows the frame to be shown.
	//	file = new File("Trees.wav");//File must be .WAV, .AU, or .AIFF
	//	stream = AudioSystem.getAudioInputStream(file);
	//	music = AudioSystem.getClip();
	//	music.open(stream);
	//	music.start(); //Start the music //allows the frame to be shown.

		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //makes the dialog box exit when you click the "x" button.
	}
}

class MyPanel extends JPanel implements MouseListener, KeyListener// ActionListener, , MouseListener
{
	private Image imprt=null;
	private BufferedImage in=null;
	private BufferedImage ln=null;
	private BufferedImage kn=null;
	private BufferedImage bg=null;
	private BufferedImage tg=null;
	private BufferedImage te=null;
	private BufferedImage ifo=null;	
	private int siobhanx = 0;
	int numclicks;
	int numpressed;
	int mousex;
	int mousey;
	boolean moveho;

	
	
	MyPanel()
	{
	//	time = new Timer(15, this);
	//	time.start();
	//	setFocusable(true);
		setVisible(true); //it's like calling the repaint method.
	//	addKeyListener(this);
		addMouseListener(this);
	
	}
	
		public void paintComponent(Graphics g) 
			
	{	int x= 100;
		  super.paintComponent(g);  	
		 //background
		 Color blue = new Color(0,162,232);
		 Color red = new Color(87,191,130);
		 Color white = new Color(255,255,255);
		try
 		{
		BufferedImage title = new BufferedImage(230,230,BufferedImage.TYPE_INT_RGB);
				in=ImageIO.read(new File("Title.png"));
		BufferedImage logopic = new BufferedImage(130,130,BufferedImage.TYPE_INT_RGB); 
				ln=ImageIO.read(new File("Logopic.png"));
		BufferedImage startbutton = new BufferedImage(50,50,BufferedImage.TYPE_INT_RGB); 
				kn=ImageIO.read(new File("Start.png"));
	/////////////////////////////////////////////////////////////////////////////////
		BufferedImage test2 = new BufferedImage(50,50,BufferedImage.TYPE_INT_RGB); // added
				te=ImageIO.read(new File("HowTo.png"));	                              //
			BufferedImage infoscren = new BufferedImage(50,50,BufferedImage.TYPE_INT_RGB);//
				ifo=ImageIO.read(new File("HowToInfo.png"));	  			                  //      
	///////////////////////////////////////////////////////////////////////////////////		
		BufferedImage background = new BufferedImage(1200,800,BufferedImage.TYPE_INT_RGB);
				bg=ImageIO.read(new File("rug.png"));
		BufferedImage girl1 = new BufferedImage(1200,800,BufferedImage.TYPE_INT_RGB);
				tg=ImageIO.read(new File("siobhanhappy.png"));
		}
		catch(IOException e) {
			System.out.println("");
		}
		g.setColor(blue);
		g.fillRect(0,0,2000,2000);
		g.drawImage(in,145,150,null);
		g.drawImage(ln,170,20,null);
		g.setColor(red);
		g.fillRect(493,550,200,90);
		g.drawImage(kn,502,550,null);
		g.setColor(white);
		g.drawImage(te,1002,50,null);
		
			if (mousex>495&&mousey<637)
				if(mousex<689&&mousey>554)
			{
				g.drawImage(bg,0,0,null);
				g.drawImage(tg,375,200,null);
				g.drawImage(tg,375 + siobhanx,200,null);
				
			}
			if(mousex>1025&&mousey<134)
				if(mousex<1101&&mousey>58)
				{
					g.drawImage(ifo,310,45,null);
				}
		
		repaint();

	}
		public void keyTyped(KeyEvent e)
	{ 		
	
	}
	@Override
		public void keyPressed(KeyEvent e) 
	{
		/*int keyCode = e.getKeyCode();
			if(keyCode == e.VK_LEFT){
				shipx-=4;
				repaint();
			}
			if(keyCode == e.VK_RIGHT){
				shipx+=4;
				repaint();*/
			if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				moveho=true;
			}
			if(e.getKeyCode() == KeyEvent.VK_LEFT)
			{
				siobhanx-=4;
			}
	//		if(e.getKeyChar() == 'a')
	//	{
	//		numpressed=1;
	//		repaint();
	//	}
	//	if(e.getKeyChar() == 'd')
	//	{
	//		numpressed=2;
			repaint();
	//	}
	}
	public void keyReleased(KeyEvent e) {}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}

	public void mouseClicked(MouseEvent e)
	{
		numclicks++;
	System.out.println(numclicks);
	repaint();
    mousex=e.getX();
   	mousey=e.getY();
   	System.out.println(mousex+","+mousey);//these co-ords are relative to the component
	
	
	
	}

	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
}