package io.inject.mock;

/**
 * @author Sergey Ivonchik <sergey @ mintpay.by>
 */
public class Resources {
  public String getString(int id) {
    return "" + id;
  }

  public String[] getStringArray(int id) {
    return new String[] { "" + id };
  }

  public int[] getIntArray(int id) {
    return new int[] { id };
  }

  public float getDimension(int id) {
    return (float) id;
  }

  public boolean getBoolean(int id) {
    return true;
  }

  public int getInteger(int id) {
    return id;
  }
}
