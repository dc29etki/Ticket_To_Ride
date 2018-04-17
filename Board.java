import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.Stack;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Write a description of class Board here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Board extends JPanel implements MouseListener 
{
    Player player1;
    Player player2;
    Player player3;
    Player player4;
    Player player5;
    Deck deck;
    MeepleGroup bMeeples;
    int curPlays;
    Stack discard;
    String cols = "RBGYPW";
    Image image;
    Image background, intro;
    Image beginningImage;
    Track [] tracks;
    int arrayCount;
    int imageWidth, imageHeight;
    boolean startClick = false;
    //MusicLoopPlayer loop;
    static JButton start = new JButton();
    static JFrame f = new JFrame();
    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    createAndShowGUI();
                }
            });
    }

    protected static void createAndShowGUI() {

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//for now
        start.setBounds(380,560,300,65);

        f.add(start);
        f.setTitle("Ticket to Ride: Germany");
        Board b = new Board();
        f.getContentPane().add(b);
        f.setResizable(false);
        f.setSize(1000,950);

        f.setVisible(true);
    }

    private void loadImage() {
        try {
            URL url = getClass().getResource("images/board.jpg");

            image = ImageIO.read(url);
            url = getClass().getResource("images/background.jpg");
            background = ImageIO.read(url);

        } 
        catch (MalformedURLException mue) {
            System.err.println(mue.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    public void introS(ActionEvent e){
        //loop.stop();
        //loop.music();
        startClick = true;

        repaint();
    }

    public Board()
    {

        start.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    introS(e);
                    f.getContentPane().remove(start);
                    String[] choices = {"2", "3", "4", "5"};
                    JComboBox<String> cb = new JComboBox<String>(choices);

                    int s = JOptionPane.showOptionDialog(new JFrame(), cb, 
                            "Number of Players", JOptionPane.YES_NO_OPTION,
                            JOptionPane.DEFAULT_OPTION, null, new Object[] 
                            { "OK", "Close" }, JOptionPane.CLOSED_OPTION);

                    if (s != 0) {
                        System.exit(s);
                    }

                    String level = (String) cb.getSelectedItem();
                }
            });
        start.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        start.setOpaque(true);

        start.setContentAreaFilled(false);
        start.setBorderPainted(false);

        player1 = new Player();
        player2 = new Player();

        if(curPlays > 2)
        {
            player3 = new Player();
            if(curPlays > 3)
            {
                player4 = new Player(); 
                if(curPlays == 5)
                {
                    player5 = new Player();
                }
            }
        }

        deck = new Deck();
        discard = new Stack();
        bMeeples = new MeepleGroup(false);
        loadImage();
        addMouseListener(this);
        imageWidth = image.getWidth(this);
        imageHeight = image.getHeight(this);
        setTrack();
        //loop = new MusicLoopPlayer();
        //loop.intro();
        loadBeg();

    }

    private String pickPlayersName(String args[])
    {
        System.out.println("Please Enter a name: ");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        return name;
    }

    private TAndMcolor pickPlayersColor()
    {
        System.out.println("Please pick a color :\n Must be one of the following");        
        for(int i = 0; i < 6; i++)
        {

        }
        return TAndMcolor.RED;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint
        (RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);

        // draws the board
        int w = getWidth();
        int h = getHeight();

        int x = (w - imageWidth) / 2;
        int y = (h - imageHeight) / 2;
        g2.drawImage(background,0,0,getWidth(),getHeight(),this);
        g2.drawImage(image, 0, 0, this);
        g2.fillOval(74,159,20,20); //Emden
        g2.fillOval(155,146,20,20); //Bremerhaven
        g2.fillOval(159,190,20,20); //Bremer
        g2.fillOval(97,277,20,20); //Munster
        g2.fillOval(93,324,20,20); //Dortmund
        g2.fillOval(52,345,20,20); //Dosseldorf
        g2.fillOval(48,390,20,20); //Koln
        g2.fillOval(58,440,20,20); //Koblenz
        g2.fillOval(115,480,20,20);//Mainz
        g2.fillOval(155,455,20,20);//Frankfurt
        g2.fillOval(133,522,20,20);//Mannheim
        g2.fillOval(129,564,20,20);//Kalsruhe
        g2.fillOval(37,534,20,20);//Saarbrücken
        g2.fillOval(222,477,20,20);//Wurzburg
        g2.fillOval(178,576,20,20);//Stuttgart
        g2.fillOval(104,656,20,20);//Frieburg
        g2.fillOval(175,670,20,20);//Konstanz
        g2.fillOval(217,679,20,20);//Lindau
        g2.fillOval(234,614,20,20);//Ulm
        g2.fillOval(279,619,20,20);//Ausburg
        g2.fillOval(347,634,20,20);//Munchen
        g2.fillOval(380,552,20,20);//Regensburg
        g2.fillOval(292,500,20,20);//Nurnberg
        g2.fillOval(208,352,20,20);//Kassel
        g2.fillOval(289,382,20,20);//Erfurt
        g2.fillOval(365,340,20,20);//Leipzig
        g2.fillOval(410,393,20,20);//Chemnitz
        g2.fillOval(452,374,20,20);//Dresden
        g2.fillOval(335,276,20,20);//Magdeburg
        g2.fillOval(421,234,20,20);//Berlin
        g2.fillOval(216,259,20,20);//Hannover
        g2.fillOval(233,78,20,20);//Kiel
        g2.fillOval(244,148,20,20);//Hamburg
        g2.fillOval(310,138,20,20);//Schwerin
        g2.fillOval(350,81,20,20);//Restock

        if(!startClick){
            g2.drawImage(intro,0,0,getWidth(),getHeight(),this);
        }
    }
    /////////////////////////////////////////////////////////////////////////temp////////////
    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {

    }

    private void setTrack()
    {
        // Initialize the tracks
        arrayCount = 0;
        tracks = new Track [313];

        //reads in file data
        Scanner inFile = null;
        // Green
        File filename = new File("green");
        setArray(filename,arrayCount,Ccolor.GREEN);
        // Orange
        filename = new File("orange.txt");
        setArray(filename,arrayCount,Ccolor.ORANGE);
        // Blue
        filename = new File("blue");
        setArray(filename,arrayCount,Ccolor.BLUE);
        // Red
        filename = new File("red");
        setArray(filename,arrayCount,Ccolor.RED);
        // Black
        filename = new File("black");
        setArray(filename,arrayCount,Ccolor.BLACK);
        // Yellow
        filename = new File("yellow");
        setArray(filename,arrayCount,Ccolor.YELLOW);
        // Purple
        filename = new File("purple");
        setArray(filename,arrayCount,Ccolor.PURPLE);
        // White
        filename = new File("white");
        setArray(filename,arrayCount,Ccolor.WHITE);
        // Grey
        filename = new File("grey");
        setArray(filename,arrayCount,Ccolor.GREY);

    }

    public void loadBeg(){
        try {
            URL url = getClass().getResource("images/intro.png");

            intro = ImageIO.read(url);

        } 
        catch (MalformedURLException mue) {
            System.err.println(mue.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }

    }

    private void setArray(File f,int counter,Ccolor c)
    {
        Scanner inFile = null;

        try { inFile = new Scanner(f); }
        catch (FileNotFoundException e) 
        {System.out.println("Cannot open "+f+" does it exist in this project directory?");
            return;
        }
        int check = 0;
        while(inFile.hasNextLine()){
            if(c == Ccolor.BLACK && check == 28)
            {
                return;
            }
            String line = inFile.nextLine();

            StringTokenizer st = new StringTokenizer(line);
            int x = Integer.parseInt(st.nextToken()); 
            int y = Integer.parseInt(st.nextToken());            
            tracks[counter] = new Track(x,y, c);
            arrayCount++;
            check++;
            counter++;

        }
    }
}
