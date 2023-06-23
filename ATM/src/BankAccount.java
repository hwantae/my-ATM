public class BankAccount {
	private String bankNumber;
	private int pinCode;
	private double balance;
	private User bankUser;
	
	public BankAccount(String inputBankNumber, int inputPinCode, double inputBalance, User inputUser) {
		//those variables are initialized by constructor.
		bankNumber = inputBankNumber;
		pinCode = inputPinCode;
		balance = inputBalance;
		bankUser = inputUser;
	}
	// This function returns the value of backNumber.
	public String getBankNumber() {
		return bankNumber;
	}
	// This function enables users to set the value of backNumber.
	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}
	// This function returns value of pinCode
	public int getPinCode() {
		return pinCode;
	}
	// This function enables users to set the value of pinCode.
	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}
	//this function returns value of balance.
	public double getBalance() {
		return balance;
	}
	//this function enables users to set the value of balance.
	public void setBalance(double balance) {
		this.balance = balance;
	}
	//this function returns value of bankUser.
	public User getBankUser() {
		return bankUser;
	}
	//this function enables users to set the value of bankUser.
	public void setBankUser(User bankUser) {
		this.bankUser = bankUser;
	}

	
}