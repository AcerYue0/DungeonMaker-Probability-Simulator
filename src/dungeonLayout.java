import java.awt.Color;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class dungeonLayout extends JFrame {
	static URL imageXMarkGray = dungeonLayout.class.getResource("xMarkGray.png");
	static URL imageXMarkRed = dungeonLayout.class.getResource("xMarkRed.png");
	static BufferedImage buttonIcon;
	static DungeonState dungeonState = new DungeonState(6, 5, 'M', RelicState.NoRelic, 1);
	static int dungeonSizeY = 0, dungeonSizeX = 0;	//X = depth, Y = width
	static String dungeonSizeString[] = { 
			" 3x3", " 3x4(U)", " 3x4(D)", " 3x5",
			" 4x3", " 4x4(U)", " 4x4(D)", " 4x5",
			" 5x3", " 5x4(U)", " 5x4(D)", " 5x5",
			" 6x3", " 6x4(U)", " 6x4(D)", " 6x5" 
	};
	static String entranceCountString[] = {" 1", " 3"};
	static String address = "leeyuchin2618005@gmail.com";
	static NumberFormat format = NumberFormat.getNumberInstance();

	public static JPanel main;
	public static JTextField bossRoom;
	public static JFormattedTextField heroValue;
	public static JLabel heroValueLabel, dungeonSizeLabel, errorLabel, entranceCountLabel;
	public static JTextField[][] normalRoom = new JTextField[5][6];
	public static Tile[][] tile = new Tile[5][6];
	public static int componentsSetNormalRoomStartY, componentsSetNormalRoomStartX;
	public static JSeparator separator = new JSeparator();
	public static List<JLabel> dungeonEntrance = new ArrayList<JLabel>();
	public static JButton calculate = new JButton();
	public static JComboBox<String> dungeonSize = new JComboBox<String>();
	public static JComboBox<String> entranceCount = new JComboBox<String>();
	public static JRadioButton relicStateCheck = new JRadioButton("無神器");
	public static JRadioButton relicFakeMapCheck = new JRadioButton("偽造地宮");
	public static JRadioButton relicRealMapCheck = new JRadioButton("真地宮");
	public static ButtonGroup relicCheckGroup = new ButtonGroup();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dungeonLayout frame = new dungeonLayout();
					frame.setTitle("Dungeon Maker Probability Simulator");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public dungeonLayout() {
		contentPaneInitialize();
		componentsSetting.bossRoomInitialize();
		componentsSetting.normalRoomsInitialize(5, 6, 660, 40);
		componentsSetting.separatorInitialize();
		componentsSetting.calculateButtonInitialize();
		componentsSetting.heroValueTextBoxInitialize();
		componentsSetting.heroValueLabelInitialize();
		componentsSetting.errorLabelInitialize();
		componentsSetting.dungeonSizeInitialize();
		componentsSetting.dungeonSizeLabelInitialize();
		componentsSetting.entranceCountInitialize();
		componentsSetting.entranceCountLabelInitialize();
		componentsSetting.relicFakeMapCheckInitialize();
		componentsSetting.relicRealMapCheckInitialize();
		componentsSetting.relicStateCheckInitialize();
		componentsSetting.relicCheckGroupInitialize();
		componentsSetting.reportBugsToInitialize();
	}

	private void contentPaneInitialize() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 840, 600);
		main = new JPanel();
		main.setBackground(Color.WHITE);
		main.setBorder(new EmptyBorder(0, 0, 0, 0));
		main.setLayout(null);
		setContentPane(main);
	}

	public static void dungeonSimulation() {
		try {
			if(Integer.parseInt(heroValue.getText().replaceAll("[^0-9]", "")) < 0) {
				errorLabel.setText("Input must bigger than 0.");
			}
			Simulation sim = new Simulation(Integer.parseInt(heroValue.getText().replaceAll("[^0-9]", "")));
			sim.startDungeonSimulating();
			sim.replaceProbebilityOf(tile);
			errorLabel.setText("Simulate Complete.");
		} catch (NumberFormatException e) {
			errorLabel.setText("Please input numberic value.");
		}
	}
}
