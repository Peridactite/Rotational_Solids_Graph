package ddd;

import java.applet.*;
import java.awt.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.PlatformGeometry;
import com.sun.j3d.utils.behaviors.keyboard.*;
import com.sun.j3d.loaders.Scene;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import com.sun.j3d.utils.behaviors.vp.*;
import com.sun.j3d.utils.universe.ViewingPlatform;
import com.sun.j3d.utils.geometry.ColorCube;

/**
 * class borrowed from https://www.youtube.com/watch?v=jzSN9_Wsf1Q  fukinotou11d's "How to Rotate 3D Models in Java 3D" tutorial in comments section 
 *  Soumia Hachami asked about view controls
 * @author David
 */
public class tinkerBellYTBACKUP extends Applet implements KeyListener {
    private SimpleUniverse universe = null;
    private Canvas3D canvas = null;
    private TransformGroup viewtrans = null;
    private TransformGroup tg = null;
    private Transform3D t3d = null;
    private Transform3D t3dstep = new Transform3D();
    private Matrix4d matrix = new Matrix4d();
    public tinkerBellYTBACKUP() {
        setLayout(new BorderLayout());
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        canvas = new Canvas3D(config);
        add("Center", canvas);
        universe = new SimpleUniverse(canvas);
        BranchGroup scene = createSceneGraph();
        universe.getViewingPlatform().setNominalViewingTransform();
        universe.getViewer().getView().setBackClipDistance(100.0);
        canvas.addKeyListener(this);
        universe.addBranchGraph(scene);
    }
    private BranchGroup createSceneGraph() {
        BranchGroup objRoot = new BranchGroup();
        BoundingSphere bounds = new BoundingSphere(new Point3d(), 10000.0);
        viewtrans = universe.getViewingPlatform().getViewPlatformTransform();
        KeyNavigatorBehavior keyNavBeh = new KeyNavigatorBehavior(viewtrans);
        keyNavBeh.setSchedulingBounds(bounds);
        PlatformGeometry platformGeom = new PlatformGeometry();
        platformGeom.addChild(keyNavBeh);
        universe.getViewingPlatform().setPlatformGeometry(platformGeom);
        // (2) 
        ViewingPlatform vp = universe.getViewingPlatform();
        OrbitBehavior orbit = new OrbitBehavior(canvas);
        orbit.setSchedulingBounds(bounds);
        vp.setViewPlatformBehavior(orbit);
        objRoot.addChild(createColorCube());
        objRoot.addChild(createBox());
        return objRoot;
    }
    // (1) 
    private BranchGroup createColorCube() {
        BranchGroup objRoot = new BranchGroup();
        tg = new TransformGroup();
        t3d = new Transform3D();
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        t3d.setTranslation(new Vector3d(0.0, -0.4, -0.5));
        t3d.setScale(0.1);
        tg.setTransform(t3d);
        TransformGroup tg_2 = new TransformGroup();
        Transform3D t3d_2 = new Transform3D();
        t3d_2.setTranslation(new Vector3d(10.0, 0.0, 0.0));
        t3d_2.setScale(1.0);
        tg_2.setTransform(t3d_2);
        tg_2.addChild(new ColorCube());
        tg.addChild(tg_2);
        objRoot.addChild(tg);
        objRoot.addChild(createLight());
        objRoot.addChild(createLight());
        objRoot.addChild(createLight());
        objRoot.compile();
        return objRoot;
    }
    // (3) 
    private BranchGroup createBox() {
        BranchGroup objRoot = new BranchGroup();
        TransformGroup tg = new TransformGroup();
        Transform3D t3d = new Transform3D();
        t3d.setTranslation(new Vector3d(0.0, 4.3, 0.0));
        t3d.setScale(5.0);
        tg.setTransform(t3d);
        Appearance app = new Appearance();
        PolygonAttributes pattr = new PolygonAttributes();
        pattr.setPolygonMode(PolygonAttributes.POLYGON_FILL);
        pattr.setCullFace(PolygonAttributes.CULL_NONE);
        app.setPolygonAttributes(pattr);
        TransparencyAttributes attr = new TransparencyAttributes(TransparencyAttributes.BLENDED, 0.0f);
        attr.setTransparency(0.5f); // Semitransparent
        app.setTransparencyAttributes(attr);
        Color3f ambientColour1 = new Color3f(1.0f, 0.0f, 0.0f);
        Color3f ambientColour2 = new Color3f(1.0f, 1.0f, 0.0f);
        Color3f emissiveColour = new Color3f(0.0f, 0.0f, 0.0f);
        Color3f specularColour = new Color3f(1.0f, 1.0f, 1.0f);
        Color3f diffuseColour = new Color3f(1.0f, 0.0f, 0.0f);
        float shininess = 20.0f;
        app.setMaterial(new Material(ambientColour1, emissiveColour, diffuseColour, specularColour, shininess));
        IndexedQuadArray indexedCube = new IndexedQuadArray(8, IndexedQuadArray.COORDINATES | IndexedQuadArray.NORMALS, 24);
        Point3f[] cubeCoordinates = {
            new Point3f(1.0f, 1.0f, 1.0f),
            new Point3f(-1.0f, 1.0f, 1.0f),
            new Point3f(-1.0f, -1.0f, 1.0f),
            new Point3f(1.0f, -1.0f, 1.0f),
            new Point3f(1.0f, 1.0f, -1.0f),
            new Point3f(-1.0f, 1.0f, -1.0f),
            new Point3f(-1.0f, -1.0f, -1.0f),
            new Point3f(1.0f, -1.0f, -1.0f)
        };
        Vector3f[] cubeNormals = {
            new Vector3f(0.0f, 0.0f, 1.0f),
            new Vector3f(0.0f, 0.0f, -1.0f),
            new Vector3f(1.0f, 0.0f, 0.0f),
            new Vector3f(-1.0f, 0.0f, 0.0f),
            new Vector3f(0.0f, 1.0f, 0.0f),
            new Vector3f(0.0f, -1.0f, 0.0f)

    
        };
        int cubeCoordIndices[] = {
            0,
            1,
            2,
            3,
            7,
            6,
            5,
            4,
            0,
            3,
            7,
            4,
            5,
            6,
            2,
            1,
            0,
            4,
            5,
            1,
            6,
            7,
            3,
            2
        };
        int cubeNormalIndices[] = {
            0,
            0,
            0,
            0,
            1,
            1,
            1,
            1,
            2,
            2,
            2,
            2,
            3,
            3,
            3,
            3,
            4,
            4,
            4,
            4,
            5,
            5,
            5,
            5
        };
        indexedCube.setCoordinates(0, cubeCoordinates);
        indexedCube.setNormals(0, cubeNormals);
        indexedCube.setCoordinateIndices(0, cubeCoordIndices);
        indexedCube.setNormalIndices(0, cubeNormalIndices);
        Shape3D Cube = new Shape3D(indexedCube, app);
        tg.addChild(Cube);
        objRoot.addChild(tg);
        objRoot.addChild(createLight());
        objRoot.compile();
        return objRoot;
    }
    private Light createLight() {
        DirectionalLight light = new DirectionalLight(true, new Color3f(1.0f, 1.0f, 1.0f), new Vector3f(-0.3f, 0.2f, -1.0f));
        light.setInfluencingBounds(new BoundingSphere(new Point3d(), 10000.0));
        return light;
    }
    public static void main(String[] args) {
        tinkerBellYTBACKUP applet = new tinkerBellYTBACKUP();
        Frame frame = new MainFrame(applet, 800, 600);
    }
    public void keyTyped(KeyEvent e) {
        char key = e.getKeyChar();
        if (key == 's') {
            t3dstep.rotY(Math.PI / 32);
            tg.getTransform(t3d);
            t3d.get(matrix);
            t3d.setTranslation(new Vector3d(0.0, 0.0, 0.0));
            t3d.mul(t3dstep);
            t3d.setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
            tg.setTransform(t3d);
        }
        if (key == 'f') {
            t3dstep.rotY(-Math.PI / 32);
            tg.getTransform(t3d);
            t3d.get(matrix);
            t3d.setTranslation(new Vector3d(0.0, 0.0, 0.0));
            t3d.mul(t3dstep);
            t3d.setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
            tg.setTransform(t3d);
        }
    }
    public void keyReleased(KeyEvent e) {}
    public void keyPressed(KeyEvent e) {}
}                   