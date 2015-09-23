(ns geohashing.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [geohashing.handler :refer :all]))

(deftest test-app
  (testing "geohash"
    (let [response (app (mock/request :get "/geohash?lat=37.421542&lon=-122.085589"))]
      (is (= (class (:body response)) String)))))
