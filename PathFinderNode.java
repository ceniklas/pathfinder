import java.util.*;
public class PathFinderNode
{
	public Vector thePathX = new Vector();
	public Vector thePathY = new Vector(); 
	public int thisX, thisY;
	
	public int fValue; 
	public int movesMade;
	public int manHattanValue;
	
	PathFinderNode()
	{
		thisX = 0;
		thisY = 0;
		manhattan();
		thePathX.addElement(0);
		thePathY.addElement(0);
		movesMade = 0;
		fValue = manHattanValue;
	}

	PathFinderNode(int x, int y, int movesMadeSoFar, Vector thePathSoFarX, Vector thePathSoFarY)
	{
		thisX = x; 
		thisY = y; 
		
		manhattan();

		for (int i = 0; i < thePathSoFarX.size(); i++) 
		{
			thePathX.addElement(thePathSoFarX.get(i));
			thePathY.addElement(thePathSoFarY.get(i));
		}

		movesMade = movesMadeSoFar;
		fValue = (movesMade+manHattanValue);
	}
	
	public void manhattan()
	{
		manHattanValue = Math.abs(GameArea.goalPosX-1-thisX)+ Math.abs(GameArea.goalPosY-1-thisY); 
	}

	public void update(int x, int y)
	{
		thisX = x;
		thisY = y;

		movesMade++;
		manhattan();
		fValue = manHattanValue+movesMade;

		thePathX.addElement(x);
		thePathY.addElement(y);
	}

	public void setTo(PathFinderNode p)
	{
		thisX = p.thisX; 
		thisY = p.thisY; 
		
		manHattanValue = p.manHattanValue;

		thePathX.clear();
		thePathY.clear();

		for (int i = 0; i < p.thePathX.size(); i++) 
		{
			thePathX.addElement(p.thePathX.get(i));
			thePathY.addElement(p.thePathY.get(i));
		}
		
		movesMade = p.movesMade;
		fValue = (movesMade+manHattanValue);
	}
}
