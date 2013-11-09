package util
import com.twitter.util.{Future => TwFuture, Promise => TwPromise}
import scala.concurrent.{Future => ScFuture, promise => scPromise}
import scala.language.implicitConversions

object futureconv {

  // convert com.twitter.util.Future to scala.concurrent.Future
  // adapted from https://gist.github.com/arschles/5504373

  implicit def twitterFutureToScala[T](twFuture: TwFuture[T]): ScFuture[T] = {
    val prom = scPromise[T]
    twFuture.onSuccess { res: T =>
      prom.success(res)
    }
    twFuture.onFailure { t: Throwable =>
      prom.failure(t)
    }
    prom.future
  }

}