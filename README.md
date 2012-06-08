## About

The JBehave JUnit Reporter is an addon to [JBehave](http://jbehave.org) 3 that provides fine-grained visualisation and reporting around the progress of your stories. Each JBehave step is tracked and reported on as an individual test case. 

If you're running a story as a JUnit test through your favorite IDE, you'll be able to get output like this:

<img src="http://www.moralesce.com/wp-content/uploads/2012/06/jbehave-scenario-reporter.png"></img>

You can gain this capability by simply dropping the jbehave-junit-reporter-3 JAR file in your classpath and providing JBehave with the appropriate configuration. Visit the [downloads](https://github.com/BrandonJohnGrenier/jbehave-junit-reporter-3/downloads) page to grab a copy of the library.

<br/>

## What You Need

#### JUnit 4.4 or Later
The JBehave JUnit Reporter depends on JUnit 4.4 or later, and is not compatible with earlier versions of JUnit.

#### JBehave 3.0.0 or Later
The JBehave JUnit Reporter depends on JBehave 3.0.0 or later, and is not compatible with earlier version of JBehave. Please visit the [JBehave JUnit Reporter 2](https://github.com/BrandonJohnGrenier/jbehave-junit-reporter-2) homepage if you're looking for support for JBehave 2.

<br/>

## How it Works

The reporter will visually mark failing steps, and also provides a count of the number of steps and stories that are failing.

<img src="http://www.moralesce.com/wp-content/uploads/2012/06/jbehave-failing-steps.png"></img>

<br/>

The reporter will visually mark pending steps, and also provides a count of the number of steps that are pending. These are reported as 'ignored' in JUnit.

<img src="http://www.moralesce.com/wp-content/uploads/2012/06/jbehave-pending-steps.png"></img>

<br/>


<br/>

## Liscence

The JBehave JUnit Reporter is licensed under Apache 2.0.

<br/>