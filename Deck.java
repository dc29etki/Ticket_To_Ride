import java.util.Stack;
import java.util.Random;
import java.util.Collections;
import java.util.ArrayList;
/**
 * Write a description of class deck here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Deck
{
    private final int totalCards = 110;
    private final int maxCards = 12;
    ArrayList<Card> deck = new ArrayList<Card>();
    Random r = new Random();
    public Deck()
    {
        int i = 0;
        deck.add(new Card(Ccolor.LOCO));
        while(i < 12)
        {
            deck.add(new Card(Ccolor.RED));
            deck.add(new Card(Ccolor.ORANGE));
            deck.add(new Card(Ccolor.YELLOW));
            deck.add(new Card(Ccolor.GREEN));
            deck.add(new Card(Ccolor.BLUE));
            deck.add(new Card(Ccolor.PURPLE));
            deck.add(new Card(Ccolor.BLACK));
            deck.add(new Card(Ccolor.WHITE));
            deck.add(new Card(Ccolor.LOCO));
            i++;
        }
        deck.add(new Card(Ccolor.LOCO));

        shuffle();
    }

    /**
     * Shuffles the cards in the deck
     */
    public void shuffle()
    {
        Collections.shuffle(deck);
    }

    
}
