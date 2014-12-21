package donnee;

/**
 * GrapheMat is a class that we use  for creating graph with an adjacency matrix,
 *  and see if we have a path from the beginning of our game to the end
 * @author gadarezgui
 *
 */
public class GrapheMat {

  public int m[][];
  public int nb;
/**
 * print the graph and his matrix
 * @param g 
 * the graph
 */
  public static void imprimer (GrapheMat g) { 

      System.out.println ("number of vertices " + g.nb);
      for (int i = 0; i < g.nb ; ++i) {
          for (int j = 0; j < g.nb; ++j) 
              System.out.print (g.m[i][j] + " ");
         System.out.println ();
      } 
      System.out.println (); 
  }
/**
 * create a graph of n1 vertices where is nothing in
 * @param n1
 * the number of vertices
 */
  GrapheMat (int n1) {

      nb = n1;
      m = new int[n1][n1]; 
  }
 

/**
 * create a graph of n1 vertices
 * with random values in the adjacency matrix
 * @param n1
 * the number of vertices
 * @param p
 * the probability to have a path between vertices
 */
  public GrapheMat (int n1, int p) { 


      nb = n1;
      m = new int[n1][n1];                           
      for (int i = 0; i < nb; ++i)
        for (int j = 0; j < nb; ++j) {
          int a  = (int) (Math.random() * p);
          if (a == p - 1 ) m[i][j] = 1;
            else m[i][j] = 0;
          m[j][i] =m[i][j] ;
        }
  }
/**
 * multiply 2 matrices a*b
 * the result is c
 * c=a*b
 * @param c
 * matrix
 * @param a
 * matrix
 * 
 * @param b
 * matrix
 */
  static void multiplier (int c[][], int a[][], int b[][]) {
                               
      int n = c.length;
      for (int i = 0; i < n; ++i)
          for (int j = 0; j < n; ++j) {
              c[i][j] = 0;
              for (int k = 0; k < n; ++k)
                  c[i][j] = c[i][j] + a[i][k] * b[k][j];
          }
  }
/**
 * sum of 2 matrices a*b=c
 * @param c
 * matrix
 * @param a
 * matrix
 * @param b
 * matrix
 */
  static void additionner (int c[][], int a[][], int b[][]) {
                               
      int n = c.length;
      for (int i = 0; i < n; ++i)
          for (int j = 0; j < n; ++j) 
              c[i][j] = a[i][j] * b[i][j];
  }
     /**
      * look if there is a path between two vertices in the graph 
      * @param i
      * the index of the vertex of the beginning
      * @param j
      * the index of the vertex of the end
      * @param g
      * is the graph
      * @return
      * true if there is a path between i and j
      * false otherwise
      */
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

}