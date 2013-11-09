package controllers

import play.api._
import play.api.mvc._
import model.thriftservice.MathServiceImpl
import thrift_mathservice.MathService$FinagleService
import org.apache.thrift.protocol.TBinaryProtocol
import util.futureconv._

object ThriftApis extends Controller {

  val mathProcessor = new MathServiceImpl
  val mathServiceImpl = new MathService$FinagleService(mathProcessor, new TBinaryProtocol.Factory())

  def mathservice = Action.async(parse.anyContent) { request =>

    val inData: Option[Array[Byte]] = request.body.asRaw.flatMap(_.asBytes())

    val outData: Option[com.twitter.util.Future[Array[Byte]]] = inData.map(mathServiceImpl.apply)

    outData match {
      case None => { throw new Exception("")}
      case Some(outFuture: com.twitter.util.Future[Array[Byte]]) => {

        //val returnFuture: scala.concurrent.Future[Array[Byte]] = util.futureconv.twitterFutureToScala(outFuture)

        // instead of explicitly converting from c.t.u.Future to
        // s.c.Future above, use implicit conversion imported from
        // util.futureconv:
        val returnFuture = outFuture

        for {
          x <- returnFuture
        } yield {
          Ok(x).as("application/thrift")
        }
      }
    }
  }

}
