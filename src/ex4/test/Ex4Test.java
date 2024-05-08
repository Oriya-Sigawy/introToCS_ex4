package ex4.test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import ex4.Ex4_Const;
import ex4.GUIShape;
import ex4.ShapeCollection;
import ex4.ShapeCollectionable;
import geo.GeoShapeable;
import geo.Point2D;
import geo.Polygon2D;
import geo.Rect2D;
import geo.Triangle2D;
import gui.Ex4;

class Ex4Test {

	@Test
	void testRotate() 
	{
		//fail("Not yet implemented");
		Point2D p1= new Point2D (-1,2);
		Point2D po1= new Point2D (1,-2);
		Point2D p2= new Point2D (1,2);
		Point2D po2= new Point2D (-1,-2);
		Point2D p5= new Point2D (0,4);
		Point2D po5= new Point2D (0,-4);
		Point2D p3= new Point2D (0,0);
		Point2D p4= new Point2D (-2,0);
		Triangle2D tri= new Triangle2D (p1,p2,p5);
		GeoShapeable g=  (GeoShapeable) tri;
		Color col= new Color (0);
		GUIShape gs= new GUIShape (g,false,col,0);
		Ex4 ex4 = Ex4.getInstance();
		ShapeCollectionable sc= new ShapeCollection();
		sc.add(gs);
		ex4.init(sc);
		ex4.getShape_Collection().get(0).setSelected(true);
		ex4.rotate(p3, p4);
		assertTrue(po1.distance(ex4.getShape_Collection().get(0).getShape().getPoints()[0])<=Ex4_Const.EPS1);
		assertTrue(po2.distance(ex4.getShape_Collection().get(0).getShape().getPoints()[1])<=Ex4_Const.EPS1);
		assertTrue(po5.distance(ex4.getShape_Collection().get(0).getShape().getPoints()[2])<=Ex4_Const.EPS1);
	}

	@Test
	void testConvertToPolygon() 
	{
		//fail("Not yet implemented");
		Point2D p1= new Point2D (-1,2);
		Point2D p2= new Point2D (1,4);
		Rect2D rect= new Rect2D (p1,p2);
		GeoShapeable g=  (GeoShapeable) rect;
		Ex4 ex4 = Ex4.getInstance();
		Polygon2D poly= ex4.convertToPolygon(g);
		assertEquals(poly.getPoints()[0].x(),p1.x());
		assertEquals(poly.getPoints()[0].y(),p1.y());
		assertEquals(poly.getPoints()[2].x(),p2.x());
		assertEquals(poly.getPoints()[2].y(),p2.y());
	}

	@Test
	void testCopy() {
		//fail("Not yet implemented");
		Point2D p1= new Point2D (-1,2);
		Point2D p2= new Point2D (1,2);
		Point2D p3= new Point2D (0,4);
		Point2D po1= new Point2D (0,7);
		Point2D po2= new Point2D (2,7);
		Point2D po3= new Point2D (1,9);
		Point2D p4= new Point2D (1,5);
		Point2D p5= new Point2D (0,0);
		Triangle2D tri= new Triangle2D (p1,p2,p3);
		GeoShapeable g=  (GeoShapeable) tri;
		Color col= new Color (0);
		GUIShape gs= new GUIShape (g,false,col,0);
		Ex4 ex4 = Ex4.getInstance();
		ShapeCollectionable sc= new ShapeCollection();
		sc.add(gs);
		ex4.init(sc);
		ex4.getShape_Collection().get(0).setSelected(true);
		ex4.actionPerformed("Copy");
		ex4.mouseClicked(p5);
		ex4.mouseClicked(p4);
		assertTrue(po1.distance(ex4.getShape_Collection().get(1).getShape().getPoints()[0])<=Ex4_Const.EPS1);
		assertTrue(po2.distance(ex4.getShape_Collection().get(1).getShape().getPoints()[1])<=Ex4_Const.EPS1);
		assertTrue(po3.distance(ex4.getShape_Collection().get(1).getShape().getPoints()[2])<=Ex4_Const.EPS1);
	}
}
