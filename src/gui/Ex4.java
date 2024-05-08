package gui;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import ex4.Ex4_Const;
import ex4.GUIShape;
import ex4.GUI_Shapeable;
import ex4.ShapeCollection;
import ex4.ShapeCollectionable;
import geo.Circle2D;
import geo.GeoShapeable;
import geo.Point2D;
import geo.Polygon2D;
import geo.Rect2D;
import geo.Segment2D;
import geo.ShapeComp;
import geo.Triangle2D;

/**
 * 
 * This class is a simple "inter-layer" connecting (aka simplifying) the
 * StdDraw with the Map class.
 * Written for 101 java course it uses simple static functions to allow a 
 * "Singleton-like" implementation.
 * @author boaz.benmoshe
 *
 */
public class Ex4 implements Ex4_GUI{
	private  ShapeCollectionable _shapes = new ShapeCollection();
	private  GUI_Shapeable _gs;
	private  Color _color = Color.blue;
	private  boolean _fill = false;
	private  String _mode = "";
	private  Point2D _p1;	//save the previous click
	private  Point2D _p2;	//save the previous previous click
	private  static Ex4 _winEx4 = null;
	private ArrayList<Point2D> polyPoints= new ArrayList<>();
	private Ex4() {
		init(null);
	}
	public void init(ShapeCollectionable s) {
		if(s==null) {_shapes = new ShapeCollection();}
		else {_shapes = s.copy();}
		GUI_Shapeable _gs = null;
		Polygon2D _pp = null;
		_color = Color.blue;
		_fill = false;
		_mode = "";
		Point2D _p1 = null;
		Point2D _p2 = null;
	}
	public void show(double d) {
		StdDraw_Ex4.setScale(0,d);
		StdDraw_Ex4.show();
		drawShapes();
	}
	public static Ex4 getInstance() {
		if(_winEx4 ==null) {
			_winEx4 = new Ex4();
		}
		return _winEx4;
	}

	public void drawShapes() {
		StdDraw_Ex4.clear();
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable sh = _shapes.get(i);

			drawShape(sh);
		}
		if(_gs!=null) {drawShape(_gs);}
		StdDraw_Ex4.show();
	}
	private static void drawShape(GUI_Shapeable g) {
		StdDraw_Ex4.setPenColor(g.getColor());
		if(g.isSelected()) {StdDraw_Ex4.setPenColor(Color.gray);}
		GeoShapeable gs = g.getShape();
		boolean isFill = g.isFilled();
		if(gs instanceof Circle2D) {
			Circle2D c = (Circle2D)gs;
			Point2D cen = c.getPoints()[0];
			double rad = c.getRadius();
			if(isFill) {
				StdDraw_Ex4.filledCircle(cen.x(), cen.y(), rad);
			}
			else { 
				StdDraw_Ex4.circle(cen.x(), cen.y(), rad);
			}
		}
		if(gs instanceof Segment2D)			
		{
			Segment2D seg= (Segment2D) gs;
			Point2D p1 = seg.getPoints()[0];
			Point2D p2 = seg.getPoints()[1];
			StdDraw_Ex4.line(p1.x(), p1.y(), p2.x(), p2.y());								//draws the segment
		}
		if(gs instanceof Rect2D)			
		{
			Rect2D rect= (Rect2D) gs;
			Point2D p1 = rect.getPoints()[0];
			Point2D p2 = rect.getPoints()[1];
			double halfWidth= Math.abs(p1.x()-p2.x())/2;
			double halfHeight= Math.abs(p1.y()-p2.y())/2;
			if(isFill) {
				StdDraw_Ex4.filledRectangle(p1.x(), p1.y(), halfWidth,halfHeight);			//draws and fills the rectangle
			}
			else {
				StdDraw_Ex4.rectangle(p1.x(), p1.y(), halfWidth,halfHeight);				//draws the rectangle
			}
		}
		if(gs instanceof Triangle2D)
		{
			Triangle2D tri= (Triangle2D) gs;
			double [] x= new double [3];
			double [] y= new double [3];
			for (int i=0;i<3;i++)
			{
				x[i]= tri.getPoints()[i].x();
				y[i]= tri.getPoints()[i].y();
			}

			if(isFill){
				StdDraw_Ex4.filledPolygon(x, y);											//draws and fills the triangle
			}
			else{
				StdDraw_Ex4.polygon(x, y);													//draws the triangle
			}
		}
		if(gs instanceof Polygon2D) {
			Polygon2D poly = (Polygon2D)gs;
			if(isFill) {
				StdDraw_Ex4.filledPolygon( poly.getXs(),  poly.getYs());
			}
			else {
				StdDraw_Ex4.polygon( poly.getXs(),  poly.getYs());
			}
		}
	}
	private void setColor(Color c) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			if(s.isSelected()) {
				s.setColor(c);
			}
		}
	}
	private void setFill() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			if(s.isSelected()) {
				s.setFilled(_fill);
			}
		}
	}

	public void actionPerformed(String p) {
		_mode = p;
		if(p.equals("Blue")) {_color = Color.BLUE; setColor(_color);}
		if(p.equals("Red")) {_color = Color.RED; setColor(_color);}
		if(p.equals("Green")) {_color = Color.GREEN; setColor(_color);}
		if(p.equals("White")) {_color = Color.WHITE; setColor(_color);}
		if(p.equals("Black")) {_color = Color.BLACK; setColor(_color);}
		if(p.equals("Yellow")) {_color = Color.YELLOW; setColor(_color);}
		if(p.equals("Fill")) {_fill = true; setFill();}
		if(p.equals("Empty")) {_fill = false; setFill();}
		if(p.equals("Clear")) {_shapes.removeAll();}

		if(p.equals("ByArea")) {_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Area));}						//sends to the function that sorts by area
		if(p.equals("ByAntiArea")) {_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Anti_Area));}				//sends to the function that sorts by the opposite way from the one that sorts by area
		if(p.equals("ByPerimeter")) {_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Perimeter));}				//sends to the function that sorts by perimeter
		if(p.equals("ByAntiPerimeter")) {_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Anti_Perimeter));}	//sends to the function that sorts by the opposite way from the one that sorts by perimeter
		if(p.equals("ByTag")) {_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Tag));}							//sends to the function that sorts by tag
		if(p.equals("ByAntiTag")) {_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Anti_Tag));}				//sends to the function that sorts by the opposite way from the one that sorts by tag
		if(p.equals("ByToString")) {_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_toString));}				//sends to the function that sorts by to string
		if(p.equals("ByAntiToString")) {_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Anti_toString));}		//sends to the function that sorts by the opposite way from the one that sorts by to string

		if(p.equals("None")) 
		{
			for(int i=0;i<_shapes.size();i++) 																//goes on every shape and unselects it
			{
				GUI_Shapeable s = _shapes.get(i);
				s.setSelected(false);
			}
		}

		if(p.equals("All")) 																				//goes on every shape and selects it
		{
			for(int i=0;i<_shapes.size();i++)
			{
				GUI_Shapeable s = _shapes.get(i);
				s.setSelected(true);
			}
		}

		if(p.equals("Anti"))																				//selects the unselected shapes
		{
			for(int i=0;i<_shapes.size();i++) 
			{
				GUI_Shapeable s = _shapes.get(i);
				if(s.isSelected()) {
					s.setSelected(false);
				}
				else {
					s.setSelected(true);
				}
			}

		}

		if(p.equals("Info")) 																			//get and print information
		{
			String str = getInfo();
			System.out.println(str);
		}
		if(p.equals("Save")) 
		{
			JFileChooser fileChooser = new JFileChooser();												//Create a new instance ofJFileChooser class
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));					//Set current directory
			int frame = fileChooser.showSaveDialog(StdDraw_Ex4.getFrame());							//checks the frame form of the file
			if (frame == JFileChooser.APPROVE_OPTION) 													//checks if the form is the same as the form that supported by JFileChooser
			{
				try 
				{
					fileChooser.getSelectedFile().getAbsoluteFile().createNewFile();					//try to create new file with the selected data
				}
				catch (IOException e) 																	//throw an exception
				{
					e.printStackTrace();
				}
				_shapes.save(fileChooser.getSelectedFile().getPath());									//save the file
			}
		}
		if(p.equals("Load")) 																			//load the file
		{
			JFileChooser fileChooser2= new JFileChooser();
			int frame2= fileChooser2.showOpenDialog(StdDraw_Ex4.getFrame());
			if (frame2==JFileChooser.APPROVE_OPTION)
			{
				try
				{
					fileChooser2.getSelectedFile().getAbsoluteFile().createNewFile();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				_shapes.load(fileChooser2.getSelectedFile().getPath());
			}
		}
		drawShapes();
	}
	public void mouseClicked(Point2D p) 
	{
		System.out.println("Mode: "+_mode+"  "+p);
		if(_mode.equals("Circle") || _mode.equals("Segment") || _mode.equals("Rect")) 
		{
			if(_gs==null) 																			//if the mode is circle, segment or rect and the shape hase'nt painted yet, save the click
			{
				_p1 = new Point2D(p);
			}

			else 
			{
				_gs.setColor(_color);																//if p1 saved the clicks, paint the shape
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;																			//reset gs
				_p1 = null;																			//reset p1
			}

		}
		if(_mode.equals("Triangle"))
		{
			if(_gs==null) 																			//if the mode is triangle, and the shape hase'nt painted yet, save the click
			{
				_p1 = new Point2D(p); 
			}
			else if (_p2==null) 																	//if p1 saved the privious click, p2 saves the corrent click
			{
				_p2 = new Point2D(p);
			}

			else 																					//if p1, p2 saved the clicks, paint the shape
			{
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;																			//reset gs
				_p1 = null;																			//reset p1
				_p2= null;																			//reset p2
			}
		}

		if(_mode.equals("Polygon"))
		{
			if(_gs==null) 																			//if the mode is triangle, and the shape hase'nt painted yet, save the click
			{
				polyPoints.add(p); 
				_p1= new Point2D(p);
			}
			else 																	//if p1 saved the privious click, p2 saves the corrent click
			{
				polyPoints.add(p); 
			}
		}

		if(_mode.equals("Move")) {
			if(_p1==null) {_p1 = new Point2D(p);}
			else {
				_p1 = new Point2D(p.x()-_p1.x(), p.y()-_p1.y());
				move();
				_p1 = null;
			}
		}

		if(_mode.equals("Point")) {
			select(p);
		}
		if(_mode.equals("Remove")) 																//goes over the shape collectionable's size with i and remove the object on the i's position
		{
			for (int i=_shapes.size()-1; i>=0;--i)
			{  
				GUI_Shapeable s = _shapes.get(i);            
				if (s.isSelected()) {                        
					_shapes.removeElementAt(i);             
				}
			}
		}
		if(_mode.equals("Copy")) 
		{
			if(_p1==null) {_p1 = new Point2D(p);}
			else {
				_p1 = new Point2D(p.x()-_p1.x(), p.y()-_p1.y());
				copy();
				_p1 = null;
			}
		}

		if(_mode.equals("Scale_90%"))
		{
			_p1 = new Point2D(p);
			for(int i=0;i<_shapes.size();i++) 												//goes over the shape collectionable's size and send it to scale if it was selected
			{
				GUI_Shapeable s = _shapes.get(i);
				GeoShapeable g = s.getShape();
				if(s.isSelected() && g!=null)
				{
					g.scale(p, 0.9);
				}
			}
		}
		if(_mode.equals("Scale_110%"))
		{
			_p1 = new Point2D(p);
			for(int i=0;i<_shapes.size();i++)											 //goes over the shape collectionable's size and send it to scale if it was selected
			{
				GUI_Shapeable s = _shapes.get(i);
				GeoShapeable g = s.getShape();
				if(s.isSelected() && g!=null)
				{
					g.scale(p, 1.1);
				}
			}
		}
		if(_mode.equals("Rotate"))
		{
			if (_p1==null)													//if the shape haven't painted yet, save the click
			{
				_p1=p;
			}
			else		  													//save the click and send it to rotate
			{
				rotate(_p1,p);
				_p1= null;
			}
		}

		drawShapes();
	}
	public void rotate(Point2D cen,Point2D p)								//goes over the shape collectionable's size shape by shape and send the shape to rotate if it was selected
	{
		for(int i=0;i<_shapes.size();i++) 
		{
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(s.isSelected() && g!=null) 
			{
				if(g instanceof Rect2D)										//in case of a rectangle, turn it into a polygon to rotate it properly (uses the function below)
				{
					g= convertToPolygon(g);
					s.setShape(g);
				}
				g.rotate(cen, Math.toDegrees(cen.angle(p)));
			}
		}

	}
	public Polygon2D convertToPolygon(GeoShapeable g)					//convert a rect to polygon
	{
		if(g instanceof Rect2D)
		{
			ArrayList<Point2D> _points;
			_points= new ArrayList<>();
			Point2D [] arrayPoints= new Point2D [4];
			arrayPoints[0]= g.getPoints()[0];
			arrayPoints[1]= new Point2D (g.getPoints()[0].x(),g.getPoints()[1].y());
			arrayPoints[2]= g.getPoints()[1];
			arrayPoints[3]= new Point2D (g.getPoints()[1].x(),g.getPoints()[0].y());
			for (int i=0; i<arrayPoints.length;i++)
			{
				_points.add(arrayPoints[i]);
			}
			return new Polygon2D (_points);
		}
		return null;
	}
	private void select(Point2D p) 
	{
		for(int i=0;i<_shapes.size();i++)
		{
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(g!=null && g.contains(p)) {
				s.setSelected(!s.isSelected());
			}
		}
	}
	private void move()
	{
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(s.isSelected() && g!=null) {
				g.move(_p1);
			}
		}
	}

	private void copy()											//copy the selected shapes and move them
	{	
		for(int i=0;i<_shapes.size();i++) 
		{
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(s.isSelected() && g!=null) 
			{
				GUI_Shapeable ns= s.copy();
				GeoShapeable ng= ns.getShape();
				ng.move(_p1);
				_shapes.add(ns);
			}
		}
	}

	public void mouseRightClicked(Point2D p) {
		System.out.println("right click!");

		if (_mode.equals("Polygon")&&_p1!= null)
		{
			Polygon2D poly= new Polygon2D (polyPoints);
			_gs= new GUIShape(poly,_fill,_color,0);
			_shapes.add(_gs);
			_gs= null;
			_p1=null;
			polyPoints.clear();
			drawShapes();
		}
	}
	public void mouseMoved(MouseEvent e) 
	{
		if(_p1!=null) 
		{
			double x1 = StdDraw_Ex4.mouseX();
			double y1 = StdDraw_Ex4.mouseY();
			GeoShapeable gs = null;
			Point2D p = new Point2D(x1,y1);
			if(_mode.equals("Circle")) {
				double r = _p1.distance(p);											//draw a circle
				gs = new Circle2D(_p1,r);
			}
			if(_mode.equals("Segment")) {
				gs = new Segment2D(_p1,p);											//draw a segment
			}
			if(_mode.equals("Rect")) {
				gs = new Rect2D(_p1,p); 											//draw a rect
			}
			if(_mode.equals("Triangle")) 
			{
				if (_p2==null)
				{
					gs = new Segment2D(_p1,p);										//if there is'nt three points yet,draw a segment
				}
				else
				{
					gs = new Triangle2D(_p1,p,_p2);									//if there is three points, draw the triangle
				}
			}

			if(_mode.equals("Polygon")) 
			{
				Polygon2D poly= new Polygon2D (polyPoints);
				poly.addPoint(p);
				gs= poly;
			}
			_gs = new GUIShape(gs,false, Color.pink, 0);
			drawShapes();
		}
	}
	@Override
	public ShapeCollectionable getShape_Collection() {
		// TODO Auto-generated method stub
		return this._shapes;
	}
	@Override
	public void show() {show(Ex4_Const.DIM_SIZE); }
	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		String ans = "";
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			ans +=s.toString()+"\n";
		}
		return ans;
	}
}
