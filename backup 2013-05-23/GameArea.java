import java.util.*;
import javax.swing.*;

public class GameArea
{
    public static char emptyTile = ' ';
    public static char walkerTile = '*';
    public static char towerTile = 'T';

    public static int sizeX = 55;
    public static int sizeY = 30;

    public static int goalPosX = 55;
    public static int goalPosY = 30;

    public char battleArea[][] = new char[sizeX][sizeY];
    public Vector<PathFinder>openList = new Vector<PathFinder>();
    public Vector<PathFinder>closedList = new Vector<PathFinder>();
    public PathFinder correctPath;
	
    GameArea() 
    {
    	for(int y = 0; y < sizeY; y++)
		{
			for(int x = 0; x < sizeX; x++)
			{
				battleArea[x][y] = emptyTile;
			}
		}

        int howManyWalls = 29;
        int myTemp = 0;
		System.out.println("CALLING TOWER");
		for(int i=2; i<=howManyWalls; i = i+4)
		{
			autoAddWall(i, 54, myTemp);
            //addWall();
			//print();
            if(myTemp == 0)
            {
                myTemp = 1;
            }
            else
            {
                myTemp = 0;
            }
		}

        startPathFinder();
        print();
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
            for (int i = howFaar; i != 0; i--) 
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
            for (int i = howFaar; i != 0; i--) 
            {
                battleArea[i][y] = towerTile;
            }
        }
    }

    private void startPathFinder()
    {
        clearOldPath();
    	openList.clear();
    	closedList.clear();
    	
        openList.addElement(new PathFinder());

    	int fValueFinder = 0;
    	int currentIndex = 0;

    	do
    	{
            //System.out.println("MAKING MOVE WITH: X:" + openList.get(currentIndex).thisX + " Y: " + openList.get(currentIndex).thisY  );
            //System.out.println("fVALUE:" + openList.get(currentIndex).fValue + " Moves:" + openList.get(currentIndex).movesMade);
            //System.out.println("SIZE OF CLOSEDLIST" + closedList.size());
            //System.out.println("SIZE OF OPENLIST" + openList.size());
    		makeMove(openList.get(currentIndex));
            //System.out.println("DONE");

    		closedList.add( openList.remove(currentIndex) ); //kanske inte behövs eftersom pathfinders har alla coords!?

    		fValueFinder = openList.firstElement().fValue;  //var säger vi att fValue är minst i första elementetet?
    		currentIndex = 0;
    		//manHattanTemp = openList.firstElement().manHattanValue;
    		
	    	for(int i = 1; i < openList.size(); i++)
	    	{
	    		int temp = openList.get(i).fValue;
	    		
	    		if(temp < fValueFinder)  //hitta minsta fValue
	    		{
	    			fValueFinder = temp;
	    			currentIndex = i;
	    			//manHattanTemp = pathFinders.get(i).manHattanValue;
	    		}
	    		/*else if(temp == fValueFinder) //om samma fvärde, välj den med minst avstånd
	    		{
	    			if(pathFinders.get(i).manHattanValue < manHattanTemp)
	    			{
	    				fValueFinder = temp;
	    				currentIndex = i;
	    				manHattanTemp = pathFinders.get(i).manHattanValue;
	    			}
	    		}*/
	    	}
    	}while(openList.get(currentIndex).manHattanValue != 0);

        correctPath = openList.get(currentIndex);

        updateBattleArea();
    }

    private void updateBattleArea()
    {
        //System.out.println("MOVESMADE: " + correctPath.movesMade);
        //System.out.println("SIZE of thePathXY: " + correctPath.thePathX.size());

        for (int i = correctPath.thePathX.size()-1; i >= 0; i--) 
        {
            //System.out.println("X:" + (int)correctPath.thePathX.get(i) + " Y: " + (int)correctPath.thePathY.get(i));
            battleArea[(int)correctPath.thePathX.get(i)][(int)correctPath.thePathY.get(i)] = walkerTile;
        }
        //System.out.println("ENDOFUPDATE");
    }

    private void clearOldPath()
    {
        for(int y = 0; y < sizeY; y++)
        {
            for(int x = 0; x < sizeX; x++)
            {
                if(battleArea[x][y] == walkerTile)
                {
                    battleArea[x][y] = emptyTile;
                }
            }
        }
    }

    private void makeMove(PathFinder p)
    {
    	int x = p.thisX;
    	int y = p.thisY;
        
        PathFinder pathFinder = new PathFinder();
        PathFinder tempP;
	
        //uppvänster
        if(x!=0 && y!=0 && (battleArea[x-1][y-1] != towerTile) && !existsClosed(x-1,y-1) && !existsOpen(x-1,y-1))
    	{
            //System.out.println("UV");
            openList.addElement(new PathFinder(x, y, p.movesMade, p.thePathX, p.thePathY));
            openList.lastElement().update(x-1, y-1);
            openList.lastElement().fValue++;
    	}
    	//vänster
    	if(x!=0 && (battleArea[x-1][y] != towerTile) && !existsClosed(x-1,y) && !existsOpen(x-1,y))
    	{
            //System.out.println("V");
    		openList.addElement(new PathFinder(x, y, p.movesMade, p.thePathX, p.thePathY));
            openList.lastElement().update(x-1, y);
    	}
    	//nervänster
    	if(x!=0 && y!=sizeY-1 && (battleArea[x-1][y+1] != towerTile) && !existsClosed(x-1,y+1) && !existsOpen(x-1,y+1))
    	{
            //System.out.println("NV");
    		openList.addElement(new PathFinder(x, y, p.movesMade, p.thePathX, p.thePathY));
            openList.lastElement().update(x-1, y+1);
            openList.lastElement().fValue++;
        }
    	//upp
    	if(y!=0 && (battleArea[x][y-1] != towerTile) && !existsClosed(x,y-1) && !existsOpen(x,y-1))
    	{
            //System.out.println("U");
    		openList.addElement(new PathFinder(x, y, p.movesMade, p.thePathX, p.thePathY));
            openList.lastElement().update(x, y-1);
    	}
    	//ner
    	if(y!=sizeY-1 && (battleArea[x][y+1] != towerTile) && !existsClosed(x,y+1) && !existsOpen(x,y+1))
    	{
            //System.out.println("N");
    		openList.addElement(new PathFinder(x, y, p.movesMade, p.thePathX, p.thePathY));
            openList.lastElement().update(x, y+1);
    	}
    	//nerhöger
    	if(x!=sizeX-1 && y!=sizeY-1 && (battleArea[x+1][y+1] != towerTile) && !existsClosed(x+1,y+1) && !existsOpen(x+1,y+1))
    	{
            //System.out.println("NH");
    		openList.addElement(new PathFinder(x, y, p.movesMade, p.thePathX, p.thePathY));
            openList.lastElement().update(x+1, y+1);
            openList.lastElement().fValue++;
    	}
    	//höger
    	if(x!=sizeX-1 && (battleArea[x+1][y] != towerTile) && !existsClosed(x+1,y) && !existsOpen(x+1,y))
    	{
            //System.out.println("H");
    		openList.addElement(new PathFinder(x, y, p.movesMade, p.thePathX, p.thePathY));
            openList.lastElement().update(x+1, y);
    	}
    	//upphöger
    	if(x!=sizeX-1 && y!=0 && (battleArea[x+1][y-1] != towerTile) && !existsClosed(x+1,y-1) && !existsOpen(x+1,y-1))
    	{
            //System.out.println("UH");
    		openList.addElement(new PathFinder(x, y, p.movesMade, p.thePathX, p.thePathY));
            openList.lastElement().update(x+1, y-1);
            openList.lastElement().fValue++;
    	}
    }
   
    private boolean existsClosed(int x, int y)
    {
    	for(int i=0; i<closedList.size(); i++)
    	{
			if(closedList.get(i).thisX == x && closedList.get(i).thisY == y)
			{
				return true;
			}		
    	}
        return false;
    }

    private boolean existsOpen(int x, int y)
    {
        for(int i=0; i<openList.size(); i++)
        {
            if(openList.get(i).thisX == x && openList.get(i).thisY == y)
            {
                return true;
            }       
        }
        return false;
    }
    
    private void print()
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
        }System.out.println();
		
    }
    
}
