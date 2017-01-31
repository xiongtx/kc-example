# kc-example

Simple Kafka client app that consumes from a topic (`kc-example-1`) and prints the key/value of each message.

## Usage

- `cd` to the project root.

- Build and run and app, using `docker-compose` to bring up dependencies.

```
$ lein deps
$ docker-compose build
$ docker-compose run app lein run
```

- Produce some messages to the `kc-example-1` topic. In a separate terminal at the project root,

```
$ docker-compose run kafka-tools
$ kafka-console-producer --broker-list kafka:9092 --topic kc-example-1
```

Enter a few messages as [described in the Kafka quickstart](https://kafka.apache.org/quickstart#quickstart_send). `^C` is `Ctrl-C`.

```
$ kafka-console-producer --broker-list kafka:9092 --topic kc-example-1
A
B
C
^C
```

- In the other terminal window, see that the app consumes the messages and prints out keys (in this case, `nil`s) and values.

```
Key: nil Value: A
Key: nil Value: B
Key: nil Value: C
```

### Testing

`cd` to the project directory and run:


```
lein test
```

You can also test specific namespaces:

```
lein test :only kc-example.core-test
```

Or specific tests:

```
lein test kc-example.core-test/producer-consumer-test
```
