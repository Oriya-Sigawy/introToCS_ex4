package geo;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a 2D polygon, as in https://en.wikipedia.org/wiki/Polygon
 * This polygon can be assumed to be simple in terms of area and contains.
 * 
 * You should update this class!
 * @author boaz.benmoshe
 *
 */
public class Polygon2D implements GeoShapeable
{
	private ArrayList<Point2D> _points = new ArrayList<Point2D>();
	private ArrayList<Point2D> _p = new ArrayList<Point2D>();
	public Polygon2D()
	{
		_points=new ArrayList<>();
	}
	public Polygon2D(ArrayList<Point2D> points) 						//constractor for the polygon with arraylist
	{

		this._points = new ArrayList <Point2D>();
		for (int i=0; i < points.size(); i++) {
			this._points.add(new Point2D(points.get(i)));
		}
	}
	public Polygon2D(Polygon2D poly) 									//constractor for the polygon
	{
		for (int i=0;i<_points.size();i++)
		{
			_points.add(new Point2D(poly.getPoints()[i]));
		}
	}
	public void addPoint(Point2D p)										//add a point for the polygon's arrayList
	{
		_points.add(new Point2D (p));
	}
	@Override
	public String toString() 											//turns the polygon's data into a string
	{
		String res = "";
		for (int i=0;i<_points.size()-1;i++) 
		{
			Point2D point = new Point2D (_points.get(i));
			res += point+ ",";
		}
		Point2D point = new Point2D (_points.get(_points.size()-1));
		res += point;
		return res;
	}
	@Override
	public boolean contains(Point2D ot) 								//checks if the point is inside the polygon or not, uses a ray from the point down and checks if the ray intersects the polygon a even number of time
	{
		// TODO Auto-generated method stub
		int sum=0;
		for(int i=0;i<_points.size()-1;i++) 
		{
			if(Line2D.linesIntersect(_points.get(i).x(), _points.get(i).y(), _points.get(i+1).x(),_points.get(i+1).y(),ot.x(),ot.y(),ot.x(),0))
			{
				sum++;
			}
		}
		if (Line2D.linesIntersect(_points.get(0).x(), _points.get(0).y(), _points.get(_points.size()-1).x(),_points.get(_points.size()-1).y(),ot.x(),ot.y(),ot.x(),0))
		{
			sum++;
		}
		return(sum%2==0)?false: true;
	}

	@Override
	public double area() 												//calculates the area of the polygon by a formula 
	{
		// TODO Auto-generated method stub
		double sum=0;
		for(int i=0;i<_points.size()-1;i++) 
		{
			sum+=_points.get(i).x()*_points.get(i+1).y()-_points.get(i+1).x()*_points.get(i).y();
		}
		sum+=_points.get(_points.size()-1).x()*_points.get(0).y()-_points.get(0).x()*_points.get(_points.size()-1).y();
		return 0.5*Math.abs(sum);
	}

	@Override
	public double perimeter()												//calculates the polygon's perimeter
	{
		// TODO Auto-generated method stub
		double peri=0;
		Point2D po1;
		Point2D po2;
		for (int i=0;i<_points.size()-1;i++)
		{
			po1= new Point2D (_points.get(i).x(),_points.get(i).y());
			po2= new Point2D (_points.get(i+1).x(),_points.get(i+1).y());
			peri+=po1.distance(po2);
		}
		po1= new Point2D (_points.get(0).x(),_points.get(0).y());
		po2= new Point2D (_points.get(_points.size()-1).x(),_points.get(_points.size()-1).y());
		peri+= po2.distance(po1);
		return peri;
	}

	@Override
	public void move(Point2D vec) 											//moves the polygon by a vector point by point
	{
		// TODO Auto-generated method stub
		for (Point2D point: _points)
		{
			point.move(vec);
		}
	}

	@Override
	public GeoShapeable copy() 												//copies the polygon by a vector point by point
	{
		// TODO Auto-generated method stub
		_p.clear();
		for (int i = 0; i < _points.size(); i++) 
		{
			Point2D p= new Point2D (_points.get(i));
			_p.add(p);
		}
		return new Polygon2D(this._p);
	}

	@Override
	public void scale(Point2D center, double ratio) 						//scale the polygon by the ratio to the center
	{
		// TODO Auto-generated method stub
		for (Point2D point: _points)
		{
			point.scale(center,ratio);
		}
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) 				//rotate the polygon by the angle to the center
	{
		// TODO Auto-generated method stub
		for (int i = 0; i < _points.size(); i++) 
		{
			_points.get(i).rotate(center,angleDegrees);
		}
	}

	@Override
	public Point2D[] getPoints()										 //returns an array of the polygon's points
	{
		// TODO Auto-generated method stub
		Point2D[] arr = new Point2D[this._points.size()];
		arr = _points.toArray(arr);
		return arr;
	}

	public double [] getXs()											//returns an array of the polygon's point's Xs
	{
		double[] ansX = new double [_points.size()];
		for (int i=0;i<_points.size();i++)
		{
			ansX[i]= _points.get(i).x();
		}
		return ansX;
	}

	public double [] getYs()											//returns an array of the polygon's point's Ys
	{
		double[] ansY = new double [_points.size()];
		for (int i=0;i<_points.size();i++)
		{
			ansY[i]= _points.get(i).y();
		}
		return ansY;
	}
}
