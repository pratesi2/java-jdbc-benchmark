# JDBC Benchmark

JDBC Benchmark is a Java Application to execute a simple benchmark analysis on a PostGRE database.

## Prerequisite
A PostGRE database with a user already configured.

You can use the `initDb.sql` script, provided in the source code, to initialize the database.

You can also provide yours `test.sql` file or use the project one.

## Installation

Clone this repository and use the Maven install command to generate jar-with-dependencies file, then rename it and load it as an usual jar.  

## Usage

```bash
java -jar jdbcBenchmark.jar [-hV] [-c=<jdbcUrl>] [-p=<password>] [-u=<user>] [-x=<commitPeriod>] [<testScript>]
```

```bash
[<testScript>]                      Optional. The file containing the script to be executed for testing.
-c, --connection-url=<jdbcUrl>      jdbc:postgresql://<server>:<port>/<dbName>
-h, --help                          Show this help message and exit.
-p, --password=<password>           The db password 
-u, --user=<user>                   The db user
-V, --version                       Print version information and exit.
-x, --commit-period=<commitPeriod>  The number of statements to be executed before a commit
```

## Improvements

Here there are some improvements to be implemented on this project.

- Scan the test file via a Reader instead of loading all lines in memory.
- Parse test file for safely recognize DML operations types
- Improve communication with connection factory
- Improve operation time measurement method
- Enable extension of Collector, Printer and Analyzer with reflection in configuration