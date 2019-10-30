import java.util.*;

public class ExpenseManager {
	List<Expense> expenses;
	// userMap: userId to User
	Map<String, User> userMap;
	Map<User, Map<User, Double>> balanceSheet;

	public ExpenseManager() {
		this.expenses = new ArrayList<Expense>();
		this.userMap = new HashMap<String, User>();
		this.balanceSheet = new HashMap<User, Map<User, Double>>();
	}

	public void addUder(User user) {
		userMap.put(user.getId(), user);
		balanceSheet.put(user.getId(), new HashMap<String, Double>());
	}
	
	public void addExpense (ExpenseType type, double amount, User payee, List<Split> splits) {
		//TODO add expense, add to expenses list, add to balance sheet
		Expense newExpense = SplitwiseService.createExpense(type, amount, payee, splits);
		expenses.add(newExpense);
		
		for (Split split : newExpense.getSplits()) {
			User paidTo = split.getUser();
			Map<User, Double> balances = balanceSheet.get(payee);
			if (!balances.containsKey(paidTo)) {
				balances.put(paidTo, 0.0);
			}
			balances.put(paidTo, balances.get(paidTo)+split.getAmount());
			balances = balanceSheet.get(paidTo);
			
			if (!balances.containsKey(payee)) {
				balances.put(payee, 0.0);
			}
			balances.put(payee, balances.get(payee) - split.getAmount());
		}
	}
	
	public void showBalance (User user) {
		//TODO
	}
	
	public void showBalances () {
		//TODO
	}

}
