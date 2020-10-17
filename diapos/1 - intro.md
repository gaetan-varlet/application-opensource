# Introduction

----

## Ca veut dire quoi rendre son application opensource ?

- la préparer à la publier sur un dépôt public
- un grand principe : la **non adhérence**

----

## Pourquoi ?

Parce qu'on est en 2020 !

----

## Pourquoi ?

- portabilité, interopérabilité, reproductibilité
    - exécution en externe pour développement sur PC perso en télétravail, pour une prestation externe
    - amélioration de la qualité du code
    - facilité du développement et de la maintenance
- contraintes légales

----

## La non adhérence

- ne pas avoir d'adhérence vers des composants Insee :
    - base de données au CEI
    - service d'authentification interne
    - autres services internes comme service de gestion des contacts, d'envoi de mails...
- avoir une interface pour les services avec une implémentation par défaut non Insee, et une autre implémentation spéficique à l'Insee
