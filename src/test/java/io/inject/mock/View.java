package io.inject.mock;

/**
 * @author Sergey Ivonchik <sergey @ mintpay.by>
 */
public class View {
  private int mId;

  public View(int id) {
    mId = id;
  }

  public View findViewById(int id) {
    return new View(id);
  }

  public Context getContext() {
    return new Context();
  }

  public int getId() {
    return mId;
  }
}
