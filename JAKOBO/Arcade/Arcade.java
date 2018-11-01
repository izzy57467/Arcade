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
import java.util.Arrays;

public class Arcade extends JPanel implements ActionListener, KeyListener, MouseListener
{
	static final int WIDTH = 800;
	static final int HEIGHT = 600;
	static JFrame frame;
	static Timer timer;
	static final long serialVersionUID = 0l;
	/*
		level 0 - start
		level 1 - menu
	*/
	static int level = 0, screen = 0;

	//start
	static long time = System.currentTimeMillis(), threshold = 1000;
	static BufferedImage[] startUp = new BufferedImage[2];

	//menu
	static Image BeginScreen;
	static Rectangle galaga, dk, mk; 
	
	public void paintComponent(Graphics g){
		switch(level){
			case 0: paintStart(g); break;
			case 1: paintMenu(g); break;
		}
	}
	public void paintStart(Graphics g){
		screen %= startUp.length;
		g.drawImage(startUp[screen], 0, 0, WIDTH, HEIGHT, null);
		if(System.currentTimeMillis() - time > threshold){
			screen++;
			time = System.currentTimeMillis();
		}
	}
	public void paintMenu(Graphics g) {
		g.drawImage(BeginScreen, 0, 0, WIDTH, HEIGHT, null);
		Graphics2D g2 = (Graphics2D) g.create();
		// g2.draw(galaga);
		// g2.draw(mk);
		// g2.draw(dk);
	}
	public Arcade(){
		galaga = new Rectangle(186, 288, 113, 89);
		mk = new Rectangle(321, 288, 113, 89);
		dk = new Rectangle(462, 288, 113, 89);

		try {
			startUp[1] = ImageIO.read(new File("res/start2.png"));
			startUp[0] = ImageIO.read(new File("res/start_up.png"));
			BeginScreen = ImageIO.read(new File("res/BeginScreen.png"));
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
		timer.start();
	}
	public void debug(Object... args){
		System.out.println(Arrays.toString(args));
	}
	public static void main(String [] args) throws IOException {
		new Arcade();
	}
	public void actionPerformed(ActionEvent e){
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
		switch(level){
			case 1: mouseMenu(e); break;
		}
	}
	public void mouseMenu(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		if(galaga.contains(x, y))
			try{
				new Galaga();
			} catch(IOException e1){
				e1.printStackTrace();
			}
		else if(dk.contains(x, y)){
			System.out.println("dsadas");
			World a = new World();
		}
		// else if(mk.contains(x, y))
		// 	new MortalKombat();
	}
	public void keyReleased(KeyEvent e){
	
	}
	public void keyPressed(KeyEvent e){
		switch(level){
			case 0: keyStart(e);
		}
		repaint();
	}
	public void keyTyped(KeyEvent e){
	
	}
	public void keyStart(KeyEvent e){
		level++;
	}
}
