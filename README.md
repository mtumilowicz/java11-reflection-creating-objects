# java11-reflection-creating-objects

* https://github.com/mtumilowicz/java11-reflection-executables
* https://github.com/mtumilowicz/java11-reflection-constructors

# preface
Using reflection, we can create objects of a class that name need not be known 
until runtime.

To create object using reflection, we have to:
1. Get reference to the constructor: https://github.com/mtumilowicz/java11-reflection-constructors
1. Use the `public T newInstance(Object ... initargs)` method of `Constructor` class
    * exceptions:
         * `IllegalAccessException` - when the underlying
                     constructor is inaccessible (enforcing Java 
                     language access control).
         * `IllegalArgumentException` - if the number of actual
                     and formal parameters differ; if an unwrapping
                     conversion for primitive arguments fails; or if,
                     after possible unwrapping, a parameter value
                     cannot be converted to the corresponding formal
                     parameter type by a method invocation conversion; if
                     this constructor pertains to an enum type.
         * `InstantiationException` - in case of abstract class.
         * `InvocationTargetException` - if the underlying constructor
                     throws an exception.
         * `ExceptionInInitializerError` - if the initialization provoked
                     by this method fails.
    * If the constructor's declaring class is an inner class in a
      non-static context, the first argument to the constructor needs
      to be the enclosing instance: https://github.com/mtumilowicz/java11-member-inner-class-compilation

Note that `public T newInstance()` method from `Class` is
deprecated since java 9:
> This method propagates any exception thrown by the
  nullary constructor, including a checked exception.  Use of
  this method effectively bypasses the compile-time exception
  checking that would otherwise be performed by the compiler.

# project description
We will show how to create object using reflection.
```
class X {
    final String name;
    final int count;

    X(String name, int count) {
        this.name = name;
        this.count = count;
    }
}
```
and creation:
```
X x = X.class.getDeclaredConstructor(String.class, int.class).newInstance("a", 1);
assertThat(x.name, is("a"));
assertThat(x.count, is(1));
```
**pay attention to parameters:**
```
@Test(expected = IllegalArgumentException.class)
public void create_wrongArguments() throws NoSuchMethodException,
        IllegalAccessException,
        InvocationTargetException,
        InstantiationException {
    X.class.getDeclaredConstructor(String.class, int.class).newInstance("a");
}
```