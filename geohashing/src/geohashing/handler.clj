(ns geohashing.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [geohashing.xkcd-builder :as xkcd]
            [clojure.data.json :as json]
            [clj-time.core :as t]
            [clj-http.client :as http]))

(def yahoo-url "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22%5ENDX%22%2C%22INDU%22)&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=")

(defn- dow-opening []
  (let [yahoo-data (json/read-str (:body (http/get yahoo-url)))]
        (get-in yahoo-data ["query" "results" "quote" 0 "Open"])))

(defroutes app-routes
  (GET "/geohash" request
       (let [{:keys [:lat :lon]} (:params request)]
         (xkcd/coordinates lat lon (t/today) (dow-opening)))))

(def app
  (wrap-defaults app-routes site-defaults))
