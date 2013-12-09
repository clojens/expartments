# expartments

A Clojure library designed to ... well, that part is up to you.

## Datomic

* Datomic is a transactional (ACID) systems.
* Datomic's EAV schema also embeds a time dimension (i.e. EAVT) which makes it very powerful if you want to perform efficient queries against your database at arbitrary points in time. This is something that non-immutable data stores (Neo4j included) simply can't do.
* Provides traversal API (are always driven by application code) and query language.
* Able to walk a graph using arbitrary traversal, filtering and data transformation code (except Neo4j requires a running transaction which in practice means it's time-bounded).
* Provide declarative query language (Datalog) that supports recursive queries
* Datomic's Datalog provides far superior querying capabilities by allowing custom filtering and aggregate functions to be implemented as arbitrary JVM code.
* This is possible because your application, not the database, is the one running queries.
* Datomic queries don't require database coordination (i.e. no read transactions) and they always work with a consistent data snapshot which means you could perform multiple queries and data transformations over an arbitrary period of time and guarantee your results will always be consistent and that no transaction will timeout (because there's none).
* Again, this is impossible to do in non-immutable data stores like the vast majority of existing databases (Neo4j included). This also applies to their traversal APIs.

### Working Set

If for some reason your queries needed to involve a huge amount of data (more than it would normally
fit in memory) and you couldn't stream the results (since Datomic provides streaming APIs), Datomic
would probably not be a good fit since you wouldn't be taking advantage of Datomic's architecture,
forcing peers to constantly evict their working memory, performing additional network calls and
decompressing data segments.

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
