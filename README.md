# java11-reflection-creating-objects

* https://github.com/mtumilowicz/java11-reflection-executables
* https://github.com/mtumilowicz/java11-reflection-constructors

# preface
Using reflection, we can create objects of a class that name need not be known 
until runtime.

To create object using reflection, we have to:
1. Get reference to the constructor: https://github.com/mtumilowicz/java11-reflection-constructors
1. Use the `public T newInstance(Object ... initargs)` method of `Constructor` class
    * If the constructor's declaring class is an inner class in a
      non-static context, the first argument to the constructor needs
      to be the enclosing instance: https://github.com/mtumilowicz/java11-member-inner-class-compilation
Note that `public T newInstance()` method from `Class` is
deprecated since java 9:
> This method propagates any exception thrown by the
  nullary constructor, including a checked exception.  Use of
  this method effectively bypasses the compile-time exception
  checking that would otherwise be performed by the compiler.

