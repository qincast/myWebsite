import java.util.HashMap;

public class Box <T> {
  //  T implements interface;
    T data;
    public T getData(){
        return data;
    }
    public Object testData(){
        data.getClass();
       // data.newInstance();
        return (data instanceof String)? null: null;

    }
    public<T> T getObject(Class<T> c) throws IllegalAccessException, InstantiationException {
        T t=c.newInstance();
       // c.newInstance();
        //t.
        return t;
    }


}
