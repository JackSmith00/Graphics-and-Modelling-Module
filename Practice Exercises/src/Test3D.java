import com.sun.j3d.utils.universe.SimpleUniverse;

import com.sun.j3d.utils.geometry.ColorCube;

import javax.media.j3d.BranchGroup;

public class Test3D {

public Test3D()

{

   SimpleUniverse universe = new SimpleUniverse();

   BranchGroup group = new BranchGroup();

   group.addChild(new ColorCube(0.3));

   universe.getViewingPlatform().setNominalViewingTransform();

   universe.addBranchGraph(group);

}

public static void main( String[] args ) {

   System.setProperty("sun.awt.noerasebackground", "true");

   new Test3D();

}

} // end of class Hello3d
