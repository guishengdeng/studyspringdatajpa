import net.sf.json.JSONArray;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * JavaTest
 *
 * @author guisheng.deng
 * @date 2017年03月27日
 * @reviewer
 * @description
 * @see
 */
public class JavaTest {
    @Test
    public void   testJSON(){
        List<String> list=new ArrayList<String>();
        list.add("first");
        list.add("second");
        JSONArray jsonObject= JSONArray.fromObject(list);
        System.out.println(jsonObject);
    }
}