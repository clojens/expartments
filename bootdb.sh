#!/usr/bin/env bash

cnstr=$(grep 'db-uri' project.clj | cut -d \" -f 2)

lein datomic start &
lein datomic initialize
sudo /opt/datomic-free/bin/console -p 1337 free ${cnstr}
