(ns geohashing.xkcd-builder-test
  (:require [clojure.test :refer :all]
            [geohashing.xkcd-builder :refer :all]))

(deftest test-coordinates
	(let [lat "37.421542"
          lon "-122.085589"
          test-date "2005-05-26"
          dow-opening "10458.68"]
   (is (= (coordinates lat lon test-date dow-opening)
          "{\"lat\":\"37.988876071126012477\",\"lon\":\"-122.627815415079518457\"}"))))
