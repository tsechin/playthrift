playthrift
==========

A simple Play Framework project that exposes a Thrift-over-HTTP service.

Notes
-----

* Scrooge is used for Thrift IDL compilation. Looks like there isn't a scrooge-sbt-plugin artifact for Scala 2.10.2 / SBT 0.13 (which is what I'm using), so ScroogeSBT.scala is included in the project/ dir (I don't remember which version I lifted this from...).

* Scrooge generates code that depends on Finagle, which is why there are a bunch of Finagle dependencies in build.sbt.

* Finagle Thrift service implementations return a com.twitter.util.Future. But Play needs a scala.concurrent.Future. Conversion from c.t.u.Future to s.c.Future is done is an implicit in util.futureconv, adapted from https://gist.github.com/arschles/5504373 (thanks!).


Clients
-------

Client code should look a bit like this::
 
	HttpClient hc = new DefaultHttpClient();
	THttpClient tHttpClient = new THttpClient(url, hc);
	TProtocol tProtocol = new TBinaryProtocol(tHttpClient);
	MathService.Client client = new MathService.Client(tProtocol);
	int result = client.addFive(10);

Relevant code
-------------

* The file *conf/routes* defines the HTTP endpoint and the controller that will handle it

* In *app/controllers/ThriftApis.scala*, controllers.ThriftApis.mathservice shuttles data between HTTP and the Thrift service implementation.

* And finally, *app/model/thriftservice/MathServiceImpl.scala* contains the actual service implementation.


