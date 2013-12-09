(defproject expartments "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :datomic {:schemas ["resources/schema" ["main-schema.edn"
                                          "initial-data.edn"]]}
  :profiles {:dev
             {:datomic {:config "resources/free-transactor-template.properties"
                        :db-uri "datomic:free://localhost:4334/my-db"}}}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [com.h2database/h2 "1.3.174"]
                 [commons-codec "1.7"]
                 [org.clojure/data.json "0.2.3"]
                 [org.clojure/math.combinatorics "0.0.7"]
                 [com.datomic/datomic "0.8.3335" :exclusions [commons-codec]]
                 [datomic-schema "1.0.2"]
                 [org.slf4j/slf4j-api "1.7.5"]
                 [swiss-arrows "0.6.0"]
                 [backtick "0.3.0"]
                 [dire "0.5.1"]
                 [ring "1.2.1" :exclusions [commons-codec]]
                 [hiccup "1.0.4"]
                 [prismatic/plumbing "0.1.1"]
                 [garden "1.1.4" :exclusions [org.clojure/clojure
                                              org.clojure/tools.reader
                                              org.clojure/data.json
                                              com.google.guava/guava]]
                 [uncomplicate/fluokitten "0.3.0"]
                 [org.jsoup/jsoup "1.7.3"]
                 [net.sf.jtidy/jtidy "r938"]
                 ])
