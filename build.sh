#!/bin/sh

echo javac -cp bin/ -d bin/ `find src/ -name *.java`
