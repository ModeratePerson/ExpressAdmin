import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("a","b");
        String put = map.put("c", "b");
        System.out.println(put);
    }
}
