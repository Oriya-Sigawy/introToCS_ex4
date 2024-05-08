package ex4.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ex4.Ex4_Const;
import geo.Circle2D;
import geo.Point2D;
import geo.Triangle2D;

class Circle2DTest {
	Point2D p3= new Point2D (1,1);
	Circle2D rony= new Circle2D(p3,10);
	@Test
	void testScale() {
		//fail("Not yet implemented");
		Point2D cen= new Point2D (0,0);
		
		Point2D ans1= new Point2D (0.9,0.9);
		rony.scale(cen, 0.9);
		assertEquals(rony.getPoints()[0],ans1);
		
		rony= new Circle2D(p3,10);
		ans1= new Point2D (1.1,1.1);
		rony.scale(cen, 1.1);
		assertEquals(rony.getPoints()[0],ans1);
		
		ans1= new Point2D (1,1);
		rony= new Circle2D(p3,10);
		rony.scale(cen, -0.9);
		assertEquals(rony.getPoints()[0],ans1);
		
	}

	@Test
	void testRotate() {
		//fail("Not yet implemented");
		Point2D p = new Point2D(5, 5);
		Point2D p1 = new Point2D(-5, -5);
		Point2D p2 = new Point2D(0, 0);
		p.rotate(p2, 180);
		boolean b= (Math.abs(p1.distance(p))<Ex4_Const.EPS) ? true:false;
		assertTrue(b);
	}

}
