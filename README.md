# expartments

A Clojure library designed to ... well, that part is up to you.

## Usage

1. Start your virtual terminal emulator
1. Run `$ lein datomic start &`
1. With your transactor now running type `$ lein datomic initialize`

Make sure you have:

* Put [lein-datomic "0.2.0"] into the :plugins vector of your :user profile, or if you are on Leiningen 1.x do lein plugin install lein-datomic 0.2.0.
* You will need to download a zip from Datomic and extract it. This extraction location is your :install-location.
* Next you'll need to configure your :user profile in ~/.lein/profiles.clj to add a :datomic key to a map with an :install-location like so:

```clj
{:user
 {:plugins [[lein-datomic "0.2.0"]]
  :datomic {:install-location "/path/to/your/install/dir/datomic-free-0.8.3619"}}}
```

In your project, add a configuration like:

```clj
(defproject ...
  :datomic {:schemas ["resources/schema" ["my-schema.edn"
                                          "initial-data.edn"]]}
  :profiles {:dev
             {:datomic {:config "resources/free-transactor-template.properties"
                        :db-uri "datomic:free://localhost:4334/my-db"}}})
```

## License

Copyright © 2013 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
