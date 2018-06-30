
# Spring Boot + Apache Cassandra + OpenShift

- Start a Cassandra instance using [Docker](https://hub.docker.com/_/cassandra/):

```bash
$ docker pull cassandra
$ docker run -d -p 9042:9042 --name my-cassandra cassandra
```
- Open Cassandra CQL Shell:

```bash
$ docker run -it --link my-cassandra:cassandra --rm cassandra sh -c 'exec cqlsh "$CASSANDRA_PORT_9042_TCP_ADDR"'
```

- Create Cassandra keyspace with name `testapp`:

```cql
cqlsh> create keyspace testapp with replication={'class':'SimpleStrategy', 'replication_factor':1};
```
- Create `customer` table in `testapp` keyspace:

```cql
cqlsh> use testapp;
cqlsh:testapp> CREATE TABLE customer(
		         id int PRIMARY KEY,
		         firstname text,
		         lastname text,
		         age int
		      );
```

- Create an index on `firstname` column:

```cql
cqlsh:testapp> CREATE INDEX ON testapp.customer (firstname);
```

- Query `customer` table:

```cql
cqlsh:testapp> select * from customer;
```

- Run the application:
```bash
$ cd spring-boot-oc-cassandra
$ mvn spring-boot:run
```
