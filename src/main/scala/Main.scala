package demo

import squid.utils._
import Helpers._
import squid.IR
import squid.IR.Predef._

object Main extends App {
  
  println("-" * 70)
  
  
  
  
  val c: Code[Double, Any] = code"123.toDouble"
  println(c)
  
  val x = Variable[Int]
  
  val c0 = code"123 + $x + 1"
  // val c0 = code"Some(123 + $x + 1)"
  
  println(c0)
  // println(c0.rep.dfn)
  // println(c0.run)
  
  val y = Variable[Int]
  code"$x + $y" : Code[Int, x.Ctx & y.Ctx]
  
  def foo[C](c: Code[Int, C & x.Ctx]): Code[Int,C] =
    c.subs(x) ~> code"42"
  
  println(foo[Any](c0))
  
  val sub = c0.subs(x) ~> code"42"
  println(sub.run)
  // println(sub.compile)
  
  // code"{(a:Int) => a+1}"
  val c1 = code"{$x => $c0}"
  println(c1)
  println(c1.run.apply(0))
  
  val c2 = code"List(1,2,3).map($c1)"
  println(c2.run)
  
  // Some(1) match {
  //   case Some(x) => x
  // }
  
  (c2: ClosedCode[Any]) match {
    case code"($ls: List[$t]).map(($v: t) => $body: $s)" =>
      println(ls)
      println(v)
      println(body)
  }
  
  val c3 = c1 fix_topDown_rewrite {
      
    // reassociate
    case code"($x0: Int) + (($x1: Int) + ($x2: Int))" =>
      code"$x0 + $x1 + $x2"
      
    // commute
    case code"($x0: Int) + (${Const(x1)}: Int)" =>
      code"${Const(x1)} + $x0"
      
    case code"(${Const(x0)}: Int) + (${Const(x1)}: Int)" =>
      Const(x0 + x1)
      
  }
  println(c3)
  
  
  
  
  println("-" * 70)
  
}
