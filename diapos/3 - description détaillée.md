# Description détaillée

----

## Création de services

- service de création et de récupération d'entreprises (en base de données)
- service de récupératon d'utilisateurs (en mémoire dans l'application)

----

## Base de données

- l'endpoint `/entreprise` en GET et en POST est branché sur une base de données (H2 ou postgre en fonction du projet exécuté)
- le code Java n'est présent que dans le projet opensource
- un script d'initialisation de la base H2 en mémoire est présent dans le projet open source pour que l'application démarre avec la base H2 avec des données
- dans le projet Insee, les properties et la dépendance maven de *PostgreSQL* sont présents et surchargent la configuration du projet open source, pour pointer sur la base *PostgreSQL* de l'Insee au lieu de la base H2

----

## Redéfinition de classe pour les spécificités Insee

- l'endpoint `/utilisateur` en GET permet d'obtenir la liste de tous les utilisateurs
- le code Java de l'interface est défini dans le projet open source, ainsi qu'une implémentation avec des données en dur (UtilisateurServiceImpl)
- une autre implémentation est définie dans le projet Insee (UtilisateurServiceImplInsee) avec d'autres données pour montrer que cette dernière est bien utilisée dans le projet Insee comme implémentation
- afin que Spring sache qu'il doit utiliser cette implémentation dans le projet Insee, il faut ajouter l'annotation **@Primary** sur la classe de l'implémentation Insee
- une autre façon de faire est d'utiliser l'annotation **@ConditionalOnSingleCandidate(UtilisateurService.class)** dans l'implémentation open source. Cette implémentation étant la seule dans le projet open source, elle sera utilisée. Dans le projet Insee, comme il existe une deuxième implémentation de l'interface UtilisateurService, celle dans le projet open source ne sera pas utilisée à cause de l'annotation
- cet exemple peut être une façon de gérer l'appel à des services interne comme Igesa (WS pour interagir avec le LDAP) : une implémentation par défaut qui est un mock, et une implémentation Insee qui fait les requêtes HTTP vers Igesa

----

## Gestion de la sécurité applicative

- l'API est sécurisée en mode BASIC avec *Spring Security* dans le projet open source, avec plusieurs couples id-mdp renseignés en dur dans la classe `SecurityConfiguration`. Chaque identifiant peut avoir un ou plusieurs rôles également renseigné
- dans le projet Insee, Keycloak est utilisé en mode BEARER à la place du mode Basic. Les rôles sont alors récupérés dans le jeton
- certains endpoints sont protégés avec un rôle particulier pour y accéder avec l'annotation `@RolesAllowed("ADMIN")` dans le projet open source. Il faut alors saisir un couple id-mdp ayant ce rôle pour y avoir accès dans le projet open source. Ces endpoints sont protégés de la même manière dans le projet Insee avec les rôles présents dans le jeton Keycloak (voir classe `KeycloakConfiguration`)
- il est possible de récupérer l'identifiant de l'utilisateur avec l'objet Java `Principal` (voir exemple dans le code). En mode BASIC, c'est le username utilisé pour l'authentification qui est récupéré. Avec Keycloak, il est possible de choisir l'information que l'on souhaite récupérer, par exemple l'idep de l'utilisateur avec la property `keycloak.principal-attribute=preferred_username`
- il est possible de tester si l'utilisateur à un rôle avec la méthode `httpServletRequest.isUserInRole("ROLE_A_TESTER")` qui retourne un booléen

----

## Construction et exécution des livrables (1)

- par défaut, le plugin *Spring Boot Maven Plugin* utilisé pour construire le livrable permet de créer un JAR exécutable qui contient les classes compilées et toutes les bibliothèques nécessaires
- ce JAR ne respecte pas l'organisation habituelle d'un JAR que l'on utilise en tant que bibliothèque dans un autre projet, l'utilisation de ces classes dans un autre projet ne fonctionnera pas, les classes ne seront pas trouvées par Maven à la compilation du projet utilisant cette bibliothèque
- le problème est qu'au lieu que la hiérarchie des dossiers contenant le code source soit à la racine du JAR, elle est dans un dossier */BOOT-INF/classes*
- ce JAR est généré en utilisant la configuration du plugin par défaut en lançant la commande `mvn clean install`. Il faut ensuite lancer la commande JAVA `java -jar NOM-JAR.jar` pour lancer l'exécutable.

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

----

## Construction et exécution des livrables (2)

- si on souhaite produire un JAR utilisable en tant que bibliothèque, il faut configurer le plugin pour qu'il produise le JAR en question (qui sera très léger car ne contenant que le code source sans les dépendances nécessaires à son exécution) à la place du JAR exécutable
- il est en plus possible de produire le JAR exécutable qui sera suffixé **exec**

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
