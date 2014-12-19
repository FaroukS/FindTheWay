package donnee;

// \esc{les graphes  repre'sente's par une matrice  

class GrapheMat {

  int m[][];
  int nb;

  static void imprimer (GrapheMat g) { 

      System.out.println ("nombre de sommets " + g.nb);
      for (int i = 0; i < g.nb ; ++i) {
          for (int j = 0; j < g.nb; ++j) 
              System.out.print (g.m[i][j] + " ");
         System.out.println ();
      } 
      System.out.println (); 
  }

  GrapheMat (int n1) {
  // Initialisation d'un graphe de n1 sommets sous forme de matrice vide

      nb = n1;
      m = new int[n1][n1]; 
  }
 
  GrapheMat (GrapheSucc h) {
  // Transformation en matrice d'adjacence
  //  d'un graphe de n1 sommets donne comme une matrice de successeurs

      nb = h.nb;
      m= new int[nb][nb]; 
      for (int i = 0; i < nb ; ++i)
         for (int j = 0; j < nb ; ++j) m[i][j] = 0; 
      for (int i = 0; i < nb ; ++i)
          for (int k = 0; h.succ[i][k] != GrapheSucc.Omega; ++k)
              m[i][h.succ[i][k]] = 1; 
  }

  GrapheMat (int n1, int p) { 
  // \esc{Initialisation d'un graphe de n1 sommets sous forme de matrice}
  // \esc{On tire au sort des nombres} 
  // \esc{entre 0 et 1 pour savoir s'il y a un arc entre i et j:  proba 1/p d'exister}

      nb = n1;
      m = new int[n1][n1];                           
      for (int i = 0; i < nb; ++i)
        for (int j = 0; j < nb; ++j) {
          int a  = (int) (Math.random() * p);
          if (a == p - 1 ) m[i][j] = 1;
            else m[i][j] = 0;
        }
  }

  static void multiplier (int c[][], int a[][], int b[][]) {
  // \esc{Produit de  matrices} 
                               
      int n = c.length;
      for (int i = 0; i < n; ++i)
          for (int j = 0; j < n; ++j) {
              c[i][j] = 0;
              for (int k = 0; k < n; ++k)
                  c[i][j] = c[i][j] + a[i][k] * b[k][j];
          }
  }

  static void additionner (int c[][], int a[][], int b[][]) {
  // \esc{Somme de  matrices} 
                               
      int n = c.length;
      for (int i = 0; i < n; ++i)
          for (int j = 0; j < n; ++j) 
              c[i][j] = a[i][j] * b[i][j];
  }
      
  static boolean existeChemin (int i, int j, GrapheMat g) {

      int n = g.nb;
      int m[][] = g.m;
      int u[][] = (int[][]) m.clone();
      int v[][] = new int[n][n];

      for (int k = 1; u[i][j] == 0 && k <= n; ++k) {
	  multiplier (v, u, m);
	  additionner (u, u, v);
      }
      return u[i][j] != 0;
  }

  static void phi (GrapheMat g, int a) { 
  // \esc{calcul de la Fermeture transitive , voir page 
  //   \pageref{prog:fermtrans} }

        for (int i = 0; i < g.nb; ++i)
        if (g.m[i][a] == 1)
            for (int j = 0; j < g.nb; ++j)
               if (g.m[a][j] == 1) 
                   g.m[i][j] = 1;
  }

  static public void fermetureTransitive (GrapheMat gr) {
   
      for (int k = 0; k < gr.nb; ++k) 
        phi(gr, k);
   }
}

class GrapheSucc{

  int succ[][];
  int nb;
  final static int Omega = -1;

  GrapheSucc (int n) {
      nb = n;
      succ = new int[n][n]; 
  } 

  GrapheSucc (GrapheMat g) {

  // Transformation en matrice de successeurs
  // d'un graphe donne comme une matrice d'adjacence

    nb = g.nb;
    int nbMaxSucc = 0;
    for (int i = 0; i < nb ; ++i) {
        nbMaxSucc = 0;
        for (int j = 0; j < nb  ; ++j) 
            if (g.m[i][j] != 0)
                nbMaxSucc = Math.max (nbMaxSucc, j);
    }
    succ = new int[nb][nbMaxSucc + 1]; 
    for (int i = 0; i < nb ; ++i) {
        int k = 0;
        for (int j = 0; j < nb  ; ++j) 
            if (g.m[i][j] != 0)
                succ[i][k++] = j;
        succ[i][k] = Omega;
    }          
  }

  static void imprimer (GrapheSucc g) {

     System.out.println ("nombre de sommets " + g.nb + " ");
     for (int i = 0; i < g.nb; ++i)
       { System.out.println ("sommet " + i + ", : successeurs: ");
         for (int j = 0; g.succ[i][j] != Omega; ++j)
         System.out.print (g.succ[i][j] +" ");
         System.out.println ();
       }
     System.out.println();
  }
}

class GrapheListe{

  Liste listeSucc[];
  int nb;

  GrapheListe (GrapheSucc g) {
  // \esc {Transformation en tableau de listes de successeurs}
  //  d'un graphe de n1 sommets donne comme une matrice de successeurs

    nb = g.nb;
    listeSucc = new Liste[nb];
    for (int i = 0; i < nb ; ++i) {
        listeSucc[i] = null;
        for (int k = 0; g.succ[i][k] != GrapheSucc.Omega; ++k)
            listeSucc[i] = Liste.ajouter(g.succ[i][k], listeSucc[i]);
    }
  }

  static void imprimer (GrapheListe g) {
  // Impression d'un graphe sous forme de  en tableau de listes de successeurs

      System.out.println ("nombre de sommets " + g.nb);
      for (int i = 0; i < g.nb; ++i) {
          System.out.println ("sommet " + i + ", : successeurs: ");
          for (Liste u = g.listeSucc[i]; u != null; u = u.suivant)
              System.out.print (" " + u.contenu);
         System.out.println ();
      } 
      System.out.println ();
      
  }

  GrapheListe (int n1) {
  // Creation d'un graphe sous forme d'un tableau de listes

      nb = n1;
      listeSucc = new Liste[n1];
  }

}




class Demo{

  public static void main (String[] args) { 

    // Demo sur les graphes- matrices
 
      GrapheMat g1 = new GrapheMat(10, 5); 
      System.out.println("Un graphe tire au hasard");
      System.out.println(); 
      GrapheMat.imprimer(g1);
      GrapheSucc g2 = new GrapheSucc(g1); 
      System.out.println("Transformation sous forme de matrice de successeurs:");
      System.out.println(); 
      GrapheSucc.imprimer(g2);
      System.out.println("Existence d'un chemin entre 1 et 5: " 
           + GrapheMat.existeChemin(1, 5, g1));
      System.out.println();
      GrapheMat.fermetureTransitive(g1); 
      System.out.println("La fermeture transitive du precedent:");
      System.out.println(); 
      GrapheMat.imprimer(g1); 
      g2 = new GrapheSucc(g1); 
      System.out.println("Transformation sous forme de matrice de successeurs:");
      System.out.println(); 
      GrapheSucc.imprimer(g2);

    // Demo sur les graphes- matrices de successeurs
  
      GrapheMat g3 = new GrapheMat(g2);
      System.out.println("Transformation sous forme de matrice de 0 et de 1 pour verifier:");
      System.out.println(); 
      GrapheMat.imprimer(g3);

    // Demo sur les graphes- listes  de successeurs
  
      GrapheListe g4 = new GrapheListe(g2);
      System.out.println("Transformation sous forme de listes ");
      System.out.println(); 
      GrapheListe.imprimer(g4);

    
    
    
  }
}

class ExceptionPile extends Exception {
  String nom;

  public ExceptionPile (String x) {
      nom = x;
  }
}

class Pile {

  final static int maxP = 10;

  int          hauteur ;
  int          contenu[];

  Pile ()  {
     hauteur = 0;
     contenu = new int[maxP];
  }

  static void faireVide (Pile p) {
    p.hauteur = 0;
  }

  static boolean estVide(Pile p) {
    return p.hauteur == 0;
  }

  static boolean estPleine(Pile p) {
    return p.hauteur == maxP;
  }

  static void ajouter (int x, Pile p) 
  {
    p.contenu[p.hauteur] = x;
    ++ p.hauteur;
  }

  static int  valeur(Pile p) 
  {
    return p.contenu [p.hauteur - 1];
   }

  static void supprimer(Pile p) 
  {
    -- p.hauteur;
  }
}

class Liste {

  int contenu;
  Liste suivant;

  Liste (int x, Liste a) {
      contenu = x;
      suivant = a;
  }

  static Liste ajouter (int x, Liste a) {

    return new Liste (x, a);
  }

  static boolean recherche(int x, Liste a) {
  // \esc{Recherche, voir page \pageref{prog:recherche-liste}} 

    while (a != null) {
        if (a.contenu == x)
            return true;
        a = a.suivant;
    }
    return false;
  }

  static boolean rechercheR (int x, Liste a) {
  // \esc{recherche re'cursive, voir page \pageref{prog:recherche-liste-rec}}

    if (a == null) 
        return false;
    else if (a.contenu == x) 
        return true;       
    else 
        return rechercheR (x, a.suivant);
  }

  static boolean rechercheRbis (int x, Liste a) {
  // \esc{recherche re'cursive, voir page \pageref{prog:recherche-liste-rec}}

    return (a != null)
        && ((a.contenu == x)
            || rechercheRbis (x, a.suivant));
  }

   static int longueur(Liste a) {
   // \esc{Longueur d'une liste, voir page \pageref{prog:longueur-liste-rec}}

    if (a == null)
        return 0;
    else
        return 1 + longueur (a.suivant);
  }

  static int longueurI(Liste a) {
   // \esc{Longueur d'une liste, voir page \pageref{prog:longueur-liste}}

    int   longueur = 0;
    while (a != null) {
        ++longueur;
        a = a.suivant;
    }
    return longueur;
  } 

  static Liste supprimer (int x, Liste a) {
  // \esc{Supprimer, voir page \pageref{prog:supprimer-liste-recursif}} 

    if (a != null)
        if (a.contenu == x)
            a = a.suivant;
        else 
         a.suivant  = supprimer (x, a.suivant);
    return a;      
  }

  static Liste supprimerI (int x, Liste a) { 

    Liste   b, c;

    if (a != null)
        if (a.contenu == x){
            c = a;
            a = a.suivant;
        } else {
            b = a ;
            while (b.suivant != null && b.suivant.contenu != x) 
                b = b.suivant;
            if (b.suivant != null) { 
                c = b.suivant;
                b.suivant = b.suivant.suivant;
            }
        } 
    return a;       
  }

  static Liste listePremier (int n) {
   //\esc{Liste des nombres premiers, voir page \pageref{prog:liste-premiers}}

    Liste  a = null;
    int    k;

    for (int i = n; i >= 2; --i) {
         a = ajouter (i, a);
    } 
    k = a.contenu; 
    for (Liste b = a; k * k <= n ; b = b.suivant){ 
        k = b.contenu;   
        for (int j = k; j <= n/k; ++j) 
            a = supprimer (j * k, a);
        }
    return(a); 
  }

  static void imprimer (Liste a) {

    for (Liste b = a; b != null; b = b.suivant) 
        System.out.print (b.contenu + " ");
    System.out.println ();
  }
}