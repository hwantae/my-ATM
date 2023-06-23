import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BankView view = new BankView(); //creates instance of BankView to get control of BankView
		
		
		view.setVisible(true);
		ArrayList<BankAccount> bankAccounts = new ArrayList<>(); //creates instance of Bankaccount to get control of BankAccount in list.
		User user1 = new User("Firuz");
		User user2 = new User("John");
		User user3 = new User("Eldor");
		
		bankAccounts.add(new BankAccount("200100237898", 1234, 500000.0, user1));
		bankAccounts.add(new BankAccount("110000022033", 4321, 700000.0, user2));
		bankAccounts.add(new BankAccount("111111111111", 2222, 900000.0, user3));
		BankController controller = new BankController(bankAccounts, view);	//creates instance to control the program.
	}

}