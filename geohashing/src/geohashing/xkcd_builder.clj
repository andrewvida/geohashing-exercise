(ns geohashing.xkcd-builder
  (:require [clojure.data.json :as json]
            [digest :as digest]
            [clojure.string :as strg]))

(defn coordinates [lat lon date dow-opening]
  (let [md5-hash (digest/md5 (str date "-" dow-opening))
        left-decimal (BigInteger. (subs md5-hash 0 15) 16)
        right-decimal (BigInteger. (subs md5-hash 16 31) 16)]
 
    (json/write-str {:lat (str (first (strg/split lat #"\.")) "." left-decimal)
                     :lon (str (first (strg/split lon #"\.")) "." right-decimal)})))