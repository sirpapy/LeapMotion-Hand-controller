import java.awt.Dimension;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import Interface.Visualizer;

import com.leapmotion.leap.CircleGesture;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Gesture.State;
import com.leapmotion.leap.Gesture.Type;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.InteractionBox;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Vector;

class MouseListener extends Listener {

	public Robot robot;

	public void onConnect(Controller c) {
		c.enableGesture(Gesture.Type.TYPE_CIRCLE);
		c.enableGesture(Gesture.Type.TYPE_SWIPE);
		c.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
	}

	public void onFrame(Controller c) {
		int f_ext_count = 0;
		int f_ext_id = 1;
		int hand_count = 0;
		Finger f_ext;
	//	Vector hand1_pos, hand2_pos;
		List<Hand> hand_pos =new ArrayList<Hand>();
		try {
			robot = new Robot();
		} catch (Exception e) {
		}
		Frame frame = c.frame();
		InteractionBox box = frame.interactionBox();
		for (Finger f : frame.fingers()) {
			//System.out.println("Vous avez montrés " + f_ext_count	+ " doigt(s) extended");
			if (f.isExtended() == true) {
				f_ext_count++;
				f_ext_id = f.id();
			}
		}
		/*
		 */

		if (f_ext_count == 1 || f_ext_count == 2) {
			if (f_ext_count == 1) {
				f_ext = frame.finger(f_ext_id);
				Vector fingerPos = f_ext.stabilizedTipPosition();
				Vector boxFingerPos = box.normalizePoint(fingerPos);
				Dimension screen = java.awt.Toolkit.getDefaultToolkit()
						.getScreenSize();
				robot.mouseMove((int) (screen.width * boxFingerPos.getX()),
						(int) (screen.height - boxFingerPos.getY()
								* screen.height));

			}
			if(f_ext_count==1){
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
			}
			if (f_ext_count == 2) {
				robot.mousePress(InputEvent.BUTTON1_MASK);
				try {
					Thread.sleep(100);
				} catch (Exception e) {
				}
				
				
			}
		} else {
			for (Gesture g : frame.gestures()) {
				/*
				 * ZZZZZZZZZZZZZOOOOOOOOOOOOMMMMMMMMMMM
				 */
				if ((frame.hands().count() == 2)
						) {
					//hand1_pos = frame.hand(1).palmPosition();
					//hand2_pos = frame.hand(2).palmPosition();
					//System.out.println("position hand1 :"+hand1_pos);
					for(Hand h:frame.hands()){
						hand_pos.add(h);
					//	System.out.println("position hand2 :"+h.palmPosition());
					}
					Frame previous = c.frame(1);
					float previous_distance = previous.hand(hand_pos.get(1).id()).palmPosition().getX() - previous.hand(hand_pos.get(2).id()).palmPosition().getX();
					float position = hand_pos.get(1).palmPosition().getX() - hand_pos.get(2).palmPosition().getX();
					System.out.println("Distance "+position);
					System.out.println("Previous distance "+previous_distance);
					
					if (previous_distance > position) {
						robot.keyPress(KeyEvent.VK_CONTROL);

						robot.mouseWheel(1);
					}
					if (previous_distance < position) {
						robot.keyPress(KeyEvent.VK_CONTROL);
						robot.mouseWheel(-1);
						;
					}

				}

				/* FIN ZOOMMMMMMMMMMMMMMMMMMMMMMMMMMM */

				if (g.type() == Type.TYPE_CIRCLE) {
					CircleGesture circle = new CircleGesture(g);
					if (circle.pointable().direction().angleTo(circle.normal()) <= Math.PI / 4) {
						robot.mouseWheel(1);
						try {
							Thread.sleep(50);
						} catch (Exception e) {
						}
					} else {
						robot.mouseWheel(-1);
						try {
							Thread.sleep(50);
						} catch (Exception e) {
						}
					}
				} else if (g.type() == Type.TYPE_SWIPE
						&& g.state() == State.STATE_START) {
					robot.keyPress(KeyEvent.VK_RIGHT);
					robot.keyRelease(KeyEvent.VK_RIGHT);
				}
			}
		}
	}
}

public class LeapMouse {

	public static void main(String[] args) {
		Visualizer visualiser = new Visualizer();
		/*
		 * 
		 * javac -classpath LeapJava.jar LeapMouse.java java -classpath
		 * "LeapJava.jar;." -Djava.lang.path=. LeapMouse
		 */
		MouseListener l = new MouseListener();
		Controller c = new Controller();
		c.setPolicy(Controller.PolicyFlag.POLICY_BACKGROUND_FRAMES);
		c.addListener(l);

		try {
			System.in.read();
		} catch (Exception e) {

		}
		c.removeListener(l);

	}
}
