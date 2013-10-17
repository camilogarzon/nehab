import SimpleOpenNI.*;

SimpleOpenNI  context;

PVector jointPos = new PVector();
PVector com = new PVector();                                   
PVector com2d = new PVector(); 
void setup()
{
  size(640,480);
  
  context = new SimpleOpenNI(this);
  // enable depthMap generation 
  context.enableDepth();
   
  // enable skeleton generation for all joints
  context.enableUser();
 
 
}

void draw()
{
    context.startTrackingSkeleton(1);
  PImage depth = context.depthImage();
image(depth, 0, 0);
  IntVector blabla= new IntVector();
  
  context.update();
  context.getUsers(blabla); // <----------------!!!!!!!!!!!!!
 // int userId = blabla.get(0);
   if(context.getCoM(1,com))
    {
      context.convertRealWorldToProjective(com,com2d);
      stroke(100,255,0);
      strokeWeight(1);
      beginShape(LINES);
        vertex(com2d.x,com2d.y - 5);
        vertex(com2d.x,com2d.y + 5);

        vertex(com2d.x - 5,com2d.y);
        vertex(com2d.x + 5,com2d.y);
      endShape();
      
      fill(0,255,100);
    //  text(Integer.toString(userId),com2d.x,com2d.y);
    }
  if (blabla.size() > 0) {
// get the first user
int userId2 = blabla.get(0);

  context.getJointPositionSkeleton(userId2,SimpleOpenNI.SKEL_RIGHT_HAND,jointPos);
  PVector convertedRightHand = new PVector();
  println(jointPos);

  context.convertRealWorldToProjective(jointPos,convertedRightHand);
 // image(context.userImage(),0,0);
  println("x= "+ convertedRightHand.x);
  println("y= "+ convertedRightHand.y);
  fill(255,0,0);
  ellipse(convertedRightHand.x, convertedRightHand.y, 25, 25); 
  context.drawLimb(userId2, SimpleOpenNI.SKEL_LEFT_ELBOW, SimpleOpenNI.SKEL_LEFT_HAND);
}
}
