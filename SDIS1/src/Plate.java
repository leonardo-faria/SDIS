import java.util.ArrayList;

public class Plate {
	String owner;
	String number;

	public static String lookup(ArrayList<Plate> plates,String number) throws Exception {
		for (int i = 0; i < plates.size(); i++) {
			if(plates.get(i).number.equals(number))
				return plates.get(i).owner;
		}
		throw new Exception();
	}

	public Plate(String owner, String number) {
		super();
		this.owner = owner;
		this.number = number;
	}

	
}
