package Default;

/**
 *
 * @author Monty Vanderlinde
 * @version 04 November 2017
 * 
 * A class for creating and drawing a box with infill.
 * 
 */
public class InfillBox
{
    private double xCoordinate;
    private double yCoordinate;
    private double zCoordinate;
    
    private double gridLength;
    
    private double skinThickness;
    
    private int numberOfPointsToSupport = 1024;
    
    private TreePoint leftPoint;
    private TreePoint rightPoint;
    private TreePoint topPoint;
    private TreePoint bottomPoint;
    private TreePoint frontPoint;
    private TreePoint backPoint;
    
    public InfillBox()
    {
        this.xCoordinate = 0.0;
        this.yCoordinate = 0.0;
        this.zCoordinate = 0.0;
        
        this.gridLength = 1.0;
        
        this.skinThickness = 1.0;
        
        this.leftPoint = null;
        this.rightPoint = null;
        this.topPoint = null;
        this.bottomPoint = null;
        this.frontPoint = null;
        this.backPoint = null;
    }
    
    public InfillBox(double xCoordinate, double yCoordinate, double zCoordinate, double gridLength, double skinThickness)
    {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.zCoordinate = zCoordinate;
        
        this.gridLength = gridLength;
        
        this.skinThickness = skinThickness;
        
        this.leftPoint = new TreePoint(this.xCoordinate - (this.gridLength / 2.0), this.yCoordinate, this.zCoordinate);
        this.rightPoint = new TreePoint(this.xCoordinate + (this.gridLength / 2.0), this.yCoordinate, this.zCoordinate);
        this.topPoint = new TreePoint(this.xCoordinate, this.yCoordinate + (this.gridLength / 2.0), this.zCoordinate);
        this.bottomPoint = new TreePoint(this.xCoordinate, this.yCoordinate - (this.gridLength / 2.0), this.zCoordinate);
        this.frontPoint = new TreePoint(this.xCoordinate, this.yCoordinate, this.zCoordinate + (this.gridLength / 2.0));
        this.backPoint = new TreePoint(this.xCoordinate, this.yCoordinate, this.zCoordinate - (this.gridLength / 2.0));
    }
    
    public void buildBox(boolean hasSkin)
    {
        
    }
}