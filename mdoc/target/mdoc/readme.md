# Presenting Squid using mdoc

Random Scala:

```scala
val x = 2
// x: Int = 2
List(x, x)
// res0: List[Int] = List(2, 2)
```

---

Setting up:

<!-- don't even need :silent here, unlike in tut -->

```scala
import squid.IR
import IR.Predef._
```

Quasiquotes:

<!-- note the use of :to-string to use the normal toString method for pretty-printing -->

```scala
val c0 = code"123.toDouble"
// c0: ClosedCode[Double] = code"123.toDouble"
val c1 = code"(x: Int) => $c0 + 1"
// c1: ClosedCode[Int => Double] = code"((x_0: scala.Int) => 123.toDouble.+(1))"
```

Running:

```scala
c0.run
// res1: Double = 123.0
```

---

```scala
val c2 = code"List(1,2,3).map($c1)"
// c2: ClosedCode[List[Double]] = code"scala.collection.immutable.List.apply[scala.Int](1, 2, 3).map[scala.Double, scala.collection.immutable.List[scala.Double]](((x_0: scala.Int) => 123.toDouble.+(1)))(scala.collection.immutable.List.canBuildFrom[scala.Double])"
```

