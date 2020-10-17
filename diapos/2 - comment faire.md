# Comment faire ?

----

## Organisation

Création de 2 projets Java distincts (API) construits avec Spring Boot :
- **api-opensource** qui contient tout le code "métier" de l'application, qui peut fonctionner en dehors de l'Insee
- **api-insee** qui a comme dépendance *api.opensource* et redéfinit certaines choses du projet opensource pour les besoins spécifiques de l'Insee

Un projet ou une application qui souhaite adopter cette logique doit avoir 2 dépôts Git distinct pour les 2 projets, par exemple un dépôt public sur Github pour la partie opensource, et un dépôt interne à l'Insee sur GitLab pour la partie Insee.

----

## Principales différences

|                     | Open Source            | Insee                       |
| :---:               | :---:                  | :---:                       |
| Port d'écoute       | 8080                   | 8082                        |
| Base de données     | H2 en mémoire          | PostgreSQL à Insee          |
| Sécurité            | Spring Security BASIC  | Keycloak BEARER             |
| UtilisateurService  | UtilisateurServiceImpl | UtilisateurServiceImplInsee |

----

