import java.util.*;
public class PathFinder
{	
	public Vector<PathFinderNode>openList = new Vector<PathFinderNode>();
	public Vector<PathFinderNode>closedList = new Vector<PathFinderNode>();
    public PathFinderNode correctPath;

    public char battleArea[][] = new char[GameArea.sizeX][GameArea.sizeY];

	PathFinder(char[][] theBattleArea)
	{
		battleArea = theBattleArea;
	}

	public void startPathFinder()
    {
        clearOldPath();
    	openList.clear();
    	closedList.clear();
    	
        openList.addElement(new PathFinderNode());

    	int fValueFinder = 0;
    	int currentIndex = 0;

    	do
    	{
            makeMove(openList.get(currentIndex));
            
    		closedList.add( openList.remove(currentIndex) ); 

    		fValueFinder = openList.firstElement().fValue;  
    		currentIndex = 0;

	    	for(int i = 1; i < openList.size(); i++)
	    	{
	    		int temp = openList.get(i).fValue;
	    		
	    		if(temp < fValueFinder)  //hitta minsta fValue
	    		{
	    			fValueFinder = temp;
	    			currentIndex = i;
	    		}
	    	}
    	}while(openList.get(currentIndex).manHattanValue != 0);

        correctPath = openList.get(currentIndex);

        updateBattleArea();
    }

	public void clearOldPath()
	{
	    for(int y = 0; y < GameArea.sizeY; y++)
	    {
	        for(int x = 0; x < GameArea.sizeX; x++)
	        {
	            if(battleArea[x][y] == GameArea.walkerTile)
	            {
	                battleArea[x][y] = GameArea.emptyTile;
	            }
	        }
	    }
	}

    private void updateBattleArea()
    {
        for (int i = correctPath.thePathX.size()-1; i >= 0; i--) 
        {
            battleArea[(int)correctPath.thePathX.get(i)][(int)correctPath.thePathY.get(i)] = GameArea.walkerTile;
        }
    }
    
    private void makeMove(PathFinderNode p)
    {
    	int x = p.thisX;
    	int y = p.thisY;
        
        PathFinderNode PathFinderNode = new PathFinderNode();
        PathFinderNode tempP;
	
        //höger
        if(x!=GameArea.sizeX-1 && (battleArea[x+1][y] != GameArea.towerTile) && !existsClosed(x+1,y) && !existsOpen(x+1,y))
        {
            //System.out.println("H");
            openList.addElement(new PathFinderNode(x, y, p.movesMade, p.thePathX, p.thePathY));
            openList.lastElement().update(x+1, y);
        }
        //ner
        if(y!=GameArea.sizeY-1 && (battleArea[x][y+1] != GameArea.towerTile) && !existsClosed(x,y+1) && !existsOpen(x,y+1))
        {
            //System.out.println("N");
            openList.addElement(new PathFinderNode(x, y, p.movesMade, p.thePathX, p.thePathY));
            openList.lastElement().update(x, y+1);
        }
        //uppvänster
        if(x!=0 && y!=0 && (battleArea[x-1][y-1] != GameArea.towerTile) && !existsClosed(x-1,y-1) && !existsOpen(x-1,y-1))
    	{
            //System.out.println("UV");
            openList.addElement(new PathFinderNode(x, y, p.movesMade, p.thePathX, p.thePathY));
            openList.lastElement().update(x-1, y-1);
            openList.lastElement().fValue++;
    	}
    	//vänster
    	if(x!=0 && (battleArea[x-1][y] != GameArea.towerTile) && !existsClosed(x-1,y) && !existsOpen(x-1,y))
    	{
            //System.out.println("V");
    		openList.addElement(new PathFinderNode(x, y, p.movesMade, p.thePathX, p.thePathY));
            openList.lastElement().update(x-1, y);
    	}
    	//nervänster
    	if(x!=0 && y!=GameArea.sizeY-1 && (battleArea[x-1][y+1] != GameArea.towerTile) && !existsClosed(x-1,y+1) && !existsOpen(x-1,y+1))
    	{
            //System.out.println("NV");
    		openList.addElement(new PathFinderNode(x, y, p.movesMade, p.thePathX, p.thePathY));
            openList.lastElement().update(x-1, y+1);
            openList.lastElement().fValue++;
        }
    	//upp
    	if(y!=0 && (battleArea[x][y-1] != GameArea.towerTile) && !existsClosed(x,y-1) && !existsOpen(x,y-1))
    	{
            //System.out.println("U");
    		openList.addElement(new PathFinderNode(x, y, p.movesMade, p.thePathX, p.thePathY));
            openList.lastElement().update(x, y-1);
    	}
        //nerhöger
        if(x!=GameArea.sizeX-1 && y!=GameArea.sizeY-1 && (battleArea[x+1][y+1] != GameArea.towerTile) && !existsClosed(x+1,y+1) && !existsOpen(x+1,y+1))
        {
            //System.out.println("NH");
            openList.addElement(new PathFinderNode(x, y, p.movesMade, p.thePathX, p.thePathY));
            openList.lastElement().update(x+1, y+1);
            openList.lastElement().fValue++;
        }
    	//upphöger
    	if(x!=GameArea.sizeX-1 && y!=0 && (battleArea[x+1][y-1] != GameArea.towerTile) && !existsClosed(x+1,y-1) && !existsOpen(x+1,y-1))
    	{
            //System.out.println("UH");
    		openList.addElement(new PathFinderNode(x, y, p.movesMade, p.thePathX, p.thePathY));
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
}
