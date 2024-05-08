package ex4.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ex4.Ex4_Const;
import geo.Point2D;
import geo.Rect2D;
import geo.Triangle2D;

class Triangle2DTest {
	Point2D p1= new Point2D (1,1);
	Point2D p2= new Point2D (5,5);
	Point2D p3= new Point2D (5,1);
	Triangle2D niv= new Triangle2D(p1,p2,p3);
	@Test
	void testContains() {
		//fail("Not yet implemented");
		Point2D p4= new Point2D (4,2);
		Point2D p5= new Point2D (-4,2);
		Point2D p6= new Point2D (2,1);
		Point2D p7= new Point2D (4,8);
		assertTrue(niv.contains(p4));
		assertTrue(niv.contains(p6));
		assertFalse(niv.contains(p5));
		assertFalse(niv.contains(p7));

	}

	@Test
	void testArea() {
		//fail("Not yet implemented");
		assertEquals(8,niv.area());
	}

	@Test
	void testPerimeter() {
		//fail("Not yet implemented");
		double peri= 8+4*Math.sqrt(2);
		assertEquals(peri,niv.perimeter());
	}

	@Test
	void testMove() {
		//fail("Not yet implemented");
		Point2D vec= new Point2D (2,3);
		niv.move(vec);
		assertEquals(niv.getPoints()[0].x(),3);
		assertEquals(niv.getPoints()[0].y(),4);
		assertEquals(niv.getPoints()[1].x(),7);
		assertEquals(niv.getPoints()[1].y(),8);
		assertEquals(niv.getPoints()[2].x(),7);
		assertEquals(niv.getPoints()[2].y(),4);
	}
	@Test
	void testScale() {
		//fail("Not yet implemented");
		Point2D cen= new Point2D (0,0);
		Point2D ans1= new Point2D (0.9,0.9);
		Point2D ans2= new Point2D (4.5,4.5);
		Point2D ans3= new Point2D (4.5,0.9);
		
		niv.scale(cen, 0.9);
		assertEquals(niv.getPoints()[0],ans1);
		assertEquals(niv.getPoints()[1],ans2);
		assertEquals(niv.getPoints()[2],ans3);
		
		niv= new Triangle2D(p1,p2,p3);
		ans1= new Point2D (1.1,1.1);
		ans2= new Point2D (5.5,5.5);
		ans3= new Point2D (5.5,1.1);
		
		niv.scale(cen, 1.1);
		assertEquals(niv.getPoints()[0],ans1);
		assertEquals(niv.getPoints()[1],ans2);
		assertEquals(niv.getPoints()[2],ans3);
		
		niv= new Triangle2D(p1,p2,p3);
		ans1= new Point2D (1,1);
		ans2= new Point2D (5,5);
		ans3= new Point2D (5,1);
		
		niv.scale(cen, -1);
		assertEquals(p1,ans1);
		assertEquals(p2,ans2);
		assertEquals(p3,ans3);
	}

	@Test
	void testRotate() {
		//fail("Not yet implemented");
		Point2D p1 = new Point2D(-5, -5);
		Point2D p2 = new Point2D(0, 0);
		niv.rotate(p2, 180);
		boolean b= (Math.abs(p1.distance(niv.getPoints()[1]))<Ex4_Const.EPS) ? true:false;
		assertTrue(b);
	}

	@Test
	void testGetPoints() {
		//fail("Not yet implemented");
		Point2D [] test1= new Point2D [3];
		test1[0]=p1;
		test1[1]=p2;
		test1[2]=p3;
		assertArrayEquals(test1,niv.getPoints());
	}
	@Test
	void testToString() {
		String s= niv.toString();
		String sTest= "1.0,1.0,5.0,5.0,5.0,1.0";
		assertEquals(s.compareTo(sTest),0);
	}
	}
