(ns __app_name.core
    (:require
      ;; Reagent libraries
      [reagent.core :as r]
      [reagent.dom :as d]

      ;; Routing libraries
      [secretary.core :as secretary :refer-macros [defroute]]
      [accountant.core :as accountant]
      
      ;; Views
      [__app_name.views.default :as vdefault]
      [__app_name.views.one :as vone]))
(set! *warn-on-infer* false)

;; -------------------------
;; Main rounting and root definition

(defonce *global-state (r/atom {:view ::default}))

(defroute "/" []
  (swap! *global-state assoc :view ::default))

(defroute "/one" []
  (swap! *global-state assoc :view ::one))

(defn root []
  [:div
   [:div.mb-1
    (case (:view @*global-state)
      ::default (vdefault/render)
      ::one (vone/render))] 
   [:span
    "Navigate: "
    [:a.transition-all.hover:text-gray-500      {:href "/"} "default"]
    [:a.transition-all.ml-2.hover:text-gray-500 {:href "/one"} "one"]]])

(accountant/configure-navigation!
  {:nav-handler   (fn [path] (secretary/dispatch! path))
   :path-exists?  (fn [path] (secretary/locate-route path))})

(secretary/dispatch! js/window.location.pathname)

;; -------------------------
;; Initialize app

(defn mount-root []
  (d/render [root] (.getElementById js/document "app")))

(defn ^:export init! []
  (mount-root))
