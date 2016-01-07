package io.inject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Sergey Ivonchik <sergey @ mintpay.by>
 */
public class Injector {
  private Injector() { }

  public static void inject(Object recipient, Object provider) {
    if (null == provider) {
      provider = recipient;
    }

    Class<?> clazz = (null == provider) ? recipient.getClass() : provider.getClass();
    Field[] fields = recipient.getClass().getDeclaredFields();
    Method findViewById = getMethod(clazz, "findViewById", Integer.TYPE);

    for (Field field : fields) {
      if (null != findViewById) {
        injectView(recipient, provider, field, findViewById);
      }

      InjectResource injectResource = field.getAnnotation(InjectResource.class);

      if (null != injectResource) {
        try {
          int idValue = injectResource.value();
          injectResource(recipient, provider, clazz, idValue, field);
        } catch (InvocationTargetException e) {
          throw new IllegalArgumentException(e);
        } catch (IllegalAccessException e) {
          throw new IllegalStateException(e);
        }
      }
    }
  }

  public static void inject(Object object) {
    inject(object, null);
  }

  private static void injectView(Object recipient, Object provider, Field field, Method findViewById) {
    field.setAccessible(true);
    InjectView injectView = field.getAnnotation(InjectView.class);

    if (null != injectView) {
      int id = injectView.value();

      try {
        field.set(recipient, findViewById.invoke(provider, id));
      } catch (Exception e) {
        throw new IllegalArgumentException(e);
      }
    }
  }

  private static void injectResource(Object recipient, Object provider, Class<?> providerClazz,
      int idValue, Field field) throws InvocationTargetException, IllegalAccessException {
    field.setAccessible(true);

    Class<?> t = field.getType();
    Method getResources = getMethod(providerClazz, "getResources");
    Object context = null;

    if (null == getResources) {
      Method getContext = getMethod(providerClazz, "getContext");

      if (null != getContext) {
        context = getContext.invoke(provider);
        getResources = getMethod(context.getClass(), "getResources");
      }
    }

    if (t.isAssignableFrom(String.class) && !t.isArray()) {
      Method getString = getMethod(providerClazz, "getString", Integer.TYPE);
      Object result = invoke(getString, provider, idValue);

      field.set(recipient, (null != result) ? result : "");
    } else if (null != getResources){
      Object resources = (null == context) ? getResources.invoke(provider) : getResources.invoke(context);
      Class<?> resourcesClazz = resources.getClass();

      if (t.isAssignableFrom(String.class)) {
        Method getString = getMethod(providerClazz, "getString", Integer.TYPE);
        Object result = invoke(getString, resources, idValue);

        field.set(recipient, (null != result) ? result : "");
      } else if (t.isAssignableFrom(String[].class)) {
        Method getStringArray = getMethod(resourcesClazz, "getStringArray", Integer.TYPE);
        Object result = invoke(getStringArray, resources, idValue);

        field.set(recipient, (null != result) ? result : new String[] { });
      } else if (t.isAssignableFrom(int.class)) {
        Method getInteger = getMethod(resourcesClazz, "getInteger", Integer.TYPE);
        Object result = invoke(getInteger, resources, idValue);

        field.set(recipient, (null != result) ? result : 0);
      } else if (t.isAssignableFrom(int[].class)) {
        Method getIntArray = getMethod(resourcesClazz, "getIntArray", Integer.TYPE);
        Object result = invoke(getIntArray, resources, idValue);

        field.set(recipient, (null != result) ? result : new int[] { });
      } else if (t.isAssignableFrom(boolean.class)) {
        Method getBoolean = getMethod(resourcesClazz, "getBoolean", Integer.TYPE);
        Object result = invoke(getBoolean, resources, idValue);

        field.set(recipient, (null != result) ? result : false);
      } else if (t.isAssignableFrom(float.class)) {
        Method getDimension = getMethod(resourcesClazz, "getDimension", Integer.TYPE);
        Object result = invoke(getDimension, resources, idValue);

        field.set(recipient, (null != result) ? result : 0.0f);
      }
    }
  }

  private static Method getMethod(Class<?> clazz, String name, Class<?>... types) {
    try {
      return clazz.getMethod(name, types);
    } catch (NoSuchMethodException e) {
      return null;
    }
  }

  private static Object invoke(Method method, Object obj, Object... args)
      throws InvocationTargetException, IllegalAccessException {
    if (null != method) {
      return method.invoke(obj, args);
    } else {
      return null;
    }
  }
}
