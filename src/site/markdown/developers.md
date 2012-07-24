For Developers
==============

General
-------

 * Each module should be a self-contained sub project (maybe interconnected with
 others via mvn/jars).
 * When changing or adding code the whole project MUST compile.
 * The unit tests MUST pass without any user configuration (e.g. use a
 stubs like an in-memory database if needed).
 * Code coverage goal: [80% and no
 less!](http://googletesting.blogspot.de/2010/07/code-coverage-goal-80-and-no-less.html)
 * Applications SHOULD work out of the box without any user
 configuration (again use stubs by default and give the user the option
 to configure a replacement).
 * We strongly SUGGEST to follow the principles of the [Clean Code
 Developer](http://www.clean-code-developer.com) web site
 * Also MAYBE have a look at [Uncle Bob's Clean
 Code](http://www.cleancoders.com/) video series.

First steps
-----------

First check out the code:

    git clone https://github.com/tubav/fiteagle.git
    cd fiteagle

Now have a look at the documentation:

    mvn -N site
    open target/site/index.html

Try to compile and test the whole project:

    mvn test

Start for example the CLI:

    cd clients/cli
    ./src/main/scripts/startCLI.sh

Continuous Integration
----------------------

See public [travis-ci](http://travis-ci.org/#!/tubav/fiteagle) service.

Setup deployment
-----------------

Edit the file .m2/settings.xml:

    ?xml version="1.0"?>
    <settings>
        <servers>
            <server>
                <id>fiteagle</id>
                    <username>xxx</username>
                    <password>xxx</password>
            </server>
        </servers>
    </settings>

Checkin new code
-----------------

    cd path/to/project/root
    mvn -T3 clean test
    mvn deploy
    git status
    git add path/to/new/files
    git commit -m "Fixed xyz" .
    git push

Create a new module
-------------------

When designing a new module / component have in mind that [delivery
mechanisms and databases are just annoying
details]
(http://blog.8thlight.com/uncle-bob/2011/09/30/Screaming-Architecture.html)
and the core component should now not know anything
about web servers or specific databases. Defer this decision by using
stubs and simple implementations as long as possible.

    mkdir path/to/module
    cd path/to/module/..
    nano pom.xml #add the new module
    cd -
    cp ../othermodule/pom.xml . # or modify existing file
    nano pom.xml #change details
    #copy or create src/main/java directory
    mvn compile # repeat this until it compiles

Integrate into Eclipse
----------------------
 * Eclipse: Install m2e plugin
 * CLI: mvn eclipse:eclipse
 * Eclipse: File > import > Git > Project from Git > Local >
   ((add your local clone repo)) > Import existing projects > 
 * CLI: mvn eclipse:clean
 * Eclipse: right click on the project > Configure > Convert to Maven
 * Repeat this with all modules in order to be able to refactor code easily