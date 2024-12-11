
# Contact Manager

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![JavaFX](https://img.shields.io/badge/JavaFX-007396?style=for-the-badge&logo=java&logoColor=white)

Une application de gestion de contacts développée en Java avec JavaFX. Contact Manager permet de gérer vos contacts de manière intuitive, grâce à une interface graphique moderne et des fonctionnalités bien structurées.

## 🎯 Fonctionnalités

- **Affichage des Contacts** : Listez vos contacts dans un tableau (TableView) organisé par `Prénom`, `Nom`, `Numéro`, `Email` et `Catégorie`.
- **Ajout de Contacts** : Ajoutez un nouveau contact en remplissant un formulaire simple.
- **Mise à Jour de Contacts** : Modifiez les informations de vos contacts existants.
- **Suppression de Contacts** : Supprimez les contacts dont vous n’avez plus besoin.
- **Persistance des Données** : Les contacts sont sauvegardés et récupérés entre les sessions grâce à la sérialisation.

## 🏗️ Structure du Projet

```
Contact/
├── build/
├── dist/
├── nbproject/
│   ├── private/
│   └── project.properties
├── src/
│   └── com/
│       └── contactmanager/
│           ├── controller/
│           │   └── ContactController.java
│           ├── model/
│           │   ├── Contact.java
│           │   └── ContactManager.java
│           └── view/
│               ├── ContactManagerGUI.java
│               └── styles.css
├── test/
├── build.xml
└── manifest.mf
```

### 📂 Détails des Fichiers

1. **`Contact.java`** : Modèle de données représentant un contact.
2. **`ContactManager.java`** : Classe gérant la liste des contacts et assurant leur persistance via la sérialisation.
3. **`ContactController.java`** : Contrôleur agissant comme un intermédiaire entre le modèle et la vue.
4. **`ContactManagerGUI.java`** : Interface utilisateur graphique créée avec JavaFX.
5. **`styles.css`** : Feuille de style définissant l'apparence de l'application.

## 🚀 Configuration et Exécution

### Prérequis

- **Java JDK** installé
- **JavaFX** correctement configuré
- Un IDE compatible avec Java et JavaFX (ex. : NetBeans, IntelliJ IDEA)

### Étapes d'Installation

1. Clonez le dépôt :
   ```bash
   git clone https://github.com/Firinze/contact-manager.git
   ```
2. Importez le projet dans votre IDE préféré.
3. Configurez les bibliothèques JavaFX si nécessaire.
4. Exécutez le fichier `ContactManagerGUI.java`.

## ✨ Points Forts

- **Architecture MVC** : Pour une séparation claire des responsabilités.
- **Interface Graphique Moderne** : Une expérience utilisateur fluide et intuitive.
- **Persistance des Données** : Sauvegarde automatique entre les sessions.

## 🎨 Personnalisation

Vous pouvez ajuster le style visuel de l'application en modifiant le fichier `styles.css`.

## 📜 Licence

Ce projet est sous licence [MIT](https://opensource.org/licenses/MIT).

---

Développé avec 💻 par Firinze Gbènito Dossou.
