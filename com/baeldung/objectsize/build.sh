#!/bin/bash

javac InstrumentationAgent.java
jar cmf MANIFEST.MF InstrumentationAgent.jar InstrumentationAgent.class
