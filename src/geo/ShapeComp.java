package geo;

import java.util.Comparator;

import ex4.Ex4_Const;
import ex4.GUI_Shapeable;

/**
 * This class represents a Comparator over GUI_Shapes - 
 * as a linear order over GUI_Shapes.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeComp implements Comparator<GUI_Shapeable>{
	
	public static final Comparator<GUI_Shapeable> CompByToString = new ShapeComp(Ex4_Const.Sort_By_toString);
	public static final Comparator<GUI_Shapeable> CompByAntiToString = new ShapeComp(Ex4_Const.Sort_By_Anti_toString);
	public static final Comparator<GUI_Shapeable> CompByArea = new ShapeComp(Ex4_Const.Sort_By_Area);
	public static final Comparator<GUI_Shapeable> CompByAntiArea = new ShapeComp(Ex4_Const.Sort_By_Anti_Area);
	public static final Comparator<GUI_Shapeable> CompByPerimeter = new ShapeComp(Ex4_Const.Sort_By_Perimeter);
	public static final Comparator<GUI_Shapeable> CompByAntiPerimeter = new ShapeComp(Ex4_Const.Sort_By_Anti_Perimeter);
	public static final Comparator<GUI_Shapeable> CompByTag = new ShapeComp(Ex4_Const.Sort_By_Tag);
	public static final Comparator<GUI_Shapeable> CompByAntiTag = new ShapeComp(Ex4_Const.Sort_By_Anti_Tag);
	private int _flag;
	public ShapeComp(int flag) {
		_flag = flag;

	}
	@Override
	public int compare(GUI_Shapeable o1, GUI_Shapeable o2) {
		int ans=0;
		if(_flag == Ex4_Const.Sort_By_toString) 
		{
			ans = o1.toString().compareTo(o2.toString());
		}
		else if(_flag == Ex4_Const.Sort_By_Anti_toString) 		//sorts in the opposite way from sort by to string
		{
			ans = o1.toString().compareTo(o2.toString()) *-1;
		}
		else if(_flag == Ex4_Const.Sort_By_Area)  				//sorts by the biggest area
		{
			ans = Double.compare(o1.getShape().area(), o2.getShape().area());
		}
		else if(_flag == Ex4_Const.Sort_By_Anti_Area)			//sorts in the opposite way from sort by area
		{
			ans = Double.compare(o1.getShape().area(), o2.getShape().area()) *-1;
		}
		else if(_flag == Ex4_Const.Sort_By_Perimeter) 			//sorts by the biggest perimeter
		{
			ans = Double.compare(o1.getShape().perimeter(), o2.getShape().perimeter());
		}
		else if(_flag == Ex4_Const.Sort_By_Anti_Perimeter)		//sorts in the opposite way from sort by perimeter
		{
			ans = Double.compare(o1.getShape().perimeter(), o2.getShape().perimeter()) *-1;
		}
		else if(_flag == Ex4_Const.Sort_By_Tag) 				//sorts by the tags
		{
			ans = Integer.compare(o1.getTag(), o2.getTag());
		}
		else if(_flag == Ex4_Const.Sort_By_Anti_Tag) 			//sorts in the opposite way from sort by tags
		{
			ans = Integer.compare(o1.getTag(), o2.getTag())*-1;
		}
		return ans;
	}

}
