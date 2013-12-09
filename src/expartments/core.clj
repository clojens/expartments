(ns expartments.core
  (:require [swiss-arrows.core :refer :all]
            [datomic-schema.schema :refer [defpart defschema fields part]]
            [datomic.api :as d]
            [datomic-schema.schema :as s])
  (:gen-class))

(defonce db-url "datomic:free://localhost:4334/my-db")

(defpart app)

(defschema broker
  (part app)
  (fields
   [username :string :indexed]
   [pwd :string "Hashed password string"]
   [email :string :indexed]
   [status :enum [:pending :active :inactive :cancelled]]
   [housing :ref :many]))

(defschema housing
  (part app)
  (fields
   [name :string]
   [address :string :indexed]
   [status :enum ["Vacant" "Occupied"]]
   [attributes :string :many]))

(defn -main [& args]
  (d/create-database db-url)
  (d/transact (d/connect db-url) (s/build-parts d/tempid))
  (d/transact (d/connect db-url) (s/build-schema d/tempid)))

(-main)
(d/transact (d/connect db-url) [{:housing/name "Foo"
                                 :db/id (d/tempid :db.part/user)
                                 :housing/address "East 1"
                                 :housing/status :housing.status/vacant}
                                ])
;(d/db (d/connect db-url))
; (s/generate-schema broker housing)
