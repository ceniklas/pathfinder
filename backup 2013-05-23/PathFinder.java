import java.util.*;
public class PathFinder
{
	public Vector thePathX = new Vector();
	public Vector thePathY = new Vector(); 
	//istället för vektorer i pathfinders, kanske att ett objekt innehåller en koordinat, ett fvalue, ett movesmade value?? känns enklare!
	public int thisX, thisY;


	//public Vector theFValue = new Vector();
	
	public int fValue; // skall vara vektor också? så att alla positioner har varsitt värde!
	public int movesMade; // skall vara vektor också?
	public int manHattanValue; // skall vara vektor också?
	
	PathFinder()
	{
		thisX = 0;
		thisY = 0;
		manhattan();
		thePathX.addElement(0);
		thePathY.addElement(0);
		movesMade = 0;
		fValue = manHattanValue;
	}

	PathFinder(int x, int y, int movesMadeSoFar, Vector thePathSoFarX, Vector thePathSoFarY)
	{
		thisX = x; //thePathX.add(x); // X = 0
		thisY = y; //thePathY.add(y); // Y = 0
		
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

	public void setTo(PathFinder p)
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