playthrift
==========

A simple Play Framework project that exposes a Thrift-over-HTTP service.

Notes
-----

* Scrooge is used for Thrift IDL compilation. Looks like there isn't a Maven artifact for Scala 2.10.2 / SBT 0.13 (which is what I'm using), so ScroogeSBT.scala is included in project/.

* Scrooge generates code that depends on Finagle, which is why there are a bunch of Finagle dependencies in build.sbt.

* The Finagle Thrift service returns a com.twitter.util.Future. But Play needs a scala.concurrent.Future. Conversion from c.t.u.Future to s.c.Future is done is an implicit in util.futureconv, adapted from https://gist.github.com/arschles/5504373 (thanks!).


Relevant bits
-------------

* **conf/routes** defines the HTTP endpoint and the controller that will handle it

* **app/controllers/ThriftApis.scala** is where the controller is implemented.

  * controllers.ThriftApis.mathservice

* **app/model/thriftservice/MathServiceImpl.scala** is the actual thrift service


