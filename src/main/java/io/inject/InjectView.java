package io.inject;

import java.lang.annotation.*;

/**
 * @author Sergey Ivonchik <sergey @ mintpay.by>
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InjectView {
  int value();
}
