package ex4.test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import ex4.GUIShape;
import ex4.GUI_Shapeable;
import ex4.ShapeCollection;
import geo.Circle2D;
import geo.Point2D;
import geo.Polygon2D;
import geo.Rect2D;
import geo.Segment2D;
import geo.ShapeComp;
import geo.Triangle2D;

class ShapeCollectionTest {
	ShapeCollection _shapes= new ShapeCollection();
	ShapeCollection _shapes2= new ShapeCollection();

	@Test
	void testShapeCollection()
	{
		//fail("Not yet implemented");
		///////////test addAt//////////////
		Point2D cen= new Point2D (3,3);
		Point2D cen2= new Point2D (3,4);
		double rad= 1;
		Circle2D c=  new Circle2D (cen,rad);
		Color col= new Color(0);
		GUIShape g= new GUIShape (c,false,col,0);
		_shapes.addAt(g,0);
		assertTrue (_shapes.get(0).getShape().getPoints()[0].x()==cen.x());
		assertTrue (_shapes.get(0).getShape().getPoints()[0].y()==cen.y());
		assertTrue (_shapes.get(0).getShape().getPoints()[1].x()==cen2.x());
		assertTrue (_shapes.get(0).getShape().getPoints()[1].y()==cen2.y());

		////////////test removeElementAt//////////////////
		_shapes.removeElementAt(0);
		assertTrue (_shapes.size()==0);

		////////////////test removeall
		_shapes.addAt(g,0);
		_shapes.addAt(g,1);
		_shapes.removeAll();
		assertTrue (_shapes.size()==0);

		////////////test sort///////////////////
		_shapes.addAt(g,0);
		Point2D p1= new Point2D (4,4);
		Triangle2D t=  new Triangle2D (cen,cen2,p1);
		GUIShape g2= new GUIShape (t,false,col,0);
		_shapes.add(g2);
		Rect2D r= new Rect2D (cen,p1);
		GUIShape g3= new GUIShape (r,false,col,0);
		_shapes.add(g3);
		_shapes.sort(ShapeComp.CompByToString);
		_shapes2.add(g);
		_shapes2.add(g3);
		_shapes2.add(g2);
		assertArrayEquals (_shapes.get(0).getShape().getPoints(),_shapes2.get(0).getShape().getPoints());
		assertArrayEquals (_shapes.get(1).getShape().getPoints(),_shapes2.get(1).getShape().getPoints());
		assertArrayEquals (_shapes.get(2).getShape().getPoints(),_shapes2.get(2).getShape().getPoints());
		//////////////////test copy///////////////
		_shapes.removeAll();
		_shapes=(ShapeCollection)_shapes2.copy();
		assertArrayEquals (_shapes.get(0).getShape().getPoints(),_shapes2.get(0).getShape().getPoints());
		assertArrayEquals (_shapes.get(1).getShape().getPoints(),_shapes2.get(1).getShape().getPoints());

		////////////////test bounding box///////////////////
		_shapes.removeAll();
		_shapes.add(g);
		Point2D p3= new Point2D (5,3);
		Point2D p4= new Point2D (7,5);
		Rect2D rect= new Rect2D (p3,p4);
		GUIShape g4= new GUIShape (rect,false,col,0);
		_shapes.add(g4);
		Rect2D test= _shapes.getBoundingBox();
		assertEquals (test.getPoints()[0].x(),2);
		assertEquals (test.getPoints()[0].y(),2);
		assertEquals (test.getPoints()[1].x(),7);
		assertEquals (test.getPoints()[1].y(),5);

		_shapes.removeAll();
		Point2D p5= new Point2D (-1,2);
		Point2D p6= new Point2D (1,4);
		Point2D p7= new Point2D (6,2);
		Point2D p8= new Point2D (8,2);
		Point2D p9= new Point2D (7,4);
		Rect2D rect2= new Rect2D (p5,p6);
		Triangle2D tri = new Triangle2D (p7,p8,p9);
		GUIShape g5= new GUIShape (rect2,false,col,0);
		GUIShape g6= new GUIShape (tri,false,col,0);
		_shapes.add(g5);
		_shapes.add(g6);
		Rect2D test2= _shapes.getBoundingBox();
		assertEquals (test2.getPoints()[0].x(),-1);
		assertEquals (test2.getPoints()[0].y(),2);
		assertEquals (test2.getPoints()[1].x(),8);
		assertEquals (test2.getPoints()[1].y(),4);

		////////////////////test save+load//////////////////////
		_shapes2.removeAll();
		_shapes.removeAll();
		
		Point2D p2= new Point2D (3,3);
		Point2D po2= new Point2D (3,4);
		Segment2D s=  new Segment2D (p2,po2);
		GUIShape g7= new GUIShape (s,false,col,0);
		
		 ArrayList<Point2D> _polyPoints = new ArrayList<Point2D>();
		 _polyPoints.add(p5);
		 _polyPoints.add(p6);
		 _polyPoints.add(p7);
		 _polyPoints.add(p8);
		 _polyPoints.add(p9);
		 Polygon2D poly= new Polygon2D (_polyPoints);
		 GUIShape g8= new GUIShape (poly,false,col,0);
		 
		_shapes2.add(g5);//rect
		_shapes2.add(g6);//triangle
		_shapes2.add(g);//circle
		_shapes2.add(g7);//segment
		_shapes2.add(g8);//polygon
		
		_shapes.add(g5);
		_shapes.add(g6);
		_shapes.add(g);
		_shapes.add(g7);
		_shapes.add(g8);
		_shapes.save("file");
		
		_shapes.removeAll();
		_shapes.load("file");
		assertArrayEquals (_shapes.get(0).getShape().getPoints(),_shapes2.get(0).getShape().getPoints());
		assertArrayEquals (_shapes.get(1).getShape().getPoints(),_shapes2.get(1).getShape().getPoints());
		assertArrayEquals (_shapes.get(2).getShape().getPoints(),_shapes2.get(2).getShape().getPoints());
		//assertArrayEquals (_shapes.get(3).getShape().getPoints(),_shapes2.get(3).getShape().getPoints());
		//assertArrayEquals (_shapes.get(4).getShape().getPoints(),_shapes2.get(4).getShape().getPoints());
	}

}
