public class Transition
{
  private int previousState;
  private char transition;
  private int nextState;
  
  public Transition(int pStat,char trans,int nState)
  {
	previousState = pStat;
	transition = trans;
	nextState = nState;
  }
  
  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder(100);
    return sb.append("(").append(previousState).append(",").append(transition).append(",").append(nextState).append(")").toString();
  }
  
  @Override
  public boolean equals(Object obj) 
  {
     if (obj==this) { return true;}
     if (obj==null) { return false;}
     
     if (obj instanceof Transition) 
     {
       Transition t = (Transition) obj;
       
       if(t.previousState == this.previousState && t.transition == this.transition && t.nextState == this.nextState)
         return true;
     }
     
     return false;
  }
  
   public static void main(String []args)
   {
     Transition t = new Transition(0,'a',0);
     Transition s = new Transition(0,'b',0);
     
     
     System.out.println(t.equals(s));
   }
}
