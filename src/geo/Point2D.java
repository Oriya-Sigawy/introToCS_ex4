
package geo;

import ex4.Ex4_Const;

/**
 * This class represents a 2D point in the plane.
 * Do NOT change this class! It would be used as is for testing.
 * Ex4: you should edit and update this class!
 * @author boaz.benmoshe
 */


public class Point2D{
	//public static final double EPS1 = 0.001, EPS2 = Math.pow(EPS1,2), EPS=EPS2;
	public static final Point2D ORIGIN = new Point2D(0,0);
	private double _x,_y;
	public Point2D(double x,double y) {
		_x=x; _y=y;
	}
	public Point2D(Point2D p) {
		this(p.x(), p.y());
	}
	public Point2D(String s) {
		try {
			String[] a = s.split(",");
			_x = Double.parseDouble(a[0]);
			_y = Double.parseDouble(a[1]);
		}
		catch(IllegalArgumentException e) {
			System.err.println("ERR: got wrong format string for Point2D init, got:"+s+"  should be of format: x,y");
			throw(e);
		}
	}
	public double x() {return _x;}
	public double y() {return _y;}

	public int ix() {return (int)_x;}
	public int iy() {return (int)_y;}

	public Point2D add(Point2D p) {
		Point2D a = new Point2D(p.x()+x(),p.y()+y());
		return a;
	}
	public String toString()
	{
		return _x+","+_y;
	}

	public double distance()
	{
		return this.distance(ORIGIN);
	}
	public double distance(Point2D p2)
	{
		double dx = this.x() - p2.x();
		double dy = this.y() - p2.y();
		double t = (dx*dx+dy*dy);
		return Math.sqrt(t);
	}
	@Override
	public boolean equals(Object p)
	{
		if(p==null || !(p instanceof Point2D)) {return false;}
		Point2D p2 = (Point2D)p;
		return ( (_x==p2._x) && (_y==p2._y));
	}
	public boolean close2equals(Point2D p2, double eps)
	{
		return ( this.distance(p2) < eps );
	}
	public boolean close2equals(Point2D p2)
	{
		return close2equals(p2, Ex4_Const.EPS);
	}
	/**
	 * This method returns the vector between this point and the target point. The vector is represented as a Point2D.
	 * @param target
	 * @return
	 */
	public Point2D vector(Point2D target) {
		double dx = target.x() - this.x();
		double dy = target.y() - this.y();
		return new Point2D(dx,dy);
	}

	public void move(Point2D vec)
	{
		this._x += vec.x();
		this._y += vec.y();
	}

	/////////////////////// You should implement the methods below ///////////////////////////
	public void scale(Point2D cen, double ratio) 												//calculating the new point by the formula of division a segment in a given ratio
	{	
		if (ratio>=0)
		{
			double newX=0;
			double newY=0;
			if (ratio>1) 																		//calculate the new point by the difference between ratio and 1
			{
				newX= (this._x*ratio)-cen._x*(ratio-1);
				newY= (this._y*ratio)-cen._y*(ratio-1);
				this._x= newX;																	//set the new x,y
				this._y= newY;	
			}
			if (ratio<1)
			{
				newX= cen._x*(1-ratio)+this._x*ratio;
				newY= cen._y*(1-ratio)+this._y*ratio;
				this._x= newX;																	//set the new x,y
				this._y= newY;	
			}
			if (ratio==0)																		//if ratio is 0, turn the point to the center point
			{
				this._x= cen._x;																//set the new x,y
				this._y= cen._y;
			}																					
		}
	}
public double angle(Point2D p1)																	//calculates the angle for the rotate function
{
	double dx = p1.x() - this._x;
	double dy = p1.y() - this._y;
	return Math.atan2(dy, dx);
}
	public void rotate(Point2D cen, double angleDegrees)
	{
	double rad = Math.toRadians(angleDegrees); 													//We need a real value so we are converting the degree to radians
		
		double newAngle = rad + cen.angle(this);
		double radius = this.distance(cen);
		
		// Updating the values of the point
		this._x = cen.x() + (radius * Math.cos(newAngle));
		this._y = cen.y() + (radius * Math.sin(newAngle));
	}
}
