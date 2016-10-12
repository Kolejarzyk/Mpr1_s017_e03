package domain;

public class Franc extends Currency{

	public Franc(int amount) {
		this.amount=amount;
	}

	public Franc times(int multiplier) {
		return new Franc(this.amount*multiplier);
	}

}
