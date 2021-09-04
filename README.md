# Rendu du TP n°1 : Java RMI

Réalisé par Antoine Barbier & Antoine Brahimi en M1 AIGLE.

Tous les chemins utilisés que ça soit pour le `gestionnaire de sécurité` ou pour le `codebase` sont en relatif. Ceci permet de simplifier la compilation et l'execution.

## Compilation & Execution 

Pour pouvoir lancer les différents exercices, se positionner dans le dossier contenant les 4 fichiers : `run.sh`, `serveur.sh`, `client.sh`, `rm.sh`.

- `run.sh` : permet de compiler tous les fichiers .java 
- `serveur.sh` : permet d'executer le serveur et de tuer le processus Rmiregistry à l'arrêt du serveur
- `client.sh` : permet d'executer le client
- `rm.sh` : permet de supprimer les fichiers .class

> L'execution d'un de ces fichiers bash se fait de la manière suivante : `./bashFile.sh`
> Le serveur doit être tué via un CTRL+C (le processus Rmiregistry sera automatiquement tué ensuite)

Veillez à respecter l'ordre d'execution des fichiers bash suivant :

1. `./run.sh`
2. `./serveur.sh`
3. `./client.sh`




