#!/bin/sh

# Generate classes
mkdir -p bin/
javac -cp bin/ -d bin/ `find src/ -name *.java`

# Generate javadoc
mkdir -p doc/
javadoc -d doc -sourcepath src -subpackages fr.unice.i3s.mdsc.simubool
