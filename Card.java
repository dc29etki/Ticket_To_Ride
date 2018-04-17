import java.awt.*;

/**
 * Write a description of class Card here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Card
{
    Ccolor color;
    int quant; 
    Image cardImage;

    public Card(Image img)
    {
        color = Ccolor.RED;
        quant = 12;//not sure if this is the right place for this
        //but itd prob help to track the amount the deck or
        //each player has somewhere
        cardImage = img;
    }

    public Card(Ccolor c)
    {
        color = c;
        if(c == Ccolor.LOCO)
        {
            quant = 14;
        }
        else
        {
            quant = 12;
        }
    }

    public Card(Ccolor c, int n)
    {
        color = c;
        quant = n;
    }

    public Ccolor getColor()
    {
        return color;
    }
    public void setImage(Image img){
       cardImage = img;
    }
    public void setColor(Color c){
        
    }
}
