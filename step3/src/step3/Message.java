package step3;

import java.io.Serializable;

public final class Message implements Serializable {
	private int number;

	Message(int number) {
		this.number = number;
	}
	
	public int getNumber() {
		return number;
	}
}