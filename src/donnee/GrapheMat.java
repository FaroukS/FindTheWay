package donnee;

// \esc{les graphes  repre'sente's par une matrice  

public class GrapheMat {

  public int m[][];
  public int nb;

  public static void imprimer (GrapheMat g) { 

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
 


  public GrapheMat (int n1, int p) { 
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
      
  public static boolean existeChemin (int i, int j, GrapheMat g) {

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
  // calcul de la Fermeture transitive

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

