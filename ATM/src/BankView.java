import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class BankView extends JFrame {

	private JPanel contentPane;
	private JLabel WooriBank;
	private JLabel skkuAtm;
	private JTextArea textBox;
	private JButton optionButton1;
	private JButton optionButton2;
	private JButton optionButton3;
	private JButton optionButton4;
	private JButton englishButton;
	private JButton koreanButton;
	private JButton numberButton1;
	private JButton numberButton2;
	private JButton numberButton3;
	private JButton numberButton4;
	private JButton numberButton5;
	private JButton numberButton6;
	private JButton numberButton7;
	private JButton numberButton8;
	private JButton numberButton9;
	private JButton numberButton0;
	private JButton cancelButton;
	private JButton clearButton;
	private JButton enterButton;
	private int state = 0;
	private int language = 0;
	private int userIndex;
	private int receiverIndex;
	private int stillZero=0;
	private JButton dayModeButton;
	private JButton nightModeButton;
	private boolean numberButtonClicked = false;
	//this function is for preventing error when nothing is entered but user clicks 'enter' button. it checks whether something is entered before clicking enter.
	public boolean isNumberButtonClicked() {
		return numberButtonClicked;
	}
	//this function is for setting the state of numberButtonClicked.
	public void setNumberButtonClicked(boolean buttonClicked) {
		this.numberButtonClicked = buttonClicked;
	}
	//this function returns value of  stillzero
	public int getStillZero() {
		return stillZero;
	}
	// this function changes settext of cancelButton to input.
	public void setCancelButton(String string) {
		this.cancelButton.setText(string);
	}
	// this function changes settext of skkuATm to input.
	public void setSkkuAtm(String string) {
		this.skkuAtm.setText(string);
	}
	// this function changes settext of setOptionButton1 to input.
	public void setOptionButton1(String string) {
		this.optionButton1.setText(string);
	}
	// this function changes settext of optionButton2 to input.
	public void setOptionButton2(String string) {
		this.optionButton2.setText(string);
	}
	// this function changes settext of optionButton 3 to input.
	public void setOptionButton3(String string) {
		this.optionButton3.setText(string);
	}
	// this function changes settext of optionButton 4 to input.
	public void setOptionButton4(String string) {
		this.optionButton4.setText(string);
	}
	// this function changes settext of englishButton to input.
	public void setEnglishButton(String string) {
		this.englishButton.setText(string);
	}
	// this function changes settext of KoreanButton to input.
	public void setKoreanButton(String string) {
		this.koreanButton.setText(string);
	}
	// this function changes settext of setClearButton to input.
	public void setClearButton(String string) {
		this.clearButton.setText(string);
	}
	// this function changes settext of enterButton to input.
	public void setEnterButton(String string) {
		this.enterButton.setText(string);
	}
	// this function changes value of stillzero to input.
	public void setStillZero(int stillZero) {
		this.stillZero = stillZero;
	}
	//this function returns receiverindex.
	public int getReceiverIndex() {
		return receiverIndex;
	}
	//this function sets receiver index value 
	public void setReceiverIndex(int receiverIndex) {
		this.receiverIndex = receiverIndex;
	}
	// this function returns userindex value
	public int getUserIndex() {
		return userIndex;
	}
	// this function sets value of userIndex.
	public void setUserIndex(int userIndex) {
		this.userIndex = userIndex;
	}
	//this function returns language flag
	public int getLanguage() {
		return language;
	}
	//this function sets language flag
	public void setLanguage(int language) {
		this.language = language;
	}
	//this funtion returns value of state.
	public int getState() {
		return state;
	}
	//this function sets the value of state.
	public void setState(int state) {
		this.state = state;
	}
	//this function changes some parts colors brighter.
	public void setColorWhite() {
		Color menuColor = new Color(240,240,240);
		textBox.setForeground(Color.BLACK);
		textBox.setBackground(Color.white);
		skkuAtm.setForeground(Color.black);
		contentPane.setBackground(menuColor);
	}
	//this function changes some parts colors darker.
	public void setColorDark() {
		Color menuColor = new Color(80,80,80);
		textBox.setForeground(Color.white);
		textBox.setBackground(Color.black);
		skkuAtm.setForeground(Color.white);
		contentPane.setBackground(menuColor);
	}
	
	/**
	 * Create the frame.
	 */
	public BankView() {
		setTitle("ATM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 536, 503);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		WooriBank = new JLabel("");
		WooriBank.setIcon(new ImageIcon(BankView.class.getResource("/image/woori.png")));
		WooriBank.setBounds(12, 10, 203, 34);
		contentPane.add(WooriBank);
		
		skkuAtm = new JLabel("SKKU ATM");
		skkuAtm.setHorizontalAlignment(SwingConstants.CENTER);
		skkuAtm.setFont(new Font("굴림", Font.BOLD, 12));
		skkuAtm.setBounds(180, 41, 141, 15);
		contentPane.add(skkuAtm);
		
		textBox = new JTextArea();
		textBox.setForeground(Color.BLACK);
		textBox.setWrapStyleWord(true);
		textBox.setText("Please, insert your card and press ENTER...");
		textBox.setLineWrap(true);
		textBox.setEditable(false);
		textBox.setBounds(141, 54, 224, 120);
		contentPane.add(textBox);
		
		optionButton1 = new JButton("OPTION 1");
		optionButton1.setIcon(new ImageIcon(BankView.class.getResource("/image/arr.png")));
		optionButton1.setFont(new Font("굴림", Font.BOLD, 12));
		optionButton1.setBounds(19, 54, 121, 30);
		contentPane.add(optionButton1);
		
		optionButton2 = new JButton("OPTION 2");
		optionButton2.setIcon(new ImageIcon(BankView.class.getResource("/image/arr.png")));
		optionButton2.setFont(new Font("굴림", Font.BOLD, 12));
		optionButton2.setBounds(19, 84, 121, 30);
		contentPane.add(optionButton2);
		
		optionButton3 = new JButton("OPTION 3");
		optionButton3.setIcon(new ImageIcon(BankView.class.getResource("/image/arr.png")));
		optionButton3.setFont(new Font("굴림", Font.BOLD, 12));
		optionButton3.setBounds(19, 114, 121, 30);
		contentPane.add(optionButton3);
		
		optionButton4 = new JButton("OPTION 4");
		optionButton4.setIcon(new ImageIcon(BankView.class.getResource("/image/arr.png")));
		optionButton4.setFont(new Font("굴림", Font.BOLD, 12));
		optionButton4.setBounds(19, 144, 121, 30);
		contentPane.add(optionButton4);
		
		englishButton = new JButton("ENGLISH");
		englishButton.setIcon(new ImageIcon(BankView.class.getResource("/image/eng.png")));
		englishButton.setFont(new Font("굴림", Font.BOLD, 12));
		englishButton.setBounds(365, 54, 141, 30);
		contentPane.add(englishButton);
		
		koreanButton = new JButton("KOREAN");
		koreanButton.setIcon(new ImageIcon(BankView.class.getResource("/image/kor.png")));
		koreanButton.setFont(new Font("굴림", Font.BOLD, 12));
		koreanButton.setBounds(365, 85, 141, 30);
		contentPane.add(koreanButton);
		
		numberButton1 = new JButton("");
		numberButton1.setIcon(new ImageIcon(BankView.class.getResource("/image/1.png")));
		numberButton1.setBounds(141, 173, 76, 61);
		contentPane.add(numberButton1);
		
		numberButton2 = new JButton("");
		numberButton2.setIcon(new ImageIcon(BankView.class.getResource("/image/2.png")));
		numberButton2.setBounds(215, 173, 76, 61);
		contentPane.add(numberButton2);
		
		numberButton3 = new JButton("");
		numberButton3.setIcon(new ImageIcon(BankView.class.getResource("/image/3.png")));
		numberButton3.setBounds(289, 173, 76, 61);
		contentPane.add(numberButton3);
		
		numberButton4 = new JButton("");
		numberButton4.setIcon(new ImageIcon(BankView.class.getResource("/image/4.png")));
		numberButton4.setBounds(141, 233, 76, 61);
		contentPane.add(numberButton4);
		
		numberButton5 = new JButton("");
		numberButton5.setIcon(new ImageIcon(BankView.class.getResource("/image/5.png")));
		numberButton5.setBounds(215, 233, 76, 61);
		contentPane.add(numberButton5);
		
		numberButton6 = new JButton("");
		numberButton6.setIcon(new ImageIcon(BankView.class.getResource("/image/6.png")));
		numberButton6.setBounds(289, 233, 76, 61);
		contentPane.add(numberButton6);
		
		numberButton7 = new JButton("");
		numberButton7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		numberButton7.setIcon(new ImageIcon(BankView.class.getResource("/image/7.png")));
		numberButton7.setBounds(141, 293, 76, 61);
		contentPane.add(numberButton7);
		
		numberButton8 = new JButton("");
		numberButton8.setIcon(new ImageIcon(BankView.class.getResource("/image/8.png")));
		numberButton8.setBounds(215, 293, 76, 61);
		contentPane.add(numberButton8);
		
		numberButton9 = new JButton("");
		numberButton9.setIcon(new ImageIcon(BankView.class.getResource("/image/9.png")));
		numberButton9.setBounds(289, 293, 76, 61);
		contentPane.add(numberButton9);
		
		numberButton0 = new JButton("");
		numberButton0.setIcon(new ImageIcon(BankView.class.getResource("/image/0.png")));
		numberButton0.setBounds(215, 354, 76, 61);
		contentPane.add(numberButton0);
		
		cancelButton = new JButton("CANCEL");
		cancelButton.setIcon(new ImageIcon(BankView.class.getResource("/image/cancel.png")));
		cancelButton.setFont(new Font("굴림", Font.BOLD, 11));
		cancelButton.setBounds(365, 173, 141, 61);
		contentPane.add(cancelButton);
		
		clearButton = new JButton("CLEAR");
		clearButton.setIcon(new ImageIcon(BankView.class.getResource("/image/clear.png")));
		clearButton.setFont(new Font("굴림", Font.BOLD, 11));
		clearButton.setBounds(365, 233, 141, 61);
		contentPane.add(clearButton);
		
		enterButton = new JButton("ENTER");
		enterButton.setIcon(new ImageIcon(BankView.class.getResource("/image/enter.png")));
		enterButton.setFont(new Font("굴림", Font.BOLD, 11));
		enterButton.setBounds(365, 293, 141, 61);
		contentPane.add(enterButton);
		
		dayModeButton = new JButton("");
		dayModeButton.setIcon(new ImageIcon(BankView.class.getResource("/image/해2.PNG")));
		dayModeButton.setFont(new Font("굴림", Font.PLAIN, 10));
		dayModeButton.setBounds(398, 125, 30, 30);
		contentPane.add(dayModeButton);
		
		nightModeButton = new JButton("");
		nightModeButton.setIcon(new ImageIcon(BankView.class.getResource("/image/달.PNG")));
		nightModeButton.setFont(new Font("굴림", Font.PLAIN, 10));
		nightModeButton.setBounds(449, 125, 30, 30);
		contentPane.add(nightModeButton);
	}
	public void dayModeButtonAction(ActionListener listener) {
		dayModeButton.addActionListener(listener);
	}
	public void nightModeButtonAction(ActionListener listener) {
		nightModeButton.addActionListener(listener);
	}
	public void optionButton1Action(ActionListener listener) {
		optionButton1.addActionListener(listener);
	}
	public void optionButton2Action(ActionListener listener) {
		optionButton2.addActionListener(listener);
	}
	public void optionButton3Action(ActionListener listener) {
		optionButton3.addActionListener(listener);
	}
	public void optionButton4Action(ActionListener listener) {
		optionButton4.addActionListener(listener);
	}
	public void englishButtonAction(ActionListener listener) {
		englishButton.addActionListener(listener);
	}
	public void koreanButtonAction(ActionListener listener) {
		koreanButton.addActionListener(listener);
	}
	public void numberButton1Action(ActionListener listener) {
		numberButton1.addActionListener(listener);
	}
	public void numberButton2Action(ActionListener listener) {
		numberButton2.addActionListener(listener);
	}
	public void numberButton3Action(ActionListener listener) {
		numberButton3.addActionListener(listener);
	}
	public void numberButton4Action(ActionListener listener) {
		numberButton4.addActionListener(listener);
	}
	public void numberButton5Action(ActionListener listener) {
		numberButton5.addActionListener(listener);
	}
	public void numberButton6Action(ActionListener listener) {
		numberButton6.addActionListener(listener);
	}
	public void numberButton7Action(ActionListener listener) {
		numberButton7.addActionListener(listener);
	}
	public void numberButton8Action(ActionListener listener) {
		numberButton8.addActionListener(listener);
	}
	public void numberButton9Action(ActionListener listener) {
		numberButton9.addActionListener(listener);
	}
	public void numberButton0Action(ActionListener listener) {
		numberButton0.addActionListener(listener);
	}
	public void cancelButtonAction(ActionListener listener) {
		cancelButton.addActionListener(listener);
	}
	public void clearButtonAction(ActionListener listener) {
		clearButton.addActionListener(listener);
	}
	public void enterButtonAction(ActionListener listener) {
		enterButton.addActionListener(listener);
	}
	//this function is to set the text in textarea.
	public void setTextBox(String string) {
		textBox.setText(string);
	}
	// this function is to append text in textarea.
	public void appendTextbox(String string) {
		textBox.append(string);
	}
	// this funtion is to get text from text area.
	public String getText() {
		return textBox.getText();
	}
}