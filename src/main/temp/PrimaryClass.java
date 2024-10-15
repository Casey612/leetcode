package temp;

import java.lang.reflect.Field;

public class PrimaryClass {

  private int a;

  public static void main(String[] args) {
    PrimaryClass primaryClass = new PrimaryClass();
    for (Field field : primaryClass.getClass().getFields()) {
      System.out.println(field.getName() + ":" + field.getClass());
    }
  }

}
