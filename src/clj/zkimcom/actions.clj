(ns zkimcom.actions
  (:use [nsfw util render]
        [ring.util.response :only (file-response redirect status header)])
  (:require [zkimcom.templates :as tpls]
            [zkimcom.social :as social]))

(defn index [req]
  (render tpls/index [] #_(social/latest! 5)))

(defn recent-projects [req]
  (redirect "/featured-work"))

(defn four-oh-four [req]
  (-> (file-response "resources/public/404.html")
      (header "Content-Type" "text/html")
      (status 404)))

(defn featured-work [req]
  (render tpls/featured-work))

