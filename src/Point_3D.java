/**
 *
 * @author Monty Vanderlinde
 * @version 19 October 2017
 * 
 * A class that encapsulates a vertex in a 3D tree as a point.
 * 
 */
public class Point_3D
{
    private double xCoordinate;
    private double yCoordinate;
    private double zCoordinate;
    
    private Point_3D backLeftSubPoint;
    private Point_3D backRightSubPoint;
    private Point_3D frontLeftSubPoint;
    private Point_3D frontRightSubPoint;
    
    private Point_3D(){}
    
    public Point_3D(double xCoordinate, double yCoordinate, double zCoordinate)
    {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.zCoordinate = zCoordinate;
        
        this.backLeftSubPoint = null;
        this.backRightSubPoint = null;
        this.frontLeftSubPoint = null;
        this.frontRightSubPoint = null;
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
    
    public Point_3D getBackLeftSubPoint()
    {
        return this.backLeftSubPoint;
    }
    
    private void setBackLeftSubPoint(Point_3D subPoint)
    {
        this.backLeftSubPoint = subPoint;
    }
    
    public Point_3D getBackRightSubPoint()
    {
        return this.backRightSubPoint;
    }
    
    private void setBackRightSubPoint(Point_3D subPoint)
    {
        this.backRightSubPoint = subPoint;
    }
    
    public Point_3D getFrontLeftSubPoint()
    {
        return this.frontLeftSubPoint;
    }
    
    private void setFrontLeftSubPoint(Point_3D subPoint)
    {
        this.frontLeftSubPoint = subPoint;
    }
    
    public Point_3D getFrontRightSubPoint()
    {
        return this.frontRightSubPoint;
    }
    
    private void setFrontRightSubPoint(Point_3D subPoint)
    {
        this.frontRightSubPoint = subPoint;
    }
    
    /**
     * Recursively builds a 3D tree given the coordinated of the head point and number of points to support.
     * @param x The xCoordinate of the head point.
     * @param y The yCoordinate of the head point.
     * @param z The zCoordinate of the head point.
     * @param numberOfSupportPoints The number of points that the head point supports.  Must be a power of 4.
     * @param gridUnitLength The length of a single grid unit.  Must be an even number, but will add 1 if not.
     * @return The head point of the tree.
     */
    public Point_3D buildTreeRecursive(double x, double y, double z, int numberOfSupportPoints, int gridUnitLength)
    {
        //base case
        if(numberOfSupportPoints == 1)
            return null;
        
        //make sure numberOfSupportPoints is a power of 4
        if(!this.isPowerOfFour(numberOfSupportPoints))
            throw new IllegalArgumentException("buildTree only works with powers of 4");
        
        //if any of the subPoints are null
        if((this.backLeftSubPoint == null) || (this.backRightSubPoint == null) || (this.frontLeftSubPoint == null) || (this.frontRightSubPoint == null))
        {
            //and if any of the points are not null at the same time
            if((this.backLeftSubPoint != null) || (this.backRightSubPoint != null) || (this.frontLeftSubPoint != null) || (this.frontRightSubPoint != null))
            {
                //all points or no points must be instatiated
                throw new RuntimeException("All subPoints are not instantiated.  Problem with the class.");
            }
        }
        
        //check gridUnitLenght
        if(gridUnitLength < 1)
            throw new IllegalArgumentException("gridUnitLength cannot be 0 or negative");
        
        //get copy that can be modified
        int length = gridUnitLength;
        
        //increse by 1 if length is odd
        if((length % 2) == 1)
            length += 1;
        
        //create head point
        Point_3D head = new Point_3D(x, y, z);
        
        //each of the four subpoints will support 1/4 of the points
        int subPointsSupportPoints = numberOfSupportPoints / 4;
        
        //used to determine subpoints' positions
        int delta;
        
        if(numberOfSupportPoints == 4)
            delta = length / 2;
        else
        {
            delta = this.getSquareRoot_PowersOfFour(numberOfSupportPoints) / 4;
        }
        
        head.setBackLeftSubPoint(this.buildTreeRecursive(x - delta, y - delta, z - delta, subPointsSupportPoints, length));
        head.setBackRightSubPoint(this.buildTreeRecursive(x + delta, y - delta, z - delta, subPointsSupportPoints, length));
        head.setFrontLeftSubPoint(this.buildTreeRecursive(x - delta, y - delta, z + delta, subPointsSupportPoints, length));
        head.setFrontRightSubPoint(this.buildTreeRecursive(x + delta, y - delta, z + delta, subPointsSupportPoints, length));
        
        
        return head;
    }
    
    private int getSquareRoot_PowersOfFour(int n)
    {
        if(n < 4)
            throw new IllegalArgumentException("getSquareRoot_PowersOfFour does not work on numbers less than 4");
        
        if(n > 1073741824)
            throw new IllegalArgumentException("getSquareRoot_PowersOfFour does not work on numbers greater than 1,073,741,824");
        
        int i = 1;
        
        do
        {
            i *= 2;
            
            if(n == (i * i))
                return i;
            
            if(n < (i * i))
                throw new IllegalArgumentException("getSquareRoot_PowersOfFour only works on powers of four");
        }while((i * i) < 1073741824);
        
        throw new RuntimeException("Should not be able to reach here.  Problem with the program");
    }
    
    private boolean isPowerOfFour(int n) throws IllegalArgumentException
    {
        if(n < 4)
            throw new IllegalArgumentException("isPowerOfFour does not work on numbers less than 4");
        
        int i = 1;
        
        do
        {
            i *= 4;
            
            if(n == i)
                return true;
            
            if(n < i)
                return false;
        }while(i < 1073741824);
        
        return false;
    }
    
    private static class Client
    {
        public static void main(String[] args)
        {
            Point_3D temp = new Point_3D(7.5, 7.5, 7.5);
            
            Point_3D headPoint = temp.buildTreeRecursive(temp.getXCoordinate(), temp.getYCoordinate(), temp.getZCoordinate(), 256, 30);
        
        }
    }
}