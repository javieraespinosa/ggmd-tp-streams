#!/bin/bash

echo "GO" > ./streams/streamstate

java -jar ./target/StreamRunners-0.1-jar-with-dependencies.jar $1 $2 $3 $4
