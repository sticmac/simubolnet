#!/bin/sh

javac -cp bin/ -d bin/ `find src/ -name *.java`
