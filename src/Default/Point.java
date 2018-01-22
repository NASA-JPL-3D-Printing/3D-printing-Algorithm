package Default;

/**
 *
 * @author Monty Vanderlinde
 * @version 16 November 2017
 */
public class Point
{
    private double xCoordinate;
    private double yCoordinate;
    private double zCoordinate;
    
    private Point(){}
    
    public Point(double xCoordinate, double yCoordinate, double zCoordinate)
    {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.zCoordinate = zCoordinate;
    }
    
    public double getXCoordinate()
    {
        return this.xCoordinate;
    }
    
    public void setXCoordinate(double xCoordinate)
    {
        this.xCoordinate = xCoordinate;
    }
    
    public double getYCoordinate()
    {
        return this.yCoordinate;
    }
    
    public void setYCoordinate(double yCoordinate)
    {
        this.yCoordinate = yCoordinate;
    }
    
    public double getZCoordinate()
    {
        return this.zCoordinate;
    }
    
    public void setZCoordinate(double zCoordinate)
    {
        this.zCoordinate = zCoordinate;
    }
}