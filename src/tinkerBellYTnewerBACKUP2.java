//
//
//import java.applet.*;
//import java.awt.*;
//import javax.media.j3d.*;
//import javax.vecmath.*;
//import com.sun.j3d.utils.applet.MainFrame;
//import com.sun.j3d.utils.universe.SimpleUniverse;
//import com.sun.j3d.utils.universe.PlatformGeometry;
//import com.sun.j3d.utils.behaviors.keyboard.*;
//import com.sun.j3d.loaders.Scene;
//import java.awt.event.KeyListener;
//import java.awt.event.KeyEvent;
//import com.sun.j3d.utils.behaviors.vp.*;
//import com.sun.j3d.utils.universe.ViewingPlatform;
//import com.sun.j3d.utils.geometry.ColorCube;
//import java.util.ArrayList;
//
///**
// * class borrowed from https://www.youtube.com/watch?v=jzSN9_Wsf1Q  fukinotou11d's "How to Rotate 3D Models in Java 3D" tutorial in comments section 
// *  Soumia Hachami asked about view controls
// * @author David
// */
//public class tinkerBellYT extends Applet implements KeyListener {
//    private SimpleUniverse universe = null;
//    private Canvas3D canvas = null;
//    private TransformGroup viewtrans = null;
//    private TransformGroup tg = null;
//    private Transform3D t3d = null;
//    private Transform3D t3dstep = new Transform3D();a
//    private Matrix4d matrix = new Matrix4d();
//    
//    ArrayList<Term> terms;//D: here goes nothing
//    public static float xMinView = -1.0f;
//    public static float xMaxView = 1.0f;
//    BranchGroup bigGroup = new BranchGroup();
//    
//    public tinkerBellYT() {
//        setLayout(new BorderLayout());
//        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
//        canvas = new Canvas3D(config);
//        add("Center", canvas);
//        universe = new SimpleUniverse(canvas);
//        BranchGroup scene = createSceneGraph();
//        universe.getViewingPlatform().setNominalViewingTransform();
//        universe.getViewer().getView().setBackClipDistance(100.0);
//        canvas.addKeyListener(this);
//        universe.addBranchGraph(scene);
//    }
//    
//    
//    //mine.
//    public tinkerBellYT(ArrayList<Term> terms) {
//        this.terms = terms;
//        setLayout(new BorderLayout());
//        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
//        canvas = new Canvas3D(config);
//        add("Center", canvas);
//        universe = new SimpleUniverse(canvas);
//        BranchGroup scene = createSceneGraph();
//        universe.getViewingPlatform().setNominalViewingTransform();
//        universe.getViewer().getView().setBackClipDistance(100.0);
//        canvas.addKeyListener(this);
//        universe.addBranchGraph(scene);
//        universe.addBranchGraph(bigGroup);
//    }
//    
//    
//    private BranchGroup createSceneGraph() {
//        BranchGroup objRoot = new BranchGroup();
//        BoundingSphere bounds = new BoundingSphere(new Point3d(), 10000.0);
//        viewtrans = universe.getViewingPlatform().getViewPlatformTransform();
//        KeyNavigatorBehavior keyNavBeh = new KeyNavigatorBehavior(viewtrans);
//        keyNavBeh.setSchedulingBounds(bounds);
//        PlatformGeometry platformGeom = new PlatformGeometry();
//        platformGeom.addChild(keyNavBeh);
//        universe.getViewingPlatform().setPlatformGeometry(platformGeom); //D: arrow direction movement
//        // (2) 
//        ViewingPlatform vp = universe.getViewingPlatform();
//        OrbitBehavior orbit = new OrbitBehavior(canvas);
//        orbit.setSchedulingBounds(bounds);
//        vp.setViewPlatformBehavior(orbit);//D: mouse orbit behavior
//        //D: Removing these to clean up scene
//        //objRoot.addChild(createColorCube());
//        createRotationalFunction(terms);
//        //objRoot.addChild(createBox());
//        
//        //D: I think I can add whatever I want here 
//        objRoot.addChild(MyGraph.xAxis());
//        objRoot.addChild(MyGraph.yAxis());
//        objRoot.addChild(MyGraph.zAxis());
//        //objRoot.addChild(printFunction(terms));
//        
//        
//        
//        return objRoot;
//    }
//    // (1) 
//    private BranchGroup createColorCube() {
//        BranchGroup objRoot = new BranchGroup();
//        tg = new TransformGroup();
//        t3d = new Transform3D();
//        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
//        t3d.setTranslation(new Vector3d(0.0, 0.0, 0.0));
//        t3d.setScale(0.1);
//        tg.setTransform(t3d);
//        TransformGroup tg_2 = new TransformGroup();
//        Transform3D t3d_2 = new Transform3D();
//        t3d_2.setTranslation(new Vector3d(0.0, 0.0, 0.0));//D: this controls the objects origin. originally (10,0,0)
//        t3d_2.setScale(1.0);
//        tg_2.setTransform(t3d_2);
//        tg_2.addChild(new ColorCube());
//        tg.addChild(tg_2);
//        objRoot.addChild(tg);
//        objRoot.addChild(createLight());
//        objRoot.addChild(createLight());
//        objRoot.addChild(createLight());
//        objRoot.compile();
//        return objRoot;
//    }
////    
////    //Working Rotation method with poor optimization
////    private void createRotationalFunction(ArrayList<Term> terms) {
////        TransformGroup mytg = null;
////        Transform3D myt3d = null;
////        Transform3D myt3dstep = new Transform3D();
////        Matrix4d mymatrix = new Matrix4d();
////        BranchGroup group = new BranchGroup();
////        TransformGroup tg_2 = new TransformGroup();
////        Transform3D t3d_2 = new Transform3D();
////        float y;
////        
//////        //original        
//////        mytg = null;
//////        myt3d = null;
//////        myt3dstep = new Transform3D();
//////        mymatrix = new Matrix4d();
//////        group = new BranchGroup();
//////        mytg = new TransformGroup();
//////        myt3d = new Transform3D();
//////        mytg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
//////        myt3d.setTranslation(new Vector3d(0.0, 0.0, 0.0));
//////        myt3d.setScale(0.1);
//////        mytg.setTransform(myt3d);
//////        tg_2 = new TransformGroup();
//////        t3d_2 = new Transform3D();
//////        t3d_2.setTranslation(new Vector3d(0.0, 0.0, 0.0));//D: this controls the objects origin. originally (10,0,0)
//////        t3d_2.setScale(1.0);
//////        tg_2.setTransform(t3d_2);
//////        tg_2.addChild(new ColorCube());
//////        mytg.addChild(tg_2);
//////        group.addChild(mytg);
//////        group.addChild(createLight());
//////        group.addChild(createLight());
//////        group.addChild(createLight());
//////        group.compile();
//////        myt3dstep.rotY(Math.PI / 32);
//////        mytg.getTransform(myt3d);
//////        myt3d.get(mymatrix);
//////        myt3d.setTranslation(new Vector3d(0.0, 0.0, 0.0));
//////        myt3d.mul(myt3dstep);
//////        myt3d.setTranslation(new Vector3d(mymatrix.m03, mymatrix.m13, mymatrix.m23));
//////        mytg.setTransform(myt3d);
//////        //return group;
//////        bigGroup.addChild(group);
////        
////        
////        
////        System.out.println("good luck");
////        for(int j = 0; j < 2; j++){
////            //for (float x = xMinView; x <= xMaxView; x += .001f){//causing black screen...
////            for(float x =-5; x < 5; x +=.05){
////                y=0;//does this help?
////                for(int i = 0; i < terms.size(); i++){
////                    if(terms.get(i).hasVariable()){
////                        Term myTerm = terms.get(i);
////                        float varPow = (float)(Math.pow(x, myTerm.getExponent())); //x raised to exp
////                        y = y + myTerm.getCoeff()*(varPow);
////                    }else{
////                        int termNum = Integer.parseInt(terms.get(i).toString());
////                        y = y + termNum;
////                    }
////                }
////                    mytg = null;
////                    myt3d = null;
////                    myt3dstep = new Transform3D();
////                    mymatrix = new Matrix4d();
////                    group = new BranchGroup();
////                    mytg = new TransformGroup();
////                    myt3d = new Transform3D();
////                    mytg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
////                    myt3d.setTranslation(new Vector3d(0.0, 0.0, 0.0));
////                    myt3d.setScale(0.1);
////                    mytg.setTransform(myt3d);
////                    tg_2 = new TransformGroup();
////                    t3d_2 = new Transform3D();
////                    t3d_2.setTranslation(new Vector3d(x, y, 0.0));//D: this controls the objects origin. originally (10,0,0)
////                    t3d_2.setScale(1.0);
////                    tg_2.setTransform(t3d_2);
////                    tg_2.addChild(new Pixel(0.1f).getPixel());//D:changing this only...
////                    mytg.addChild(tg_2);
////                    group.addChild(mytg);
////                    group.addChild(createLight());
////                    group.addChild(createLight());
////                    group.addChild(createLight());
////                    group.compile();
////                        //myt3dstep.rotY(Math.PI / 32);
////                        //myt3dstep.rotY(Math.PI / 4);
////                        //myt3dstep.rotY(Math.PI * j / 10);
////                        mytg.getTransform(myt3d);
////                        myt3d.get(mymatrix);
////                        myt3d.setTranslation(new Vector3d(0.0, 0.0, 0.0));
////                        myt3d.mul(myt3dstep);
////                        myt3d.setTranslation(new Vector3d(mymatrix.m03, mymatrix.m13, mymatrix.m23));
////                        mytg.setTransform(myt3d);
////                    //return group;
////                    bigGroup.addChild(group);
////            }
////        }
////    }
//    
//    //optimization attempt
//    private void createRotationalFunction(ArrayList<Term> terms, int angle) {
//        TransformGroup mytg = null;
//        Transform3D myt3d = null;
//        Transform3D myt3dstep = new Transform3D();
//        Matrix4d mymatrix = new Matrix4d();
//        BranchGroup group = new BranchGroup();
//        TransformGroup tg_2 = new TransformGroup();
//        Transform3D t3d_2 = new Transform3D();
//        float y;
//        
//        System.out.println("good luck");
//        for(int j = 0; j < 1; j++){
//            //for (float x = xMinView; x <= xMaxView; x += .001f){//causing black screen...
//            for(float x =-5; x < 5; x +=.05){
//                y=0;//does this help?
//                for(int i = 0; i < terms.size(); i++){
//                    if(terms.get(i).hasVariable()){
//                        Term myTerm = terms.get(i);
//                        float varPow = (float)(Math.pow(x, myTerm.getExponent())); //x raised to exp
//                        y = y + myTerm.getCoeff()*(varPow);
//                    }else{
//                        int termNum = Integer.parseInt(terms.get(i).toString());
//                        y = y + termNum;
//                    }
//                }
//                    mytg = null;
//                    myt3d = null;
//                    myt3dstep = new Transform3D();
//                    mymatrix = new Matrix4d();
//                    group = new BranchGroup();
//                    mytg = new TransformGroup();
//                    myt3d = new Transform3D();
//                    mytg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
//                    myt3d.setTranslation(new Vector3d(0.0, 0.0, 0.0));
//                    myt3d.setScale(0.1);
//                    mytg.setTransform(myt3d);
//                    tg_2 = new TransformGroup();
//                    t3d_2 = new Transform3D();
//                    t3d_2.setTranslation(new Vector3d(x, y, 0.0));//D: this controls the objects origin. originally (10,0,0)
//                    t3d_2.setScale(1.0);
//                    tg_2.setTransform(t3d_2);
//                    tg_2.addChild(new Pixel(0.1f).getPixel());//D:changing this only...
//                    mytg.addChild(tg_2);
//                    group.addChild(mytg);
//                    group.addChild(createLight());
//                    group.addChild(createLight());
//                    group.addChild(createLight());
//                    group.compile();
//                        myt3dstep.rotY(Math.PI / 32);
//                        //myt3dstep.rotY(Math.PI / 4);
//                        //myt3dstep.rotY(Math.PI * j / 10);
//                        mytg.getTransform(myt3d);
//                        myt3d.get(mymatrix);
//                        myt3d.setTranslation(new Vector3d(0.0, 0.0, 0.0));
//                        myt3d.mul(myt3dstep);
//                        myt3d.setTranslation(new Vector3d(mymatrix.m03, mymatrix.m13, mymatrix.m23));
//                        mytg.setTransform(myt3d);
//                    //return group;
//                    bigGroup.addChild(group);
//            }
//        }
//    }
//    // (3) 
//    private BranchGroup createBox() {
//        BranchGroup objRoot = new BranchGroup();
//        TransformGroup tg = new TransformGroup();
//        Transform3D t3d = new Transform3D();
//        t3d.setTranslation(new Vector3d(0.0, 4.3, 0.0));
//        t3d.setScale(5.0);
//        tg.setTransform(t3d);
//        Appearance app = new Appearance();
//        PolygonAttributes pattr = new PolygonAttributes();
//        pattr.setPolygonMode(PolygonAttributes.POLYGON_FILL);
//        pattr.setCullFace(PolygonAttributes.CULL_NONE);
//        app.setPolygonAttributes(pattr);
//        TransparencyAttributes attr = new TransparencyAttributes(TransparencyAttributes.BLENDED, 0.0f);
//        attr.setTransparency(0.5f); // Semitransparent
//        app.setTransparencyAttributes(attr);
//        Color3f ambientColour1 = new Color3f(1.0f, 0.0f, 0.0f);
//        Color3f ambientColour2 = new Color3f(1.0f, 1.0f, 0.0f);
//        Color3f emissiveColour = new Color3f(0.0f, 0.0f, 0.0f);
//        Color3f specularColour = new Color3f(1.0f, 1.0f, 1.0f);
//        Color3f diffuseColour = new Color3f(1.0f, 0.0f, 0.0f);
//        float shininess = 20.0f;
//        app.setMaterial(new Material(ambientColour1, emissiveColour, diffuseColour, specularColour, shininess));
//        IndexedQuadArray indexedCube = new IndexedQuadArray(8, IndexedQuadArray.COORDINATES | IndexedQuadArray.NORMALS, 24);
//        Point3f[] cubeCoordinates = {
//            new Point3f(1.0f, 1.0f, 1.0f),
//            new Point3f(-1.0f, 1.0f, 1.0f),
//            new Point3f(-1.0f, -1.0f, 1.0f),
//            new Point3f(1.0f, -1.0f, 1.0f),
//            new Point3f(1.0f, 1.0f, -1.0f),
//            new Point3f(-1.0f, 1.0f, -1.0f),
//            new Point3f(-1.0f, -1.0f, -1.0f),
//            new Point3f(1.0f, -1.0f, -1.0f)
//        };
//        Vector3f[] cubeNormals = {
//            new Vector3f(0.0f, 0.0f, 1.0f),
//            new Vector3f(0.0f, 0.0f, -1.0f),
//            new Vector3f(1.0f, 0.0f, 0.0f),
//            new Vector3f(-1.0f, 0.0f, 0.0f),
//            new Vector3f(0.0f, 1.0f, 0.0f),
//            new Vector3f(0.0f, -1.0f, 0.0f)
//
//    
//        };
//        int cubeCoordIndices[] = {
//            0,
//            1,
//            2,
//            3,
//            7,
//            6,
//            5,
//            4,
//            0,
//            3,
//            7,
//            4,
//            5,
//            6,
//            2,
//            1,
//            0,
//            4,
//            5,
//            1,
//            6,
//            7,
//            3,
//            2
//        };
//        int cubeNormalIndices[] = {
//            0,
//            0,
//            0,
//            0,
//            1,
//            1,
//            1,
//            1,
//            2,
//            2,
//            2,
//            2,
//            3,
//            3,
//            3,
//            3,
//            4,
//            4,
//            4,
//            4,
//            5,
//            5,
//            5,
//            5
//        };
//        indexedCube.setCoordinates(0, cubeCoordinates);
//        indexedCube.setNormals(0, cubeNormals);
//        indexedCube.setCoordinateIndices(0, cubeCoordIndices);
//        indexedCube.setNormalIndices(0, cubeNormalIndices);
//        Shape3D Cube = new Shape3D(indexedCube, app);
//        tg.addChild(Cube);
//        objRoot.addChild(tg);
//        objRoot.addChild(createLight());
//        objRoot.compile();
//        return objRoot;
//    }
//    private Light createLight() {
//        DirectionalLight light = new DirectionalLight(true, new Color3f(1.0f, 1.0f, 1.0f), new Vector3f(-0.3f, 0.2f, -1.0f));
//        light.setInfluencingBounds(new BoundingSphere(new Point3d(), 10000.0));
//        return light;
//    }
//    public static void main(String[] args) {
//        ArrayList<Term> termList = new ArrayList<>();
//        termList.add(new Term("x^2"));
//        tinkerBellYT applet = new tinkerBellYT(termList);
//        //tinkerBellYT applet = new tinkerBellYT();
//        Frame frame = new MainFrame(applet, 800, 600);
//    }
//    public void keyTyped(KeyEvent e) {
//        char key = e.getKeyChar();
//        if (key == 'a') {
//            t3dstep.rotY(Math.PI / 32);
//            tg.getTransform(t3d);
//            t3d.get(matrix);
//            t3d.setTranslation(new Vector3d(0.0, 0.0, 0.0));
//            t3d.mul(t3dstep);
//            t3d.setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
//            tg.setTransform(t3d);
//        }
//        if (key == 'd') {
//            t3dstep.rotY(-Math.PI / 32);
//            tg.getTransform(t3d);
//            t3d.get(matrix);
//            t3d.setTranslation(new Vector3d(0.0, 0.0, 0.0));
//            t3d.mul(t3dstep);
//            t3d.setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
//            tg.setTransform(t3d);
//        }
//    }
//    public void keyReleased(KeyEvent e) {}
//    public void keyPressed(KeyEvent e) {}
//    
////    
////    BranchGroup printFunction(ArrayList<Term> terms){
////        BranchGroup group = new BranchGroup();
////        float y = 0;
////        
////        for (float x = xMinView; x <= xMaxView; x += .001f){
////            int coeff = 0;//unused?
////            int exp = 0;
////
////                
////            y=0;//might want to remove this...
////
////            //if(terms.size() == 1){
////            for(int i = 0; i < terms.size(); i++){
////                if(terms.get(i).hasVariable()){
////                    Term myTerm = terms.get(i);
////                    float varPow = (float)(Math.pow(x, myTerm.getExponent())); //x raised to exp
////                    y = y + myTerm.getCoeff()*(varPow);
////                }else{
////                    int termNum = Integer.parseInt(terms.get(i).toString());
////                    y = y + termNum;
////                }
////            }
////            Pixel sphere = new Pixel();
////            TransformGroup tg = new TransformGroup();
////            Transform3D transform = new Transform3D();
////            Vector3f vector = new Vector3f( x, y, .0f);
////            transform.setTranslation(vector);
////            tg.setTransform(transform);
////            tg.addChild(sphere.getPixel());
////            group.addChild(tg);
////        }
////        Color3f light1Color = new Color3f(.1f, 1.4f, .1f); // green light
////        BoundingSphere bounds =
////           new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
////        Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
////        DirectionalLight light1
////           = new DirectionalLight(light1Color, light1Direction);
////        light1.setInfluencingBounds(bounds);
////        group.addChild(light1);
////        
////        //hoping this group might let me render graph alone
////        group.addChild(createLight());
////        group.addChild(createLight());
////        group.addChild(createLight());
////        group.compile();
////        
////        
////        return group;
////    }
//    
//    
////    //trying to rotate this SOB ATTEMPT 1
////    BranchGroup printFunction(ArrayList<Term> terms){
////        BranchGroup group = new BranchGroup();
////        float y = 0;
////        
////        for (float x = xMinView; x <= xMaxView; x += .001f){
////            int coeff = 0;//unused?
////            int exp = 0;
////
////                
////            y=0;//might want to remove this...
////
////            //if(terms.size() == 1){
////            for(int i = 0; i < terms.size(); i++){
////                if(terms.get(i).hasVariable()){
////                    Term myTerm = terms.get(i);
////                    float varPow = (float)(Math.pow(x, myTerm.getExponent())); //x raised to exp
////                    y = y + myTerm.getCoeff()*(varPow);
////                }else{
////                    int termNum = Integer.parseInt(terms.get(i).toString());
////                    y = y + termNum;
////                }
////            }
////            //rotatehopefully
////            BranchGroup objRoot = new BranchGroup();
////            tg = new TransformGroup();
////            t3d = new Transform3D();
////            tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
////            t3d.setTranslation(new Vector3d(0.0, 0.0, 0.0));
////            t3d.setScale(0.1);
////            tg.setTransform(t3d);
////            TransformGroup tg_2 = new TransformGroup();
////            Transform3D t3d_2 = new Transform3D();
////            t3d_2.setTranslation(new Vector3d( x, y, .0f));//D: this controls the objects origin. originally (10,0,0)
////            t3d_2.setScale(1.0);
////            tg_2.setTransform(t3d_2);
////            tg_2.addChild(new Pixel().getPixel());
////            tg.addChild(tg_2);
////            group.addChild(tg);
////            group.compile();
////            
////            
////            
////            
////            
//////            Pixel sphere = new Pixel();
//////            TransformGroup tg = new TransformGroup();
//////            Transform3D transform = new Transform3D();
//////            Vector3f vector = new Vector3f( x, y, .0f);
//////            transform.setTranslation(vector);
//////            tg.setTransform(transform);
//////            tg.addChild(sphere.getPixel());
//////            group.addChild(tg);
////            //you may want to start over.
////        }
////        Color3f light1Color = new Color3f(.1f, 1.4f, .1f); // green light
////        BoundingSphere bounds =
////           new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
////        Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
////        DirectionalLight light1
////           = new DirectionalLight(light1Color, light1Direction);
////        light1.setInfluencingBounds(bounds);
////        group.addChild(light1);
////        
////        //hoping this group might let me render graph alone
////        group.addChild(createLight());
////        group.addChild(createLight());
////        group.addChild(createLight());
////        group.compile();
////        
////        
////        return group;
////    }
//      
//    //rotate attempt 2
//    BranchGroup printFunction(ArrayList<Term> terms){
//        TransformGroup mytg = new TransformGroup();
//        Transform3D myt3d = new Transform3D();
//        mytg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
//        Transform3D myt3dstep = new Transform3D();
//        Matrix4d mymatrix = new Matrix4d();
//        BranchGroup group = new BranchGroup();
//        float y = 0;
//        
//        
//        mytg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
//        myt3d.setTranslation(new Vector3d(0.0, 0.0, 0.0));
//        myt3d.setScale(0.1);
//        mytg.setTransform(myt3d);
//        for (float x = xMinView; x <= xMaxView; x += .001f){
//            int coeff = 0;//unused?
//            int exp = 0;
//
//                
//            y=0;//might want to remove this...
//
//            //if(terms.size() == 1){
//            for(int i = 0; i < terms.size(); i++){
//                if(terms.get(i).hasVariable()){
//                    Term myTerm = terms.get(i);
//                    float varPow = (float)(Math.pow(x, myTerm.getExponent())); //x raised to exp
//                    y = y + myTerm.getCoeff()*(varPow);
//                }else{
//                    int termNum = Integer.parseInt(terms.get(i).toString());
//                    y = y + termNum;
//                }
//                TransformGroup mytg_2 = new TransformGroup();
//                Transform3D myt3d_2 = new Transform3D();
//                myt3d_2.setTranslation(new Vector3d(x, y, 0.0));//D: this controls the objects origin. originally (10,0,0)
//                myt3d_2.setScale(1.0);
//                mytg_2.setTransform(myt3d_2);
//                mytg_2.addChild(new Pixel(1).getPixel());
//                //mytg.addChild(mytg_2);
//                group.addChild(mytg_2);
//            }
//            group.addChild(mytg); //branch changed from objRoot to group //moved out of loop
////            myt3dstep.rotY(Math.PI / 32);
////            mytg.getTransform(myt3d);
////            myt3d.get(mymatrix);
////            myt3d.setTranslation(new Vector3d(0.0, 0.0, 0.0));
////            myt3d.mul(myt3dstep);
////            myt3d.setTranslation(new Vector3d(mymatrix.m03, mymatrix.m13, mymatrix.m23));
////            mytg.setTransform(myt3d);
//            
//            
//            
//            
////            objRoot.addChild(createLight());
////            objRoot.addChild(createLight());
////            objRoot.addChild(createLight());
////            objRoot.compile();
//            return group;
//        }
//        Color3f light1Color = new Color3f(.1f, 1.4f, .1f); // green light
//        BoundingSphere bounds =
//           new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
//        Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
//        DirectionalLight light1
//           = new DirectionalLight(light1Color, light1Direction);
//        light1.setInfluencingBounds(bounds);
//        group.addChild(light1);
//        
//        //hoping this group might let me render graph alone
//        group.addChild(createLight());
//        group.addChild(createLight());
//        group.addChild(createLight());
//        group.compile();
//        
//        
//        return group;
//    }
//}                   