import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class Path extends JCheckBox{
	private PathOrientation pathOrientation;
	private int connectIndexA;
	private int connectIndexB;
	
	public Path(PathOrientation pathOrientation) {
		super();
		this.setPathOrientation(pathOrientation);
	}

	public PathOrientation getPathOrientation() {
		return pathOrientation;
	}

	public void setPathOrientation(PathOrientation pathOrientation) {
		this.pathOrientation = pathOrientation;
	}
	
	public int getConnectIndexA() {
		return connectIndexA;
	}

	public void setConnectIndexA(int connectIndexA) {
		this.connectIndexA = connectIndexA;
	}

	public int getConnectIndexB() {
		return connectIndexB;
	}

	public void setConnectIndexB(int connectIndexB) {
		this.connectIndexB = connectIndexB;
	}

	public class ChangeStateListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				
			} else {
				
			}
		}
	}
}
