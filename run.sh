#!/usr/bin/env bash

lein datomic start &
lein datomic initialize

echo "sudo ./opt/datomic-free/bin/console -p 1337 free datomic:free://localhost:4334/my-db"
