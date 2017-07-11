#!/bin/sh

mkdir -p bin/
javac -cp bin/ -d bin/ `find src/ -name *.java`
