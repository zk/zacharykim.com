(ns zkimcom.site
  (:require [hiccup.core :as hc]
            [hiccup.page :as hp]
            [garden.core :refer [css]]))

(def stylesheet
  [[:html :body {:height "100%" :width "100%"}]
   [:body {:font-family "'Lato', sans-serif"}
    [:.logo {:max-width "100%"
             :display 'block
             :margin-left 'auto
             :margin-right 'auto
             :margin-top "70px"
             :margin-bottom "40px"

             :border-radius "50%"
             :background-color "#79D6FD"
             :padding "10px"}]
    [:.heading {:font-weight 300
                :font-size "33px"
                :line-height "130%"
                :margin-top 0
                :margin-bottom "20px"}
     [:strong {:font-weight 500}]]
    [:.redact {:text-decoration 'line-through
               :color "#aaa"
               :white-space 'nowrap}]
    [:.above-fold
     {:padding-top "50px"
      :min-height "100%"}]
    [:.callout {:margin-bottom "60px"}]
    [:.contact
     {:text-align 'left}
     [:.contact-logo
      {:height "30px"
       :margin-left 0
       :margin "0 30px"}]]

    [:.nav
     {:margin-bottom "25px"}
     [:ul
      {:padding-left 0}
      [:li {:display 'inline-block
            :margin-right "20px"}
       [:a {:font-size "18px"
            :color "#ccc"}]]]]
    [:.featured
     [:.featured-work-item
      [:.header
       {:width "100%"
        :height "300px"
        :background-size 'cover
        :background-position 'center
        :color 'white
        :position 'relative
        :margin-bottom "30px"}
       [:.caption {:position 'absolute
                   :bottom 0
                   :left 0
                   :right 0
                   :margin 0
                   :padding "10px"
                   ;;:background-color "rgba(0,0,0,0.5)"
                   :background "linear-gradient(rgba(0,0,0,0),rgba(0,0,0,1))"}
        [:h3 :h4 {:margin 0}]
        [:h4 {:margin-top "5px"
              :text-transform 'uppercase
              :font-weight 300
              :font-size "16px"}]]]
      [:p {:font-size "18px"}]]
     [:.divider {:text-align 'center
                 :margin "60px 0"}]
     [:.mockdbs
      [:.header {:color "black"}
       [:.caption {:background "linear-gradient(rgba(255,255,255,0),rgba(255,255,255,1))"}]]]]]])

(defn featured-section []
  [:div.section.featured
   [:div.container
    [:div.col-sm-12.col-md-10.col-md-offset-1
     [:h2 "Featured Work & Talks"]

     [:div.featured-work-item.cuba
      [:div.header {:style "background-image: url('/images/cuba2.jpg'); background-position: bottom;"}
       [:div.caption
        [:h3 "Technology and Entrepreneurship in Silicon Valley"]
        [:h4 "Havana, July 2015"]]]
      [:p "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt."]]
     [:div.divider "---"]
     [:div.featured-work-item
      [:div.header {:style "background-image: url('/images/recent_projects/celltracker.png');"}
       [:div.caption
        [:h3 "Intra-Op 3D Visualizer for Deep Brain Stimulation"]]]
      [:p "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt."]
      [:p "Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?"]]
     [:div.divider "---"]
     [:div.featured-work-item.mockdbs
      [:div.header {:style "background-image: url('/images/recent_projects/mockdbs.png');"}
       [:div.caption
        [:h3 "Intra-Op Neuronal Signal Simulator"]]]
      [:p "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt."]
      [:p "Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?"]]
     [:div.divider "---"]
     [:div.featured-work-item.cuba
      [:div.header {:style "background-image: url('/images/veedev.jpg'); background-position: bottom;"}
       [:div.caption
        [:h3 "No-Strings Mobile App Development in Clojure"]
        [:h4 "Portland, March 2015"]]]
      [:p "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt."]]]]])

(def index
  (hp/html5
    [:head
     [:link {:rel "stylesheet" :href "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"}]
     [:link {:rel "stylesheet" :href "http://fonts.googleapis.com/css?family=Lato:400,700,300"}]
     [:link {:rel "stylesheet" :href "css/site.css"}]]
    [:body
     [:div.section.above-fold
      [:div.container
       [:div.row
        [:div.col-sm-12.col-md-10.col-md-offset-1
         [:div.row
          [:div.col-sm-4
           [:img.logo {:src "https://pbs.twimg.com/profile_images/532617885617627136/KEYfBh1r.png"}]]
          [:div.col-sm-8
           [:div.nav
            [:ul
             [:li [:a {:href "#"} "About"]]
             [:li [:a {:href "#"} "Featured Work & Talks"]]]]
           [:div.callout
            [:h1.heading "Hey there. "]
            [:h1.heading
             "This is the professional portfolio of " [:strong "Zachary Kim"] ", an Engineer and Entrepreneur located in "
             [:span.redact "Honolulu, HI,"]
             " "
             [:span.redact "Denver, CO,"]
             " "
             [:span.redact "Chicago, IL,"]
             " "
             "San Francisco, CA"
             "."]]
           [:div.contact
            [:img.contact-logo {:src "images/email-logo.png"}]
            [:img.contact-logo {:src "https://cdn4.iconfinder.com/data/icons/iconsimple-logotypes/512/github-512.png"}]
            [:img.contact-logo {:src "images/twitter-logo.png"}]
            [:img.contact-logo {:src "images/linkedin-logo.png"}]
            ]]]]]]]
     [:div.section.about
      [:div.container
       [:div.col-sm-12.col-md-10.col-md-offset-1
        [:h1 "HI"]]]]
     (featured-section)
     (repeat 20 [:br])
     [:script {:type "text/javascript" :src "cljs/app.js"}]]))

(def pages
  {"index.html" index})
