# Presenting Squid using mdoc

Random Scala:

```scala mdoc
val x = 2
List(x, x)
```

---

Setting up:

<!-- don't even need :silent here, unlike in tut -->

```scala mdoc
import squid.IR
import IR.Predef._
```

Quasiquotes:

<!-- note the use of :to-string to use the normal toString method for pretty-printing -->

```scala mdoc:to-string
val c0 = code"123.toDouble"
val c1 = code"(x: Int) => $c0 + 1"
```

Running:

```scala mdoc
c0.run
```

---

```scala mdoc:to-string
val c2 = code"List(1,2,3).map($c1)"
```







