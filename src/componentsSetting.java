import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class componentsSetting extends dungeonLayout{
	public static void relicCheckGroupInitialize() {
		relicCheckGroup.add(relicFakeMapCheck);
		relicCheckGroup.add(relicRealMapCheck);
		relicCheckGroup.add(relicStateCheck);
		relicCheckGroup.setSelected(relicStateCheck.getModel(), true);
	}

	public static void relicRealMapCheckInitialize() {
		relicStateCheckListener(relicRealMapCheck);
		relicRealMapCheck.setBounds(140, 400, 100, 25);
		relicRealMapCheck.setFont(new Font("�L�n������", Font.PLAIN, 15));
		relicRealMapCheck.setAlignmentX(RIGHT_ALIGNMENT);
		relicRealMapCheck.setBackground(Color.WHITE);
		main.add(relicRealMapCheck);
	}

	public static void relicFakeMapCheckInitialize() {
		relicStateCheckListener(relicFakeMapCheck);
		relicFakeMapCheck.setBounds(140, 370, 100, 25);
		relicFakeMapCheck.setFont(new Font("�L�n������", Font.PLAIN, 15));
		relicFakeMapCheck.setAlignmentX(RIGHT_ALIGNMENT);
		relicFakeMapCheck.setBackground(Color.WHITE);
		main.add(relicFakeMapCheck);
	}

	public static void relicStateCheckInitialize() {
		relicStateCheckListener(relicStateCheck);
		relicStateCheck.setBounds(140, 340, 100, 25);
		relicStateCheck.setFont(new Font("�L�n������", Font.PLAIN, 15));
		relicStateCheck.setAlignmentX(RIGHT_ALIGNMENT);
		relicStateCheck.setBackground(Color.WHITE);
		main.add(relicStateCheck);
	}

	public static void dungeonSizeLabelInitialize() {
		dungeonSizeLabel = new JLabel();
		dungeonSizeLabel.setBounds(40, 40, 150, 25);
		dungeonSizeLabel.setText("�a�c�j�p(�`x��)�G");
		dungeonSizeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		dungeonSizeLabel.setVerticalAlignment(SwingConstants.CENTER);
		dungeonSizeLabel.setFont(new Font("�L�n������", Font.PLAIN, 15));
		main.add(dungeonSizeLabel);
	}

	public static void dungeonSizeInitialize() {
		for (String s : dungeonSizeString) {
			dungeonSize.addItem(s);
		}
		dungeonSizeListener(dungeonSize);
		dungeonSize.setBounds(40, 70, 100, 25);
		dungeonSize.setFont(new Font("�L�n������", Font.PLAIN, 15));
		dungeonSize.setAlignmentX(RIGHT_ALIGNMENT);
		dungeonSize.setSelectedIndex(15);
		main.add(dungeonSize);
	}

	public static void entranceCountLabelInitialize() {
		entranceCountLabel = new JLabel();
		entranceCountLabel.setBounds(40, 335, 100, 25);
		entranceCountLabel.setText("�J�f�ƶq�G");
		entranceCountLabel.setHorizontalAlignment(SwingConstants.LEFT);
		entranceCountLabel.setVerticalAlignment(SwingConstants.CENTER);
		entranceCountLabel.setFont(new Font("�L�n������", Font.PLAIN, 15));
		main.add(entranceCountLabel);
	}
	
	public static void entranceCountInitialize() {
		for (String s : entranceCountString) {
			entranceCount.addItem(s);
		}
		entranceCountListener(entranceCount);
		entranceCount.setBounds(40, 365, 80, 25);
		entranceCount.setFont(new Font("�L�n������", Font.PLAIN, 15));
		entranceCount.setAlignmentX(RIGHT_ALIGNMENT);
		entranceCount.setSelectedIndex(0);
		main.add(entranceCount);
	}

	public static void bossRoomInitialize() {
		bossRoom = new JTextField();
		bossRoom.setBackground(Color.LIGHT_GRAY);
		bossRoom.setBounds(40, 130, 200, 200);
		bossRoom.setFont(new Font("�L�n������", Font.PLAIN, 15));
		bossRoom.setEditable(false);
		bossRoom.setHorizontalAlignment(SwingConstants.CENTER);
		bossRoom.setText("Boss Room");
		bossRoom.setColumns(10);
		main.add(bossRoom);
	}

	public static void normalRoomsInitialize(int newDungeonHeight, int newDungeonDepth, int startX, int startY) {
		removeRooms();
		dungeonSizeY = newDungeonHeight;
		dungeonSizeX = newDungeonDepth;
		componentsSetNormalRoomStartY = startY;
		for (int i = 0; i < newDungeonHeight; i++) {
			componentsSetNormalRoomStartX = startX;
			for (int j = 0; j < newDungeonDepth; j++) {
				normalRoom[i][j] = new JTextField();
				normalRoom[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				normalRoom[i][j].setEditable(false);
				normalRoom[i][j].setBounds(componentsSetNormalRoomStartX, componentsSetNormalRoomStartY, 60, 60);
				normalRoom[i][j].setColumns(10);
				normalRoom[i][j].setText("0");
				main.add(normalRoom[i][j]);
				componentsSetNormalRoomStartX -= 80;
			}
			componentsSetNormalRoomStartY += 80;
		}
		dungeonEntranceInitialize(startX + 80, startY, newDungeonHeight);
	}

	public static void dungeonEntranceInitialize(int x, int y, int value) {
		main.repaint();
		JLabelInitialize(value);
		componentsSetNormalRoomStartY = y;
		for (JLabel i : dungeonEntrance) {
			if (componentsSetNormalRoomStartY == 200 ||
				entranceState == 3 && (
					dungeonEntrance.indexOf(i) == 0 ||
					dungeonEntrance.indexOf(i) == dungeonEntrance.size() - 1
				)) {
				setRedIcon(i);
			} else {
				setGrayIcon(i);
			}
			i.setBounds(x, componentsSetNormalRoomStartY, 60, 60);
			i.setBorder(BorderFactory.createEmptyBorder());
			i.setFocusable(false);
			main.add(i);
			componentsSetNormalRoomStartY += 80;
		}
	}

	public static void removeRooms() {
		for (int i = 0; i < dungeonSizeY; i++) {
			for (int j = 0; j < dungeonSizeX; j++) {
				main.remove(normalRoom[i][j]);
			}
		}
		for(JLabel Label : dungeonEntrance) {
			main.remove(Label);
		}
		main.repaint();
	}

	public static void separatorInitialize() {
		separator.setBounds(40, 450, 755, 2);
		main.add(separator);
	}

	public static void calculateButtonInitialize() {
		JButtonListener(calculate);
		calculate.setBounds(40, 490, 60, 25);
		calculate.setText("GO");
		calculate.setBorder(new EmptyBorder(0, 0, 0, 0));
		calculate.setBackground(Color.decode("#d6faff"));
		calculate.setFont(new Font("�L�n������", Font.PLAIN, 13));
		main.add(calculate);
	}

	public static void heroValueTextBoxInitialize() {
		heroValue = new JFormattedTextField(format);
		heroValue.setBackground(Color.WHITE);
		heroValue.setBounds(240, 490, 100, 25);
		heroValue.setFont(new Font("�L�n������", Font.PLAIN, 13));
		heroValue.setHorizontalAlignment(SwingConstants.RIGHT);
		heroValue.setColumns(1);
		main.add(heroValue);
	}

	public static void heroValueLabelInitialize() {
		heroValueLabel = new JLabel();
		heroValueLabel.setBounds(130, 490, 100, 25);
		heroValueLabel.setText("�i�h�ƶq�G");
		heroValueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		heroValueLabel.setVerticalAlignment(SwingConstants.CENTER);
		heroValueLabel.setFont(new Font("�L�n������", Font.PLAIN, 13));
		main.add(heroValueLabel);
	}
	
	public static void errorLabelInitialize() {
		errorLabel = new JLabel();
		errorLabel.setBounds(40, 520, 300, 25);
		errorLabel.setText("");
		errorLabel.setHorizontalAlignment(SwingConstants.LEFT);
		errorLabel.setVerticalAlignment(SwingConstants.CENTER);
		errorLabel.setFont(new Font("�L�n������", Font.PLAIN, 13));
		main.add(errorLabel);
	}

	public static void JLabelInitialize(int value) {
		dungeonEntrance.removeAll(dungeonEntrance);
		for (int i = 0; i < value; i++) {
			dungeonEntrance.add(new JLabel());
		}
	}

	public static void reportBugsToInitialize() {
		JLabel lblReportBugsTo = new JLabel("<html><br><font size=2><a href=#>" + address + "</a></font></html>");
		lblReportBugsTo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblReportBugsTo.setText("Bugs report: leeyuchin2681005@gmail.com");
		lblReportBugsTo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReportBugsTo.setFont(new Font("�L�n������", Font.PLAIN, 12));
		lblReportBugsTo.setBounds(550, 535, 250, 24);
		lblReportBugsTo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblReportBugsTo.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        try {
		            Desktop.getDesktop().mail(new URI("mailto:" + address + "?subject=Bug%20report"));
		        } catch (URISyntaxException | IOException ex) {
		        }
		    }
		});
		main.add(lblReportBugsTo);
	}
	
	private static void entranceCountListener(JComboBox<String> comboBox) {
		comboBox.addItemListener(new ItemListener() {
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
		});
	}

	private static void dungeonSizeListener(JComboBox<String> comboBox) {
		comboBox.addItemListener(new ItemListener() {
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
		});
	}

	private static void relicStateCheckListener(JRadioButton RadioButton) {
		RadioButton.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					switch(RadioButton.getText()) {
					case "�L����":
						System.out.println("set relic state to no relic");
						dungeonState.setRelicState(RelicState.NoRelic);
						break;
					case "���y�a�c":
						System.out.println("set relic state to fake map");
						dungeonState.setRelicState(RelicState.FakeMap);
						break;
					case "�u�a�c":
						System.out.println("set relic state to real map");
						dungeonState.setRelicState(RelicState.RealMap);
						break;
					}
				}
			}
		});
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