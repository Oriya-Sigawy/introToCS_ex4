package ex4.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ex4.Ex4_Const;
import geo.Point2D;
import geo.Rect2D;
import geo.Segment2D;
import geo.Triangle2D;

class Rect2DTest {
	Point2D p1= new Point2D (1,1);
	Point2D p2= new Point2D (5,5);
	Rect2D agam= new Rect2D(p1,p2);
	Rect2D re= new Rect2D(p1,p2);
	@Test
	void testContains() {
		//fail("Not yet implemented");
		Point2D p3= new Point2D (4,4);
		Point2D p4= new Point2D (-4,4);
		Point2D p5= new Point2D (1,4);
		Point2D p6= new Point2D (7,10);
		assertTrue(agam.contains(p3));
		assertTrue(agam.contains(p5));
		assertFalse(agam.contains(p4));
		assertFalse(agam.contains(p6));
	}

	@Test
	void testArea() {
		//fail("Not yet implemented");
		assertEquals(16,agam.area());
	}

	@Test
	void testPerimeter() {
		//fail("Not yet implemented");
		assertEquals(16,agam.perimeter());
	}

	@Test
	void testMove() {
		//fail("Not yet implemented");
		Point2D vec= new Point2D (2,3);
		agam.move(vec);
		boolean test= true;
		for (int i = 0; i < agam.getPoints().length; i++) 
		{
			if (agam.getPoints()[i].x()!=(re.getPoints()[i].x()+2)||(agam.getPoints()[i].y()!=re.getPoints()[i].y()+3))
			{
				test= false;
			}
		}
		assertTrue(test);
	}

	@Test
	void testScale() {
	//	fail("Not yet implemented");
		Point2D cen= new Point2D (0,0);
		Point2D ans1= new Point2D (0.9,0.9);
		Point2D ans2= new Point2D (4.5,4.5);
		
		agam.scale(cen, 0.9);
		assertEquals(agam.getPoints()[0],ans1);
		assertEquals(agam.getPoints()[1],ans2);
		
		agam= new Rect2D(p1,p2);
		ans1= new Point2D (1.1,1.1);
		ans2= new Point2D (5.5,5.5);
		
		agam.scale(cen, 1.1);
		assertEquals(agam.getPoints()[0],ans1);
		assertEquals(agam.getPoints()[1],ans2);
		
		agam= new Rect2D(p1,p2);
		ans1= new Point2D (1,1);
		ans2= new Point2D (5,5);
		
		agam.scale(cen, -1.1);
		assertEquals(agam.getPoints()[0],ans1);
		assertEquals(agam.getPoints()[1],ans2);
	}

	@Test
	void testRotate() 
	{
		//fail("Not yet implemented");
		//I have done a test for the polygon
	}
	@Test
	void testGetPoints() {
		//fail("Not yet implemented");
		Point2D [] test1= new Point2D [2];
		test1[0]=p1;
		test1[1]=p2;
		assertArrayEquals(test1,agam.getPoints());
	}
	
	@Test
	void testToString() {
		String s= agam.toString();
		String sTest= "1.0,1.0,1.0,5.0,5.0,5.0,5.0,1.0";
		assertEquals(s.compareTo(sTest),0);
	}

}
