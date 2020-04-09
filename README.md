# Exemple de projet open-source

Il existe 2 projets construit avec Spring Boot dans ce dépôt Git :
- **api-opensource** qui contient tout le code "métier" de l'application, qui peut fonctionner en dehors de l'Insee
- **api-insee** qui a comme dépendance *api.opensource* et redéfinit certaines choses du projet opensource pour les besoins spécifiques de l'Insee

Un projet ou une application qui souhaite adopter cette logique doit avoir 2 dépôts Git distinct pour les 2 projets, par exemple un dépôt public sur Github pour la partie opensource, et un dépôt interne à l'Insee sur GitLab pour la partie Insee.  
Ici comme il s'agit d'un projet de démonstration, les 2 projets sont dans le même dépôt par soucis de simplicité pour les personnes souhaitant tester cette architecture.

## Principales différences :

|                     | Open Source            | Insee                       |
| :---:               | :---:                  | :---:                       |
| Port d'écoute       | 8080                   | 8082                        |
| Base de données     | H2 en mémoire          | PostgreSQL à Insee          |
| Sécurité            | Spring Security BASIC  | Keycloak BEARER             |
| UtilisateurService  | UtilisateurServiceImpl | UtilisateurServiceImplInsee |

## Description plus détaillée

### Base de données

L'endpoint `/entreprise` en GET et en POST est branché sur une base de données (H2 ou postgre en fonction du projet exécuté). Le code JAVA n'est présent que dans le projet opensource. Un script d'initialisation de la base H2 en mémoire est présent dans le projet open source pour que l'application démarre avec la base H2 avec des données. Dans le projet Insee, les properties et la dépendance maven de *PostgreSQL* sont présents et surchargent la configuration du projet open source, pour pointer sur la base *PostgreSQL* de l'Insee au lieu de la base H2.

### Redéfinition de classe pour les spécificités Insee

L'endpoint `/utilisateur` en GET permet d'obtenir la liste de tous les utilisateurs. Le code JAVA de l'interface est défini dans le projet open source, ainsi qu'une implémentation avec des données en dur (UtilisateurServiceImpl). Une autre implémentation est définie dans le projet Insee (UtilisateurServiceImplInsee) avec d'autres données pour montrer que cette dernière est bien utilisée dans le projet Insee comme implémentation. Afin que Spring sache qu'il doit utiliser cette implémentation dans le projet Insee, il faut ajouter l'annotation **@Primary** sur la classe de l'implémentation Insee.  
Une autre façon de faire est d'utiliser l'annotation **@ConditionalOnSingleCandidate(UtilisateurService.class)** dans l'implémentation open source. Cette implémentation étant la seule dans le projet open source, elle sera utilisée. Dans le projet Insee, comme il existe une deuxième implémentation de l'interface UtilisateurService, celle dans le projet open source ne sera utilisée à cause de l'annotation.  
Cet exemple peut être une façon de gérer l'appel à des services interne comme Igesa (WS pour interagir avec le LDAP) : une implémentation par défaut qui est un mock, et une implémentation Insee qui fait les requêtes HTTP vers Igesa.

### Gestion de la sécurité applicative

- L'API est sécurisée en mode BASIC avec *Spring Security* dans le projet open source, avec plusieurs couples id-mdp renseignés en dur dans la classe `SecurityConfiguration`. Chaque identifiant peut avoir un ou plusieurs rôles également renseigné. Dans le projet Insee, Keycloak est utilisé en mode BEARER à la place du mode Basic. Les rôles sont alors récupéré dans le jeton.
- Certains endpoints sont protégés avec un rôle particulier pour y accéder avec l'annotation `@RolesAllowed("ADMIN")` dans le projet open source. Il faut alors saisir un couple id-mdp ayant ce rôle pour y avoir accès dans le projet open source. Ces endpoints sont protégés de la même manière dans le projet Insee avec les rôles présents dans le jeton Keycloak (voir classe `KeycloakConfiguration`).
- Il est possible de récupérer l'identifiant de l'utilisateur avec l'objet Java `Principal` (voir exemple dans le code). En mode BASIC, c'est le username utilisé pour l'authentification qui est récupéré. Avec Keycloak, il est possible de choisir l'information que l'on souhaite récupérer, par exemple l'idep de l'utilisateur avec la property `keycloak.principal-attribute=preferred_username`.
- Il est possible de tester si l'utilisateur à un rôle avec la méthode `httpServletRequest.isUserInRole("ROLE_A_TESTER")` qui retourne un booléen.

## Construction et exécution des livrables

Par défaut, le plugin *Spring Boot Maven Plugin* utilisé pour construire le livrable permet de créer un JAR exécutable qui contient les classes compilées et toutes les bibliothèques nécessaires. Ce JAR ne respecte pas l'organisation habituelle d'un JAR que l'on utilise en tant que bibliothèque dans un autre projet, l'utilisation de ces classes dans un autre projet ne fonctionnera pas, les classes ne seront pas trouvées par Maven à la compilation du projet utilisant cette bibliothèque. Le problème est qu'au lieu que la hiérarchie des dossiers contenant le code source soit à la racine du JAR, elle est dans un dossier */BOOT-INF/classes*. Ce JAR est généré en utilisant la configuration du plugin par défaut en lançant la commande ``mvn clean install`. Il faut ensuite lancer la commande JAVA `java -jar NOM-JAR.jar` pour lancer l'exécutable.

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

Si on souhaite produire un JAR utilisable en tant que bibliothèque, il faut configurer le plugin pour qu'il produise le JAR en question (qui sera très léger car ne contenant que le code source sans les dépendances nécessaires à son exécution) à la place du JAR exécutable. Il est en plus possible de produire le JAR exécutable qui sera suffixé **exec**.

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <executions>
                <execution>
                <id>repackage</id>
                <configuration>
                    <classifier>exec</classifier>
                </configuration>
                </execution>
            </executions>
            </plugin>
    </plugins>
</build>
```

Il est également possible de lancer le projet avec la commande `mvn spring-boot:run` lorsqu'on veut le lancer en local sans créer de JAR.