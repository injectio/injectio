package io.inject.mock;

/**
 * @author Sergey Ivonchik <sergey @ mintpay.by>
 */
public class Activity extends Context {
  public View findViewById(int id) {
    return new View(id);
  }
}
