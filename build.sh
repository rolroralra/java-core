#!/bin/bash

javac com/baeldung.objectsize/InstrumentationAgent.java
jar cmf MANIFEST.MF com/baeldung/objectsize/InstrumentationAgent.jar com/baeldung/objectsize/InstrumentationAgent.class
