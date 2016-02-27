package week10;
import java.util.UUID;

public class IdGenerator {
	public static String generate(Product product) {
		if (product instanceof Movie) {
			String id = "MOV" + UUID.randomUUID();
			return id;
		}
		if (product instanceof Game) {
			String id = "GAM" + UUID.randomUUID();
			return id;
		}
		String id = "BOO" + UUID.randomUUID();
		return id;
	}
}
