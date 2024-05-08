package geo;


/**
 * This class represents a 2D segment on the plane, 
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Segment2D implements GeoShapeable{
	private Point2D _p1;
	private Point2D _p2;
	public Segment2D(Point2D p1, Point2D p2) 			//constructor for the segment
	{
		this._p1 = new Point2D(p1);
		this._p2 = new Point2D(p2);
	}

	@Override
	public boolean contains(Point2D ot) 				//checks if ot is in the segment by the equasion of the segment
	{
		// TODO Auto-generated method stub
		if (ot.x()<0||ot.y()<0)
		{
			return false;
		}
		double m= (_p1.y()-_p2.y())/(_p1.x()-_p2.x());
		double otM= (_p1.y()-ot.y())/(_p1.x()-ot.x());
		if (ot.x()==_p1.x()&&ot.y()==_p1.y())
		{
			return true;
		}
		if(m==otM && ot.x()<=Math.max(_p1.x(),_p2.x())&& ot.x()>=Math.min(_p1.x(),_p2.x()))
		{
			return true;
		}
		return false;
	}
	@Override
	public String toString()							//turns the segment's data into a string
	{
		return _p1.toString()+","+ _p2.toString();
	}
	@Override
	public double area() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double perimeter() 							//calculates the segment's perimeter (twice its length)
	{
		// TODO Auto-generated method stub
		return 2*_p1.distance(_p2);
	}

	@Override
	public void move(Point2D vec) 						//moves the segment's points by the vector
	{
		// TODO Auto-generated method stub
		_p1.move(vec);
		_p2.move(vec);
	}

	@Override
	public GeoShapeable copy() 							//copy the segment
	{
		// TODO Auto-generated method stub
		return new Segment2D(_p1,_p2);
	}

	@Override
	public void scale(Point2D center, double ratio) 	//sends the points of the segment to the function scale in point2D
	{
		// TODO Auto-generated method stub
		if (ratio>0)
		{
		_p1.scale(center,ratio);
		_p2.scale(center,ratio);
		}
	}

	@Override
	public void rotate(Point2D center, double angleDegrees)//sends the points of the segment to the function scale in point2D
	{
		// TODO Auto-generated method stub
		_p1.rotate(center,angleDegrees);
		_p2.rotate(center,angleDegrees);
	}

	@Override
	public Point2D[] getPoints() 						//returns an array contains the start and the end points
	{
		// TODO Auto-generated method stub
		Point2D[] ans = new Point2D[2];
		ans[0]=_p1;
		ans[1]=_p2;
		return ans;
	}

}