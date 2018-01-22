package Clients;

import Default.MathHelpers;
import Default.OBJHandler;
import Default.TreePoint;

/**
 *
 * @author Monty Vanderlinde
 * @version 20 November 2017
 * 
 * A client for testing the TreePoint class.
 * 
 */
public class Client_TreePoint
{
    public static void main(String[] args)
        {
        double gridLength = 1.5;
        double skinThickness = 0.5;
        
        //this value MUST be a power of 4 (i.e. 4, 16, 64...)
        //works best for 256, 1024, 4096, and 16384.  Larger values not recommended
        int numberOfPoints = 1024;
        
        //the minimum width value in mm (1.0 at thinnest and 1.5 to be safe)
        double minWidth = 1.5;
        
        OBJHandler m = new OBJHandler();
        
        TreePoint headPoint = new TreePoint(0.0, 0.0, 0.0, minWidth);
        
        TreePoint leftPoint = new TreePoint(headPoint.getXCoordinate() - (gridLength / 2.0), headPoint.getYCoordinate(), headPoint.getZCoordinate(), minWidth);
        
        TreePoint rightPoint = new TreePoint(headPoint.getXCoordinate() + (gridLength / 2.0), headPoint.getYCoordinate(), headPoint.getZCoordinate(), minWidth);
        
        TreePoint topPoint = new TreePoint(headPoint.getXCoordinate(), headPoint.getYCoordinate() + (gridLength / 2.0), headPoint.getZCoordinate(), minWidth);
        
        TreePoint bottomPoint = new TreePoint(headPoint.getXCoordinate(), headPoint.getYCoordinate() - (gridLength / 2.0), headPoint.getZCoordinate(), minWidth);
        
        TreePoint frontPoint = new TreePoint(headPoint.getXCoordinate(), headPoint.getYCoordinate(), headPoint.getZCoordinate() + (gridLength / 2.0), minWidth);
        
        TreePoint backPoint = new TreePoint(headPoint.getXCoordinate(), headPoint.getYCoordinate(), headPoint.getZCoordinate() - (gridLength / 2.0), minWidth);
        
        m.addSquare_Centered(headPoint, gridLength);
        
//        leftPoint.buildTree(numberOfPoints, gridLength, 1);
//        m.drawSkin(headPoint, skinThickness, gridLength * MathHelpers.getSquareRoot_PowersOfFour(numberOfPoints), 1);
//        
        //uncomment to get full cube
        //rightPoint.buildTree(numberOfPoints, gridLenght, 2);
        //m.drawSkin(headPoint, skinThickness, gridLenght * getSquareRoot_PowersOfFour(numberOfPoints), 2);
        
        topPoint.buildTree(numberOfPoints, gridLength, 3);
        m.drawSkin(headPoint, skinThickness, gridLength * MathHelpers.getSquareRoot_PowersOfFour(numberOfPoints), 3);
        
        bottomPoint.buildTree(numberOfPoints, gridLength, 4);
        m.drawSkin(headPoint, skinThickness, gridLength * MathHelpers.getSquareRoot_PowersOfFour(numberOfPoints), 4);
        
        frontPoint.buildTree(numberOfPoints, gridLength, 5);
        m.drawSkin(headPoint, skinThickness, gridLength * MathHelpers.getSquareRoot_PowersOfFour(numberOfPoints), 5);
        
        backPoint.buildTree(numberOfPoints, gridLength, 6);
        m.drawSkin(headPoint, skinThickness, gridLength * MathHelpers.getSquareRoot_PowersOfFour(numberOfPoints), 6);
        
//        leftPoint.buildObj_JustLines(m, gridLength, 1);
//        
//        rightPoint.buildObj_JustLines(m, gridLength, 2);
        
        topPoint.buildObj_JustLines(m, gridLength, 3);
        
        bottomPoint.buildObj_JustLines(m, gridLength, 4);
        
        frontPoint.buildObj_JustLines(m, gridLength, 5);
        
        backPoint.buildObj_JustLines(m, gridLength, 6);
        
        m.createFile();
    }
}