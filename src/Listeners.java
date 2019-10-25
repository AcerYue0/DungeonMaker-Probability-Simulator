import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class Listeners extends dungeonLayout{
	public static class startSimulationListener implements ActionListener{
		JButton Button;
		public startSimulationListener(JButton Button){
			this.Button = Button;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			dungeonSimulation();
		}
	}
	
	public static class relicStateCheckListener implements ItemListener{
		JRadioButton RadioButton;
		public relicStateCheckListener(JRadioButton RadioButton){
			this.RadioButton = RadioButton;
		}
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				switch(RadioButton.getText()) {
				case "無神器":
					System.out.println("set relic state to no relic");
					dungeonState.setRelicState(RelicState.NoRelic);
					break;
				case "偽造地宮":
					System.out.println("set relic state to fake map");
					dungeonState.setRelicState(RelicState.FakeMap);
					break;
				case "真地宮":
					System.out.println("set relic state to real map");
					dungeonState.setRelicState(RelicState.RealMap);
					break;
				}
			}
		}
	}
	
	public static class dungeonSizeListener implements ItemListener{
		JComboBox<String> comboBox;
		public dungeonSizeListener(JComboBox<String> comboBox){
			this.comboBox = comboBox;
		}
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				String item = comboBox.getSelectedItem().toString();
				dungeonState.setDepthX(Character.getNumericValue(item.charAt(1)));
				dungeonState.setWidthY(Character.getNumericValue(item.charAt(3)));
				dungeonState.setPosition((item.length() > 5) ? item.charAt(5) : 'M');
				componentsSetNormalRoomStartX = 260 + (dungeonState.getDepth() - 1) * 80;
				componentsSetNormalRoomStartY = (dungeonState.getWidth() == 3 || dungeonState.getPosition() == 'D'? 120 : 40);
				componentsSetting.normalRoomsInitialize(dungeonState.getWidth(), dungeonState.getDepth(), componentsSetNormalRoomStartX, componentsSetNormalRoomStartY);
		    }
		}
	}
	
	public static class entranceCountListener implements ItemListener{
		JComboBox<String> comboBox;
		public entranceCountListener(JComboBox<String> comboBox){
			this.comboBox = comboBox;
		}
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				int item = Integer.parseInt(comboBox.getSelectedItem().toString().trim());
				switch(item) {
				case 1:
					for(JLabel Label : dungeonEntrance) {
						if (Label.getY() == 200) {
							setRedIcon(Label);
						} else {
							setGrayIcon(Label);
						}
					}
					entranceState = 1;
					break;
				case 3:
					for(JLabel Label : dungeonEntrance) {
						if (dungeonEntrance.indexOf(Label) == 0 || dungeonEntrance.indexOf(Label) == dungeonEntrance.size() - 1) {
							setRedIcon(Label);
						} else if (Label.getY() == 200) {
							setRedIcon(Label);
						} else {
							setGrayIcon(Label);
						}
					}
					entranceState = 3;
					break;
				}
		    }
		}
	}
	
	public static void setGrayIcon(JLabel Label) {
		setIcon(imageXMarkGray);
		Label.setIcon(new ImageIcon(buttonIcon));
	}

	public static void setRedIcon(JLabel Label) {
		setIcon(imageXMarkRed);
		Label.setIcon(new ImageIcon(buttonIcon));
	}

	public static void setIcon(URL image) {
		try {
			buttonIcon = ImageIO.read(image);
		} catch(IOException e1) {
			throw new Error("No such file found, please contact us to fix out.");
		}
	}
}