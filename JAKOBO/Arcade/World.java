import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.imageio.*;

public class World extends JPanel implements ActionListener, KeyListener{
    static final long serialVersionUID = 0L;
    static JFrame frame;
    Ladder ladders[];
    LinkedList<Barrel> barrels;
    int timer = 0;
    Timer tim;
    int lightningTimer = 0;
    int rightDir = 0;
    int leftDir = 0;
    // 0 = right and 1 = left
    int direction = 0;
    
    //lightning is happening?
    boolean lightning = false;
    
    //jumping variable
    boolean jumping = false;
    
    //false if not climbing, true if climbing
    //int climb to keep track of which climbing frame
    boolean climbing = false;
    int climb = 0;
    
    //if false he is facing left, if true he is facing right
    boolean facing = false;
    
    //Initialize floors***********************************************
    Floor[] floors = new Floor[6]; //6 floors
    Mario mario;
    Peach peach;
    DonkeyKong donkey;
    boolean hasWon = false;
    public World(){
        barrels = new LinkedList<Barrel>();

        ladders = new Ladder[5];
        ladders[0] = new Ladder(0.4, 0.125);
        ladders[1] = new Ladder(0.7, 0.275);
        ladders[2] = new Ladder(0.3, 0.425);
        ladders[3] = new Ladder(0.6, 0.575);
        ladders[4] = new Ladder(0.45, 0.725);

        //initialize the floors alternating which edge they touch
        for (int i = 0; i < floors.length; i++) {
            if (i % 2 == 0) {
                floors[i] = new Floor(0.4, 0.8 - i * 0.15);
            }
            else {
                floors[i] = new Floor(0.6, 0.65 - (i - 1) * 0.15);
            }
        }

        //set mario/peach/donkeykong to their starting points
        mario = new Mario(0.5, floors[5].getY() 
                                    + Floor.getHeight() + 0.025);
        peach = new Peach(0.70, floors[0].getY() 
                                    + Floor.getHeight() + 0.035);
        donkey = new DonkeyKong(0.15, floors[0].getY() 
                                               + Floor.getHeight() + 0.04);

        frame = new JFrame("Donkey Kong");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setResizable(false);
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
        frame.addKeyListener(this);
        // addMouseListener(this);
        tim = new Timer(1000 / 30, this);
        tim.start();
    }
    public void paintComponent(Graphics g){
        //Begin gameplay loop ********************************************
        if(mario.isAlive() && !hasWon) {
            //draw 4 barrels in top corner
            Barrel.draw4(floors);
            
            //draw all the floors and ladders
            for (int i = 0; i < floors.length; i++) {
                if (i < floors.length)
                    floors[i].draw();
                if (i < ladders.length)
                    ladders[i].draw();
            }
            
            //draw donkey depending on what frame we are on using timer
            if (0 <= timer && timer < 145) {
                donkey.drawOriginal();
            }
            else if (145 <= timer && timer < 155) {
                donkey.drawLeft();
            }
            else if (155 <= timer && timer < 165) {
                donkey.drawCenter();
            }
            else if (165 <= timer && timer < 185) {
                donkey.drawRight();
            }
            else donkey.drawOriginal();
            
            //draw peach
            peach.draw();
            
            //make sure mario is in an acceptable position before drawing
            mario.checkPosition();
            
            //if mario is above 0.35, use pikachu images
            if (mario.getY() > 1) {
                if (direction == 1) {
                    mario.pDrawRight(rightDir);
                } else if (direction == 2) {
                    mario.pDrawLeft(leftDir);
                } else {
                    mario.pDraw(facing);
                }
            } else if (mario.ladderCollision(ladders) && climbing) {
                mario.drawClimbing(climb); 
            } else if (direction == 1) {
                mario.drawRight(rightDir);
            } else if (direction == 2) {
                mario.drawLeft(leftDir);
            } else if (!(mario.floorCollision(floors)) &&
                       !(mario.ladderCollision(ladders))) {
                mario.drawJump(facing);
            } else {
                mario.draw(facing);
            }
            
            if (lightningTimer >= 30) {
                lightningTimer = 0;
                lightning = false;
            }
            
            //resets direction before input so mario can stand if not moving
            direction = 0;
            
            //checks for arrow pad input (w, a, s, d)
            if (PennDraw.hasNextKeyTyped()) {
                char dir = PennDraw.nextKeyTyped();
                if (dir == 'a') {
                    //if is not in the ladder and on the floor move
                    if (!(mario.ladderCollision(ladders) &&
                          !mario.floorCollision(floors))) {
                        mario.moveLeft();
                        leftDir++;
                        rightDir = 0;
                        climbing = false;
                        facing = false;
                        direction = 2;
                    }
                } 
                else if (dir == 'd') {
                    //if mario is not in the ladder and on the floor
                    if (!(mario.ladderCollision(ladders) &&
                          !mario.floorCollision(floors))) {
                        mario.moveRight();
                        rightDir++;
                        leftDir = 0;
                        climbing = false;
                        facing = true;
                        direction = 1;
                    }
                }
                if (dir == 'w') {
                    //if mario is in ladder, move up ladder
                    if (mario.ladderCollision(ladders)) {
                        // System.out.println("we made it here");
                        climbing = true;
                        climb++;
                        mario.moveUp();
                    }
                    //otherwise if he is on the floor, jump
                    else if (mario.floorCollision(floors)) {
//                            StdAudio.play("jump.wav");
                        climbing = false;
                        jumping = true;
                        mario.jump();
                    }
                } else if (dir == 's') {
                    //if mario is in ladder and not on the floor, move down
                    if (mario.ladderCollision(ladders) &&
                        !mario.floorCollision(floors)) { 
                        climbing = true;
                        climb--;
                        mario.moveDown();
                    }
                } else if (dir == 'f' && mario.getY() > 0.35) {
                    mario.lightning(mario.getX(), mario.getY() + 0.285);
                    lightningTimer++;
                    lightning = true;
                }
            }
            
            //let lightning happen for several frames and draw lightning
            if (lightningTimer > 0) {
                mario.lightning(mario.getX(), mario.getY() + 0.285);
                lightningTimer++;
            }
            
            //update mario's y position for jumping
            mario.updateY();
            
            //checks colliding with floors
            int counter = 0;
            for (int i = 0; i < floors.length; i++) {
                if ((floors[i].collision(mario))) {
                    counter++;
                }
            }
          
            //checks colliding with ladders
            if(counter <= 0.0 && !(mario.ladderCollision(ladders))) {
                mario.fall();
            } else if (mario.getVelY() < 0.0) {
                mario.stop(floors);
            }
            
            //if timer gets to 180 (frames), add a new barrel
            if (timer % 180 == 0) {
                barrels.add(new Barrel(0.2, floors[0].getY() 
                                           + floors[0].getHeight() + 0.025));
            } else if (barrels.size() > 5) {
                barrels.remove(0);
            }
            
            //checks barrels collision with floors
            int counter1 = 0;
            while (counter1 < barrels.size()) {
                if (barrels.get(counter1).floorCollision(floors)) {
                    if (barrels.get(counter1).getFloorLevel() % 2 == 0) {
                        barrels.get(counter1).rollRight();
                    } else {
                        barrels.get(counter1).rollLeft();
                    }
                } 
                
                //falls or stops barrels if neccesary
                if (!(barrels.get(counter1).floorCollision(floors))) {
                    barrels.get(counter1).fall();
                } else if (barrels.get(counter1).getVelY() < 0.0) {
                    int temp =  barrels.get(counter1).getFloorLevel();
                    barrels.get(counter1).setFloorLevel(temp + 1);
                    barrels.get(counter1).stop(floors);
                }
                
                barrels.get(counter1).updateY();
                counter1++;
            }
            
            //draws all barrels
            int counter2 = 0;
            while (counter2 < barrels.size()) {
                barrels.get(counter2).draw();
                counter2++;
            }
            
            //checks for mario collision with barrels
            mario.barrelCollision(barrels);
            
            timer++;
            if (timer >= 180) {
                timer = 0;
            }
            
            hasWon = mario.hasWon(peach); //check mario's location to peachs
        }
        else {
            if (hasWon) {
                g.setColor(Color.GREEN);
                g.setFont(new Font(Font.SERIF, Font.BOLD, 100));
                PennDraw.text(0.5, 0.5, "YOU WON!");
            }
            else if (!mario.isAlive()) {
    //                StdAudio.play("death.wav");
                g.setColor(Color.RED);
                g.setFont(new Font(Font.SERIF, Font.BOLD, 100));
                PennDraw.text(0.5, 0.5, "YOU LOST!");
            }
            
            //function to see if user wants to play again************
            g.setColor(Color.WHITE);
            g.setFont(new Font(Font.SERIF, Font.BOLD, 25));
            PennDraw.text(0.5, 0.4, "Press 'y' to play again or 'n' to not");
        } 
    }
    public static void main(String[] args) {
        new World();
    }
    public void actionPerformed(ActionEvent e){
        repaint();
    }
    public void keyReleased(KeyEvent e){
    
    }
    public void keyPressed(KeyEvent e){
    
    }
    public void keyTyped(KeyEvent e){
    
    }
}


