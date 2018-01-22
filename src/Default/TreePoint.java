package Default;

/**
 *
 * @author Monty Vanderlinde
 * @version 16 November 2017
 * 
 * A class that encapsulates a point in a 3D tree.
 * 
 */
public class TreePoint extends Point
{
    private TreePoint backLeftSubPoint;
    private TreePoint backRightSubPoint;
    private TreePoint frontLeftSubPoint;
    private TreePoint frontRightSubPoint;
    
    private boolean isOnLeftFace;
    private boolean isOnRightFace;
    private boolean isOnFrontFace;
    private boolean isOnBackFace;
    
    private static double minWidth;
    
    private Vertex[] vertices;
    
    /**
     * Not for use!!!
     */
    private TreePoint()
    {
        super(0,0,0);
    }
    
    private TreePoint(double xCoordinate, double yCoordinate, double zCoordinate)
    {
        super(xCoordinate, yCoordinate, zCoordinate);
        
        this.backLeftSubPoint = null;
        this.backRightSubPoint = null;
        this.frontLeftSubPoint = null;
        this.frontRightSubPoint = null;
        
        this.vertices = null;
    }
    
    public TreePoint(double xCoordinate, double yCoordinate, double zCoordinate, double minWidth)
    {
        super(xCoordinate, yCoordinate, zCoordinate);
        
        TreePoint.minWidth = minWidth;
    }
    
    public TreePoint getBackLeftSubPoint()
    {
        return this.backLeftSubPoint;
    }
    
    private void setBackLeftSubPoint(TreePoint subPoint)
    {
        this.backLeftSubPoint = subPoint;
    }
    
    public TreePoint getBackRightSubPoint()
    {
        return this.backRightSubPoint;
    }
    
    private void setBackRightSubPoint(TreePoint subPoint)
    {
        this.backRightSubPoint = subPoint;
    }
    
    public TreePoint getFrontLeftSubPoint()
    {
        return this.frontLeftSubPoint;
    }
    
    private void setFrontLeftSubPoint(TreePoint subPoint)
    {
        this.frontLeftSubPoint = subPoint;
    }
    
    public TreePoint getFrontRightSubPoint()
    {
        return this.frontRightSubPoint;
    }
    
    private void setFrontRightSubPoint(TreePoint subPoint)
    {
        this.frontRightSubPoint = subPoint;
    }
    
    private void setNineVertexNumbers(double branchWidth)
    {
        this.vertices = new Vertex[9];
        this.vertices[0] = new Vertex(this.getXCoordinate() - (branchWidth / 2.0), this.getYCoordinate() - (branchWidth / 2.0), this.getZCoordinate() - (branchWidth / 2.0));
        this.vertices[1] = new Vertex(this.getXCoordinate(), this.getYCoordinate() - (branchWidth / 2.0), this.getZCoordinate() - (branchWidth / 2.0));
        this.vertices[2] = new Vertex(this.getXCoordinate() + (branchWidth / 2.0), this.getYCoordinate() - (branchWidth / 2.0), this.getZCoordinate() - (branchWidth / 2.0));
        this.vertices[3] = new Vertex(this.getXCoordinate() - (branchWidth / 2.0), this.getYCoordinate() - (branchWidth / 2.0), this.getZCoordinate());
        this.vertices[4] = new Vertex(this.getXCoordinate(), this.getYCoordinate() - (branchWidth / 2.0), this.getZCoordinate());
        this.vertices[5] = new Vertex(this.getXCoordinate() + (branchWidth / 2.0), this.getYCoordinate() - (branchWidth / 2.0), this.getZCoordinate());
        this.vertices[6] = new Vertex(this.getXCoordinate() - (branchWidth / 2.0), this.getYCoordinate() - (branchWidth / 2.0), this.getZCoordinate() + (branchWidth / 2.0));
        this.vertices[7] = new Vertex(this.getXCoordinate(), this.getYCoordinate() - (branchWidth / 2.0), this.getZCoordinate() + (branchWidth / 2.0));
        this.vertices[8] = new Vertex(this.getXCoordinate() + (branchWidth / 2.0), this.getYCoordinate() - (branchWidth / 2.0), this.getZCoordinate() + (branchWidth / 2.0));
    }
    
    private void setFourVertexNumbers(double branchWidth)
    {
        
    }
    
    /**
     * 
     * @param numberOfPointsSupported
     * @param gridUnitLength 
     * @param side 1 for left, 2 for right, 3 for top, 4 for bottom, 5 for front, 6 for back.  Anything else throws an exception.
     */
    public void buildTree(int numberOfPointsSupported, double gridUnitLength, int side)
    {
        if((side < 1) || (side > 6))
            throw new IllegalArgumentException("side must be a number from 1 to 6.");
        
        if(!(MathHelpers.isPowerOfFour(numberOfPointsSupported)))
            throw new IllegalArgumentException("numberOfPointsSupported must be a power of 4.");
        
        if(gridUnitLength < 0.0)
            throw new IllegalArgumentException("gridUnitLength cannot be a negative number.");
        
        switch(side)
        {
            case 1:
                this.buildLeftTreeRecursive(numberOfPointsSupported, gridUnitLength);
                break;
            case 2:
                this.buildRightTreeRecursive(numberOfPointsSupported, gridUnitLength);
                break;
            case 3:
                this.buildTopTreeRecursive(numberOfPointsSupported, gridUnitLength);
                break;
            case 4:
                this.buildBottomTreeRecursive(numberOfPointsSupported, gridUnitLength);
                break;
            case 5:
                this.buildFrontTreeRecursive(numberOfPointsSupported, gridUnitLength);
                break;
            case 6:
                this.buildBackTreeRecursive(numberOfPointsSupported, gridUnitLength);
                break;
        }
    }
    
    /**
     * 
     * @param numberOfSupportPoints
     * @param gridUnitLength 
     */
    private void buildLeftTreeRecursive(int numberOfSupportPoints, double gridUnitLength)
    {
        //base case
        if(numberOfSupportPoints == 1)
            return;
        
        //used to determine subpoints' positions
        double delta;
        
        if(numberOfSupportPoints == 4)
            delta = 0.5 * gridUnitLength;
        else
        {
            delta = (MathHelpers.getSquareRoot_PowersOfFour(numberOfSupportPoints) / 4.0) * gridUnitLength;
        }
        
        this.setBackLeftSubPoint(new TreePoint(this.getXCoordinate() - delta, this.getYCoordinate() + delta, this.getZCoordinate() - delta));
        this.getBackLeftSubPoint().buildLeftTreeRecursive(numberOfSupportPoints / 4, gridUnitLength);
        
        this.setBackRightSubPoint(new TreePoint(this.getXCoordinate() - delta, this.getYCoordinate() - delta, this.getZCoordinate() - delta));
        this.getBackRightSubPoint().buildLeftTreeRecursive(numberOfSupportPoints / 4, gridUnitLength);
        
        this.setFrontLeftSubPoint(new TreePoint(this.getXCoordinate() - delta, this.getYCoordinate() + delta, this.getZCoordinate() + delta));
        this.getFrontLeftSubPoint().buildLeftTreeRecursive(numberOfSupportPoints / 4, gridUnitLength);
        
        this.setFrontRightSubPoint(new TreePoint(this.getXCoordinate() - delta, this.getYCoordinate() - delta, this.getZCoordinate() + delta));
        this.getFrontRightSubPoint().buildLeftTreeRecursive(numberOfSupportPoints / 4, gridUnitLength);
    }
    
    /**
     * 
     * @param numberOfSupportPoints
     * @param gridUnitLength 
     */
    private void buildRightTreeRecursive(int numberOfSupportPoints, double gridUnitLength)
    {
        //base case
        if(numberOfSupportPoints == 1)
            return;
        
        //used to determine subpoints' positions
        double delta;
        
        if(numberOfSupportPoints == 4)
            delta = 0.5 * gridUnitLength;
        else
        {
            delta = (MathHelpers.getSquareRoot_PowersOfFour(numberOfSupportPoints) / 4.0) * gridUnitLength;
        }
        
        this.setBackLeftSubPoint(new TreePoint(this.getXCoordinate() + delta, this.getYCoordinate() + delta, this.getZCoordinate() - delta));
        this.getBackLeftSubPoint().buildRightTreeRecursive(numberOfSupportPoints / 4, gridUnitLength);
        
        this.setBackRightSubPoint(new TreePoint(this.getXCoordinate() + delta, this.getYCoordinate() - delta, this.getZCoordinate() - delta));
        this.getBackRightSubPoint().buildRightTreeRecursive(numberOfSupportPoints / 4, gridUnitLength);
        
        this.setFrontLeftSubPoint(new TreePoint(this.getXCoordinate() + delta, this.getYCoordinate() + delta, this.getZCoordinate() + delta));
        this.getFrontLeftSubPoint().buildRightTreeRecursive(numberOfSupportPoints / 4, gridUnitLength);
        
        this.setFrontRightSubPoint(new TreePoint(this.getXCoordinate() + delta, this.getYCoordinate() - delta, this.getZCoordinate() + delta));
        this.getFrontRightSubPoint().buildRightTreeRecursive(numberOfSupportPoints / 4, gridUnitLength);
    }
    
    /**
     * 
     * @param numberOfSupportPoints
     * @param gridUnitLength 
     */
    private void buildTopTreeRecursive(int numberOfSupportPoints, double gridUnitLength)
    {
        //base case
        if(numberOfSupportPoints == 1)
            return;
        
        //used to determine subpoints' positions
        double delta;
        
        if(numberOfSupportPoints == 4)
            delta = 0.5 * gridUnitLength;
        else
        {
            delta = (MathHelpers.getSquareRoot_PowersOfFour(numberOfSupportPoints) / 4.0) * gridUnitLength;
        }
        
        this.setBackLeftSubPoint(new TreePoint(this.getXCoordinate() - delta, this.getYCoordinate() + delta, this.getZCoordinate() - delta));
        this.getBackLeftSubPoint().buildTopTreeRecursive(numberOfSupportPoints / 4, gridUnitLength);
        
        this.setBackRightSubPoint(new TreePoint(this.getXCoordinate() + delta, this.getYCoordinate() + delta, this.getZCoordinate() - delta));
        this.getBackRightSubPoint().buildTopTreeRecursive(numberOfSupportPoints / 4, gridUnitLength);
        
        this.setFrontLeftSubPoint(new TreePoint(this.getXCoordinate() - delta, this.getYCoordinate() + delta, this.getZCoordinate() + delta));
        this.getFrontLeftSubPoint().buildTopTreeRecursive(numberOfSupportPoints / 4, gridUnitLength);
        
        this.setFrontRightSubPoint(new TreePoint(this.getXCoordinate() + delta, this.getYCoordinate() + delta, this.getZCoordinate() + delta));
        this.getFrontRightSubPoint().buildTopTreeRecursive(numberOfSupportPoints / 4, gridUnitLength);
    }
    
    /**
     * 
     * @param numberOfSupportPoints
     * @param gridUnitLength 
     */
    private void buildBottomTreeRecursive(int numberOfSupportPoints, double gridUnitLength)
    {
        //base case
        if(numberOfSupportPoints == 1)
            return;
        
        //set vertices for this point
        this.setNineVertexNumbers(minWidth);
        
        //used to determine subpoints' positions
        double delta;
        
        if(numberOfSupportPoints == 4)
            delta = 0.5 * gridUnitLength;
        else
        {
            delta = (MathHelpers.getSquareRoot_PowersOfFour(numberOfSupportPoints) / 4.0) * gridUnitLength;
        }
        
        this.setBackLeftSubPoint(new TreePoint(this.getXCoordinate() - delta, this.getYCoordinate() - delta, this.getZCoordinate() - delta));
        this.getBackLeftSubPoint().buildBottomTreeRecursive(numberOfSupportPoints / 4, gridUnitLength);
        
        this.setBackRightSubPoint(new TreePoint(this.getXCoordinate() + delta, this.getYCoordinate() - delta, this.getZCoordinate() - delta));
        this.getBackRightSubPoint().buildBottomTreeRecursive(numberOfSupportPoints / 4, gridUnitLength);
        
        this.setFrontLeftSubPoint(new TreePoint(this.getXCoordinate() - delta, this.getYCoordinate() - delta, this.getZCoordinate() + delta));
        this.getFrontLeftSubPoint().buildBottomTreeRecursive(numberOfSupportPoints / 4, gridUnitLength);
        
        this.setFrontRightSubPoint(new TreePoint(this.getXCoordinate() + delta, this.getYCoordinate() - delta, this.getZCoordinate() + delta));
        this.getFrontRightSubPoint().buildBottomTreeRecursive(numberOfSupportPoints / 4, gridUnitLength);
    }
    
    /**
     * 
     * @param numberOfSupportPoints
     * @param gridUnitLength 
     */
    private void buildFrontTreeRecursive(int numberOfSupportPoints, double gridUnitLength)
    {
        //base case
        if(numberOfSupportPoints == 1)
            return;
        
        //used to determine subpoints' positions
        double delta;
        
        if(numberOfSupportPoints == 4)
            delta = 0.5 * gridUnitLength;
        else
        {
            delta = (MathHelpers.getSquareRoot_PowersOfFour(numberOfSupportPoints) / 4.0) * gridUnitLength;
        }
        
        this.setBackLeftSubPoint(new TreePoint(this.getXCoordinate() - delta, this.getYCoordinate() - delta, this.getZCoordinate() + delta));
        this.getBackLeftSubPoint().buildFrontTreeRecursive(numberOfSupportPoints / 4, gridUnitLength);
        
        this.setBackRightSubPoint(new TreePoint(this.getXCoordinate() + delta, this.getYCoordinate() - delta, this.getZCoordinate() + delta));
        this.getBackRightSubPoint().buildFrontTreeRecursive(numberOfSupportPoints / 4, gridUnitLength);
        
        this.setFrontLeftSubPoint(new TreePoint(this.getXCoordinate() - delta, this.getYCoordinate() + delta, this.getZCoordinate() + delta));
        this.getFrontLeftSubPoint().buildFrontTreeRecursive(numberOfSupportPoints / 4, gridUnitLength);
        
        this.setFrontRightSubPoint(new TreePoint(this.getXCoordinate() + delta, this.getYCoordinate() + delta, this.getZCoordinate() + delta));
        this.getFrontRightSubPoint().buildFrontTreeRecursive(numberOfSupportPoints / 4, gridUnitLength);
    }
    
    /**
     * 
     * @param numberOfSupportPoints
     * @param gridUnitLength 
     */
    private void buildBackTreeRecursive(int numberOfSupportPoints, double gridUnitLength)
    {
        //base case
        if(numberOfSupportPoints == 1)
            return;
        
        //used to determine subpoints' positions
        double delta;
        
        if(numberOfSupportPoints == 4)
            delta = 0.5 * gridUnitLength;
        else
        {
            delta = (MathHelpers.getSquareRoot_PowersOfFour(numberOfSupportPoints) / 4.0) * gridUnitLength;
        }
        
        this.setBackLeftSubPoint(new TreePoint(this.getXCoordinate() - delta, this.getYCoordinate() - delta, this.getZCoordinate() - delta));
        this.getBackLeftSubPoint().buildBackTreeRecursive(numberOfSupportPoints / 4, gridUnitLength);
        
        this.setBackRightSubPoint(new TreePoint(this.getXCoordinate() + delta, this.getYCoordinate() - delta, this.getZCoordinate() - delta));
        this.getBackRightSubPoint().buildBackTreeRecursive(numberOfSupportPoints / 4, gridUnitLength);
        
        this.setFrontLeftSubPoint(new TreePoint(this.getXCoordinate() - delta, this.getYCoordinate() + delta, this.getZCoordinate() - delta));
        this.getFrontLeftSubPoint().buildBackTreeRecursive(numberOfSupportPoints / 4, gridUnitLength);
        
        this.setFrontRightSubPoint(new TreePoint(this.getXCoordinate() + delta, this.getYCoordinate() + delta, this.getZCoordinate() - delta));
        this.getFrontRightSubPoint().buildBackTreeRecursive(numberOfSupportPoints / 4, gridUnitLength);
    }
    
    /**
     * 
     * @param m
     * @param squareWidth
     * @param lineWidth 
     * @param side 
     */
    public void buildObj(OBJHandler m, double squareWidth, double lineWidth, int side)
    {
        m.addSquare_Centered(this, squareWidth);
        
        if(this.getBackLeftSubPoint() != null)
        {
            m.addDiagonalLine_Centered(this, this.getBackLeftSubPoint(), lineWidth, side);
            m.addDiagonalLine_Centered(this, this.getBackRightSubPoint(), lineWidth, side);
            m.addDiagonalLine_Centered(this, this.getFrontLeftSubPoint(), lineWidth, side);
            m.addDiagonalLine_Centered(this, this.getFrontRightSubPoint(), lineWidth, side);
            
            this.getBackLeftSubPoint().buildObj(m, squareWidth, lineWidth, side);
            this.getBackRightSubPoint().buildObj(m, squareWidth, lineWidth, side);
            this.getFrontLeftSubPoint().buildObj(m, squareWidth, lineWidth, side);
            this.getFrontRightSubPoint().buildObj(m, squareWidth, lineWidth, side);
        }
    }
    
    /**
     * 
     * @param m
     * @param lineWidth 
     * @param side 
     */
    public void buildObj_JustLines(OBJHandler m, double lineWidth, int side)
    {
        if(this.getBackLeftSubPoint() != null)
        {
            m.addDiagonalLine_Centered(this, this.getBackLeftSubPoint(), lineWidth, side);
            m.addDiagonalLine_Centered(this, this.getBackRightSubPoint(), lineWidth, side);
            m.addDiagonalLine_Centered(this, this.getFrontLeftSubPoint(), lineWidth, side);
            m.addDiagonalLine_Centered(this, this.getFrontRightSubPoint(), lineWidth, side);
            
            this.getBackLeftSubPoint().buildObj_JustLines(m, lineWidth, side);
            this.getBackRightSubPoint().buildObj_JustLines(m, lineWidth, side);
            this.getFrontLeftSubPoint().buildObj_JustLines(m, lineWidth, side);
            this.getFrontRightSubPoint().buildObj_JustLines(m, lineWidth, side);
        }
    }
    
    public double getThinnestMember(int numberOfPoints, double gridLenght)
    {
        if(!MathHelpers.isPowerOfFour(numberOfPoints))
            throw new IllegalArgumentException("numberOfPoints must be a power of 4.");
        
        int n = MathHelpers.getSquareRoot_PowersOfFour(numberOfPoints) / 2;
        
        if(n == 0)
            throw new IllegalArgumentException("numberOfPoints must be 4 or greater.");
        
        return gridLenght / ((double) n);
    }
}