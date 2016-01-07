package io.inject.mock.impl;

import io.inject.InjectResource;
import io.inject.InjectView;
import io.inject.Injector;
import io.inject.mock.View;

/**
 * @author Sergey Ivonchik <sergey @ mintpay.by>
 */
public class ViewImpl extends View {
  @InjectView(1)
  private View mSubView;

  @InjectResource(2)
  private float mDimmen;

  @InjectResource(2)
  private int[] mIntArray;

  private View mEmpty;
  private int mZero;
  private String mNull;

  public ViewImpl(int id) {
    super(id);
    Injector.inject(this, this);
  }

  public View getSubView() {
    return mSubView;
  }

  public float getDimmen() {
    return mDimmen;
  }

  public int[] getIntArray() {
    return mIntArray;
  }

  public View getEmpty() {
    return mEmpty;
  }

  public int getZero() {
    return mZero;
  }

  public String getNull() {
    return mNull;
  }
}
