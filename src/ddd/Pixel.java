/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ddd;

import com.sun.j3d.utils.geometry.Box;
import javax.media.j3d.Appearance;

/**
 *
 * @author David
 */
public class Pixel {
    Box pixel;
    Pixel(){
        pixel = new Box(.005f,.005f,.005f,new Appearance());
    }
    Pixel(float size){
        pixel = new Box(size,size,size,new Appearance());
    }
    public Box getPixel(){
        return pixel;
    }
    
}
