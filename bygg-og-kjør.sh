#!/bin/bash

set -e

mvn clean install

cd nyhende-server
mvn spring-boot:run