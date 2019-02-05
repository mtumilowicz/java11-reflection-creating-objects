import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by mtumilowicz on 2019-02-04.
 */
public class ObjectCreationTest {
    @Test
    public void create() throws NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException {
        X x = X.class.getDeclaredConstructor(String.class, int.class).newInstance("a", 1);
        assertThat(x.name, is("a"));
        assertThat(x.count, is(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_wrongArguments() throws NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException {
        X.class.getDeclaredConstructor(String.class, int.class).newInstance("a");
    }
    
    @Test(expected = IllegalAccessException.class)
    public void create_privateConstructor() throws NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException {
        X.class.getDeclaredConstructor().newInstance();
    }
}
