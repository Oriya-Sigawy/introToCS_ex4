package ex4.test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import ex4.Ex4_Const;
import geo.Point2D;
import geo.Polygon2D;

class Polygon2DTest {
	public static ArrayList<Point2D> _points = new ArrayList<Point2D>();
	@Test
	void testPolygon() 
	{
		Point2D p1= new Point2D (0.5,0.5);
		Point2D p2= new Point2D (0.5,3);
		Point2D p3= new Point2D (3,3);
		Point2D p4= new Point2D (6,3.5);
		Point2D p5= new Point2D (6,1);
		Point2D p7= new Point2D (4,4);
		Point2D p8= new Point2D (3,4);
		Point2D p9= new Point2D (4,3);
		Point2D p11= new Point2D (4,0.5);
		Point2D p12= new Point2D (2,2);
		Point2D p13= new Point2D (2,4);
		////////////////test contains///////////////
		_points.add(p1);
		_points.add(p2);
		_points.add(p3);
		_points.add(p7);
		_points.add(p11);
		Polygon2D ben = new Polygon2D (_points);
		assertTrue(ben.contains(p12));
		assertFalse(ben.contains(p13));
		////////////////test area///////////////
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.CEILING);
		assertEquals(9.25,Double.parseDouble(df.format(ben.area())));
		_points.removeAll(_points);

		//////////test perimeter/////////
		_points.add(p7);
		_points.add(p8);
		_points.add(p3);
		_points.add(p9);
		Polygon2D ben3 = new Polygon2D (_points);
		assertEquals(ben3.perimeter(),4);
		_points.removeAll(_points);

		////////////test move///////////////
		Point2D vec= new Point2D (2,3);
		_points.add(p1);
		_points.add(p2);
		_points.add(p3);
		_points.add(p7);
		_points.add(p8);
		Polygon2D ben4 = new Polygon2D (_points);
		Polygon2D chaim= new Polygon2D (_points);
		ben4.move(vec);
		boolean test=true;
		for (int i = 0; i < _points.size(); i++)
		{
			if (ben4.getXs()[i]!=(chaim.getXs()[i]+2)||ben4.getYs()[i]!=(chaim.getYs()[i]+3))
			{
				test=false;
			}
		}
		assertTrue(test);
		_points.removeAll(_points);
		
		///////////test scale////////////////////
		Point2D cen= new Point2D (0,0);
		_points.add(p1);
		_points.add(p2);
		_points.add(p3);
		_points.add(p7);
		_points.add(p8);
		Polygon2D ben5 = new Polygon2D (_points);
		Polygon2D chaim2= new Polygon2D (_points);
		boolean test2= true;
		ben5.scale(cen,0.9);
		for (int i = 0; i < _points.size(); i++)
		{
			if((ben5.getPoints()[i].x()!=(chaim2.getPoints()[i].x()*0.9)||ben5.getPoints()[i].y()!=(chaim2.getPoints()[i].y()*0.9)))
			{
				test2= false;
			}
		}
		assertTrue(test2);
		//		ben.scale(cen,1.1);
		//		ben.scale(cen,1);
		//		ben.scale(cen,0);
		//		ben.scale(cen,-0.9);
		_points.removeAll(_points);

		///////////test rotate////////////////////
		_points.add(p3);
		_points.add(p5);
		_points.add(p7);
		Polygon2D ben6 = new Polygon2D (_points);
		Polygon2D chaim3= new Polygon2D (_points);
		ben6.rotate(cen,180);
		boolean test3= true;
		for (int i = 0; i < _points.size(); i++)
		{
			Point2D tmp= new Point2D(chaim3.getPoints()[i].x()*-1,chaim3.getPoints()[i].y()*-1);
			if((ben6.getPoints()[i]).distance(tmp)>Ex4_Const.EPS1)
			{
				test3= false;
			}
		}
		assertTrue(test3);
		_points.removeAll(_points);
		///////////////test getpoints/////////////
		_points.add(p1);
		_points.add(p2);
		_points.add(p3);
		_points.add(p4);
		_points.add(p5);
		System.out.println(_points);
		Point2D [] ansP= new Point2D [5];
		ansP[0]= p1;
		ansP[1]= p2;
		ansP[2]= p3;
		ansP[3]= p4;
		ansP[4]= p5;
		Polygon2D ben7 = new Polygon2D (_points);
		Object [] arrayP= ben7.getPoints();
		assertArrayEquals(arrayP,ansP);
		_points.removeAll(_points);

		///////////////test getxs/////////////
		_points.add(p1);
		_points.add(p2);
		_points.add(p3);
		_points.add(p4);
		_points.add(p5);
		double [] ansX= {0.5,0.5,3,6,6};
		Polygon2D ben8 = new Polygon2D (_points);
		double[] arrayX= ben8.getXs();
		assertArrayEquals(arrayX,ansX);
		double [] ansY= {0.5,3,3,3.5,1};
		double[] arrayY= ben8.getYs();
		assertArrayEquals(arrayY,ansY);
		
	/////////////////test toString//////////////////////
			String s= ben8.toString();
			String sTest= "0.5,0.5,0.5,3.0,3.0,3.0,6.0,3.5,6.0,1.0";
			assertEquals(s.compareTo(sTest),0);
		//}
	}
}