
# Spring Boot + Apache Cassandra + OpenShift

- Start a Cassandra instance using [Docker](https://hub.docker.com/_/cassandra/):

```bash
$ docker pull cassandra
$ docker run -d -p 9042:9042 --name my-cassandra cassandra
```
- Open Cassandra CQL Shell to create Cassandra table:

```bash
$ docker run -it --link my-cassandra:cassandra --rm cassandra sh -c 'exec cqlsh "$CASSANDRA_PORT_9042_TCP_ADDR"'
```

- Create Cassandra keyspace with name `testapp`:

```cql
cqlsh> create keyspace testapp with replication={'class':'SimpleStrategy', 'replication_factor':1};
```
- Create `customer` table for `testapp` keyspace:

```cql
cqlsh> use testapp;
cqlsh> CREATE TABLE customer(
         id int PRIMARY KEY,
         firstname text,
         lastname text,
         age int
      );
```

- Create an index on `firstname` column:

```cql
cqlsh> CREATE INDEX ON testapp.customer (firstname);
```

- Query `customer` table:

```cql
cqlsh> select * from customer;
```

- Run the application:
```bash
$ cd spring-boot-oc-cassandra
$ mvn spring-boot:run
```
