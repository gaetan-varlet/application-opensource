# Exemple

----

## Création de services

- service de création et de récupération d'entreprises (en base de données)
- service de récupération d'utilisateurs (en mémoire dans l'application)

----

## Base de données

- l'endpoint `/entreprise` est branché sur une base de données
- code Java présent que dans le projet opensource
- script d'initialisation de la base H2 dans le projet open source
- dépendance maven de *PostgreSQL* dans le projet Insee et properties de la BDD qui surchargent la configuration du projet open source,

----

## Redéfinition de classe pour les spécificités Insee

- l'endpoint `/utilisateur` renvoie la liste des utilisateurs
- projet opensource : interface + implémentation avec des données en dur
- projet Insee : autre implémentation pouvant utiliser des composants propriétaires
- annotation **@Primary** sur l'implémentation Insee ou **@ConditionalOnSingleCandidate(UtilisateurService.class)** sur l'implémentation open source 

