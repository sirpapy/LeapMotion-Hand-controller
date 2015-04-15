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
  //alphabet
  private final String A;
  //Etats
  private final List<Integer> e;
  //etat initial
  private final int ei;
  //etats finaux
  private final List<Integer> f;
  //ensemble des transitions
  private final List<Transition> delta;
  //etat dans lequel est l'automate
  private int etati;
   
  public Automate(String A,List<Integer> e,List<Integer> f,List<Transition> delta)
  {
    this.A=A;
    this.e=new ArrayList<Integer>(e);
    this.f=new ArrayList<Integer>(f);
    this.delta=new ArrayList<Transition>(delta);
    this.ei=0;
  }
  
  public String getAlphabet() { return A;}
  
  public List<Integer> getEtat() { return e;}
   
  public List<Integer> getEtatsFinaux() { return f;}
  
  public List<Transition> getTransition(){ return delta;}
  
  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    return sb.append("alphabet : ").append(A).append("\n")
             .append("Etats : ").append(e).append("\n")
             .append("Etat initial : ").append(ei).append("\n")
             .append("Etat finaux : ").append(f).append("\n")
             .append("transitions : ").append(delta)
             .toString();
  }
  
  @Override
  public boolean equals(Object obj)
  {
     if (obj==this) { return true;}
     if (obj==null) { return false;}
     
     if (obj instanceof Automate) 
     {
       Automate t = (Automate) obj;
       
       if(A.equals(t.A) && e.equals(t.e) && f.equals(t.f) && delta.equals(t.delta))
         return true;
     }
     
     return false;
  }
  
  public static void main(String []args)
  {
	 String alphab="ab";
	 List<Integer> etat = new ArrayList<Integer>();
   etat.add(0);
   etat.add(1);
   etat.add(2);
   etat.add(3);
	 List<Integer> etatF = new ArrayList<Integer>();
   etatF.add(3);
	 List<Transition> transition=new ArrayList<Transition>();
	 transition.add(new Transition(0,'a',0));
	 transition.add(new Transition(0,'b',0));
	 transition.add(new Transition(0,'b',1));
	 transition.add(new Transition(1,'a',2));
	 transition.add(new Transition(2,'b',3));
	 
	 Automate a = new Automate(alphab,etat,etatF,transition);
	 
   System.out.println(a);
  }
}
