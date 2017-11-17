# OPML Parser

[![Maven Central badge](https://maven-badges.herokuapp.com/maven-central/be.ceau/opml-parser/badge.svg)](https://mvnrepository.com/artifact/be.ceau/opml-parser) [![Javadocs](https://javadoc.io/badge/be.ceau/opml-parser.svg)](https://javadoc.io/doc/be.ceau/opml-parser)  [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0.txt) [![build status](https://api.travis-ci.org/mdewilde/opml-parser.svg?branch=master)](https://travis-ci.org/mdewilde/opml-parser) ![code coverage](https://codecov.io/gh/mdewilde/opml-parser/branch/master/graph/badge.svg)


Java library for parsing and writing OPML (Outline Processor Markup Language) documents.

  * supports [1.0 specification](http://dev.opml.org/spec1.html)
  * supports [2.0 specification](http://dev.opml.org/spec2.html)
  * over 90% test coverage

### Usage

Provide your serialized input as `String`, `InputStream` or `Reader`:

```Java
Opml opml = new OpmlParser().parse(input);
```

To create XML from an `Opml` instance, use the `OpmlWriter` class:

```Java
String xml = new OpmlWriter().write(opml);
```

### Requirements
This library requires Java 7 or higher.

### Maven Central
Include this project directly from Maven Central
```XML
<dependency>
	<groupId>be.ceau</groupId>
	<artifactId>opml-parser</artifactId>
	<version>${project.version}</version>
</dependency>
```

### GnuPG public key
Verify signature files with my [GnuPG public key](https://www.ceau.be/pubkey.gpg).

### License
Licensed under [the Apache 2.0 license](http://www.apache.org/licenses/LICENSE-2.0.txt).