import javax.swing.*;
import java.util.*;

public class Tower 
{
	public int[] coords = {0,0};
    
	
    /*public Tower()
    {
    	//while(!insert());
    }*/

    boolean towerBuilt = false;
    int x = 0;
    int y = 0;
    String input;

    /*while(!towerBuilt)
    { 
        input = JOptionPane.showInputDialog("X-Coordinate?");
        y = Integer.parseInt(input);
        input = JOptionPane.showInputDialog("Y-Coordinate?");
        x = Integer.parseInt(input);
        
        if(    battleArea[x][y] != towerTile
            && battleArea[x+1][y] != towerTile
            && battleArea[x][y+1] != towerTile
            && battleArea[x+1][y+1] != towerTile)
        {
            battleArea[x][y] = towerTile;
            battleArea[x+1][y] = towerTile;
            battleArea[x][y+1] = towerTile;
            battleArea[x+1][y+1] = towerTile;
            towerBuilt = true;
        }
        else
        {
            towerBuilt = false;
        }
    }*/
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
