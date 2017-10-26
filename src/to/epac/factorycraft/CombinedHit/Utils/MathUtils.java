package to.epac.factorycraft.CombinedHit.Utils;

public class MathUtils {
	public static boolean isNumber(String s) {
	    try {
	    	Long.parseLong(s);
	        return true;
	    }catch (NumberFormatException ex) {
	        return false;
	    }
	}
}
