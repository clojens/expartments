(ns expartments.core
  (:require [swiss-arrows.core :refer :all]
            [clojure.pprint :refer [pprint]]
            [datomic-schema.schema :refer [defpart defschema fields part]]
            [datomic.api :as d]
            [datomic-schema.schema :as s]
            [cfg.current :refer [project]])
  (:gen-class))


(defonce db-url (get-in @project [:datomic :db-uri]))
;(defonce db-url "datomic:free://localhost:4334/myfoo")

(defpart app)

(defschema broker
  (part app)
  (fields
   [username :string :indexed]
   [pwd :string "Hashed password string"]
   [email :string :indexed]
   [status :enum [:pending :active :inactive :cancelled]]
   [housing :ref :many
    "Reference to point to a collection of housing objects like apartments or houses."]))

(defschema housing
  (part app)
  (fields
   [name :string]
   [address :string :indexed
    "Unique address? How about brokers who have the same houses?"]
   [availability :enum ["Vacant" "Occupied"]
    "Status to denote if a particular housing is available (vacant) or not (occupied)."]
   [attributes :string :many
    "Specific attributes of housing facilities such as a pool, fireplace, garden etc."]))

(defn create
  []
  (d/create-database db-url)
  (d/transact (d/connect db-url) (s/build-parts d/tempid))
  (d/transact (d/connect db-url) (s/build-schema d/tempid)))

(create)

(d/transact (d/connect db-url) [{:housing/name "Foo"
                                 :db/id (d/tempid :db.part/user)
                                 :housing/address "East 1"
                                 :housing/status :housing.status/vacant}
                                ])

;; (d/db (d/connect db-url))
;; (s/generate-schema broker housing)
