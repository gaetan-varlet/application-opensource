# Exemple de projet open-source

Il existe 2 projets dans ce dépôt Git :
- **api.opensource** qui contient tout le code "métier" de l'application, qui peut fonctionner en dehors de l'Insee
- **api.insee** qui a comme dépendance *api.opensource* et redéfinit certaines choses du projet opensource pour les besoins spécifiques de l'Insee

Un projet/application qui souhaite adopter cette logique doit avoir 2 dépôts Git distinct pour les 2 projets, par exemple un dépôt public sur Github pour la partie opensource, et un dépôt interne à l'Insee sur GitLab pour la partie Insee.  
Ici comme il s'agit d'un projet de démonstration, les 2 projets sont dans le même dépôt par soucis de simplicité pour les personnes souhaitant tester cette architecture.

## Principales différences :

|                     | Open Source            | Insee                       |
| :---:               | :---:                  | :---:                       |
| Port d'écoute       | 8080                   | 8082                        |
| Base de données     | h2 en mémoire          | postgre Insee               |
| Sécurité            | Spring Security BASIC  | Keycloak BEARER             |
| UtilisateurService  | UtilisateurServiceImpl | UtilisateurServiceImplInsee |

## Description plus détaillée

- l'endpoint `/entreprise` en GET et en POST est branché sur une base de données (h2 ou postgre en fonction du projet exécuté). Le code JAVA n'est présent que dans le projet opensource. Un script d'initialisation de la base h2 en mémoire est présent dans le projet open source pour que l'application démarre avec la base h2 avec des données. Dans le projet Insee, les properties et la dépendance maven de *postgre* sont présents et surchargent la configuration du projet open source, pour pointer sur la base *postgre* de l'Insee au lieu de la base h2.
- l'endpoint `/utilisateur` en GET permet d'obtenir la liste de tous les utilisateurs. Le code JAVA de l'interface est défini dans le projet open source, ainsi qu'une implémentation avec des données en dur (UtilisateurServiceImpl). Une autre implémentation est définie dans le projet Insee (UtilisateurServiceImplInsee) avec d'autres données pour montrer que cette dernière est bien utilisée dans le projet Insee comme implémentation. Afin que Spring sache qu'il doit utiliser cette implémentation dans le projet Insee, il faut ajouter l'annotation **@Primary** sur la classe de l'implémentation Insee.  
Cet exemple peut être une façon de gérer l'appel à des services interne comme Igesa (WS pour interagir avec le LDAP) : une implémentation par défaut qui est un mock, et une implémentation Insee qui fait les requêtes HTTP vers Igesa.
- L'API est sécurisée en monde basic avec *Spring Security* dans le projet open source (avec plusieurs id-mdp en fonction du profil souhaité). Dans le projet Insee, Keycloak est mis en place en mode Bearer à la place du mode Basic.
- Certains endpoints sont protégés avec un rôle particulier pour y accéder avec l'annotation `RolesAllowed("ADMIN")` dans le projet open source. Il faut alors saisir le couple idep/mdp correspondant à ce rôle pour y avoir accès. Ces endpoints sont protégés de la même manière dans le projet Insee avec les rôles présents dans le jeton Keycloak.