import java.util.*;
import javax.swing.*;

public class GameArea
{
    public static char emptyTile = ' ';
    public static char walkerTile = '*';
    public static char towerTile = 'T';

    public static int sizeX;
    public static int sizeY;

    public static int goalPosX;
    public static int goalPosY;

	public char battleArea[][];

    private PathFinder p;
	
    GameArea(int x, int y) 
    {
        sizeX = x;
        sizeY = y;

        battleArea = new char[sizeX][sizeY];
        clearBattleArea();

        goalPosX = sizeX;
        goalPosY = sizeY;

        int howManyWalls = sizeY-1;
        int myTemp = 0;
		for(int i=1; i<=howManyWalls; i = i+8)
		{
			autoAddWall(i, sizeX-1, myTemp);
            
            if(myTemp == 0){
                myTemp = 1;
            }
            else{
                myTemp = 1;
            }
		}

        p = new PathFinder(battleArea); 
    }

    private void clearBattleArea()
    {
        for(int y = 0; y < sizeY; y++)
        {
            for(int x = 0; x < sizeX; x++)
            {
                battleArea[x][y] = emptyTile;
            }
        }
    }

    private void addTower()
    {
    	boolean towerBuilt = false;
    	int x = 0;
    	int y = 0;
    	String input;

    	while(!towerBuilt)
    	{ 
	    	input = JOptionPane.showInputDialog("X-Coordinate?");
			y = Integer.parseInt(input);
			input = JOptionPane.showInputDialog("Y-Coordinate?");
			x = Integer.parseInt(input);
			
	    	if(		battleArea[x][y] != towerTile
	    		&&  battleArea[x+1][y] != towerTile
	    		&&  battleArea[x][y+1] != towerTile
	    		&&  battleArea[x+1][y+1] != towerTile)
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
    	}
    }

    private void addWall()
    {
        int howFaar, y, temp;
        String input;

        input = JOptionPane.showInputDialog("Y-Coordinate?");
        y = Integer.parseInt(input);
        input = JOptionPane.showInputDialog("How Faar?");
        howFaar = Integer.parseInt(input);
        input = JOptionPane.showInputDialog("From Left or Right? (0 or 1)");
        temp = Integer.parseInt(input);

        
        if(temp == 0)
        {   
            for (int i = 0; i < howFaar; i++) 
            {
                battleArea[i][y] = towerTile;
            }
        }
        else
        {
            for (int i = sizeX-1; i >= sizeX-howFaar; i--) 
            {
                battleArea[i][y] = towerTile;
            }
        }
    }

    private void autoAddWall(int y, int howFaar, int temp)
    {
        if(temp == 0)
        {   
            for (int i = 0; i < howFaar; i++) 
            {
                battleArea[i][y] = towerTile;
            }
        }
        else
        {
            for (int i = sizeX-1; i >= sizeX-howFaar; i--) 
            {
                battleArea[i][y] = towerTile;
            }
        }
    }

    public void calculatePath()
    {
        p.startPathFinder();
    }
    
    public void print()
    {
    	System.out.println("BOARD OF DOOM");
        for(int x = 0; x < sizeX; x++)
        {
            System.out.print("-");
        }
        System.out.println();

    	for(int y = 0; y < sizeY; y++)
		{
			for(int x = 0; x < sizeX; x++)
			{
				//System.out.print( ((Vector)(battleArea.get(y))).get(x) );
				System.out.print(battleArea[x][y]);
			}
			System.out.println("|");
		}

        for(int x = 0; x < sizeX; x++)
        {
            System.out.print("-");
        }

        System.out.println();
        System.out.println();
    }
    
}
