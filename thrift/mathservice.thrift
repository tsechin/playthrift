namespace java thrift_mathservice
namespace py thrift_mathservice

service MathService {
	void heartbeat(),

    i32 addThree(
        1: i32 n,
    ),

    i32 addFive(
        1: i32 n,
    ),

    i32 addSeven(
        1: i32 n,
    )

}

