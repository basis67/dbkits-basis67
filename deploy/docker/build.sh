#!/bin/sh

cp ../../target/db-scaffold-0.0.1-SNAPSHOT.jar ./app.jar

docker build . -t app-db-kits:latest