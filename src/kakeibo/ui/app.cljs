(ns kakeibo.ui.app
  (:require [goog.dom :as dom]
            [kakeibo.ui.db :as db]
            [kakeibo.ui.events]
            [kakeibo.ui.subs]
            [kakeibo.ui.views :as views]
            [re-frame.core :as re-frame]
            [reagent.core :as reagent]))

(defn mount []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/layout] (dom/getElement "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [::db/reset])
  (mount))

(comment
 (print "Hello again!"))
