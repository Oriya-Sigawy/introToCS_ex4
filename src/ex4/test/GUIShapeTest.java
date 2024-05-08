package ex4.test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import ex4.GUIShape;
import geo.Circle2D;
import geo.GeoShapeable;
import geo.Point2D;

class GUIShapeTest {

	@Test
	void testSetShape() {
		//fail("Not yet implemented");
	
		Point2D cen= new Point2D (3,3);
		Point2D cen2= new Point2D (3,4);
		double rad= 1;
		Circle2D c=  new Circle2D (cen,rad);
		Color col= new Color(-16777216);
		GeoShapeable g=  (GeoShapeable) (c);
		GUIShape gs= new GUIShape (g,false,col,0);
		assertTrue (gs.getShape().getPoints()[0].y()==cen.y());
		assertTrue (gs.getShape().getPoints()[0].x()==cen.x());
		assertTrue (gs.getShape().getPoints()[1].x()==cen2.x());
		assertTrue (gs.getShape().getPoints()[1].y()==cen2.y());
	}

}
