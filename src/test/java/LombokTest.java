import java.lang.reflect.Field;

import org.junit.Test;

import com.google.appengine.api.datastore.Entity;
import com.zy17.entity.ImageItem;

/**
 * Created by zy17 on 2016/3/11.
 */
public class LombokTest {
    @Test
    public void testReflect() throws IllegalAccessException {
        ImageItem imageItem = new ImageItem();
        imageItem.setPicUrl("url");
        Field[] fields = imageItem.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
        Entity entity = imageItem.genEntity();
    }
}
