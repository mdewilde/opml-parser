# OPML Parser

![Maven Central badge](https://maven-badges.herokuapp.com/maven-central/be.ceau/opml-parser/badge.svg) [![Javadocs](https://javadoc.io/badge/be.ceau/opml-parser.svg)](https://javadoc.io/doc/be.ceau/opml-parser)  [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0.txt) ![build status](https://api.travis-ci.org/mdewilde/opml-parser.svg?branch=master)


Java library for parsing OPML (Outline Processor Markup Language) documents.

  * supports [1.0 specification](http://dev.opml.org/spec1.html)
  * supports [2.0 specification](http://dev.opml.org/spec2.html)
  * over 90% test coverage

### Usage

```Java
Opml opml = new OpmlParser().parse(input);
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