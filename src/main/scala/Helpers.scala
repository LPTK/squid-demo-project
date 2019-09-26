package demo

object Helpers {
  
  implicit class Ops[A](a: A) {
    def tap(f: A => Any): A = {
      f(a)
      a
    }
  }

}
