(ns reveal.core
    (:require
      ;; Reagent libraries
      [reagent.core :as r]
      [reagent.dom :as d]

      ;; The Preso
      [reveal.presentation :as p]))
(set! *warn-on-infer* false)

;; -------------------------
;; The presentation mount root

(defn root []
  (into [:div.slides] p/presentation))

;; -------------------------
;; Initialize app

(defn mount-root []
  (d/render [root] 
            (aget (.getElementsByClassName js/document "reveal") 0)))

(defn ^:export init! []
  (mount-root))
