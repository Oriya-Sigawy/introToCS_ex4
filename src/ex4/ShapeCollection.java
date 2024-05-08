package ex4;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import geo.Circle2D;
import geo.GeoShapeable;
import geo.Point2D;
import geo.Polygon2D;
import geo.Rect2D;
import geo.Segment2D;
import geo.Triangle2D;

/**
 * This class represents a collection of GUI_Shape.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeCollection implements ShapeCollectionable
{
	private ArrayList<GUI_Shapeable> _shapes;

	public ShapeCollection() 
	{
		_shapes = new ArrayList<GUI_Shapeable>();
	}
	@Override
	public GUI_Shapeable get(int i) {
		return _shapes.get(i);
	}

	@Override
	public int size() {
		return _shapes.size();
	}

	@Override
	public GUI_Shapeable removeElementAt(int i) 
	{
		return _shapes.remove(i); 																								//calls the remove function of arraylist of java for the i'th location.
	}

	@Override
	public void addAt(GUI_Shapeable s, int i) {
		_shapes.add(i, s);																										//calls the add function of arraylists of java

	}
	@Override
	public void add(GUI_Shapeable s) {
		if(s!=null && s.getShape()!=null) {
			_shapes.add(s);
		}
	}
	@Override
	public ShapeCollectionable copy() 
	{
		ShapeCollection copy_Shapes_Collection= new ShapeCollection(); 															//create a new shapes collection
		for (int i = 0; i<_shapes.size(); i++) 
		{
			GeoShapeable gs = _shapes.get(i).getShape().copy(); 																// copies the shapes characteristics
			GUIShape guiSh = new GUIShape(gs,_shapes.get(i).isFilled(),_shapes.get(i).getColor(), _shapes.get(i).getTag());		//creates a new shape with gs's properties 
			copy_Shapes_Collection.add(guiSh);																					//add the shape to the new shapes collection
		}

		return copy_Shapes_Collection;
	}

	@Override
	public void sort(Comparator<GUI_Shapeable> comp) {
		_shapes.sort(comp);																										//calls the sort function of arraylists of java with the selected comperator
	}

	@Override
	public void removeAll() {
		_shapes.removeAll(_shapes); 																							//calls the remove all function of arraylists of java

	}

	@Override
	public void save(String file) 
	{
		try 
		{
			FileWriter file_Write = new FileWriter(file); 								//opens the file											
			for(int i = 0; i<_shapes.size(); i++) 
			{
				file_Write.write((_shapes.get(i).toString()+ "\n"));					//turn every shape in the shape collection to a string wich represents the shape and add the string to the file
			}	
			file_Write.close();															//close the file
		}
		catch(Exception e) 
		{
			e.printStackTrace();														//throw an exception
		}
	}
	@Override
	public void load(String file)
	{
		_shapes.clear(); 
		try {
																						// Open the file
			FileReader r = new FileReader(file); 										//saves every line in the file 
			BufferedReader bR = new BufferedReader(r); 
			String line= bR.readLine();												
			while (line!= null)
			{
				_shapes.add(new GUIShape(line));										//turns the line to a shapeand add the shape to the shape collection
				line = bR.readLine(); 													//saves the nrxt line
			}

																						// Close the file
			bR.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public Rect2D getBoundingBox() 
	{
		if (_shapes.isEmpty())
		{
			return null;
		}
		double maxY=-1;																	//saves the max y
		double minY=11;																	//saves the min y
		double maxX=-1;																	//saves the max X
		double minX=11;																	//saves the minX
		for (int i = 0; i<_shapes.size(); i++)
		{
			GeoShapeable gs = _shapes.get(i).getShape();
			if(gs instanceof Circle2D)													//for a circle, checks the points of the circle's peripheral
			{
				Circle2D circle = (Circle2D) gs;
				double radius = circle.getRadius();
				if ((circle.getPoints()[0].x()-radius)<minX)
				{
					minX=circle.getPoints()[0].x()-radius;
				}
				if ((circle.getPoints()[0].x()+radius)>maxX)
				{
					maxX=(circle.getPoints()[0].x()+radius);
				}
				if ((circle.getPoints()[0].y()-radius)<minY)
				{
					minY=(circle.getPoints()[0].y()-radius);
				}
				if ((circle.getPoints()[0].y()+radius)>maxY)
				{
					maxY=(circle.getPoints()[0].y()+radius);
				}
			}
			Point2D [] arrayPoints = _shapes.get(i).getShape().getPoints();			//checks the minx, miny, maxx, maxy of every shape in the shape collection
			for (int j=0;j<arrayPoints.length;j++)
			{
				if (arrayPoints[j].x()<minX)
				{
					minX=arrayPoints[j].x();
				}
				if (arrayPoints[j].x()>maxX)
				{
					maxX=arrayPoints[j].x();
				}
				if (arrayPoints[j].y()<minY)
				{
					minY=arrayPoints[j].y();
				}
				if (arrayPoints[j].y()>maxY)
				{
					maxY=arrayPoints[j].y();
				}
			}
		}
		Point2D p1= new Point2D(minX,minY); 				//save the min, max points for the rect
		Point2D p2= new Point2D(maxX,maxY);
		return new Rect2D(p1, p2);							//draw the rect
	}	
															//string for the all shape collection
	@Override
	public String toString() 
	{
		String ans = "";
		for(int i=0;i<size();i=i+1) {
			ans += this.get(i);
		}
		return ans;
	}


}
