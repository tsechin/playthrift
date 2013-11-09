package model.thriftservice

class MathServiceImpl extends thrift_mathservice.MathService[com.twitter.util.Future] {
  def heartbeat(): com.twitter.util.Future[Unit] = ???

  def addThree(n: Int): com.twitter.util.Future[Int] = {
    com.twitter.util.Future(n+3)
  }

  def addFive(n: Int): com.twitter.util.Future[Int] = {
    com.twitter.util.Future(n+5)
  }

  def addSeven(n: Int): com.twitter.util.Future[Int] = {
    com.twitter.util.Future(n+7)
  }

}
