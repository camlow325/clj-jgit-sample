(ns clj-jgit-sample.core
  (:require
            [clj-jgit.porcelain :as porcelain]
            [clojure.contrib.io :as io]
            [fs.core :as fs]))

(def repo-hosted-loc
  "https://github.com/camlow325/clj-jgit-sample-testrepo.git")

(def repo-cloned-dir
  "/tmp/clj-git-sample-cloned")

(def repo-change-file
  "README.md")

(defn repo-clean []
  (io/delete-file-recursively repo-cloned-dir true)
  )

(defn
  clone-repo []
  (:repo (clj-jgit.porcelain/git-clone-full repo-hosted-loc repo-cloned-dir)))

(defn display-status [git]
  (println "Status is: " (clj-jgit.porcelain/git-status git)))

(defn update-file []
  (spit (str repo-cloned-dir "/" repo-change-file)
        (str "Last changed: " (.toString (java.util.Date.)))))

(defn add-file [repo]
  (clj-jgit.porcelain/git-add repo repo-change-file))

(defn commit-file [repo]
  (clj-jgit.porcelain/git-commit repo "update via lein test run"
                                 {:name "lein tester" :email "lein.tester@bogus.com"}))

(defn clone-and-modify []
  (println "Cloning repo...")
  (let [cloned-repo (clone-repo)]
    (println "Repo cloned")
    (display-status cloned-repo)
    (println "Updating file...")
    (update-file)
    (println "Adding file...")
    (add-file cloned-repo)
    (display-status cloned-repo)
    (println "Committing file...")
    (commit-file cloned-repo)
    (display-status cloned-repo)
    ))

(defn -main []
  (println "Cleaning up to start...")
  (repo-clean)
  (clone-and-modify)
  (println "Leaving main"))

