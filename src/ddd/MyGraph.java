/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ddd;

import com.sun.j3d.utils.geometry.*;

import com.sun.j3d.utils.universe.*;
import java.util.ArrayList;

import javax.media.j3d.*;

import javax.vecmath.*;

public class MyGraph {
    public static final float axisSize = 0.025f;
    BranchGroup group;
    SimpleUniverse universe;
    public static float xMinView = -5.0f;
    public static float xMaxView = 5.0f;
    
    public MyGraph() {
        universe = new SimpleUniverse();

        group = new BranchGroup();


        allAxis(group);

        //lineToPoint(2f,-1f, group);


//        Color3f light1Color = new Color3f(.1f, 1.4f, .1f); // green light
//        BoundingSphere bounds =
//           new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
//        Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
//        DirectionalLight light1
//           = new DirectionalLight(light1Color, light1Direction);
//        light1.setInfluencingBounds(bounds);
//        group.addChild(light1);
//        universe.getViewingPlatform().setNominalViewingTransform();
//
//        // add the group of objects to the Universe
//        universe.addBranchGraph(group);

    }

    void printFunction(ArrayList<Term> terms){
        float y = 0;
        
        for (float x = -1.0f; x <= 1.0f; x += .001f){
            int coeff = 0;//unused?
            int exp = 0;

                
                y=0;//might want to remove this...
                
                //if(terms.size() == 1){
                for(int i = 0; i < terms.size(); i++){
                    if(terms.get(i).hasVariable()){
                        Term myTerm = terms.get(i);
                        float varPow = (float)(Math.pow(x, myTerm.getExponent())); //x raised to exp
                        y = y + myTerm.getCoeff()*(varPow);
                    }else{
                        int termNum = Integer.parseInt(terms.get(i).toString());
                        y = y + termNum;
                    }
                }
                
                
    //            System.out.println(y);
    //            System.out.println(x);
                Pixel sphere = new Pixel();
//                Tried moving this above to bugfix, didn't work too well
                TransformGroup tg = new TransformGroup();
                Transform3D transform = new Transform3D();
                Vector3f vector = new Vector3f( x, y, .0f);
                transform.setTranslation(vector);
                tg.setTransform(transform);
                tg.addChild(sphere.getPixel());
                group.addChild(tg);
        }
        Color3f light1Color = new Color3f(.1f, 1.4f, .1f); // green light
        BoundingSphere bounds =
           new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
        Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
        DirectionalLight light1
           = new DirectionalLight(light1Color, light1Direction);
        light1.setInfluencingBounds(bounds);
        group.addChild(light1);
        universe.getViewingPlatform().setNominalViewingTransform();

        // add the group of objects to the Universe
        universe.addBranchGraph(group);
    }
    
    void lineToPoint(float p1, float p2, BranchGroup group){
        float y = 0;
        for (float x = -1.0f; x <= p1; x += .001f)

        {
           //prevent an error for y = (p1/p2)*x
            if(p2 != 0){
                y = (p1/p2)*x*x+3*x*x*x; 

    //            System.out.println(y);
    //            System.out.println(x);
                Pixel sphere = new Pixel();

                TransformGroup tg = new TransformGroup();

                Transform3D transform = new Transform3D();

                Vector3f vector = new Vector3f( x, y, .0f);
                transform.setTranslation(vector);

                tg.setTransform(transform);

                tg.addChild(sphere.getPixel());

                group.addChild(tg);

            }
        }
    }

    public void allAxis(BranchGroup group){
        xAxis(group);
        yAxis(group);
        zAxis(group);
    }
    
    //public void xAxis(BranchGroup group){
    //    //x axis going 45 made of spheres
    //    for (float x = 0f; x <= .5f; x = x + 0.07f)
    //    {
    //       Sphere sphere = new Sphere(0.05f);
    //       TransformGroup tg = new TransformGroup();
    //       Transform3D transform = new Transform3D();
    //       Vector3f vector = new Vector3f( x, x, .0f);
    //       transform.setTranslation(vector);
    //       tg.setTransform(transform);
    //       tg.addChild(sphere);
    //       group.addChild(tg);
    //    }  
    //}

    public void xAxis(BranchGroup group){
        //X axis made of spheres
        for (float x = -1.0f; x <= 1.0f; x = x + 0.1f)
        {
           Sphere sphere = new Sphere(axisSize);
           TransformGroup tg = new TransformGroup();
           Transform3D transform = new Transform3D();
           Vector3f vector = new Vector3f( x, .0f, .0f);
           transform.setTranslation(vector);
           tg.setTransform(transform);
           tg.addChild(sphere);
           group.addChild(tg);
        }
    }
    

    public void yAxis(BranchGroup group){
        //Y axis made of cones
        for (float y = -1.0f; y <= 1.0f; y = y + 0.1f)
        {
           TransformGroup tg = new TransformGroup();
           Transform3D transform = new Transform3D();
           Cone cone = new Cone(axisSize, axisSize*2.0f);
           Vector3f vector = new Vector3f(.0f, y, .0f);
           transform.setTranslation(vector);
           tg.setTransform(transform);
           tg.addChild(cone);
           group.addChild(tg);
        }
    }

    public void zAxis(BranchGroup group){
        // Z axis made of cylinders
        for (float z = -1.0f; z <= 1.0f; z = z+ 0.1f)
        {
            float cylAreTooBig = 1.5f;
            TransformGroup tg = new TransformGroup();
            Transform3D transform = new Transform3D();
            Cylinder cylinder = new Cylinder(axisSize/cylAreTooBig, (axisSize*2.0f)/cylAreTooBig);
            Vector3f vector = new Vector3f(.0f, .0f, z);
            transform.setTranslation(vector);
            tg.setTransform(transform);
            tg.addChild(cylinder);
            group.addChild(tg);
        }
    }
    public static BranchGroup xAxis(){
        //X axis made of spheres
        BranchGroup group = new BranchGroup();
        for (float x = xMinView; x <= xMaxView; x = x + 0.1f)
        {
            Sphere sphere = new Sphere(axisSize);
            TransformGroup tg = new TransformGroup();
            Transform3D transform = new Transform3D();
            Vector3f vector = new Vector3f( x, .0f, .0f);
            transform.setTranslation(vector);
            tg.setTransform(transform);
            tg.addChild(sphere);
            group.addChild(tg);
        }
        return group;
    }

    public static BranchGroup yAxis(){
        //Y axis made of cones
        BranchGroup group = new BranchGroup();
        for (float y = xMinView; y <= xMaxView; y = y + 0.1f)
        {
           TransformGroup tg = new TransformGroup();
           Transform3D transform = new Transform3D();
           Cone cone = new Cone(axisSize, axisSize*2.0f);
           Vector3f vector = new Vector3f(.0f, y, .0f);
           transform.setTranslation(vector);
           tg.setTransform(transform);
           tg.addChild(cone);
           group.addChild(tg);
        }
        return group;
    }
    
    public static BranchGroup zAxis(){
        // Z axis made of cylinders
        BranchGroup group = new BranchGroup();
        for (float z = -1.0f; z <= 1.0f; z = z+ 0.1f)
        {
            float cylAreTooBig = 1.5f;
            TransformGroup tg = new TransformGroup();
            Transform3D transform = new Transform3D();
            Cylinder cylinder = new Cylinder(axisSize/cylAreTooBig, (axisSize*2.0f)/cylAreTooBig);
            Vector3f vector = new Vector3f(.0f, .0f, z);
            transform.setTranslation(vector);
            tg.setTransform(transform);
            tg.addChild(cylinder);
            group.addChild(tg);
        }
        return group;
    }

}