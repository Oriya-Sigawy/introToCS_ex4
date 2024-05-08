package geo;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Triangle2D implements GeoShapeable
{
	private Point2D _p1;
	private Point2D _p2;
	private Point2D _p3;
	public Point2D[] ans = new Point2D[4];
	public Triangle2D(Point2D p1, Point2D p2, Point2D p3)											//constructor for the triangle
	{
		this._p1 = new Point2D(p1);	
		this._p2= new Point2D(p2);
		this._p3= new Point2D(p3);
		ans[0]= _p1;
		ans[1]= _p2;
		ans[2]= _p3;
	}

	@Override
	public boolean contains(Point2D ot)																//checks if ot is in the triangle
	{
		// TODO Auto-generated method stub
		if (ot.x()<0||ot.y()<0)
		{
			return false;
		}
		ans[3]= _p1;
		double areaOfInsideTriangles=0;
		for (int i=0;i<3;i++)																		//divide the triangle to 3 little triangles and calculates their area
		{
			double rib= ans[i].distance(ans[i+1]);
			double height=0;
			if (ans[i+1].y()-ans[i].y()==0)
			{
				height=Math.abs(ot.y()-ans[i].y());
			}
			else if(ans[i+1].x()-ans[i].x()==0)
			{
				height=Math.abs(ot.x()-ans[i].x());
			}
			else 
			{
				double  m= ((ans[i+1].y()-ans[i].y())/(ans[i+1].x()-ans[i].x()));
				double  b=ans[i].y()-m*ans[i].x();
				height= Math.abs((ot.x()*m-ot.y()+b)/Math.sqrt(m*m+1));
			}
			areaOfInsideTriangles+=(rib*height)/2;

		}
		return (Math.abs(area()-areaOfInsideTriangles)<=ex4.Ex4_Const.EPS) ? true:false;			//if the sum of the areas of the little triangles is the same as the area of the big triangle, return true
	}
	@Override
	public String toString()																		//turns the triangle's data into a string
	{
		return _p1.toString()+ ","+_p2.toString()+","+_p3.toString();
	}
	@Override
	public double area() 																			//calculates the triangle's area
	{
		// TODO Auto-generated method stub
		double rib=_p1.distance(_p2);
		double m=Math.abs(_p1.y()-_p2.y())/Math.abs(_p1.x()-_p2.x());
		double b=_p1.y()-m*_p1.x();
		double height2= Math.abs((_p3.x()*m-_p3.y()+b)/Math.sqrt(m*m+1));
		return (rib*height2/2);
	}

	@Override
	public double perimeter() 																		//calculates the rectangle's perimeter
	{
		// TODO Auto-generated method stub
		return _p1.distance(_p2)+_p2.distance(_p3)+_p3.distance(_p1);
	}

	@Override
	public void move(Point2D vec)																	//moves the triangle's points by the vector
	{
		// TODO Auto-generated method stub
		_p1.move(vec);
		_p2.move(vec);
		_p3.move(vec);
	}

	@Override
	public GeoShapeable copy() 																		//copy the triangle
	{
		// TODO Auto-generated method stub
		return new Triangle2D(_p1, _p2,_p3);
	}

	@Override
	public void scale(Point2D center, double ratio) 												//sends the points of the triangle to the function scale in point2D
	{
		// TODO Auto-generated method stub
		if (ratio>0)
		{
		_p3.scale(center,ratio);
		_p2.scale(center,ratio);
		_p1.scale(center,ratio);
		}
	}

	@Override
	public void rotate(Point2D center, double angleDegrees)											//sends the points of the triangle to the function rotate in point2D
	{
		// TODO Auto-generated method stub
		_p3.rotate(center,angleDegrees);
		_p2.rotate(center,angleDegrees);
		_p1.rotate(center,angleDegrees);

	}

	@Override
	public Point2D[] getPoints() 																	//returns an array contains the triangle's points
	{
		// TODO Auto-generated method stub
		Point2D[] answer = new Point2D[3];
		for (int i=0;i<3;i++)
		{
			answer[i]=ans[i];
		}
		return answer;
	}

}
