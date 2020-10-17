# Introduction

----

## Ca veut dire quoi rendre son application opensource ?

- **la préparer à la publier en public**
- **pourquoi ?**
    - amélioration de la qualité du code
    - facilité du développement et de la maintenance
    - exécution en externe pour développement sur PC perso en télétravail, pour une prestation externe
    - contraintes légales
- un grand principe : la **non adhérence**

----

## La non adhérence

- ne pas avoir d'adhérence vers des composants Insee :
    - base de données
    - service d'authentification
    - autres services comme service de gestion des contacts, d'envoi de mails...
- avoir une interface pour les services avec une implémentation par défaut non Insee, et une autre implémentation spéficique à l'Insee
