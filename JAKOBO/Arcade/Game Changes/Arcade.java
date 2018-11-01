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

public class Arcade extends JPanel implements ActionListener, KeyListener, MouseListener
{
	static final int WIDTH = 800;
	static final int HEIGHT = 600;
	static JFrame frame;
	static Timer timer;
	static final long serialVersionUID = 0l;
	/*
		level 0 - menu
	*/
	static int level = 0;

	static int x,y,y2;
	static int add;
	static Image startUp, startUp2, BeginScreen; 
	static boolean isClicked = false, afterThought = false; //shows the next start screen
	static int startX, startY;
	static String mystery="Mystery";
	static int left;
	static int numclicks;
	static int mousex;
	static int mousey;
	public void paintComponent(Graphics g){
		switch(level){
			case 0: paintMenu(g);
		}
	}
	public void paintMenu(Graphics g)
	{
		g.drawImage(BeginScreen, 0, 0, WIDTH, HEIGHT,null);
	}
	
	public void paintStart(Graphics g)
	{
		if(x%30<15)
			g.drawImage(startUp, 0,0,1424,822,null); //start up screen (image, startx,starty,length,width,null)
		else
			g.drawImage(startUp2, 0,0,1424,822,null);
	}
	public Arcade(){
		try {
			startUp= ImageIO.read(new File("abc.png"));
			startUp2= ImageIO.read(new File("START-UP.png"));
			BeginScreen = ImageIO.read(new File("BeginScreen.png"));
			/*
			Muzak = AudioSystem.getClip();
			Muzak.open(AudioSystem.getAudioInputStream(new File("music.wav")));
			Muzak.loop(Clip.LOOP_CONTINUOUSLY);
			Muzak.start();
			*/
		} catch(Exception e){
			e.printStackTrace();
		}
		frame = new JFrame("Arcade");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setResizable(false);
		frame.add(this);
		frame.pack();
		frame.setVisible(true);
		add=1;
		x=y=600;
		y2=0;
		startX = startY = 10;
		left=0;
		frame.addKeyListener(this);
		addMouseListener(this);
		timer = new Timer(20, this);
	}
	public static void main(String [] args) throws IOException {
		new Arcade();
	}
	public void actionPerformed(ActionEvent e){
		y+=add;

		if (y >=400)
			y=0;
		if (y%2==0)	
			y2+=add;
		if (y2>=500)
			y2=0;
		
		x+=add;
		repaint();
	}
	public void mouseReleased(MouseEvent e){
	
	}
	public void mouseExited(MouseEvent e){
	
	}
	public void mouseEntered(MouseEvent e){
	
	}
	public void mousePressed(MouseEvent e){
	
	}
	public void mouseClicked(MouseEvent e){
		numclicks++;
		System.out.println(numclicks);
		isClicked = true; //call another method to create another screen
		mousex=e.getX();
   		mousey=e.getY();
   		System.out.println(mousex+","+mousey);
	}
	public void keyReleased(KeyEvent e){
	
	}
	public void keyPressed(KeyEvent e){
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
	public void keyTyped(KeyEvent e){
	
	}
}
