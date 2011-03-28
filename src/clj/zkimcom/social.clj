(ns zkimcom.social
  (:require [somnium.congomongo :as mongo]))

(defn latest [type]
  (->> (mongo/fetch type :limit 10 :sort {:pub-date -1})
       (map #(assoc % :type type))))

(defn latest! [num]
  (->> (concat
        (latest :reader)
        (latest :twitter)
        (latest :delicious))
       (sort-by :pub-date)
       (reverse)
       (take num)))


