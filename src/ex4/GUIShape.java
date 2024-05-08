

package ex4;
/**
 * This class implements the GUI_shape.
 * Ex4: you should implement this class!
 * @author I2CS
 */
import java.awt.Color;
import java.util.ArrayList;

import geo.Circle2D;
import geo.GeoShapeable;
import geo.Point2D;
import geo.Polygon2D;
import geo.Rect2D;
import geo.Segment2D;
import geo.Triangle2D;


public class GUIShape implements GUI_Shapeable{
	private GeoShapeable _g = null;
	private boolean _fill;
	private Color _color;
	private int _tag;
	private boolean _isSelected;

	public GUIShape(GeoShapeable g, boolean f, Color c, int t) {
		_g = null;
		if (g!=null) {_g = g.copy();}
		_fill= f;
		_color = c;
		_tag = t;
		_isSelected = false;
	}

	public GUIShape(GUIShape ot) {
		this(ot._g, ot._fill, ot._color, ot._tag);
	}

																	// will help for loading state, to know to create a shape from String
	public GUIShape(String str) 
	{
		this(str.split(","));
	} 

	@Override
	public GeoShapeable getShape() {
		return _g;
	}

	@Override
	public boolean isFilled() {
		return _fill;
	}

	@Override
	public void setFilled(boolean filled) {
		_fill = filled;
	}

	@Override
	public Color getColor() {
		return _color;
	}

	@Override
	public void setColor(Color cl) {
		_color = cl;
	}

	@Override
	public int getTag() {
		return _tag;
	}

	@Override
	public void setTag(int tag) {
		_tag = tag;

	}

	@Override
	public GUI_Shapeable copy() {
		GUI_Shapeable cp = new GUIShape(this);
		return cp;
	}
	public GUIShape(String[] s) 										
	{
		_color = new Color(Integer.parseInt(s[1]));								//turns the string unto a shape's data
		_fill = Boolean.parseBoolean(s[2]);		
		_tag = Integer.parseInt(s[3]);
		String info = s[4]; 
		if(info.compareTo("Point2D") == 0)											 //if the string represent a point, turn its data to the point's variables
		{
			Point2D p = new Point2D(Double.parseDouble(s[5]),Double.parseDouble(s[6])); 
		}
		else if (info.compareTo("Circle2D") == 0) 									//if the string represent a circle,turn its data to the circle's variables
		{
			double x = 0, y = 0;
			x = Double.parseDouble(s[5]);
			y = Double.parseDouble(s[6]);
			Point2D center = new Point2D(x,y);
			double radius = Double.parseDouble(s[7]);
			_g = new Circle2D(center,radius);
		}

		else if (info.compareTo("Rect2D") == 0)										//if the string represent a rect, turn its data to the rect's variables
		{

			 double x1=Double.parseDouble(s[5]);
			 double y1=Double.parseDouble(s[6]);
			 double x2=Double.parseDouble(s[9]);
			 double y2=Double.parseDouble(s[10]);
			Point2D p100 =new Point2D(x1,y1);
			Point2D p200 = new Point2D(x2,y2);
			_g = new Rect2D(p100, p200);
		}

		else if (info.compareTo("Segment2D") == 0)									//if the string represent a segment, turn its data to the segment's variables
		{
			double x1 = 0, y1 = 0, x2 = 0, y2 = 0;

			x1=Double.parseDouble(s[5]);
			y1=Double.parseDouble(s[6]);
			x2=Double.parseDouble(s[7]);
			y2=Double.parseDouble(s[8]);

			Point2D p1,p2;
			p1 = new Point2D(x1,y1);
			p2 = new Point2D(x2,y2);
			_g = new Segment2D(p1, p2);
		}
		else if (info.compareTo("Triangle2D") == 0)									//if the string represent a triangle, turn its data to the triangle's variables
		{
			double x1 = 0, y1 = 0, x2 = 0, y2 = 0, x3 = 0, y3 = 0;
			x1=Double.parseDouble(s[5]);
			y1=Double.parseDouble(s[6]);
			x2=Double.parseDouble(s[7]);
			y2=Double.parseDouble(s[8]);
			x3=Double.parseDouble(s[9]);
			y3=Double.parseDouble(s[10]);

			Point2D p1, p2, p3;
			p1 =new Point2D(x1,y1);
			p2 = new Point2D(x2,y2);
			p3 = new Point2D(x3,y3);

			_g = new Triangle2D(p1, p2, p3);
		}

		else if (info.compareTo("Polygon2D") == 0)									//if the string represent a polygon turn its data to the polygon's variables
		{
			ArrayList<Point2D> polyPoints = new ArrayList<Point2D>();
			for (int i = 5; i<s.length; i = i+2) {
				Point2D pol = new Point2D(Double.parseDouble(s[i]),Double.parseDouble(s[i+1]));
				polyPoints.add(pol);
			}
			_g = new Polygon2D(polyPoints);
		}
	}

	@Override
	public String toString() {
		return "GUIShape," + _color.getRGB() +"," + _fill + "," + _tag + "," + _g.getClass().getSimpleName()  + "," +_g;
	}

	@Override
	public boolean isSelected() {
		return this._isSelected;
	}
	@Override
	public void setSelected(boolean s) {
		this._isSelected = s;
	}
	@Override
	public void setShape(GeoShapeable g) {
		// TODO Auto-generated method stub
		if(g instanceof Rect2D) {
			this._g = (Rect2D)g;
		}
		if(g instanceof Circle2D) {
			this._g = (Circle2D)g;	
		}
		if(g instanceof Polygon2D) {
			this._g = (Polygon2D)g;
		}
		if(g instanceof Triangle2D) {
			this._g = (Triangle2D)g;			
		}
		if(g instanceof Segment2D) {
			this._g = (Segment2D)g;
		}
	}
}
