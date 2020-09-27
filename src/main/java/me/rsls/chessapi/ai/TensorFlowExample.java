package me.rsls.chessapi.ai;


import org.tensorflow.*;

public class TensorFlowExample {
    public static void main(String[] args) {

        //https://www.baeldung.com/tensorflow-java

        Graph graph = new Graph();

        Operation a = graph.opBuilder("Const", "a")
                .setAttr("dtype", DataType.fromClass(Double.class))
                .setAttr("value", Tensor.<Double>create(3.0, Double.class))
                .build();
        Operation b = graph.opBuilder("Const", "b")
                .setAttr("dtype", DataType.fromClass(Double.class))
                .setAttr("value", Tensor.<Double>create(2.0, Double.class))
                .build();


        Operation x = graph.opBuilder("Placeholder", "x")
                .setAttr("dtype", DataType.fromClass(Double.class))
                .build();
        Operation y = graph.opBuilder("Placeholder", "y")
                .setAttr("dtype", DataType.fromClass(Double.class))
                .build();



        Operation ax = graph.opBuilder("Mul", "ax")
                .addInput(a.output(0))
                .addInput(x.output(0))
                .build();
        Operation by = graph.opBuilder("Mul", "by")
                .addInput(b.output(0))
                .addInput(y.output(0))
                .build();

        Operation z = graph.opBuilder("Add", "z")
                .addInput(ax.output(0))
                .addInput(by.output(0))
                .build();

//        System.out.println(z.output(0));


        Session sess = new Session(graph);

        Tensor<Double> tensor = sess.runner()
                .fetch("z")
                .feed("x", Tensor.<Double>create(3.0, Double.class))
                .feed("y", Tensor.<Double>create(6.0, Double.class))
                .run()
                .get(0)
                .expect(Double.class);
        System.out.println(tensor.doubleValue());




//        try (Graph g = new Graph()) {
//            final String value = "Hello from " + TensorFlow.version();
//
//            // Construct the computation graph with a single operation, a constant
//            // named "MyConst" with a value "value".
//            try (Tensor t = Tensor.create(value.getBytes("UTF-8"))) {
//                // The Java API doesn't yet include convenience functions for adding operations.
//                g.opBuilder("Const", "MyConst").setAttr("dtype", t.dataType()).setAttr("value", t).build();
//            }
//
//            // Execute the "MyConst" operation in a Session.
//            try (Session s = new Session(g);
//                 // Generally, there may be multiple output tensors,
//                 // all of them must be closed to prevent resource leaks.
//                 Tensor output = s.runner().fetch("MyConst").run().get(0)) {
//                System.out.println(new String(output.bytesValue(), "UTF-8"));
//            }
//        }
    }
}

