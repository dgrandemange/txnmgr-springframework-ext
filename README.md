txnmgr-springframework-ext
==========================

Spring XML extensions for the [txnmgr](https://github.com/dgrandemange/txnmgr) project

See this [test resource](/src/test/resources/fr/dgrandemange/springframework/ext/txnmgr/xml/some-app-context__txnmgr.xml/) as an example of a Spring XML application context configuration defining a transaction manager bean in the Spring factory, along all its participants/groups of participants/subflows(aka groups subsets).

Note that such transaction manager configuration is compatible with the [jPos Workflow Eclipse plugin](https://github.com/dgrandemange/jPosWorkflowEclipsePlugin) (requires version >= 1.1.3).

<h2>Adding txnmgr-springframework-ext to a Maven project</h2>
Add the following dependency section to your pom.xml :
```
<dependency>
	<groupId>com.github.dgrandemange</groupId>
	<artifactId>txnmgr-springframework-ext</artifactId>
	<version>1.0.0</version>
</dependency>
```
