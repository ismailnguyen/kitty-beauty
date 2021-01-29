import java.util.Arrays;

public class ChatonsPhotos {
  public static void main(String[] args) {

	  // Tableau contenant les votes de chaque chaton
    int[] votes = new int[10];
	
	  // Nombre de votants total
    int nombreVotants = 0;

	  // Tableau avec les surnoms de chaque chaton
    String[] chatons = {
      "Rondoudou",
      "Ronflex",
      "Bulbizarre",
      "Salamèche",
      "Pikachu",
      "Ramoloss",
      "Dracaufeu"
    };

	  // Tableau avec les images de chaque chaton
    String[] images = {
      "rondoudou.jpg",
      "ronflex.jpg",
      "bulbizarre.jpg",
      "salamèche.jpg",
      "pikachu.jpeg",
      "ramoloss.jpg",
      "dracaufeu.jpg"
    };

	  // Boucle pour toujours proposer le menu à l'utilisateur
    while (true) {
      System.out.println("------------------------------");
      // affichage menu
      menu();

		  // On récupère le choix de l'utilisateur sur le menu
      int choixUtilisateur = Terminal.lireInt();

		  // Selon le choix de l'utilisateur on execute la fonction correspondant au numero de choix
      switch (choixUtilisateur) {
        default:
        case 1:
          AfficheChatons(images);
          break;

        case 2:
          AfficheChatonsParNote(votes, images);
          break;

        case 3:
          AfficherNoteChaton(votes, nombreVotants);
          break;

        case 4:
          AjouteNoteChaton(votes);
		    // A chaque ajout d'une note on incrémente le nombre de personnes qui ont voté
          nombreVotants++;
          break;

        case 5:
          AjouterChaton(chatons);
          break;

        case 6:
          RetirerChaton(chatons);
          break;

        case 7:
          QuitterProgramme();
          break;
      }
    }
  }

  private static void AfficheChatons(String[] images) {
	  // On boucle sur chaque images de chaton
    for (String image : images) {
		  // On utilise un try/catch pour eviter de tout casser si le fichier n'est pas trouvé
      try {
        // On affiche l'image du chaton
        AfficheImage.afficheImage(image);
		    // On attend que l'utilisateur appuie sur Entrée avant de fermer l'image
        Terminal.lireString();
        AfficheImage.fermeImage();
      } catch(ImageNonTrouvee exc) {
        System.out.println("Image non trouvée: " + images);
      }
    }
  }

  private static void AfficheChatonsParNote(int[] votes, String[] images) {
    String[] imagesChatonsParNote = images;

	  // On trie le tableau des images des chatons en fonctions des votes
    int n = imagesChatonsParNote.length;
    for (int i = 0; i < n-1; i++) {
      for (int j = 0; j < n-i-1; j++) {
        if (votes[j] > votes[j+1])
        {
            String temp = imagesChatonsParNote[j];
            imagesChatonsParNote[j] = imagesChatonsParNote[j+1];
            imagesChatonsParNote[j+1] = temp;
        }
      }
    }
    
  	// On affiche les images ordonneés
    AfficheChatons(imagesChatonsParNote);
  }

  private static void AfficherNoteChaton(int[] votes, int nombreVotants) {
    System.out.println("Saisir le n° du chaton pour voir sa note :");
    int numeroChaton = Terminal.lireInt();

    // On retrouve le vote pour le chaton grâce à son numéro et l'indice dans le tableau des votes
    int nombreVotes = votes[numeroChaton];

    // Si le chaton n'a pas encore de vote on affiche un message
    if (nombreVotes == 0) {
      System.out.println("Pas de vote pour ce chaton");
      return;
    }

    // On divise la note par le nombre de personne ayant voté puis on arrondi à la moyenne entier
    double moyenne = nombreVotes/nombreVotants;
    int moyenneArrondi = (int) Math.round(moyenne);

    System.out.println(moyenneArrondi);
  }

  private static void AjouteNoteChaton(int[] votes) {
    System.out.println("Pour quel chaton votez vous ? (saisir son n°) :");
    int numeroChaton = Terminal.lireInt();

    // On ajoute de 1 vote pour le chaton sur le tableau des votes
    votes[numeroChaton]++;
  }

  private static void RetirerChaton(String[] chatons) {
    System.out.println("N° du chaton à retirer :");
    int numeroChaton = Terminal.lireInt();

    // On vérifie qu'on ne supprime pas en dessous de 5 chatons
    if (chatons.length > 5) {
      // On enlève le chaton du tableau
      System.arraycopy(chatons, numeroChaton + 1, chatons, numeroChaton, chatons.length - 1 - numeroChaton);
    } else {
      Terminal.ecrireException(new Exception("Pas possible d'avoir moins de 5 chatons"));
    }
  }

  private static void AjouterChaton(String[] chatons) {
    System.out.println("Surnom du chaton à ajouter :");
    String surnomChaton = Terminal.lireString();

    // On ajoute un chaton seulement si le tableau n'a pas déjà 10 chatons
    if (chatons.length <= 10) {
        // Pour ajouter un chaton, on crée une nouvelle liste plus grande d'un élément 
         String[] nouvelleListeChatons = new String[chatons.length + 1]; 
         
         // On recopie tous les chatons du tableau "chatons" dans la nouvelle liste
         for (int i = 0; i < chatons.length; i++) {
            nouvelleListeChatons[i] = chatons[i];
         }
            
         // A La fin de la nouvelle liste on vient rajouter le nouveau chaton
         nouvelleListeChatons[nouvelleListeChatons.length-1] = surnomChaton;

         // On remplace l'ancienne liste de chatons par la nouvelle
         chatons = nouvelleListeChatons;
    } else {
      Terminal.ecrireException(new Exception("Pas possible d'ajouter plus de 10 chatons"));
    }
  }

  private static void QuitterProgramme() {
    System.exit(0);
  }

  // affichage menu
  public static void menu() {
    System.out.println("1: afficher les chatons dans l'ordre des numéros.");
    System.out.println("2: afficher les chatons par note.");
    System.out.println("3: afficher la note pour un chaton.");
    System.out.println("4: ajouter une note pour un chaton.");
    System.out.println("5: retirer un chaton de la liste des candidats.");
    System.out.println("6: ajouter un chaton à la liste des candidats.");
    System.out.println("7: quitter le programme.");
    System.out.print("Votre choix : ");
  }
}
