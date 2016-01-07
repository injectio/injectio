# Inject.io

[ ![Download](https://api.bintray.com/packages/injectio/injectio/io.inject/images/download.svg) ](https://bintray.com/injectio/injectio/io.inject/_latestVersion)

Tiny and nice injections syntax sugar for Android. If
you're using heavy injection frameworks just to inject
views and resources try this.

### Wat?

- Just few lines of code, completely no dependencies (even Android ones).
- Runtime (or cry slooooow if your want...whatever).
- Injects views and resources, that's it.

### How?

```groovy

repositories {
  repositories {
    maven {
      url 'https://dl.bintray.com/injectio/injectio/'
    }
  }
}

dependencies {
  compile 'inject:inject:1.0.4'
}

```

```java

public class BaseActivity extends Activity {
  @InjectView(R.id.title)
  private TextView mTitle;

  @InjectResource(R.string.fruits)
  private String[] mFruits;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.base_activity);
    Injector.inject(this);
  }
}

public class BaseFragment extends Fragment {
  @InjectView(R.id.title)
  private TextView mTitle;

  @InjectResource(R.string.hello)
  private String mHello;

  @InjectResource(R.array.bases)
  private int[] mBases;

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      Injector.inject(this, view);
  }
}

public class BaseViewGroup extends ViewGroup {
  @InjectView(R.id.subview)
  private View mSubview;

  @InjectResource(R.dimen.font_size)
  private float mFontSize;

  public BaseViewGroup(Context ctx, AttributeSet attrs, int style) {
    super(ctx, attrs, style);

    String service = Context.LAYOUT_INFLATER_SERVICE;
    LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(service);
    inflater.inflate(R.layout.base_view_group, this, true);
    Injector.inject(this);
  }
}

```

### License

Copyright 2015 Sergey Ivonchik

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0
