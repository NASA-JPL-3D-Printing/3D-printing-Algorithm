package Default;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Andy
 * @author Monty Vanderlinde
 * @version 16 November 2017
 */
public class OBJHandler
{
    private ArrayList<Double> vertexList;
    private ArrayList<Integer> faceList;
    private int numberOfShapes;
    
    public OBJHandler()
    {
        this.vertexList = new ArrayList(100);
        this.faceList = new ArrayList<>(100);
        this.numberOfShapes = 0;
    }
    
    public void addTestAngle(double xCoordinate, double yCoordinate, double zCoordinate, double width, double translationDistance, double baseHeight)
    {
        double root2 = 1.41421356237309504;
        
        if(width >= translationDistance)
            throw new IllegalArgumentException("\"translationDistance\" must be greater than \"width\".");
        
        //add vertices
        //base square vertices
        this.vertexList.add(xCoordinate - (width / 2.0));
        this.vertexList.add(yCoordinate);
        this.vertexList.add(zCoordinate - (width / 2.0));
        
        this.vertexList.add(xCoordinate + (width / 2.0));
        this.vertexList.add(yCoordinate);
        this.vertexList.add(zCoordinate - (width / 2.0));
        
        this.vertexList.add(xCoordinate - (width / 2.0));
        this.vertexList.add(yCoordinate);
        this.vertexList.add(zCoordinate + (width / 2.0));
        
        this.vertexList.add(xCoordinate + (width / 2.0));
        this.vertexList.add(yCoordinate);
        this.vertexList.add(zCoordinate + (width / 2.0));
        
        //top square verticies
        this.vertexList.add(xCoordinate - (width / 2.0));
        this.vertexList.add(yCoordinate + (2.0 * root2 * translationDistance));
        this.vertexList.add(zCoordinate - (width / 2.0));
        
        this.vertexList.add(xCoordinate + (width / 2.0));
        this.vertexList.add(yCoordinate + (2.0 * root2 * translationDistance));
        this.vertexList.add(zCoordinate - (width / 2.0));
        
        this.vertexList.add(xCoordinate - (width / 2.0));
        this.vertexList.add(yCoordinate + (2.0 * root2 * translationDistance));
        this.vertexList.add(zCoordinate + (width / 2.0));
        
        this.vertexList.add(xCoordinate + (width / 2.0));
        this.vertexList.add(yCoordinate + (2.0 * root2 * translationDistance));
        this.vertexList.add(zCoordinate + (width / 2.0));
        
        //left square verticies
        this.vertexList.add(xCoordinate - translationDistance - (width / 2.0));
        this.vertexList.add(yCoordinate + root2 * translationDistance);
        this.vertexList.add(zCoordinate - translationDistance - (width / 2.0));
        
        this.vertexList.add(xCoordinate - translationDistance + (width / 2.0));
        this.vertexList.add(yCoordinate + root2 * translationDistance);
        this.vertexList.add(zCoordinate - translationDistance - (width / 2.0));
        
        this.vertexList.add(xCoordinate - translationDistance - (width / 2.0));
        this.vertexList.add(yCoordinate + root2 * translationDistance);
        this.vertexList.add(zCoordinate - translationDistance + (width / 2.0));
        
        this.vertexList.add(xCoordinate - translationDistance + (width / 2.0));
        this.vertexList.add(yCoordinate + root2 * translationDistance);
        this.vertexList.add(zCoordinate - translationDistance + (width / 2.0));
        
        //right square verticies
        this.vertexList.add(xCoordinate + translationDistance - (width / 2.0));
        this.vertexList.add(yCoordinate + root2 * translationDistance);
        this.vertexList.add(zCoordinate + translationDistance - (width / 2.0));
        
        this.vertexList.add(xCoordinate + translationDistance + (width / 2.0));
        this.vertexList.add(yCoordinate + root2 * translationDistance);
        this.vertexList.add(zCoordinate + translationDistance - (width / 2.0));
        
        this.vertexList.add(xCoordinate + translationDistance - (width / 2.0));
        this.vertexList.add(yCoordinate + root2 * translationDistance);
        this.vertexList.add(zCoordinate + translationDistance + (width / 2.0));
        
        this.vertexList.add(xCoordinate + translationDistance + (width / 2.0));
        this.vertexList.add(yCoordinate + root2 * translationDistance);
        this.vertexList.add(zCoordinate + translationDistance + (width / 2.0));
        
        //left bottom diagonal
        this.vertexList.add(xCoordinate - (width / 2.0));
        this.vertexList.add(yCoordinate + root2 * width);
        this.vertexList.add(zCoordinate - (width / 2.0));
        
        //right bottom diagonal
        this.vertexList.add(xCoordinate + (width / 2.0));
        this.vertexList.add(yCoordinate + root2 * width);
        this.vertexList.add(zCoordinate + (width / 2.0));
        
        //left top diagonal
        this.vertexList.add(xCoordinate - (width / 2.0));
        this.vertexList.add(yCoordinate + (2.0 * root2 * translationDistance) - root2 * width);
        this.vertexList.add(zCoordinate - (width / 2.0));
        
        //right top diagonal
        this.vertexList.add(xCoordinate + (width / 2.0));
        this.vertexList.add(yCoordinate + (2.0 * root2 * translationDistance) - root2 * width);
        this.vertexList.add(zCoordinate + (width / 2.0));
        
        //stable base vertices
        this.vertexList.add(xCoordinate - translationDistance - (width / 2.0));
        this.vertexList.add(yCoordinate - baseHeight);
        this.vertexList.add(zCoordinate - translationDistance - (width / 2.0));
        
        this.vertexList.add(xCoordinate + translationDistance + (width / 2.0));
        this.vertexList.add(yCoordinate - baseHeight);
        this.vertexList.add(zCoordinate - translationDistance - (width / 2.0));
        
        this.vertexList.add(xCoordinate - translationDistance - (width / 2.0));
        this.vertexList.add(yCoordinate - baseHeight);
        this.vertexList.add(zCoordinate + translationDistance + (width / 2.0));
        
        this.vertexList.add(xCoordinate + translationDistance + (width / 2.0));
        this.vertexList.add(yCoordinate - baseHeight);
        this.vertexList.add(zCoordinate + translationDistance + (width / 2.0));
        
        
        //add faces
        //base face
        this.faceList.add(21 + (24 * this.numberOfShapes));
        this.faceList.add(22 + (24 * this.numberOfShapes));
        this.faceList.add(24 + (24 * this.numberOfShapes));
        this.faceList.add(23 + (24 * this.numberOfShapes));
        
        //base back face
        this.faceList.add(1 + (24 * this.numberOfShapes));
        this.faceList.add(2 + (24 * this.numberOfShapes));
        this.faceList.add(22 + (24 * this.numberOfShapes));
        this.faceList.add(21 + (24 * this.numberOfShapes));
        
        //base front face
        this.faceList.add(24 + (24 * this.numberOfShapes));
        this.faceList.add(4 + (24 * this.numberOfShapes));
        this.faceList.add(3 + (24 * this.numberOfShapes));
        this.faceList.add(23 + (24 * this.numberOfShapes));
        
        //base left face
        this.faceList.add(21 + (24 * this.numberOfShapes));
        this.faceList.add(23 + (24 * this.numberOfShapes));
        this.faceList.add(3 + (24 * this.numberOfShapes));
        this.faceList.add(1 + (24 * this.numberOfShapes));
        
        //base right face
        this.faceList.add(4 + (24 * this.numberOfShapes));
        this.faceList.add(24 + (24 * this.numberOfShapes));
        this.faceList.add(22 + (24 * this.numberOfShapes));
        this.faceList.add(2 + (24 * this.numberOfShapes));
        
        //top face
        this.faceList.add(5 + (24 * this.numberOfShapes));
        this.faceList.add(7 + (24 * this.numberOfShapes));
        this.faceList.add(8 + (24 * this.numberOfShapes));
        this.faceList.add(6 + (24 * this.numberOfShapes));
        
        //center column
        //left face
        this.faceList.add(17 + (24 * this.numberOfShapes));
        this.faceList.add(3 + (24 * this.numberOfShapes));
        this.faceList.add(7 + (24 * this.numberOfShapes));
        this.faceList.add(19 + (24 * this.numberOfShapes));
        
        //right face
        this.faceList.add(2 + (24 * this.numberOfShapes));
        this.faceList.add(6 + (24 * this.numberOfShapes));
        this.faceList.add(20 + (24 * this.numberOfShapes));
        this.faceList.add(18 + (24 * this.numberOfShapes));
        
        //front face
        this.faceList.add(3 + (24 * this.numberOfShapes));
        this.faceList.add(18 + (24 * this.numberOfShapes));
        this.faceList.add(20 + (24 * this.numberOfShapes));
        this.faceList.add(7 + (24 * this.numberOfShapes));
        
        //back face
        this.faceList.add(17 + (24 * this.numberOfShapes));
        this.faceList.add(19 + (24 * this.numberOfShapes));
        this.faceList.add(6 + (24 * this.numberOfShapes));
        this.faceList.add(2 + (24 * this.numberOfShapes));
        
        //left bottom column
        //left face
        this.faceList.add(1 + (24 * this.numberOfShapes));
        this.faceList.add(3 + (24 * this.numberOfShapes));
        this.faceList.add(11 + (24 * this.numberOfShapes));
        this.faceList.add(9 + (24 * this.numberOfShapes));
        
        //right face
        this.faceList.add(17 + (24 * this.numberOfShapes));
        this.faceList.add(2 + (24 * this.numberOfShapes));
        this.faceList.add(10 + (24 * this.numberOfShapes));
        this.faceList.add(12 + (24 * this.numberOfShapes));
        
        //front face
        this.faceList.add(3 + (24 * this.numberOfShapes));
        this.faceList.add(17 + (24 * this.numberOfShapes));
        this.faceList.add(12 + (24 * this.numberOfShapes));
        this.faceList.add(11 + (24 * this.numberOfShapes));
        
        //back face
        this.faceList.add(2 + (24 * this.numberOfShapes));
        this.faceList.add(1 + (24 * this.numberOfShapes));
        this.faceList.add(9 + (24 * this.numberOfShapes));
        this.faceList.add(10 + (24 * this.numberOfShapes));
        
        //left top column
        //left face
        this.faceList.add(5 + (24 * this.numberOfShapes));
        this.faceList.add(9 + (24 * this.numberOfShapes));
        this.faceList.add(11 + (24 * this.numberOfShapes));
        this.faceList.add(7 + (24 * this.numberOfShapes));
        
        //right face
        this.faceList.add(12 + (24 * this.numberOfShapes));
        this.faceList.add(10 + (24 * this.numberOfShapes));
        this.faceList.add(6 + (24 * this.numberOfShapes));
        this.faceList.add(19 + (24 * this.numberOfShapes));
        
        //front face
        this.faceList.add(7 + (24 * this.numberOfShapes));
        this.faceList.add(11 + (24 * this.numberOfShapes));
        this.faceList.add(12 + (24 * this.numberOfShapes));
        this.faceList.add(19 + (24 * this.numberOfShapes));
        
        //back face
        this.faceList.add(5 + (24 * this.numberOfShapes));
        this.faceList.add(6 + (24 * this.numberOfShapes));
        this.faceList.add(10 + (24 * this.numberOfShapes));
        this.faceList.add(9 + (24 * this.numberOfShapes));
        
        //right bottom column
        //left face
        this.faceList.add(18 + (24 * this.numberOfShapes));
        this.faceList.add(3 + (24 * this.numberOfShapes));
        this.faceList.add(15 + (24 * this.numberOfShapes));
        this.faceList.add(13 + (24 * this.numberOfShapes));
        
        //right face
        this.faceList.add(4 + (24 * this.numberOfShapes));
        this.faceList.add(2 + (24 * this.numberOfShapes));
        this.faceList.add(14 + (24 * this.numberOfShapes));
        this.faceList.add(16 + (24 * this.numberOfShapes));
        
        //front face
        this.faceList.add(3 + (24 * this.numberOfShapes));
        this.faceList.add(4 + (24 * this.numberOfShapes));
        this.faceList.add(16 + (24 * this.numberOfShapes));
        this.faceList.add(15 + (24 * this.numberOfShapes));
        
        //back face
        this.faceList.add(2 + (24 * this.numberOfShapes));
        this.faceList.add(18 + (24 * this.numberOfShapes));
        this.faceList.add(13 + (24 * this.numberOfShapes));
        this.faceList.add(14 + (24 * this.numberOfShapes));
        
        //right top column
        //left face
        this.faceList.add(7 + (24 * this.numberOfShapes));
        this.faceList.add(20 + (24 * this.numberOfShapes));
        this.faceList.add(13 + (24 * this.numberOfShapes));
        this.faceList.add(15 + (24 * this.numberOfShapes));
        
        //right face
        this.faceList.add(6 + (24 * this.numberOfShapes));
        this.faceList.add(8 + (24 * this.numberOfShapes));
        this.faceList.add(16 + (24 * this.numberOfShapes));
        this.faceList.add(14 + (24 * this.numberOfShapes));
        
        //front face
        this.faceList.add(7 + (24 * this.numberOfShapes));
        this.faceList.add(15 + (24 * this.numberOfShapes));
        this.faceList.add(16 + (24 * this.numberOfShapes));
        this.faceList.add(8 + (24 * this.numberOfShapes));
        
        //back face
        this.faceList.add(20 + (24 * this.numberOfShapes));
        this.faceList.add(6 + (24 * this.numberOfShapes));
        this.faceList.add(14 + (24 * this.numberOfShapes));
        this.faceList.add(13 + (24 * this.numberOfShapes));
        
        
        //increase number of objects in 
        this.numberOfShapes++;
    }

    public void addSquare_Centered(TreePoint p, double boxLength)
    {
        //add vertices
        //left-bottom-back (1)
        this.vertexList.add(p.getXCoordinate() - (boxLength / 2.0));
        this.vertexList.add(p.getYCoordinate() - (boxLength / 2.0));
        this.vertexList.add(p.getZCoordinate() - (boxLength / 2.0));
        
        //right-bottom-back (2)
        this.vertexList.add(p.getXCoordinate() + (boxLength / 2.0));
        this.vertexList.add(p.getYCoordinate() - (boxLength / 2.0));
        this.vertexList.add(p.getZCoordinate() - (boxLength / 2.0));
        
        //left-bottom-front (3)
        this.vertexList.add(p.getXCoordinate() - (boxLength / 2.0));
        this.vertexList.add(p.getYCoordinate() - (boxLength / 2.0));
        this.vertexList.add(p.getZCoordinate() + (boxLength / 2.0));
        
        //right-bottom-front (4)
        this.vertexList.add(p.getXCoordinate() + (boxLength / 2.0));
        this.vertexList.add(p.getYCoordinate() - (boxLength / 2.0));
        this.vertexList.add(p.getZCoordinate() + (boxLength / 2.0));
        
        //left-top-back (5)
        this.vertexList.add(p.getXCoordinate() - (boxLength / 2.0));
        this.vertexList.add(p.getYCoordinate() + (boxLength / 2.0));
        this.vertexList.add(p.getZCoordinate() - (boxLength / 2.0));
        
        //right-top-back (6)
        this.vertexList.add(p.getXCoordinate() + (boxLength / 2.0));
        this.vertexList.add(p.getYCoordinate() + (boxLength / 2.0));
        this.vertexList.add(p.getZCoordinate() - (boxLength / 2.0));
        
        //left-top-front (7)
        this.vertexList.add(p.getXCoordinate() - (boxLength / 2.0));
        this.vertexList.add(p.getYCoordinate() + (boxLength / 2.0));
        this.vertexList.add(p.getZCoordinate() + (boxLength / 2.0));
        
        //right-top-front (8)
        this.vertexList.add(p.getXCoordinate() + (boxLength / 2.0));
        this.vertexList.add(p.getYCoordinate() + (boxLength / 2.0));
        this.vertexList.add(p.getZCoordinate() + (boxLength / 2.0));
        
        
        //add faces
        //left
        this.faceList.add(1 + (8 * this.numberOfShapes));
        this.faceList.add(3 + (8 * this.numberOfShapes));
        this.faceList.add(7 + (8 * this.numberOfShapes));
        this.faceList.add(5 + (8 * this.numberOfShapes));
        
        //right
        this.faceList.add(2 + (8 * this.numberOfShapes));
        this.faceList.add(4 + (8 * this.numberOfShapes));
        this.faceList.add(8 + (8 * this.numberOfShapes));
        this.faceList.add(6 + (8 * this.numberOfShapes));
        
        //bottom
        this.faceList.add(1 + (8 * this.numberOfShapes));
        this.faceList.add(2 + (8 * this.numberOfShapes));
        this.faceList.add(4 + (8 * this.numberOfShapes));
        this.faceList.add(3 + (8 * this.numberOfShapes));
        
        //top
        this.faceList.add(5 + (8 * this.numberOfShapes));
        this.faceList.add(6 + (8 * this.numberOfShapes));
        this.faceList.add(8 + (8 * this.numberOfShapes));
        this.faceList.add(7 + (8 * this.numberOfShapes));
        
        //back
        this.faceList.add(1 + (8 * this.numberOfShapes));
        this.faceList.add(2 + (8 * this.numberOfShapes));
        this.faceList.add(6 + (8 * this.numberOfShapes));
        this.faceList.add(5 + (8 * this.numberOfShapes));
        
        //front
        this.faceList.add(3 + (8 * this.numberOfShapes));
        this.faceList.add(4 + (8 * this.numberOfShapes));
        this.faceList.add(8 + (8 * this.numberOfShapes));
        this.faceList.add(7 + (8 * this.numberOfShapes));
        
        
        //increase number of objects in 
        this.numberOfShapes++;
    }
    
    /**
     * 
     * @param centerPoint
     * @param thickness
     * @param boxSideLength
     * @param side 1 for left, 2 for right, 3 for top, 4 for bottom, 5 for front, 6 for back.  Anything else throws an exception.
     */
    public void drawSkin(TreePoint centerPoint, double thickness, double boxSideLength, int side)
    {
        switch(side)
        {
            case 1://left
                //add vertices
                //left-bottom-back (1)
                this.vertexList.add(centerPoint.getXCoordinate() - (boxSideLength / 2.0) - thickness);
                this.vertexList.add(centerPoint.getYCoordinate() - (boxSideLength / 2.0) - thickness);
                this.vertexList.add(centerPoint.getZCoordinate() - (boxSideLength / 2.0) - thickness);
                
                //right-bottom-back (2)
                this.vertexList.add(centerPoint.getXCoordinate() - (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getYCoordinate() - (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getZCoordinate() - (boxSideLength / 2.0));
                
                //left-bottom-front (3)
                this.vertexList.add(centerPoint.getXCoordinate() - (boxSideLength / 2.0) - thickness);
                this.vertexList.add(centerPoint.getYCoordinate() - (boxSideLength / 2.0) - thickness);
                this.vertexList.add(centerPoint.getZCoordinate() + (boxSideLength / 2.0) + thickness);
                
                //right-bottom-front (4)
                this.vertexList.add(centerPoint.getXCoordinate() - (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getYCoordinate() - (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getZCoordinate() + (boxSideLength / 2.0));
                
                //left-top-back (5)
                this.vertexList.add(centerPoint.getXCoordinate() - (boxSideLength / 2.0) - thickness);
                this.vertexList.add(centerPoint.getYCoordinate() + (boxSideLength / 2.0) + thickness);
                this.vertexList.add(centerPoint.getZCoordinate() - (boxSideLength / 2.0) - thickness);
                
                //right-top-back (6)
                this.vertexList.add(centerPoint.getXCoordinate() - (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getYCoordinate() + (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getZCoordinate() - (boxSideLength / 2.0));
                
                //left-top-front (7)
                this.vertexList.add(centerPoint.getXCoordinate() - (boxSideLength / 2.0) - thickness);
                this.vertexList.add(centerPoint.getYCoordinate() + (boxSideLength / 2.0) + thickness);
                this.vertexList.add(centerPoint.getZCoordinate() + (boxSideLength / 2.0) + thickness);
                
                //right-top-front (8)
                this.vertexList.add(centerPoint.getXCoordinate() - (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getYCoordinate() + (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getZCoordinate() + (boxSideLength / 2.0));
                break;
            case 2://right
                //add vertices
                //left-bottom-back (1)
                this.vertexList.add(centerPoint.getXCoordinate() + (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getYCoordinate() - (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getZCoordinate() - (boxSideLength / 2.0));
                
                //right-bottom-back (2)
                this.vertexList.add(centerPoint.getXCoordinate() + (boxSideLength / 2.0) + thickness);
                this.vertexList.add(centerPoint.getYCoordinate() - (boxSideLength / 2.0) - thickness);
                this.vertexList.add(centerPoint.getZCoordinate() - (boxSideLength / 2.0) - thickness);
                
                //left-bottom-front (3)
                this.vertexList.add(centerPoint.getXCoordinate() + (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getYCoordinate() - (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getZCoordinate() + (boxSideLength / 2.0));
                
                //right-bottom-front (4)
                this.vertexList.add(centerPoint.getXCoordinate() + (boxSideLength / 2.0) + thickness);
                this.vertexList.add(centerPoint.getYCoordinate() - (boxSideLength / 2.0) - thickness);
                this.vertexList.add(centerPoint.getZCoordinate() + (boxSideLength / 2.0) + thickness);
                
                //left-top-back (5)
                this.vertexList.add(centerPoint.getXCoordinate() + (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getYCoordinate() + (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getZCoordinate() - (boxSideLength / 2.0));
                
                //right-top-back (6)
                this.vertexList.add(centerPoint.getXCoordinate() + (boxSideLength / 2.0) + thickness);
                this.vertexList.add(centerPoint.getYCoordinate() + (boxSideLength / 2.0) + thickness);
                this.vertexList.add(centerPoint.getZCoordinate() - (boxSideLength / 2.0) - thickness);
                
                //left-top-front (7)
                this.vertexList.add(centerPoint.getXCoordinate() + (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getYCoordinate() + (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getZCoordinate() + (boxSideLength / 2.0));
                
                //right-top-front (8)
                this.vertexList.add(centerPoint.getXCoordinate() + (boxSideLength / 2.0) + thickness);
                this.vertexList.add(centerPoint.getYCoordinate() + (boxSideLength / 2.0) + thickness);
                this.vertexList.add(centerPoint.getZCoordinate() + (boxSideLength / 2.0) + thickness);
                break;
            case 3://top
                //add vertices
                //left-bottom-back (1)
                this.vertexList.add(centerPoint.getXCoordinate() - (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getYCoordinate() + (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getZCoordinate() - (boxSideLength / 2.0));
                
                //right-bottom-back (2)
                this.vertexList.add(centerPoint.getXCoordinate() + (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getYCoordinate() + (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getZCoordinate() - (boxSideLength / 2.0));
                
                //left-bottom-front (3)
                this.vertexList.add(centerPoint.getXCoordinate() - (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getYCoordinate() + (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getZCoordinate() + (boxSideLength / 2.0));
                
                //right-bottom-front (4)
                this.vertexList.add(centerPoint.getXCoordinate() + (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getYCoordinate() + (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getZCoordinate() + (boxSideLength / 2.0));
                
                //left-top-back (5)
                this.vertexList.add(centerPoint.getXCoordinate() - (boxSideLength / 2.0) - thickness);
                this.vertexList.add(centerPoint.getYCoordinate() + (boxSideLength / 2.0) + thickness);
                this.vertexList.add(centerPoint.getZCoordinate() - (boxSideLength / 2.0) - thickness);
                
                //right-top-back (6)
                this.vertexList.add(centerPoint.getXCoordinate() + (boxSideLength / 2.0) + thickness);
                this.vertexList.add(centerPoint.getYCoordinate() + (boxSideLength / 2.0) + thickness);
                this.vertexList.add(centerPoint.getZCoordinate() - (boxSideLength / 2.0) - thickness);
                
                //left-top-front (7)
                this.vertexList.add(centerPoint.getXCoordinate() - (boxSideLength / 2.0) - thickness);
                this.vertexList.add(centerPoint.getYCoordinate() + (boxSideLength / 2.0) + thickness);
                this.vertexList.add(centerPoint.getZCoordinate() + (boxSideLength / 2.0) + thickness);
                
                //right-top-front (8)
                this.vertexList.add(centerPoint.getXCoordinate() + (boxSideLength / 2.0) + thickness);
                this.vertexList.add(centerPoint.getYCoordinate() + (boxSideLength / 2.0) + thickness);
                this.vertexList.add(centerPoint.getZCoordinate() + (boxSideLength / 2.0) + thickness);
                break;
            case 4://bottom
                //add vertices
                //left-bottom-back (1)
                this.vertexList.add(centerPoint.getXCoordinate() - (boxSideLength / 2.0) - thickness);
                this.vertexList.add(centerPoint.getYCoordinate() - (boxSideLength / 2.0) - thickness);
                this.vertexList.add(centerPoint.getZCoordinate() - (boxSideLength / 2.0) - thickness);
                
                //right-bottom-back (2)
                this.vertexList.add(centerPoint.getXCoordinate() + (boxSideLength / 2.0) + thickness);
                this.vertexList.add(centerPoint.getYCoordinate() - (boxSideLength / 2.0) - thickness);
                this.vertexList.add(centerPoint.getZCoordinate() - (boxSideLength / 2.0) - thickness);
                
                //left-bottom-front (3)
                this.vertexList.add(centerPoint.getXCoordinate() - (boxSideLength / 2.0) - thickness);
                this.vertexList.add(centerPoint.getYCoordinate() - (boxSideLength / 2.0) - thickness);
                this.vertexList.add(centerPoint.getZCoordinate() + (boxSideLength / 2.0) + thickness);
                
                //right-bottom-front (4)
                this.vertexList.add(centerPoint.getXCoordinate() + (boxSideLength / 2.0) + thickness);
                this.vertexList.add(centerPoint.getYCoordinate() - (boxSideLength / 2.0) - thickness);
                this.vertexList.add(centerPoint.getZCoordinate() + (boxSideLength / 2.0) + thickness);
                
                //left-top-back (5)
                this.vertexList.add(centerPoint.getXCoordinate() - (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getYCoordinate() - (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getZCoordinate() - (boxSideLength / 2.0));
                
                //right-top-back (6)
                this.vertexList.add(centerPoint.getXCoordinate() + (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getYCoordinate() - (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getZCoordinate() - (boxSideLength / 2.0));
                
                //left-top-front (7)
                this.vertexList.add(centerPoint.getXCoordinate() - (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getYCoordinate() - (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getZCoordinate() + (boxSideLength / 2.0));
                
                //right-top-front (8)
                this.vertexList.add(centerPoint.getXCoordinate() + (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getYCoordinate() - (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getZCoordinate() + (boxSideLength / 2.0));
                break;
            case 5://front
                //add vertices
                //left-bottom-back (1)
                this.vertexList.add(centerPoint.getXCoordinate() - (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getYCoordinate() - (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getZCoordinate() + (boxSideLength / 2.0));
                
                //right-bottom-back (2)
                this.vertexList.add(centerPoint.getXCoordinate() + (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getYCoordinate() - (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getZCoordinate() + (boxSideLength / 2.0));
                
                //left-bottom-front (3)
                this.vertexList.add(centerPoint.getXCoordinate() - (boxSideLength / 2.0) - thickness);
                this.vertexList.add(centerPoint.getYCoordinate() - (boxSideLength / 2.0) - thickness);
                this.vertexList.add(centerPoint.getZCoordinate() + (boxSideLength / 2.0) + thickness);
                
                //right-bottom-front (4)
                this.vertexList.add(centerPoint.getXCoordinate() + (boxSideLength / 2.0) + thickness);
                this.vertexList.add(centerPoint.getYCoordinate() - (boxSideLength / 2.0) - thickness);
                this.vertexList.add(centerPoint.getZCoordinate() + (boxSideLength / 2.0) + thickness);
                
                //left-top-back (5)
                this.vertexList.add(centerPoint.getXCoordinate() - (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getYCoordinate() + (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getZCoordinate() + (boxSideLength / 2.0));
                
                //right-top-back (6)
                this.vertexList.add(centerPoint.getXCoordinate() + (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getYCoordinate() + (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getZCoordinate() + (boxSideLength / 2.0));
                
                //left-top-front (7)
                this.vertexList.add(centerPoint.getXCoordinate() - (boxSideLength / 2.0) - thickness);
                this.vertexList.add(centerPoint.getYCoordinate() + (boxSideLength / 2.0) + thickness);
                this.vertexList.add(centerPoint.getZCoordinate() + (boxSideLength / 2.0) + thickness);
                
                //right-top-front (8)
                this.vertexList.add(centerPoint.getXCoordinate() + (boxSideLength / 2.0) + thickness);
                this.vertexList.add(centerPoint.getYCoordinate() + (boxSideLength / 2.0) + thickness);
                this.vertexList.add(centerPoint.getZCoordinate() + (boxSideLength / 2.0) + thickness);
                break;
            case 6://back
                //add vertices
                //left-bottom-back (1)
                this.vertexList.add(centerPoint.getXCoordinate() - (boxSideLength / 2.0) - thickness);
                this.vertexList.add(centerPoint.getYCoordinate() - (boxSideLength / 2.0) - thickness);
                this.vertexList.add(centerPoint.getZCoordinate() - (boxSideLength / 2.0) - thickness);
                
                //right-bottom-back (2)
                this.vertexList.add(centerPoint.getXCoordinate() + (boxSideLength / 2.0) + thickness);
                this.vertexList.add(centerPoint.getYCoordinate() - (boxSideLength / 2.0) - thickness);
                this.vertexList.add(centerPoint.getZCoordinate() - (boxSideLength / 2.0) - thickness);
                
                //left-bottom-front (3)
                this.vertexList.add(centerPoint.getXCoordinate() - (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getYCoordinate() - (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getZCoordinate() - (boxSideLength / 2.0));
                
                //right-bottom-front (4)
                this.vertexList.add(centerPoint.getXCoordinate() + (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getYCoordinate() - (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getZCoordinate() - (boxSideLength / 2.0));
                
                //left-top-back (5)
                this.vertexList.add(centerPoint.getXCoordinate() - (boxSideLength / 2.0) - thickness);
                this.vertexList.add(centerPoint.getYCoordinate() + (boxSideLength / 2.0) + thickness);
                this.vertexList.add(centerPoint.getZCoordinate() - (boxSideLength / 2.0) - thickness);
                
                //right-top-back (6)
                this.vertexList.add(centerPoint.getXCoordinate() + (boxSideLength / 2.0) + thickness);
                this.vertexList.add(centerPoint.getYCoordinate() + (boxSideLength / 2.0) + thickness);
                this.vertexList.add(centerPoint.getZCoordinate() - (boxSideLength / 2.0) - thickness);
                
                //left-top-front (7)
                this.vertexList.add(centerPoint.getXCoordinate() - (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getYCoordinate() + (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getZCoordinate() - (boxSideLength / 2.0));
                
                //right-top-front (8)
                this.vertexList.add(centerPoint.getXCoordinate() + (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getYCoordinate() + (boxSideLength / 2.0));
                this.vertexList.add(centerPoint.getZCoordinate() - (boxSideLength / 2.0));
                break;
        }
        
        
        //add faces
        //left
        this.faceList.add(1 + (8 * this.numberOfShapes));
        this.faceList.add(3 + (8 * this.numberOfShapes));
        this.faceList.add(7 + (8 * this.numberOfShapes));
        this.faceList.add(5 + (8 * this.numberOfShapes));
        
        //right
        this.faceList.add(2 + (8 * this.numberOfShapes));
        this.faceList.add(4 + (8 * this.numberOfShapes));
        this.faceList.add(8 + (8 * this.numberOfShapes));
        this.faceList.add(6 + (8 * this.numberOfShapes));
        
        //bottom
        this.faceList.add(1 + (8 * this.numberOfShapes));
        this.faceList.add(2 + (8 * this.numberOfShapes));
        this.faceList.add(4 + (8 * this.numberOfShapes));
        this.faceList.add(3 + (8 * this.numberOfShapes));
        
        //top
        this.faceList.add(5 + (8 * this.numberOfShapes));
        this.faceList.add(6 + (8 * this.numberOfShapes));
        this.faceList.add(8 + (8 * this.numberOfShapes));
        this.faceList.add(7 + (8 * this.numberOfShapes));
        
        //back
        this.faceList.add(1 + (8 * this.numberOfShapes));
        this.faceList.add(2 + (8 * this.numberOfShapes));
        this.faceList.add(6 + (8 * this.numberOfShapes));
        this.faceList.add(5 + (8 * this.numberOfShapes));
        
        //front
        this.faceList.add(3 + (8 * this.numberOfShapes));
        this.faceList.add(4 + (8 * this.numberOfShapes));
        this.faceList.add(8 + (8 * this.numberOfShapes));
        this.faceList.add(7 + (8 * this.numberOfShapes));
        
        
        //increase number of objects in 
        this.numberOfShapes++;
    }
    
    /**
     * 
     * @param first
     * @param second
     * @param lineWidth
     * @param side 1 for left, 2 for right, 3 for top, 4 for bottom, 5 for front, 6 for back.  Anything else throws an exception.
     */
    public void addDiagonalLine_Centered(TreePoint first, TreePoint second, double lineWidth, int side)
    {
        switch(side)
        {
            case 1:
            case 2:
                //add vertices
                //first-left-back (1)
                this.vertexList.add(first.getXCoordinate());
                this.vertexList.add(first.getYCoordinate() + (lineWidth / 2.0));
                this.vertexList.add(first.getZCoordinate() - (lineWidth / 2.0));
                
                //first-right-back (2)
                this.vertexList.add(first.getXCoordinate());
                this.vertexList.add(first.getYCoordinate() - (lineWidth / 2.0));
                this.vertexList.add(first.getZCoordinate() - (lineWidth / 2.0));
                
                //first-left-front (3)
                this.vertexList.add(first.getXCoordinate());
                this.vertexList.add(first.getYCoordinate() + (lineWidth / 2.0));
                this.vertexList.add(first.getZCoordinate() + (lineWidth / 2.0));
                
                //first-right-front (4)
                this.vertexList.add(first.getXCoordinate());
                this.vertexList.add(first.getYCoordinate() - (lineWidth / 2.0));
                this.vertexList.add(first.getZCoordinate() + (lineWidth / 2.0));
                
                //second-left-back (5)
                this.vertexList.add(second.getXCoordinate());
                this.vertexList.add(second.getYCoordinate() + (lineWidth / 2.0));
                this.vertexList.add(second.getZCoordinate() - (lineWidth / 2.0));
                
                //second-right-back (6)
                this.vertexList.add(second.getXCoordinate());
                this.vertexList.add(second.getYCoordinate() - (lineWidth / 2.0));
                this.vertexList.add(second.getZCoordinate() - (lineWidth / 2.0));
                
                //second-left-front (7)
                this.vertexList.add(second.getXCoordinate());
                this.vertexList.add(second.getYCoordinate() + (lineWidth / 2.0));
                this.vertexList.add(second.getZCoordinate() + (lineWidth / 2.0));
                
                //second-right-front (8)
                this.vertexList.add(second.getXCoordinate());
                this.vertexList.add(second.getYCoordinate() - (lineWidth / 2.0));
                this.vertexList.add(second.getZCoordinate() + (lineWidth / 2.0));
                break;
            case 3:
            case 4:
                //add vertices
                //first-left-back (1)
                this.vertexList.add(first.getXCoordinate() - (lineWidth / 2.0));
                this.vertexList.add(first.getYCoordinate());
                this.vertexList.add(first.getZCoordinate() - (lineWidth / 2.0));
                
                //first-right-back (2)
                this.vertexList.add(first.getXCoordinate() + (lineWidth / 2.0));
                this.vertexList.add(first.getYCoordinate());
                this.vertexList.add(first.getZCoordinate() - (lineWidth / 2.0));
                
                //first-left-front (3)
                this.vertexList.add(first.getXCoordinate() - (lineWidth / 2.0));
                this.vertexList.add(first.getYCoordinate());
                this.vertexList.add(first.getZCoordinate() + (lineWidth / 2.0));
                
                //first-right-front (4)
                this.vertexList.add(first.getXCoordinate() + (lineWidth / 2.0));
                this.vertexList.add(first.getYCoordinate());
                this.vertexList.add(first.getZCoordinate() + (lineWidth / 2.0));
                
                //second-left-back (5)
                this.vertexList.add(second.getXCoordinate() - (lineWidth / 2.0));
                this.vertexList.add(second.getYCoordinate());
                this.vertexList.add(second.getZCoordinate() - (lineWidth / 2.0));
                
                //second-right-back (6)
                this.vertexList.add(second.getXCoordinate() + (lineWidth / 2.0));
                this.vertexList.add(second.getYCoordinate());
                this.vertexList.add(second.getZCoordinate() - (lineWidth / 2.0));
                
                //second-left-front (7)
                this.vertexList.add(second.getXCoordinate() - (lineWidth / 2.0));
                this.vertexList.add(second.getYCoordinate());
                this.vertexList.add(second.getZCoordinate() + (lineWidth / 2.0));
                
                //second-right-front (8)
                this.vertexList.add(second.getXCoordinate() + (lineWidth / 2.0));
                this.vertexList.add(second.getYCoordinate());
                this.vertexList.add(second.getZCoordinate() + (lineWidth / 2.0));
                break;
            case 5:
            case 6:
                //add vertices
                //first-left-back (1)
                this.vertexList.add(first.getXCoordinate() - (lineWidth / 2.0));
                this.vertexList.add(first.getYCoordinate() - (lineWidth / 2.0));
                this.vertexList.add(first.getZCoordinate());
                
                //first-right-back (2)
                this.vertexList.add(first.getXCoordinate() + (lineWidth / 2.0));
                this.vertexList.add(first.getYCoordinate() - (lineWidth / 2.0));
                this.vertexList.add(first.getZCoordinate());
                
                //first-left-front (3)
                this.vertexList.add(first.getXCoordinate() - (lineWidth / 2.0));
                this.vertexList.add(first.getYCoordinate() + (lineWidth / 2.0));
                this.vertexList.add(first.getZCoordinate());
                
                //first-right-front (4)
                this.vertexList.add(first.getXCoordinate() + (lineWidth / 2.0));
                this.vertexList.add(first.getYCoordinate() + (lineWidth / 2.0));
                this.vertexList.add(first.getZCoordinate());
                
                //second-left-back (5)
                this.vertexList.add(second.getXCoordinate() - (lineWidth / 2.0));
                this.vertexList.add(second.getYCoordinate() - (lineWidth / 2.0));
                this.vertexList.add(second.getZCoordinate());
                
                //second-right-back (6)
                this.vertexList.add(second.getXCoordinate() + (lineWidth / 2.0));
                this.vertexList.add(second.getYCoordinate() - (lineWidth / 2.0));
                this.vertexList.add(second.getZCoordinate());
                
                //second-left-front (7)
                this.vertexList.add(second.getXCoordinate() - (lineWidth / 2.0));
                this.vertexList.add(second.getYCoordinate() + (lineWidth / 2.0));
                this.vertexList.add(second.getZCoordinate());
                
                //second-right-front (8)
                this.vertexList.add(second.getXCoordinate() + (lineWidth / 2.0));
                this.vertexList.add(second.getYCoordinate() + (lineWidth / 2.0));
                this.vertexList.add(second.getZCoordinate());
                break;
        }
        
        
        //add faces
        //left
        this.faceList.add(1 + (8 * this.numberOfShapes));
        this.faceList.add(3 + (8 * this.numberOfShapes));
        this.faceList.add(7 + (8 * this.numberOfShapes));
        this.faceList.add(5 + (8 * this.numberOfShapes));
        
        //right
        this.faceList.add(2 + (8 * this.numberOfShapes));
        this.faceList.add(4 + (8 * this.numberOfShapes));
        this.faceList.add(8 + (8 * this.numberOfShapes));
        this.faceList.add(6 + (8 * this.numberOfShapes));
        
        //bottom
        this.faceList.add(1 + (8 * this.numberOfShapes));
        this.faceList.add(2 + (8 * this.numberOfShapes));
        this.faceList.add(4 + (8 * this.numberOfShapes));
        this.faceList.add(3 + (8 * this.numberOfShapes));
        
        //top
        this.faceList.add(5 + (8 * this.numberOfShapes));
        this.faceList.add(6 + (8 * this.numberOfShapes));
        this.faceList.add(8 + (8 * this.numberOfShapes));
        this.faceList.add(7 + (8 * this.numberOfShapes));
        
        //back
        this.faceList.add(1 + (8 * this.numberOfShapes));
        this.faceList.add(2 + (8 * this.numberOfShapes));
        this.faceList.add(6 + (8 * this.numberOfShapes));
        this.faceList.add(5 + (8 * this.numberOfShapes));
        
        //front
        this.faceList.add(3 + (8 * this.numberOfShapes));
        this.faceList.add(4 + (8 * this.numberOfShapes));
        this.faceList.add(8 + (8 * this.numberOfShapes));
        this.faceList.add(7 + (8 * this.numberOfShapes));
        
        
        //increase number of objects in 
        this.numberOfShapes++;
    }
    
    public void createFile()
    {

        // Create the File
        File file = new File("object.obj");
        PrintWriter writer = null;

        // Write to the file
        try
        {
            file.createNewFile();
            
            writer = new PrintWriter(file);
        }
        catch(FileNotFoundException fnfe)
        {
            System.err.println("Error, file not found!");
        }
        catch(IOException ioe)
        {
            System.err.println("Error, ioexception!");
        }
        
        //check for null writer
        if(writer == null)
        {
            System.err.println("Writer Not Found!");
            return;
        }
        
        //print header
        writer.println("# Object");
        writer.println();
        
        //print the vertices
        for (int i = 0; i < this.vertexList.size(); i++)
        {
            //three items in the vertexList per actual vertex
            switch(i % 3)
            {
                case 0:
                    writer.print("v " + this.vertexList.get(i));
                    break;
                case 1:
                    writer.print(" " + this.vertexList.get(i));
                    break;
                case 2:
                    writer.println(" " + this.vertexList.get(i));
                    break;
            }
            
            //print line between the verticies of each shape
            if(((i + 1) % (8 * 3)) == 0)
                writer.println();
        }
        
        //print the number of vertices; 3 vertexList items define one vertex
        writer.println("# " + (this.vertexList.size() / 3));
        writer.println();
        
        //print the faces
        for (int i = 0; i < this.faceList.size(); i++)
        {
            //four items in the faceList per actual face
            switch(i % 4)
            {
                case 0:
                    writer.print("f " + this.faceList.get(i));
                    break;
                case 1:
                    writer.print(" " + this.faceList.get(i));
                    break;
                case 2:
                    writer.print(" " + this.faceList.get(i));
                    break;
                case 3:
                    writer.println(" " + this.faceList.get(i));
                    break;
            }
            
            //print line between the verticies of each shape
            if(((i + 1) % (6 * 4)) == 0)
                writer.println();
        }
        
        //print the number of faces; 4 faceList items define one face
        writer.println("# " + (this.faceList.size() / 4));
        writer.println();

        // Close File
        writer.println("#end of obj_0");
        writer.close();
    }
}