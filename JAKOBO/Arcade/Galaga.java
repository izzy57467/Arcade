import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;

public class Galaga extends JPanel implements KeyListener, ActionListener{
	static final long serialVersionUID = 0L;
	static JFrame frame;
	static final int WIDTH = 800;
	static final int HEIGHT = 600;
	static int[][] stars;
	static final int numStars = 1000;
	static int numEnemies = 25;
	static Rectangle[] stage;
	static final int cW = 100;
	static final int            cH = 50;
	static Rectangle character;
	static int velx = 10;
	static int vely = 5;
	static int nonnull = 0;
	static int accx = 1;
	static ArrayList<Rectangle> shots;
	static long time = System.currentTimeMillis();
	static javax.swing.Timer timer;
	static int mod = 10;
	static BufferedImage img;
	public Galaga() throws IOException{
		img = ImageIO.read(new File("res/ship.png"));
		shots = new ArrayList<Rectangle>();
		frame = new JFrame();
		timer = new javax.swing.Timer(20, this);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.getContentPane().add(this);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
		stars = new int[numStars][2];
		for(int i = 0;i<numStars;i++){
			stars[i][0] = (int)(Math.random()*WIDTH);
			stars[i][1] = (int)(Math.random()*HEIGHT);
		}
		stage = getStage("main.level");
		character = new Rectangle((WIDTH-cW)/2, HEIGHT-50-cH, cW, cH);
		frame.addKeyListener(this);
		timer.start();
	}
	public void actionPerformed(ActionEvent e){
		repaint();
	}
	public void paintComponent(Graphics g){
		if(stage.length == 0){
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.WHITE);
			g.setFont(new Font(Font.SERIF, Font.BOLD, 50));
			g.drawString("YOU WON", 10, HEIGHT - 10);
			return;
		}
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.white);
		for(int[] a: stars){
			g.fillRect(a[0], a[1], 1, 1);
		}
		paintStage(g, stage);
		g.setColor(Color.red);
		for(int i = 0;i<shots.size(); i++){
			Rectangle a = shots.get(i);
			g.fillRect(a.x, a.y, a.width, a.height);
			a.y-=vely;
			for(int j = 0;j<stage.length;j++){
				Rectangle b = stage[j];
				if(b!=null&&b.intersects(a)){
					shots.remove(a);
					stage[j] = null;
					if(i-1>0)
						i--;
				}
			}
		}
		paintCharacter(g);
		if(System.currentTimeMillis()-time>1000){
			mod *=-1;
			for(Rectangle a:stage)
				if(a!=null)
					a.x+=mod;
			time = System.currentTimeMillis();
		}
	}
	public void paintCharacter(Graphics g){
		// g.setColor(Color.blue);
		// g.fillRect(character.x, character.y+character.height/2, character.width, character.height/2);
		// g.fillRect(character.x+character.width/3, character.y, character.width/3, character.height/2);
		g.drawImage(img, character.x, character.y, character.width, character.height, null);
	}
	public void paintStage(Graphics g, Rectangle[] stg){
		g.setColor(Color.orange);
		for(Rectangle a:stg){
			if(a!=null)
				g.fillRect(a.x,a.y,a.width,a.height);
		}
	}
	public static void main(String[] args) throws IOException{
		new Galaga();
	}
	public Rectangle[] getStage(String name) throws IOException{
		BufferedReader file = new BufferedReader(new FileReader(name));
		ArrayList<String> strs = new ArrayList<>();
		StringTokenizer reqs = new StringTokenizer(file.readLine());
		int x = i(reqs);
		int y = i(reqs);
		int w = i(reqs);
		int b = i(reqs);
		int max = 0;
		while(file.ready()){
			String line = file.readLine();
			strs.add(line);
			max = Math.max(max, line.length());
		}
		Rectangle[] temp = new Rectangle[strs.size()*max];
		for(int r = 0;r<strs.size();r++)
			for(int c = 0;c<max;c++){
				if(strs.get(r).length()>c&&strs.get(r).charAt(c)=='*'){
					temp[r*max+c] = new Rectangle(x+c*(w+b), y+r*(w+b), w, w);
				}
			}
		return temp;
	}
	public static int i(StringTokenizer s){
		return Integer.parseInt(s.nextToken());
	}
	public void keyReleased(KeyEvent e){
		// System.out.println(Arrays.deepToString(stage).replaceAll("],","\n],"));
		System.out.println(stage.length);
	}
	public void keyPressed(KeyEvent e){
		int code = e.getKeyCode();
		if(code==KeyEvent.VK_LEFT){
			character.x-=velx;
			// if(System.currentTimeMillis()-time<500){
			// 	velx+=accx;
			// 	time = System.currentTimeMillis();
			// }
		}else if(code==KeyEvent.VK_RIGHT){
			character.x+=velx;
			// if(System.currentTimeMillis()-time<1000){
			// 	System.out.println(true);
			// 	velx+=accx;
			// 	time = System.currentTimeMillis();
			// }
		}
		else if(code == 32){
			shots.add(new Rectangle(character.x+character.width/3, character.y-character.width/3, character.width/3, character.width/3));
		}
	}
	public void keyTyped(KeyEvent e){
	
	}
}