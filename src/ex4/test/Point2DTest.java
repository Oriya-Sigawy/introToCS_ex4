package ex4.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import geo.Point2D;
import gui.Ex4;

import ex4.Ex4_Const;

class Point2DTest {

	@Test
	void rotate() {
		Point2D p = new Point2D(5, 5);
		Point2D p1 = new Point2D(-5, -5);
		Point2D p2 = new Point2D(0, 0);
		p.rotate(p2, 180);
		boolean b= (Math.abs(p1.distance(p))<Ex4_Const.EPS) ? true:false;
		assertTrue(b);
	}

	@Test
	void testScale() {
		Point2D p = new Point2D(15, 25);
		Point2D p1 = new Point2D(25, 15);
		Point2D p2 = new Point2D(20, 20);
		Point2D ans1= new Point2D (14.5,25.5);
		p.scale(p2, 1.1);
		boolean bool= (p.distance(ans1)<Ex4_Const.EPS) ? true:false;
		assertTrue(bool);	
	}

	@Test
	void testAngle()
	{
		Point2D p = new Point2D(5, 5);
		Point2D p2 = new Point2D(0, 0);
		double alpha= p.angle(p2);
		assertTrue(alpha-0.78539<= Ex4_Const.EPS1);
	}
}
