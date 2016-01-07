package io.inject.mock.impl;

import io.inject.InjectResource;
import io.inject.InjectView;
import io.inject.Injector;
import io.inject.mock.Activity;
import io.inject.mock.View;

/**
 * @author Sergey Ivonchik <sergey @ mintpay.by>
 */
public class ActivityImpl extends Activity {
  @InjectView(1)
  private View mViewOne;

  @InjectView(2)
  private View mViewTwo;

  @InjectResource(3)
  private boolean mBooleanRes;

  @InjectResource(4)
  private String[] mStringArray;

  public ActivityImpl() {
    Injector.inject(this);
  }

  public View getViewOne() {
    return mViewOne;
  }

  public View getViewTwo() {
    return mViewTwo;
  }

  public boolean isBooleanRes() {
    return mBooleanRes;
  }

  public String[] getStringArray() {
    return mStringArray;
  }
}
