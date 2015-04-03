/*
 * Classe automate qui va nous permettre de crée un automate fini
 * deterministe qui va par la suite servie à la reconnaissance de
 * gestes prédefinis
 * 
 */
import java.util.ArrayList;
import java.util.List;

public class Automate
{
  private String A;//alphabet
  private int e[];//Etats
  private int ei;//etat initial
  private int f[];//etats finaux 
  private List<Transition> delta;//ensemble des transitions
  
  private int etati;//etat dans lequel est l'automate
   
  public Automate(String A,int e[],int f[],List<Transition> delta)
  {
    this.A=A;
    this.e=e;
    this.ei=0;
    this.f=f;
    delta=new ArrayList(delta);
  }
  
  public String getAlphabet()
  {
    return A;
  }
  
  public int[] getEtat()
  {
    
    return e;
  }
   
  public int[] getEtatsFinaux()
  {
    return f;
  }
  
  public List<Transition> getTransition()
  {
    return delta;
  }
  
  /*@Override
  public String toString()
  {
    
  }
  */
  
  /*public boolean ifInAlphabet(String s)
  {
    for(int i=0;i<s.length();i++)
    {
      switch(s.charAt(i))
      {
        
        
        
        default:
            return false;
      }
    }
    
    return true;
  }*/
  
  /*void addTransistion(Transition T)
  {
    delta.add
  }*/
  public static void main(String []args)
  {
	 String alphab="ab";
	 int etat[] = {0,1,2,3};
	 int etatF[] = {3};
	 List<Transition> transition=new ArrayList<Transition>();
	 transition.add(new Transition(0,'a',0));
	 transition.add(new Transition(0,'b',0));
	 transition.add(new Transition(0,'b',1));
	 transition.add(new Transition(1,'a',2));
	 transition.add(new Transition(2,'b',3));
	 
	 Automate a = new Automate(alphab,etat,etatF,transition);
     
   System.out.println(a.getAlphabet());
   System.out.println(a.getEtat());
   System.out.println(a.getEtatsFinaux());
   System.out.println(a.getTransition());
   
     
  }
}
