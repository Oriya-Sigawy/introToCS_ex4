package geo;

/**
 * This class represents a 2D rectangle (NOT necessarily axis parallel - this shape can be rotated!)
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Rect2D implements GeoShapeable 
{
	private Point2D _lowerLeft;
	private Point2D _upperRight;
	public Rect2D(Point2D p1, Point2D p2)				//constructor for the rectangle, saves the upper-right and the lower-left points.
	{
		this._lowerLeft = new Point2D(Math.min(p1.x(), p2.x()),Math.min(p1.y(), p2.y()));
		this._upperRight = new Point2D(Math.max(p1.x(), p2.x()),Math.max(p1.y(), p2.y()));
	}

	@Override
	public boolean contains(Point2D ot) 				//checks if ot is in the rectangle
	{
		// TODO Auto-generated method stub
		double maxX= Math.max(_upperRight.x(),_lowerLeft.x());
		double maxY= Math.max(_upperRight.y(),_lowerLeft.y());
		double minX= Math.min(_upperRight.x(),_lowerLeft.x());
		double minY= Math.min(_upperRight.y(),_lowerLeft.y());
		return(ot.x()<=maxX && ot.x()>=minX && ot.y()<=maxY && ot.y()>=minY)? true: false;
	}
	@Override
	public String toString()							//turns the rectangle's data into a string
	{
		Point2D upperLeft = new Point2D (_lowerLeft.x(), _upperRight.y());
		Point2D lowerRight = new Point2D (_upperRight.x(), _lowerLeft.y());
		return _lowerLeft.toString()+","+upperLeft.toString()+ ","+ _upperRight.toString() +","+lowerRight.toString() ;
	}
	@Override
	public double area() 								//calculates the rectangle's area
	{
		// TODO Auto-generated method stub
		return Math.abs(_lowerLeft.x()-_upperRight.x())*Math.abs(_lowerLeft.y()-_upperRight.y());
	}

	@Override
	public double perimeter() 							//calculates the rectangle's perimeter
	{
		// TODO Auto-generated method stub
		return 2*(Math.abs(_lowerLeft.x()-_upperRight.x())+Math.abs(_lowerLeft.y()-_upperRight.y()));
	}

	@Override
	public void move(Point2D vec) 						//moves the rectangle's points by the vector
	{
		// TODO Auto-generated method stub
		_lowerLeft.move(vec);
		_upperRight.move(vec);
	}

	@Override
	public GeoShapeable copy() 							//copy the rectangle
	{
		//TODO- check if it works
		// TODO Auto-generated method stub
		return new Rect2D(_lowerLeft, _upperRight);
	}

	@Override
	public void scale(Point2D center, double ratio)		//sends the points of the rect to the function scale in point2D
	{
		// TODO Auto-generated method stub
		if (ratio>0)
		{
			_lowerLeft.scale(center,ratio);
			_upperRight.scale(center,ratio);
			Point2D upperLeft = new Point2D (_lowerLeft.x(), _upperRight.y());
			Point2D lowerRight = new Point2D (_upperRight.x(), _lowerLeft.y());
			lowerRight.scale(center,ratio);
			 upperLeft.scale(center,ratio);
		}
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) //in case of rotating, I turned the rect into a polygon and rotated it as a polygon.
	{
		// TODO Auto-generated method stub
	}

	@Override
	public Point2D[] getPoints() 						//returns an array contains the rectangle's low-left and up-right points
	{
		// TODO Auto-generated method stub
		Point2D[] ans = new Point2D[2];
		ans[0]= _lowerLeft;
		ans[1]=_upperRight;
		return ans;
	}

}
