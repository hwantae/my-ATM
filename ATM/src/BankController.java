import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankController {
	private ArrayList<BankAccount> bankAccount;
	private BankView bankView;
	
	public BankController(ArrayList<BankAccount> bankAccount, BankView bankView) {
		//bankAccount and bankView are initialized by constructor, so that controller has authority to approach model and view.
		this.bankAccount = bankAccount;
		this.bankView = bankView;
		optionActionListener1();
		optionActionListener2();
		optionActionListener3();
		optionActionListener4();
		enterActionListener();
		cancelActionListener();
		clearActionListener();
		koreanActionListener();
		englishActionListener();
		numberActionListener1();
		numberActionListener2();
		numberActionListener3();
		numberActionListener4();
		numberActionListener5();
		numberActionListener6();
		numberActionListener7();
		numberActionListener8();
		numberActionListener9();
		numberActionListener0();
		dayModeActionListener();
		nightModeActionListener();
	}
	//this function returns current bankAccount.
	public ArrayList<BankAccount> getBankAccount() {
		return bankAccount;
	}
	//this function enables users to set the value of bankAccount
	public void setBankAccount(ArrayList<BankAccount> bankAccount) {
		this.bankAccount = bankAccount;
	}
	//this function is for when enterButton is clicked by user.
	public void enterActionListener() {
		this.bankView.enterButtonAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// if getLanguage is 0, it runs program with English. else if getLanguage is 1, it runs program with Korean.
				if (bankView.getLanguage()==0) {
					/*this if condition is controlled by value of state. each state has each goal and option.*/
					if (bankView.getState() == 0) {		//when state value is 0, it changes state to 1 and make stillzero 0, so that when clear button is clicked, program can figure out how much text is deleted.
						bankView.setState(1);			//state 1 is state when it begins program.
						bankView.setStillZero(0);		
						bankView.setTextBox("Pin:");
						bankView.setNumberButtonClicked(false);	//to prevent user clicking enter without entering any numbers.
					}
					/*when state value is 1, it means pin value is entered by user, so program should verify what is entered.*/
					else if(bankView.getState()==1 && bankView.isNumberButtonClicked()) {
						bankView.setStillZero(1);	//it makes stillZero value 1, so it varies clear button's action, because when entering actionPerformed of enter, its state becomes 1 from 0.
						//only state flag cannot distinguish between action of clear button when display shows "Pin:" and "Wrong Pin...".
						String text = bankView.getText();
						long pin = Long.parseLong(text.replaceAll("[^0-9]", ""));//gets the text from textArea and picks only integer value from text.
						boolean correctPin = false; // to check if there is same pin with input.
						int count = 0;
						//this for loop is for find whether there is same pin or not. when same pin exists, correctPin becomes true.
						for(count =0;count<getBankAccount().size();count++) {
							if (pin == getBankAccount().get(count).getPinCode()){
								correctPin = true;
								break;
							}
						}
				/*when correctPin is true it tells options that user can choose. if there is no same pin, correctPin stays false and alert
				 * user that there is no same pin and reenter the pin.*/
						if(correctPin) {
							bankView.setState(2);
							bankView.setUserIndex(count);
							bankView.setTextBox("WelCome ");
							bankView.appendTextbox(getBankAccount().get(count).getBankUser().getName());
							bankView.appendTextbox("\nPlease chosse options\n");
							bankView.appendTextbox("Option 1: Balance Checking\n");
							bankView.appendTextbox("Option 2: Withdrawing money\n");
							bankView.appendTextbox("Option 3: Deposit\n");
							bankView.appendTextbox("Option 4: Transfer");
						}
						else {
							bankView.setState(1);
							bankView.setTextBox("Wrong pin! Try Again: \n");
							bankView.appendTextbox("Pin: ");
						}
						correctPin = false;
						bankView.setNumberButtonClicked(false);//to prevent user clicking enter without entering any numbers.
					}
					else if(bankView.getState()==3) {
						bankView.setTextBox("Thank you for banking with us!\npress ENTER...");
						bankView.setState(0);//goes back to the first display asking Pin number.
					}// this is for greeting. when state is 3, it prints greeting.
					//when state is 4, it goes to withdrawal part.
					else if(bankView.getState()==4 && bankView.isNumberButtonClicked()) {
						String text = bankView.getText();
						long withdraw = Long.parseLong(text.replaceAll("[^0-9]", ""));
						//this if condition is for whether there is enough money to withdraw or not.
						if (withdraw > getBankAccount().get(bankView.getUserIndex()).getBalance()) {
							bankView.setTextBox("Not enough money!\nPress ENTER...");
							bankView.setState(3);
						}
						else {
							bankView.setState(3);
							double balanceLeft = getBankAccount().get(bankView.getUserIndex()).getBalance() - withdraw;
							getBankAccount().get(bankView.getUserIndex()).setBalance(balanceLeft);
							bankView.setTextBox("Success:)\nUser: ");
							bankView.appendTextbox(getBankAccount().get(bankView.getUserIndex()).getBankUser().getName());
							bankView.appendTextbox("\nWithdrawal Amount: ");
							bankView.appendTextbox(withdraw+"\nCurrent Balance: ");
							bankView.appendTextbox(balanceLeft+"\n");
							bankView.appendTextbox("Press ENTER...");
						}
						bankView.setNumberButtonClicked(false);//to prevent user clicking enter without entering any numbers.
					}
					else if(bankView.getState()==5&&bankView.isNumberButtonClicked()) {
						bankView.setState(3);
						String text = bankView.getText();
						long balance = Long.parseLong(text.replaceAll("[^0-9]", ""));
						double balanceAdded = getBankAccount().get(bankView.getUserIndex()).getBalance() + balance;
						//origianal balance + added balance
						getBankAccount().get(bankView.getUserIndex()).setBalance(balanceAdded);
						bankView.setTextBox("Success:)\nUser: ");
						bankView.appendTextbox(getBankAccount().get(bankView.getUserIndex()).getBankUser().getName());
						bankView.appendTextbox("\nDeposit Amount: ");
						bankView.appendTextbox(balance+"\nCurrent Balance: ");
						bankView.appendTextbox(balanceAdded+"\n");
						bankView.appendTextbox("Press ENTER...");
						bankView.setNumberButtonClicked(false);
					}
					else if(bankView.getState()==6 &&bankView.isNumberButtonClicked()) {
						bankView.setState(7);
						String text = bankView.getText();
						Long account = Long.parseLong(text.replaceAll("[^0-9]", ""));
						int correctAccount = 0; //this is for checking if same account is entered by user.
						int count = 0;
						//this for loop loops size of getBankAccount and checks whether its element has same account with input.
						for(count =0;count<getBankAccount().size();count++) {
							long bankNumber = Long.parseLong(getBankAccount().get(count).getBankNumber());
							//this if condition checks whether account and backNumber are same. if they are same, correctAccount becomes true.
							if (account == bankNumber){
								correctAccount = 1;
								break;
							}
						}
						//this if condition checks the input bank number is user's bank number.
						if(count == bankView.getUserIndex()) {
							correctAccount = 2;
						}
						//this if condition prints transfer amount regarding to the value of correctAccount.
						//if user entered wrong bank number, it prints error message.
						if (correctAccount==1) {
							bankView.setTextBox("Transfer Account: ");
							bankView.setReceiverIndex(count);
							bankView.appendTextbox(getBankAccount().get(count).getBankUser().getName());
							bankView.appendTextbox("\nEnter Transfer Amount: ");
						}
						else if (correctAccount==0){
							bankView.setTextBox("You entered the wrong account number!\nPress Enter...");
							bankView.setState(3);
						}
						else if (correctAccount == 2){
							bankView.setTextBox("It is your bank number!\nPress Enter...");
							bankView.setState(3);
						}
						bankView.setNumberButtonClicked(false);
					}
					else if(bankView.getState()==7&&bankView.isNumberButtonClicked()) {
						bankView.setState(3);
						String text = bankView.getText();
						double transferAmount = Integer.parseInt(text.replaceAll("[^0-9]", ""));
						double accountBalance = getBankAccount().get(bankView.getUserIndex()).getBalance();
						double receiverBalance = getBankAccount().get(bankView.getReceiverIndex()).getBalance();
						double balanceSubtract = accountBalance - transferAmount; //original balance is subtracted by transfer amount in sender's account
						double balanceAdd = receiverBalance + transferAmount;//original balance is added by transfer amouont in receiver's account
						//this if condition checks whether it has enoubh money to send.
						if (balanceSubtract<0) {
							bankView.setTextBox("Not enought money\nPress the ENTER...");
						}
						else {
							bankView.setTextBox("Transfer Amount: ");
							bankView.appendTextbox(transferAmount+"");
							bankView.appendTextbox("\nCurrent Balance: ");
							getBankAccount().get(bankView.getUserIndex()).setBalance(balanceSubtract);//sets the balance of sender to subtracted value.
							getBankAccount().get(bankView.getReceiverIndex()).setBalance(balanceAdd);//sets the balance of receiver to added value.
							bankView.appendTextbox(balanceSubtract+"\n");
							bankView.appendTextbox("Press ENTER...");
						}
						bankView.setNumberButtonClicked(false);
						
					}
				}
				//if getLanguage value is 1, it does same instruction above with korean.
				else if(bankView.getLanguage()==1) {
					if (bankView.getState() == 0) {
						bankView.setState(1);
						bankView.setStillZero(0);
						bankView.setTextBox("핀:");
					}
					else if(bankView.getState()==1&& bankView.isNumberButtonClicked()) {
						bankView.setStillZero(1);
						String text = bankView.getText();
						long pin = Long.parseLong(text.replaceAll("[^0-9]", ""));
						boolean correctPin = false;
						int count = 0;
						//ArrayList<BankAccount> bankAccount = getBankAccount();
						for(count =0;count<getBankAccount().size();count++) {
							if (pin == getBankAccount().get(count).getPinCode()){
								correctPin = true;
								break;
							}
						}
						if(correctPin) {
							bankView.setState(2);
							bankView.setUserIndex(count);
							bankView.setTextBox("어서오세요 ");
							bankView.appendTextbox(getBankAccount().get(count).getBankUser().getName());
							bankView.appendTextbox("\n옵션을 골라주세요\n");
							bankView.appendTextbox("옵션 1: 잔액확인\n");
							bankView.appendTextbox("옵션 2: 출금\n");
							bankView.appendTextbox("옵션 3: 저금\n");
							bankView.appendTextbox("옵션 4: 송금");
						}
						else {
							bankView.setState(1);
							bankView.setTextBox("핀이 틀렸습니다! 다시 입력해주세요: \n");
							bankView.appendTextbox("핀: ");
						}
						correctPin = false;
						bankView.setNumberButtonClicked(false);
					}
					else if(bankView.getState()==3) {
						bankView.setTextBox("저희 은행을 이용해주셔서 갑사합니다!\n확인을 눌러주세요...");
						bankView.setState(0);
					}
					else if(bankView.getState()==4&& bankView.isNumberButtonClicked()) {
						String text = bankView.getText();
						long withdraw = Long.parseLong(text.replaceAll("[^0-9]", ""));
						if (withdraw > getBankAccount().get(bankView.getUserIndex()).getBalance()) {
							bankView.setTextBox("돈이 부족합니다!\n확인을 눌러주세요...");
							bankView.setState(3);
						}
						else {
							bankView.setState(3);
							double balanceLeft = getBankAccount().get(bankView.getUserIndex()).getBalance() - withdraw;
							getBankAccount().get(bankView.getUserIndex()).setBalance(balanceLeft);
							bankView.setTextBox("성공했습니다:)\n사용자: ");
							bankView.appendTextbox(getBankAccount().get(bankView.getUserIndex()).getBankUser().getName());
							bankView.appendTextbox("\n출금 금액: ");
							bankView.appendTextbox(withdraw+"\n잔액: ");
							bankView.appendTextbox(balanceLeft+"\n");
							bankView.appendTextbox("입력을 눌러주세요...");
						}
						bankView.setNumberButtonClicked(false);
					}
					else if(bankView.getState()==5&& bankView.isNumberButtonClicked()) {
						bankView.setState(3);
						String text = bankView.getText();
						long balance = Long.parseLong(text.replaceAll("[^0-9]", ""));
						double balanceAdded = getBankAccount().get(bankView.getUserIndex()).getBalance() + balance;
						getBankAccount().get(bankView.getUserIndex()).setBalance(balanceAdded);
						bankView.setTextBox("성공했습니다:)\n사용자: ");
						bankView.appendTextbox(getBankAccount().get(bankView.getUserIndex()).getBankUser().getName());
						bankView.appendTextbox("\n입금 금액: ");
						bankView.appendTextbox(balance+"\n잔액: ");
						bankView.appendTextbox(balanceAdded+"\n");
						bankView.appendTextbox("입력을 눌러주세요...");
						bankView.setNumberButtonClicked(false);
					}
					else if(bankView.getState()==6&& bankView.isNumberButtonClicked()) {
						bankView.setState(7);
						String text = bankView.getText();
						Long account = Long.parseLong(text.replaceAll("[^0-9]", ""));
						
						int correctAccount = 0;
						int count = 0;
						//ArrayList<BankAccount> bankAccount = getBankAccount();
						for(count =0;count<getBankAccount().size();count++) {
							long bankNumber = Long.parseLong(getBankAccount().get(count).getBankNumber());
							if (account == bankNumber){
								correctAccount = 1;
								break;
							}
						}
						if(count == bankView.getUserIndex()) {
							correctAccount = 2;
						}
						if (correctAccount == 1) {
							bankView.setTextBox("송금할 계좌번호: ");
							bankView.setReceiverIndex(count);
							bankView.appendTextbox(getBankAccount().get(count).getBankUser().getName());
							bankView.appendTextbox("\n보내실 금액: ");
						}
						else if (correctAccount == 0){
							bankView.setTextBox("잘못된 계좌번호입니다!\n입력을 눌러주세요...");
							bankView.setState(3);
						}
						else if (correctAccount == 2){
							bankView.setTextBox("귀하의 계좌번호입니다!\n입력을 눌러주세요...");
							bankView.setState(3);
						}
						bankView.setNumberButtonClicked(false);
					}
					else if(bankView.getState()==7&& bankView.isNumberButtonClicked()) {
						bankView.setState(3);
						String text = bankView.getText();
						double transferAmount = Integer.parseInt(text.replaceAll("[^0-9]", ""));
						double accountBalance = getBankAccount().get(bankView.getUserIndex()).getBalance();
						double receiverBalance = getBankAccount().get(bankView.getReceiverIndex()).getBalance();
						double balanceSubtract = accountBalance - transferAmount;
						double balanceAdd = receiverBalance + transferAmount;
						if (balanceSubtract<0) {
							bankView.setTextBox("돈이 부족합니다\n입력을 눌러주세요...");
						}
						else {
							bankView.setTextBox("보내신 금액: ");
							bankView.appendTextbox(transferAmount+"");
							bankView.appendTextbox("현재 잔액: ");
							getBankAccount().get(bankView.getUserIndex()).setBalance(balanceSubtract);
							getBankAccount().get(bankView.getReceiverIndex()).setBalance(balanceAdd);
							bankView.appendTextbox(balanceSubtract+"\n");
							bankView.appendTextbox("입력을 눌러주세요...");
						}
						bankView.setNumberButtonClicked(false);
					}
				}
			}
			
		});
	}
//this function is enabled when clearbutton is clicked.
	public void clearActionListener() {
		this.bankView.clearButtonAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//this if condition checks the language mode.
				if (bankView.getLanguage()==0) {
					//this if conditions set the textarea regarding the flag of state and stillzero.
					/*Because it is impossible to distinguish enter button state "Pin:" and "Wrong pin! Try Again: \nPin:"
					 * entered with state 0, it should consider getStillZero value as well.*/
					if(bankView.getState()==1 && bankView.getStillZero()==0) {
						bankView.setTextBox("Pin:");
						bankView.setNumberButtonClicked(false);//to prevent user clicking enter without entering any numbers.
					}
					else if(bankView.getState()==1 && bankView.getStillZero()==1) {
						bankView.setTextBox("Wrong pin! Try Again: \nPin:");
						bankView.setNumberButtonClicked(false);//to prevent user clicking enter without entering any numbers.
					}
					else if(bankView.getState()==4) {
						bankView.setTextBox("Enter Withdrawal Amount: ");
						bankView.setNumberButtonClicked(false);//to prevent user clicking enter without entering any numbers.
					}
					else if(bankView.getState()==5) {
						bankView.setTextBox("Enter Deposit Amount: ");
						bankView.setNumberButtonClicked(false);//to prevent user clicking enter without entering any numbers.
					}
					else if(bankView.getState()==6) {
						bankView.setTextBox("Enter Account Number(Receiver): ");
						bankView.setNumberButtonClicked(false);//to prevent user clicking enter without entering any numbers.
					}
					else if(bankView.getState()==7) {
						bankView.setTextBox("Transfer Account: ");
						bankView.appendTextbox(getBankAccount().get(bankView.getReceiverIndex()).getBankUser().getName());
						bankView.appendTextbox("\nEnter Transfer Amount: ");
						bankView.setNumberButtonClicked(false);//to prevent user clicking enter without entering any numbers.
					}
				}
				else if (bankView.getLanguage()==1) {
					if(bankView.getState()==1 && bankView.getStillZero()==0) {
						bankView.setTextBox("핀:");
						bankView.setNumberButtonClicked(false);
					}
					else if(bankView.getState()==1 && bankView.getStillZero()==1) {
						bankView.setTextBox("잘못된 핀입니다! 다시 입력해주세요: \n핀:");
						bankView.setNumberButtonClicked(false);
					}
					else if(bankView.getState()==4) {
						bankView.setTextBox("출금할 금액을 입력해주세요: ");
						bankView.setNumberButtonClicked(false);
					}
					else if(bankView.getState()==5) {
						bankView.setTextBox("입금할 금액을 입력해주세요: ");
						bankView.setNumberButtonClicked(false);
					}
					else if(bankView.getState()==6) {
						bankView.setTextBox("계좌번호를 입력해주세요(수신자): ");
						bankView.setNumberButtonClicked(false);
					}
					else if(bankView.getState()==7) {
						bankView.setTextBox("송금할 계좌번호: ");
						bankView.appendTextbox(getBankAccount().get(bankView.getReceiverIndex()).getBankUser().getName());
						bankView.appendTextbox("\n송금할 금액을 입력해주세요: ");
						bankView.setNumberButtonClicked(false);
					}
				}
			}
			
		});
	}
	//this function is for when englishButton is clicked.
	public void englishActionListener() {
		this.bankView.englishButtonAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//this if condition checks language flag, if language flag is 1, it changes to 0 and change the component name to english.
				if (bankView.getLanguage()==1) {
					bankView.setLanguage(0);
					bankView.setCancelButton("CANCEL");
					bankView.setClearButton("CLEAR");
					bankView.setEnterButton("ENTER");
					bankView.setOptionButton1("OPTION 1");
					bankView.setOptionButton2("OPTION 2");
					bankView.setOptionButton3("OPTION 3");
					bankView.setOptionButton4("OPTION 4");
					bankView.setEnglishButton("ENGLISH");
					bankView.setKoreanButton("KOREAN");
					bankView.setSkkuAtm("SKKU ATM");
				}
				
			}
			
		});
	}
	//this function is for when koreanButton is clicked.
	public void koreanActionListener() {
		this.bankView.koreanButtonAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//this if condition checks language flag, if language flag is 0, it changes to 1 and change the component name to korean.
				if (bankView.getLanguage()==0) {
					bankView.setLanguage(1);
					bankView.setCancelButton("취소");
					bankView.setClearButton("삭제");
					bankView.setEnterButton("입력");
					bankView.setOptionButton1("옵션1");
					bankView.setOptionButton2("옵션2");
					bankView.setOptionButton3("옵션3");
					bankView.setOptionButton4("옵션4");
					bankView.setEnglishButton("영어");
					bankView.setKoreanButton("한국어");
					bankView.setSkkuAtm("성균관대 현금인출기");
				}
			}
			
		});
	}
	//this function is activated when cancelButton is clicked.
	public void cancelActionListener() {
		this.bankView.cancelButtonAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//this if condition changes language of printed sentence.
				if (bankView.getLanguage()==0) {
					bankView.setTextBox("Process is canceled by user! Please press ENTER...");
					bankView.setState(3);
				}
				else if(bankView.getLanguage()==1) {
					bankView.setTextBox("진행상황이 취소되었습니다! 입력을 눌러주세요...");
					bankView.setState(3);
				}
				bankView.setNumberButtonClicked(false);
			}
			
		});
	}
	//this function is for when option1 is clicked.
	public void optionActionListener1() {
		this.bankView.optionButton1Action(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//this if condition changes language of printed sentence.
				if (bankView.getLanguage()==0) {
					if (bankView.getState()==2) {
						bankView.setState(3);//it sets the state to 3 to follow the instruction given by the user.
						bankView.setTextBox("User: ");
						bankView.appendTextbox(getBankAccount().get(bankView.getUserIndex()).getBankUser().getName());
						bankView.appendTextbox("\nBalance: ");
						bankView.appendTextbox(getBankAccount().get(bankView.getUserIndex()).getBalance()+"");
						bankView.appendTextbox("\n");
						bankView.appendTextbox("Press Enter...");
					}
				}
				else if(bankView.getLanguage()==1) {
					if (bankView.getState()==2) {
						bankView.setState(3);
						bankView.setTextBox("사용자: ");
						bankView.appendTextbox(getBankAccount().get(bankView.getUserIndex()).getBankUser().getName());
						bankView.appendTextbox("\n잔액: ");
						bankView.appendTextbox(getBankAccount().get(bankView.getUserIndex()).getBalance()+"");
						bankView.appendTextbox("\n");
						bankView.appendTextbox("입력을 눌러주세요...");
					}
				}
			}
			
		});
	}
	//this function is for when option2 is clicked.
	public void optionActionListener2() {
		this.bankView.optionButton2Action(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//this if condition changes language of printed sentence.
				if (bankView.getLanguage()==0) {
					if (bankView.getState()==2) {
						bankView.setState(4);
						bankView.setTextBox("Enter Withdrawal Amount: ");	
					}
				}
				else if (bankView.getLanguage()==1) {
					if (bankView.getState()==2) {
						bankView.setState(4);
						bankView.setTextBox("출금하실 금액을 입력해주세요: ");	
					}
				}
			}
			
		});
	}
	//this function is for when option3 is clicked.
	public void optionActionListener3() {
		this.bankView.optionButton3Action(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//this if condition changes language of printed sentence.
				if (bankView.getLanguage()==0) {
					if (bankView.getState()==2) {
						bankView.setState(5);
						bankView.setTextBox("Enter Deposit Amount: ");
					}
				}
				else if (bankView.getLanguage()==1) {
					if (bankView.getState()==2) {
						bankView.setState(5);
						bankView.setTextBox("저금할 금액을 입력해주세요: ");
					}
				}
			}
			
		});
	}
	//this function is for when option4 is clicked.
	public void optionActionListener4() {
		this.bankView.optionButton4Action(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//this if condition changes language of printed sentence.
				if (bankView.getLanguage()==0) {
					if (bankView.getState()==2) {
						bankView.setState(6);
						bankView.setTextBox("Enter Account Number(Receiver): ");
					}
				}
				else if (bankView.getLanguage()==1) {
					if (bankView.getState()==2) {
						bankView.setState(6);
						bankView.setTextBox("계좌번호를 입력해주세요(수금자): ");
					}
				}
			}
			
		});
	}
	//those 10 functions below is numberButtons. It activates only when state is 1 or 4 or 5 or 6 or 7.
	public void numberActionListener1() {
		this.bankView.numberButton1Action(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(bankView.getState()==1 || bankView.getState()==4 || bankView.getState()==5 || bankView.getState()==6 || bankView.getState()==7) {
					bankView.appendTextbox("1");	
					bankView.setNumberButtonClicked(true); //Since number is entered, users are now permitted to click enter, so it changes numberButtonClicked to true.
				}
			}
			
		});
	}
	public void numberActionListener2() {
		this.bankView.numberButton2Action(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(bankView.getState()==1 || bankView.getState()==4 || bankView.getState()==5 || bankView.getState()==6 || bankView.getState()==7) {
					bankView.appendTextbox("2");	
					bankView.setNumberButtonClicked(true);//Since number is entered, users are now permitted to click enter, so it changes numberButtonClicked to true.
				}
			}
			
		});
	}
	public void numberActionListener3() {
		this.bankView.numberButton3Action(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(bankView.getState()==1 || bankView.getState()==4 || bankView.getState()==5 || bankView.getState()==6 || bankView.getState()==7) {
					bankView.appendTextbox("3");
					bankView.setNumberButtonClicked(true);//Since number is entered, users are now permitted to click enter, so it changes numberButtonClicked to true.
				}
			}
			
		});
	}
	public void numberActionListener4() {
		this.bankView.numberButton4Action(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(bankView.getState()==1 || bankView.getState()==4 || bankView.getState()==5 || bankView.getState()==6 || bankView.getState()==7) {
					bankView.appendTextbox("4");	
					bankView.setNumberButtonClicked(true);//Since number is entered, users are now permitted to click enter, so it changes numberButtonClicked to true.
				}
			}
			
		});
	}
	public void numberActionListener5() {
		this.bankView.numberButton5Action(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(bankView.getState()==1 || bankView.getState()==4 || bankView.getState()==5 || bankView.getState()==6 || bankView.getState()==7) {
					bankView.appendTextbox("5");
					bankView.setNumberButtonClicked(true);//Since number is entered, users are now permitted to click enter, so it changes numberButtonClicked to true.
				}
			}
			
		});
	}
	public void numberActionListener6() {
		this.bankView.numberButton6Action(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(bankView.getState()==1 || bankView.getState()==4 || bankView.getState()==5 || bankView.getState()==6 || bankView.getState()==7) {
					bankView.appendTextbox("6");	
					bankView.setNumberButtonClicked(true);//Since number is entered, users are now permitted to click enter, so it changes numberButtonClicked to true.
				}
			}
			
		});
	}
	public void numberActionListener7() {
		this.bankView.numberButton7Action(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(bankView.getState()==1 || bankView.getState()==4 || bankView.getState()==5 || bankView.getState()==6 || bankView.getState()==7) {
					bankView.appendTextbox("7");	
					bankView.setNumberButtonClicked(true);//Since number is entered, users are now permitted to click enter, so it changes numberButtonClicked to true.
				}
			}
			
		});
	}
	public void numberActionListener8() {
		this.bankView.numberButton8Action(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(bankView.getState()==1 || bankView.getState()==4 || bankView.getState()==5 || bankView.getState()==6 || bankView.getState()==7) {
					bankView.appendTextbox("8");	
					bankView.setNumberButtonClicked(true);//Since number is entered, users are now permitted to click enter, so it changes numberButtonClicked to true.
				}
			}
			
		});
	}
	public void numberActionListener9() {
		this.bankView.numberButton9Action(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(bankView.getState()==1 || bankView.getState()==4 || bankView.getState()==5 || bankView.getState()==6 || bankView.getState()==7) {
					bankView.appendTextbox("9");
					bankView.setNumberButtonClicked(true);//Since number is entered, users are now permitted to click enter, so it changes numberButtonClicked to true.
				}
			}
			
		});
	}
	public void numberActionListener0() {
		this.bankView.numberButton0Action(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(bankView.getState()==1 || bankView.getState()==4 || bankView.getState()==5 || bankView.getState()==6 || bankView.getState()==7) {
					bankView.appendTextbox("0");	
					bankView.setNumberButtonClicked(true);//Since number is entered, users are now permitted to click enter, so it changes numberButtonClicked to true.
				}
			}
			
		});
	}
	//this function activates when day mode button is clicked. it changes program to day mode.
	public void dayModeActionListener() {
		this.bankView.dayModeButtonAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				bankView.setColorWhite();
			}
			
		});
	}
	//this function activates when night mode button is clicked. it changes program to night mode.
	public void nightModeActionListener() {
		this.bankView.nightModeButtonAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				bankView.setColorDark();
			}
			
		});
	}
}
