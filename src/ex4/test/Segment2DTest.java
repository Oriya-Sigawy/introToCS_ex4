package ex4.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ex4.Ex4_Const;
import geo.Point2D;
import geo.Segment2D;
import geo.Triangle2D;

class Segment2DTest {

	Point2D p1= new Point2D (1,1);
	Point2D p2= new Point2D (5,5);
	Segment2D shalom= new Segment2D(p1,p2);

	@Test
	void testContains() {
		//fail("Not yet implemented");
		Point2D p3= new Point2D (4,4);
		Point2D p4= new Point2D (17,35);
		Point2D p5= new Point2D (-17,35);
		boolean test_1= shalom.contains(p3);
		assertTrue(test_1);
		boolean test_2= shalom.contains(p4);
		assertFalse(test_2);
		boolean test_3= shalom.contains(p5);
		assertFalse(test_3);
	}

	@Test
	void testArea() {
		//fail("Not yet implemented");
		assertEquals(shalom.area(),0);
	}

	@Test
	void testPerimeter() {
		//fail("Not yet implemented");
		double dist1= 2*p1.distance(p2);
		assertEquals(dist1,shalom.perimeter());
	}

	@Test
	void testMove() {
		//fail("Not yet implemented");
		Point2D vec= new Point2D (2,3);
		shalom.move(vec);
		assertEquals(shalom.getPoints()[0].x(),3);
		assertEquals(shalom.getPoints()[0].y(),4);
		assertEquals(shalom.getPoints()[1].x(),7);
		assertEquals(shalom.getPoints()[1].y(),8);

	}

	@Test
	void testScale() {
		//fail("Not yet implemented");
		Point2D cen= new Point2D (0,0);
		Point2D ans1= new Point2D (0.9,0.9);
		Point2D ans2= new Point2D (4.5,4.5);
		shalom.scale(cen, 0.9);
		assertEquals(shalom.getPoints()[0],ans1);
		assertEquals(shalom.getPoints()[1],ans2);
		
		shalom= new Segment2D(p1,p2);
		ans1= new Point2D (1.1,1.1);
		ans2= new Point2D (5.5,5.5);
		
		shalom.scale(cen, 1.1);
		assertEquals(shalom.getPoints()[0],ans1);
		assertEquals(shalom.getPoints()[1],ans2);
		
		shalom= new Segment2D(p1,p2);
		ans1= new Point2D (1,1);
		ans2= new Point2D (5,5);
		
		shalom.scale(cen, -1);
		assertEquals(p1,ans1);
		assertEquals(p2,ans2);
	}

	@Test
	void testRotate() {
		//fail("Not yet implemented");
		Point2D p1 = new Point2D(-5, -5);
		Point2D p2 = new Point2D(0, 0);
		shalom.rotate(p2, 180);
		boolean b= (Math.abs(p1.distance(shalom.getPoints()[1]))<Ex4_Const.EPS) ? true:false;
		assertTrue(b);
	}

	@Test
	void testGetPoints() {
		//fail("Not yet implemented");
		Point2D [] test1= new Point2D [2];
		test1[0]=p1;
		test1[1]=p2;
		assertArrayEquals(test1,shalom.getPoints());
	}
	@Test
	void testToString() {
		String s= shalom.toString();
		String sTest= "1.0,1.0,5.0,5.0";
		assertEquals(s.compareTo(sTest),0);
	}

}
