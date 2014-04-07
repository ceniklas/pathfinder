/**
 * @(#)Tower_Defence.java
 *
 *
 * @author
 * @version 1.00 2013/5/16
 */
import javax.swing.*;
import java.util.*;

//onödig class än så länge!

public class Tower 
{
	public int[] coords = {0,0};
    
	
    public Tower()
    {
    	//while(!insert());
    }
    /*
    private boolean insert()
    {
    	String input = JOptionPane.showInputDialog("X-Coordinate?");
		coords[0] = Integer.parseInt(input);
		input = JOptionPane.showInputDialog("Y-Coordinate?");
		coords[1] = Integer.parseInt(input);
		
    	if(((Vector)battleArea.get(coords[0])).get(coords[1]) != "T" || ((Vector)battleArea.get(coords[0]+1)).get(coords[1]) != "T" || ((Vector)battleArea.get(coords[0])).get(coords[1]+1) != "T" || ((Vector)battleArea.get(coords[0]+1)).get(coords[1]+1) != "T")
    	{
    		((Vector)battleArea.get(coords[0])).add(coords[1],"T");
    		((Vector)battleArea.get(coords[0]+1)).add(coords[1],"T");
    		((Vector)battleArea.get(coords[0])).add(coords[1]+1,"T");
    		((Vector)battleArea.get(coords[0]+1)).add(coords[1]+1,"T");
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }*/
}