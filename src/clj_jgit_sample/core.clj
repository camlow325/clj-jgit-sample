(ns clj-jgit-sample.core)

(:require '[clj-jgit.porcelain :as porc])
;(:require '[clj-jgit.porcelain])

(def my-repo
  (porc/git-clone-full "https://github.com/camlow325/clj-git-sample.git"
                       "local-folder/shadow-of-clj-git-sample"))

(defn -main
  (println "I'm in main")
  ;(clj-jgit.porcelain/git-status my-repo)
  (porc/git-status my-repo)
  (println ("After git status")))
